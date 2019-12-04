import agents.MarioAgent;
import engine.core.*;
import levelGenerators.MarioLevelGenerator;

import java.util.Arrays;

import static engine.helper.RunUtils.*;

@SuppressWarnings("ConstantConditions")
public class PlayLevel {

    private static int GAP_IDEAL = 16;
    private static double GAP_WEIGHT = 0.4;

    private static double featureAnalysis(String level){
        /*
        1. Identify a set of features
         - Check the number of gaps on the floor
         - Proportion of ground tiles tpo floating tiles
         - Density of enemies of different types

        2. Calculate value of that feature given string level (count) - theta
        - Consider building the level in the MarioGame.buildWorld object

        3. For each features define an ideal theta* and (theta - theta*)

        4. For each feature define a weight giving the importance of the feature.

        5. Calculate fitness of a level - sum(delta_theta[i]*w[i])

        in main method
        - generateDifferentLevel=true
        - print put final fitness values

        */

//        //Testing
//        System.out.println(level);
//        String [] listLevels = level.split( "\n");
//        System.out.println("list level: " + Arrays.toString(listLevels));
//        System.out.println("list level 15: " + listLevels[15]);
//        boolean gapsPresent = listLevels[15].contains("-");
//        System.out.println("gapsPresent: " + gapsPresent);
//        long numGaps = listLevels[15].chars().filter(num -> num == '-').count();
//        System.out.println("numGaps: " +  numGaps); // This will count the number of single gaps.


        //Gap Value - TODO - consecutive gaps '-' shoudl be counted as one
        String [] ListLevels = level.split("\n");
        double gapValue = (double) ListLevels[15].chars().filter(num -> num == '-').count();
        double gapFitness = (gapValue - GAP_IDEAL)*GAP_WEIGHT;

        double totalLevelFeatFitness =  gapFitness;

        return totalLevelFeatFitness;
    }

    public static void main(String[] args) {

        // Run settings:
        boolean visuals = true;  // Set to false if no visuals required for this run.
        boolean generateDifferentLevels = true;  // If true, each play will be a different generated level.
        String levelFile = null; // "levels/original/lvl-11.txt";  // null;
        //String levelFile = "levels/original/lvl-11.txt";  // null;
        MarioLevelGenerator generator = new levelGenerators.random.LevelGenerator();  // null;

        // Note: either levelFile or generator must be non-null. If neither is null, levelFile takes priority.
        if (levelFile == null && generator == null) {
            return;
        }

        // Create a MarioGame instance and game-playing AI
        MarioGame game = new MarioGame();
        MarioAgent agent = new agents.robinBaumgarten.Agent();

        // Grab a level from file, found in directory "levels/" or pass null to generate a level automatically.
        String level = getLevel(levelFile, generator);
        double featureFitness = featureAnalysis(level);

        // Display the entire level.
        game.buildWorld(level, 1); //scale is just the size of the screen

        // Repeat the game several times, maybe.
        int playAgain = 0;
        while (playAgain == 0) {  // 0 - play again! 1 - end execution.

            // Level - FeatureAnalysis Fitness
            System.out.println("Level - FeatureAnalysis Fitness: " + featureFitness);

            // Play the level, either as a human ...
            //MarioResult result = game.playGame(level, 200, 0);

            // ... Or with an AI agent
            MarioResult result = game.runGame(agent, level, 20, 0, visuals);

            // Print the results of the game
            System.out.println(result.getGameStatus().toString());
            System.out.println(resultToStats(result).toString());


            if (generateDifferentLevels) {
                level = generateLevel(generator);
            }

            // Check if we should play again. TODO: Be able to run the game consecutively for a number fo times. Do that in RunLevels.
            playAgain = (game.playAgain == 0 && visuals) ? 0 : 1;  // If visuals are not on, only play 1 time
        }
    }
}
