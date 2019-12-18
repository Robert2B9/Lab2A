import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class RenaultMagnum extends Car {

    enum FlatbedState{
        UP, DOWN
    }
    private FlatbedState flatbedState = FlatbedState.UP;
    private Deque<Car> carsList = new ArrayDeque<>();
    private final int MAX_CARS = 7;
    private final int MAX_SIZE = 8;
    public static final int MAX_DISTANCE = 5;

    public void lowerFlatbed (){
        if (getCurrentSpeed() != 0){
            flatbedState = FlatbedState.DOWN;
        }
    }
    public void liftFlatbed (){
        flatbedState = FlatbedState.UP;
    }

    public RenaultMagnum (){
        super (2, 600, Color.pink, "RenaultMagnum", 20);
    }
    protected double speedFactor (){
        return getEnginePower() * 0.003;
    }

    @Override
    public void startEngine() {
        if (flatbedState == FlatbedState.UP)
            super.startEngine();
    }

    private double getDistance (Car car){
        return Math.sqrt(Math.pow(getxPos() - car.getxPos(), 2) + Math.pow(getyPos() - car.getyPos(), 2));
    }

    public void addCar (Car car) {
        if (carsList.size() < MAX_CARS && car.getSize() < MAX_SIZE && car.getClass() != this.getClass() &&
                flatbedState == FlatbedState.DOWN && getDistance(car) <= MAX_DISTANCE)
            carsList.push(car);
    }

    public Car removeCar (){
        if (!carsList.isEmpty() && flatbedState == FlatbedState.DOWN){
            carsList.peekFirst().setxPos(carsList.peekFirst().getxPos() + MAX_DISTANCE);
            return carsList.pop();

        }
        else return null;
    }

    /**
     * Syncs the position of a car with the Renault Magnum
     */
    @Override
    public void move(){
        super.move();
        for(Car car: carsList) {
            car.setxPos(this.getxPos());
            car.setyPos(this.getyPos());
        }
    }

}
