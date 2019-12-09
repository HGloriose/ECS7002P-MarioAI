//package levelGenerators.CellularGenerator;
//
//import engine.core.MarioLevelModel;
//import engine.helper.RunUtils;
//import engine.helper.MarioTimer;
//import levelGenerators.MarioLevelGenerator;
//import java.util.Random;
//
//import static engine.helper.RunUtils.getLevel;
//
//public class LevelGenerator implements MarioLevelGenerator{
//
//    private double RANDOM_NUMBER = 0;
//    private double SET_PROB = 0.5;
//    private int FLOOR_PADDING = 10;
//
//    public LevelGenerator(){
//
//        Random rand = new Random();
//        double RANDOM_NUMBER = rand.nextDouble();
//    }
//
//    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
//        //implement the idea of cellular automata
//        /*
//
//        get the list of tiles to be used in the level
//        */
//
//        char[] tiles = MarioLevelModel.getAllTiles();
//
//        MarioLevelGenerator generator = new levelGenerators.random.LevelGenerator();
//        String randomLevel = RunUtils.generateLevel(generator);
//
//        for (int x = 0; x < model.getWidth(); x++) {
//            Random rand = new Random();
//            for (int y = 0; y < model.getHeight(); y++) {
//                for (int t = 0; t< tiles.length; t++) {
//                    if (rand.nextDouble() < RANDOM_NUMBER) {
//                        char tile = tiles[rand.nextInt(tiles.length)];
//                        model.setBlock(x, y, tile);
//                    }
//                }
//            }
//        }
//
//        Random rand = new Random();
//        FLOOR_PADDING = rand.nextInt(15);
//        model.setRectangle(0, 14, FLOOR_PADDING, 2, MarioLevelModel.GROUND);
//        model.setRectangle(model.getWidth() - 1 - FLOOR_PADDING, 14, FLOOR_PADDING, 2, MarioLevelModel.GROUND);
//        return model.getMap();
//
//    }
//
//    public String cellularFunction(String level){
//        String [] levelList = level.split("\n");
//        for (int x = 0; x < levelList.length; x++) {
//            for (int y = 0; y < levelList[x].length(); y++) {
//
//            }
//        }
//    }
//
//    public String getGeneratorName(){ return  "CellularGenerator";}
//
//}