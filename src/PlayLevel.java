import agents.MarioAgent;
import engine.core.*;
import engine.helper.MarioStats;
import levelGenerators.MarioLevelGenerator;
import levelGenerators.GroupG.LevelGenerator;

import java.util.ArrayList;
import java.util.List;
import java.lang.*;

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


        //Gap Value - TODO - consecutive gaps '-' should be counted as one
        String [] ListLevels = level.split("\n");
        double gapValue = (double) ListLevels[15].chars().filter(num -> num == '-').count();
        double gapFitness = (gapValue - GAP_IDEAL)*GAP_WEIGHT;

        double totalLevelFeatFitness =  gapFitness;

        return totalLevelFeatFitness;
    }

    private static double simulationAnalysis(MarioAgent [] agents, MarioGame game, String level, int repsPerLevel){


        for (int i = 0; i< agents.length; i++) {

            MarioStats average = new MarioStats();

            for( int j = 0; j< repsPerLevel; j++) {
                MarioResult result = game.runGame(agents[i], level, 20, 0, false);
                System.out.println((i+1) + "/" + ";" + (j+1) + "/" + repsPerLevel + ": "
                        + result.getGameStatus().toString());
                MarioStats stats = resultToStats(result);
                average = average.merge(stats);

            }
        }

        return (double)0;

    }

    public static void main(String[] args) {

        // Run settings:
        boolean visuals = true;  // Set to false if no visuals required for this run.
        boolean generateDifferentLevels = true;  // If true, each play will be a different generated level.
        String levelFile = null; // "levels/original/lvl-11.txt";  // null;
        //String levelFile = "levels/original/lvl-11.txt";  // null;
        //MarioLevelGenerator generator = new levelGenerators.notch.LevelGenerator();  // null;
        MarioLevelGenerator generator;

        StringBuilder generators = new StringBuilder();
        String [] levelsLines = new String[16];

        for (int j = 0; j<levelsLines.length; j++){
            levelsLines[j] = "";

        }

        int maxGenerators = 10;

        for(int i = 0; i<maxGenerators; i++){
            generator = new levelGenerators.GroupG.LevelGenerator().selectRandomGenerator();
            String genLevel = generateLevel(generator);
            generators.append(genLevel);
            String [] listTemp = genLevel.split("\n");
            for (int j = 0; j<levelsLines.length; j++){
                int startSub = (int)(150/(maxGenerators)*(i));
                int endSub = (int)(150/(maxGenerators)*(i+1));
                levelsLines[j] += listTemp[j].substring(startSub, endSub);
            }
        }

        String randomLevel = new String();

        for (int j = 0; j<levelsLines.length; j++){
            randomLevel += levelsLines[j] + "\n";

        }

        System.out.println("randomLevel: " + "\n" + randomLevel);

        // Note: either levelFile or generator must be non-null. If neither is null, levelFile takes priority.
        if (levelFile == null && generator == null) {
            return;
        }

        // Create a MarioGame instance and game-playing AI
        MarioGame game = new MarioGame();
        MarioAgent agent = new agents.robinBaumgarten.Agent();

        // Grab a level from file, found in directory "levels/" or pass null to generate a level automatically.
//        String level = getLevel(levelFile, generator);
        double featureFitness = featureAnalysis(randomLevel);

//        System.out.println("level: " + level);

        // Display the entire level.
        game.buildWorld(randomLevel, 1); //scale is just the size of the screen

        // Repeat the game several times, maybe.
        int playAgain = 0;
        while (playAgain == 0) {  // 0 - play again! 1 - end execution.

            // Level - FeatureAnalysis Fitness
            System.out.println("Level - FeatureAnalysis Fitness: " + featureFitness);

            // Play the level, either as a human ...
            //MarioResult result = game.playGame(level, 200, 0);

            // ... Or with an AI agent
            MarioResult result = game.runGame(agent, randomLevel, 1000, 0, visuals);

            // Print the results of the game
            System.out.println(result.getGameStatus().toString());
            System.out.println(resultToStats(result).toString());


            if (generateDifferentLevels) {
                randomLevel = generateLevel(generator);
            }

            // Check if we should play again. TODO: Be able to run the game consecutively for a number fo times. Do that in RunLevels.
            playAgain = (game.playAgain == 0 && visuals) ? 0 : 1;  // If visuals are not on, only play 1 time
        }
    }
}
