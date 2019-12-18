import java.awt.*;
import java.awt.image.BufferedImage;

/**
* Car class is used as the base for other cars
 */
public abstract class Car implements Movable {
    private int size;
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private double xPos;
    private double yPos;
    private DIRECTION dir;
    enum DIRECTION {
        NORTH, SOUTH, WEST, EAST
    }
    BufferedImage icon;

    /**
     * Constructor for Car
     * @param nrDoors Determines how many doors the Car has
     * @param enginePower Determines the engine power of the Car
     * @param color Determines the color of the Car
     * @param modelName Determines what name the Car has
     */
    Car (int nrDoors, double enginePower, Color color, String modelName, int size){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        xPos = 0;
        yPos = 0;
        dir = DIRECTION.EAST;
        this.size = size;
        stopEngine();
    }

    /**
     *Gets the current direction of the Car
     * @return returns the current direction
     */
    public DIRECTION getDir (){
        return dir;
    }
    /**
     *Gets the number of doors on the Car
     * @return returns the number of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }
    /**
     *Gets the Cars engine power
     * @return returns the engine power
     */
    public double getEnginePower(){
        return enginePower;
    }
    /**
     *Gets the current speed of the Car
     * @return returns the current speed
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    /**
     *Gets the color of the Car
     * @return returns the color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Sets the color of the Car
     * @param clr sets the new color of the Car
     */
    public void setColor(Color clr){
        color = clr;
    }

    public void setxPos(double xPos){
        this.xPos = xPos;
    }

    public void setyPos(double yPos){
        this.yPos = yPos;
    }

    /**
     * Gets the position of the X-axis
     * @return return the x-position
     */
    public double getxPos () {
        return xPos;
}
    /**
     * Gets the position of the y-axis
     * @return return the y-position
     */
    public double getyPos () {
        return yPos;
    }

    /**
     * Gets the car size of the car
     * @return returns the car size
     */
    public int getSize (){
        return size;
    }
    /**
     * Starts the engine of the Car
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }
    /**
     * Stops the engine of the Car
     */
    public void stopEngine(){
        currentSpeed = 0;
    }
    /**
     * Sets the speedFactor used in modifying the current speed
     */
    protected abstract double speedFactor();
    /**
     * increases the current speed based on speedFactor and amount
     * @param amount amount is multiplied with speedFactor and added to currentSpeed
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }
    /**
     * decreases the current speed based on speedFactor and amount
     * @param amount amount is multiplied with speedFactor and removed from currentSpeed
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
    /**
     * Moves the Car in a certain direction based on the current direction
     */
    public void move() {
        switch (dir){
            case NORTH:
                yPos += currentSpeed;
                break;
            case SOUTH:
                yPos -= currentSpeed;
                break;
            case EAST:
                xPos += currentSpeed;
                break;
            case WEST:
                xPos -= currentSpeed;
                break;
        }

    }
    /**
     * Changes the Car's direction to the left based on the current direction
     */
    public void turnLeft() {
        switch (dir){
            case NORTH:
                dir = DIRECTION.WEST;
                break;
            case SOUTH:
                dir = DIRECTION.EAST;
            case WEST:
                dir = DIRECTION.SOUTH;
                break;
            case EAST:
                dir = DIRECTION.NORTH;
                break;
        }
    }
    /**
     * Changes the Car's direction to the right based on the current direction
     */
    public void turnRight() {
        switch (dir){
            case NORTH:
                dir = DIRECTION.EAST;
                break;
            case SOUTH:
                dir = DIRECTION.WEST;
            case WEST:
                dir = DIRECTION.NORTH;
                break;
            case EAST:
                dir = DIRECTION.SOUTH;
                break;
        }
    }

    public void gas(double amount){
        amount = Math.max(0, Math.min(1, amount));
        incrementSpeed(amount);
    }

    public void brake(double amount){
        amount = Math.max(0, Math.min(1, amount));
        decrementSpeed(amount);
    }

    public void turnAround(){
        switch (dir){
            case NORTH:
                dir = DIRECTION.SOUTH;
                break;
            case SOUTH:
                dir = DIRECTION.NORTH;
            case WEST:
                dir = DIRECTION.EAST;
                break;
            case EAST:
                dir = DIRECTION.WEST;
                break;
        }
    }
    public BufferedImage getIcon() {
        return icon;
    }

    public String getModelName() {
        return modelName;
    }
}
