package levelGenerators.GroupG;
import levelGenerators.MarioLevelGenerator;
import java.util.Random;

import static engine.helper.RunUtils.generateLevel;

public class LevelGenerator {

    // create a function that randomly select a generator below

    public String ensembleGenerators(int maxGenerators){

        StringBuilder generators = new StringBuilder();
        String [] levelsLines = new String[16];

        for (int j = 0; j<levelsLines.length; j++){
            levelsLines[j] = "";

        }

        for(int i = 0; i<maxGenerators; i++){
            MarioLevelGenerator generator = new levelGenerators.GroupG.LevelGenerator().selectRandomGenerator();
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

        return randomLevel;
    }

    public MarioLevelGenerator selectRandomGenerator(){
        Random randSelect = new Random();
        int i = randSelect.nextInt(3);
        MarioLevelGenerator generator = null;

        switch(i){
            case 0: generator = new levelGenerators.random.LevelGenerator(); break;
            case 1: generator = new levelGenerators.notch.LevelGenerator(); break;
            case 2: generator = new levelGenerators.benWeber.LevelGenerator(); break;
        }

        return generator;
    }

}
