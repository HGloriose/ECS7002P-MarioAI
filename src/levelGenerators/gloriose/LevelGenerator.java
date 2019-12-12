package levelGenerators.gloriose;

import levelGenerators.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.helper.MarioTimer;
import levelGenerators.ParamMarioLevelGenerator;

import java.util.ArrayList;
import java.util.Random;

public class LevelGenerator implements ParamMarioLevelGenerator {
    private float [] GROUND_Y_LOCATION = new float []{13 , 14, 20};
    private float [] GROUND_PROB = new float []{0.4f,0.4f};
    private float [] OBSTACLES_LOCATION = new float []{10 , 11};
    private float [] OBSTACLES_PROB = new float []{0.1f, 0.2f};
    private float [] COLLECTIBLE_LOCATION = new float []{3, 4,5,6};
    private float [] COLLECTIBLE_PROB = new float []{0.05f, 0.1f};
    private float [] ENEMY_PROB = new float []{0.1f, 0.9f};
    private float [] FLOOR_PADDING = new float []{3, 6};

    private ArrayList<float []> searchSpace = new ArrayList<float []>();

    @Override
    public ArrayList < float []> getParameterSearchSpace () {
        searchSpace.add( GROUND_Y_LOCATION); // GROUND_Y_LOCATION
        searchSpace.add( GROUND_PROB); // GROUND_PROB
        searchSpace.add( OBSTACLES_LOCATION); // OBSTACLES_LOCATION
        searchSpace.add( OBSTACLES_PROB); // OBSTACLES_PROB
        searchSpace.add( COLLECTIBLE_LOCATION); // COLLECTIBLE_LOCATION
        searchSpace.add( COLLECTIBLE_PROB); // COLLECTIBLE_PROB
        searchSpace.add( ENEMY_PROB); // ENEMY_PROB
        searchSpace.add( FLOOR_PADDING); // FLOOR_PADDING

        return searchSpace;
    }


    @Override
    public void setParameters (int [] paramIndex ) {

        for(int i=0; i< paramIndex.length; i++){

            int paramlength = (searchSpace.get(i)).length;
            Random rndIndex = new Random();

            if(paramlength == 1){
                paramIndex[i] = 0;
            } else {
                paramIndex[i] = rndIndex.nextInt(paramlength-1);
            }
        }
    }


    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        Random random = new Random();
        model.clearMap();

//        ArrayList < float []> tunedSearchSpace = getParameterSearchSpace();
        getParameterSearchSpace();

        int [] paramIndx = new int[8];
        setParameters(paramIndx);
        float ground_y_location = searchSpace.get(0)[paramIndx[0]];
        float ground_prob = searchSpace.get(1)[paramIndx[1]];
        float obastacles_location = searchSpace.get(2)[paramIndx[2]];
        float obastacles_prob = searchSpace.get(3)[paramIndx[3]];
        float collectible_location = searchSpace.get(4)[paramIndx[4]];
        float collectible_prob = searchSpace.get(5)[paramIndx[5]];
        float enemy_prob = searchSpace.get(6)[paramIndx[6]];
        int floor_padding = (int)searchSpace.get(7)[paramIndx[7]];


        for (int x = 0; x < model.getWidth(); x++) {
            for (int y = 0; y < model.getHeight(); y++) {
                model.setBlock(x, y, MarioLevelModel.EMPTY);
                setParameters(paramIndx);
                if (y > ground_y_location) {
                    if (random.nextDouble() < ground_prob) {
                        model.setBlock(x, y, MarioLevelModel.GROUND);
                    }
                } else if (y > obastacles_location) {
                    if (random.nextDouble() < obastacles_prob) {
                        model.setBlock(x, y, MarioLevelModel.PYRAMID_BLOCK);
                    } else if (random.nextDouble() < enemy_prob) {
                        model.setBlock(x, y,
                                MarioLevelModel.getEnemyCharacters()[random.nextInt(MarioLevelModel.getEnemyCharacters().length)]);
                    }
                } else if (y > collectible_location) {
                    if (random.nextDouble() < collectible_prob) {
                        model.setBlock(x, y,
                                MarioLevelModel.getCollectablesTiles()[random.nextInt(MarioLevelModel.getCollectablesTiles().length)]);
                    }
                }



            }
        }
        model.setRectangle(0, 14, floor_padding, 2, MarioLevelModel.GROUND);
        model.setRectangle(model.getWidth() - 1 - floor_padding, 14, floor_padding, 2, MarioLevelModel.GROUND);
        model.setBlock(floor_padding / 2, 13, MarioLevelModel.MARIO_START);
        model.setBlock(model.getWidth() - 1 - floor_padding / 2, 13, MarioLevelModel.MARIO_EXIT);
        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "glorioseGenerator";
    }

}

