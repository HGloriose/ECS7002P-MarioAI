package levelGenerators.random;

import levelGenerators.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.helper.MarioTimer;

import java.util.ArrayList;
import java.util.Random;

public class LevelGenerator implements MarioLevelGenerator {
    private int GROUND_Y_LOCATION = 13;
    private float GROUND_PROB = 0.8f;
    private int OBSTACLES_LOCATION = 10;
    private float OBSTACLES_PROB = 0.1f;
    private int COLLECTIBLE_LOCATION = 3;
    private float COLLECTIBLE_PROB = 0.05f;
    private float ENEMY_PROB = 0.1f;
    private int FLOOR_PADDING = 10;

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        Random random = new Random();
        model.clearMap();
        for (int x = 0; x < model.getWidth(); x++) {
            for (int y = 0; y < model.getHeight(); y++) {
                model.setBlock(x, y, MarioLevelModel.EMPTY);
                if (y > GROUND_Y_LOCATION) {
                    if (random.nextDouble() < GROUND_PROB) {
                        model.setBlock(x, y, MarioLevelModel.GROUND);
                    }
                } else if (y > OBSTACLES_LOCATION) {
                    if (random.nextDouble() < OBSTACLES_PROB) {
                        model.setBlock(x, y, MarioLevelModel.PYRAMID_BLOCK);
                    } else if (random.nextDouble() < ENEMY_PROB) {
                        model.setBlock(x, y,
                                MarioLevelModel.getEnemyCharacters()[random.nextInt(MarioLevelModel.getEnemyCharacters().length)]);
                    }
                } else if (y > COLLECTIBLE_LOCATION) {
                    if (random.nextDouble() < COLLECTIBLE_PROB) {
                        model.setBlock(x, y,
                                MarioLevelModel.getCollectablesTiles()[random.nextInt(MarioLevelModel.getCollectablesTiles().length)]);
                    }
                }
            }
        }
        model.setRectangle(0, 14, FLOOR_PADDING, 2, MarioLevelModel.GROUND);
        model.setRectangle(model.getWidth() - 1 - FLOOR_PADDING, 14, FLOOR_PADDING, 2, MarioLevelModel.GROUND);
        model.setBlock(FLOOR_PADDING / 2, 13, MarioLevelModel.MARIO_START);
        model.setBlock(model.getWidth() - 1 - FLOOR_PADDING / 2, 13, MarioLevelModel.MARIO_EXIT);
        return model.getMap();
    }

    @Override
    public String getGeneratorName() {
        return "RandomLevelGenerator";
    }

}
