import java.util.ArrayList;
import java.util.Random;

public class testingFile {

    public static void main(String[] args) {

        ArrayList<float []> searchSpace =new ArrayList<float []>();

        searchSpace.add( new float []{13 , 14}); // GROUND_Y_LOCATION
        searchSpace.add( new float []{0.4f,0.3f}); // GROUND_PROB
        searchSpace.add( new float []{10 , 12}); // OBSTACLES_LOCATION
        searchSpace.add( new float []{0.1f, 0.2f}); // OBSTACLES_PROB
        searchSpace.add( new float []{3, 4}); // COLLECTIBLE_LOCATION
        searchSpace.add( new float []{0.05f, 0.1f}); // COLLECTIBLE_PROB
        searchSpace.add( new float []{0.1f, 0.9f}); // ENEMY_PROB
        searchSpace.add( new float []{3, 6}); // FLOOR_PADDING

        int [] paramIndex = new int[8];
        for(int i=0; i< paramIndex.length; i++){
            int paramlength = searchSpace.get(i).length;
            if(paramlength == 1){
                paramIndex[i] = 0;
            } else{
                Random random = new Random();
                paramIndex[i] = random.nextInt(paramlength-1);
            }
        }

        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        System.out.println(cars.get(0));
    }
}
