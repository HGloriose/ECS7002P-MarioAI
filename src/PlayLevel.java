import agents.MarioAgent;
import engine.core.*;
import levelGenerators.MarioLevelGenerator;

import java.util.Arrays;

import static engine.helper.RunUtils.*;

@SuppressWarnings("ConstantConditions")
public class PlayLevel {


    public double featureAnalysis(String level){
        /*
        Check the number of gaps on the floor
        check the proportion of ground tiles to floating tiles
        Density of enemies pf different types
        */

        /*
        Gap - check line 15 and 16 for X'-*'X or anything else
        1. find the x coordiante of x.
        2. Then search line 15.
         */

        String [] ListLevels = level.split("\n");



        return (double)0;
    }

    public static void main(String[] args) {
        // Run settings:
        boolean visuals = true;  // Set to false if no visuals required for this run.
        boolean generateDifferentLevels = true;  // If true, each play will be a different generated level.
        String levelFile = null; // "levels/original/lvl-11.txt";  // null;
//        String levelFile = "levels/original/lvl-11.txt";  // null;
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
        System.out.println(level);
        String [] listLevels = level.split( "\n");
        System.out.println("list level: " + Arrays.toString(listLevels));
        System.out.println("list level 15: " + listLevels[15]);

        //find Location of F
        int FxLocation = 0;
        int FyLocation = 0;
        for (int x = 149; x <=0; x--){
            for(int y = 0; y< 15; y++){
                if (listLevels[x,y] == 'F'){
                    FxLocation = x;
                    FyLocation = y;
                }
            }
        }

        for (int x = 0; x <=FxLocation; x++){
            for(int y = 0; y<=FyLocation; y++){
                // if '-' follows X
            }
        }


        // Display the entire level.
        game.buildWorld(level, 1);

        // Repeat the game several times, maybe.
        int playAgain = 0;
        while (playAgain == 0) {  // 0 - play again! 1 - end execution.

            // Play the level, either as a human ...
//            MarioResult result = game.playGame(level, 200, 0);

            // ... Or with an AI agent
            MarioResult result = game.runGame(agent, level, 20, 0, visuals);

            // Print the results of the game
            System.out.println(result.getGameStatus().toString());
//            System.out.println(resultToStats(result).toString());

            if (generateDifferentLevels) {
                level = generateLevel(generator);
            }

            // Check if we should play again.
            playAgain = (game.playAgain == 0 && visuals) ? 0 : 1;  // If visuals are not on, only play 1 time
        }
    }
}
