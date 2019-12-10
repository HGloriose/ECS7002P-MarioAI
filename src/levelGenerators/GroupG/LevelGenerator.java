package levelGenerators.GroupG;
import levelGenerators.MarioLevelGenerator;
import java.util.Random;

public class LevelGenerator {

    //Select a generator randomly (out of the 3 below)

    // list the generated.
    private String RANDOM_GENERATOR = "random";
    private String NOTCH_GENERATOR = "notch";
    private String BENWEBER_GENERATOR = "benWeber";
    private String [] LIST_GENERATORS =  {RANDOM_GENERATOR, NOTCH_GENERATOR, BENWEBER_GENERATOR};


    // create a function that randomly select the generator from the list above

    public MarioLevelGenerator selectRandomGenerator(){
        Random randSelect = new Random();
        int i = randSelect.nextInt(3);
        System.out.println("random i: "+ i);
        MarioLevelGenerator generator = null;

        //since there is only 3 you can do a a for loop and check or case
        switch(i){
            case 0: generator = new levelGenerators.random.LevelGenerator(); break;
            case 1: generator = new levelGenerators.notch.LevelGenerator(); break;
            case 2: generator = new levelGenerators.benWeber.LevelGenerator(); break;
        }

        return generator;
    }

}
