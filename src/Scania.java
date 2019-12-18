import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A type of car named Scania
 */
public class Scania extends Car {
    /**
     * The angle of the cargo bed
     */
    private double bedAngle = 0;
    /**
     * creates a Scania
     */
    public Scania (double xPos, double yPos) {
        super (2, 400,Color.blue, "Scania",15);
        setxPos(xPos);
        setyPos(yPos);
        try{this.icon = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg"));
        }catch (IOException ex)
        {
            ex.printStackTrace();
            icon = null;
        }
    }


    /**
     * Starts the engine if the cargo bed's angle is 0;
     */
    @Override
    public void startEngine(){
         if (bedAngle == 0)
         {
             super.startEngine();
         }
    }

    /**
     * The speedFactor of the Scania
     * @return returns the Speed factor
     */
    protected double speedFactor (){
        return getEnginePower() * 0.003;
    }

    /**
     * Changes the angle of the cargo bed to the requested angle
     * @param requestedAngle The requested angle
     */
    public void changeAngle (double requestedAngle) {
        if (getCurrentSpeed() == 0) {
            if (requestedAngle > bedAngle){
                while (true) {
                    bedAngle += 0.1;
                    bedAngle = Math.min(bedAngle, 70);
                    if (bedAngle >= requestedAngle){
                        break;
                    }
            }
        }
            else if (requestedAngle < bedAngle) {
                while (true) {
                    bedAngle -= 0.1;
                    bedAngle = Math.max(bedAngle, 0);
                    if (bedAngle <= requestedAngle) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Gets the cargo bed's angle
     * @return returns the value of the cargo bed's angle
     */
    public double getBedAngle (){
        return bedAngle;
    }
}
