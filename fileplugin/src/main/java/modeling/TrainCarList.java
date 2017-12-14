package modeling;

/**
 * Created by tyler on 10/17/2017.
 * Each player will have a train car list object which will hold the number of cars they have currently available
 */

public class TrainCarList {
    private int numOfCars;

    public TrainCarList( ) {
        this.numOfCars = 30;
    }

    public int getNumOfCars() {
        return numOfCars;
    }
    /**Decrement number of cars held by 1*/
    public void decrementCars(){
        numOfCars--;
    }
    /**Decrement number of cars held by some integer
     * @param num Integer*/
    public void decrementCars(int num){
        numOfCars -= num;
//        if(numOfCars<=2){
//
//        }
    }
}
