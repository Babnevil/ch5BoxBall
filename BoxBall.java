import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
/**
 * A sphere that will represent a ball randomly bouncing about a drawn box.
 * It expects input for the number of ball objects to be created, and the size of the box.
 * 
 * @author Matthew Schilling
 * @version 10.23.2017
 */
public class BoxBall
{
    // instance variables, walls will be assigned values from the constructor paramaters based on 
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private Canvas canvas;
    private int ySpeed;
    private int xSpeed;
    private int leftWall;
    private int topWall;
    private int rightWall;
    private int bottomWall;
    
    Random roll = new Random();

    /**
     * Constructor for objects of class BoxBall
     * Random values will assign most attributes
     */
    public BoxBall(int leftBarrier, int topBarrier, int rightBarrier, int bottomBarrier, Canvas workingCanvas)
    {
        // initialise instance variables
        leftWall = leftBarrier;
        topWall = topBarrier;
        rightWall = rightBarrier;
        bottomWall = bottomBarrier;
        
        // determine valid area, then get a random position on that axis.
        xPosition = roll.nextInt((rightBarrier - leftBarrier));
        yPosition = roll.nextInt((bottomBarrier - topBarrier));
        
        // get a random color for the ball, padded to avoid being too light.
        Color randColor = new Color((roll.nextInt(245) + 11), (roll.nextInt(245) + 11), (roll.nextInt(245) + 11));
        color = randColor;
        diameter = 12;     
        ySpeed = roll.nextInt(7) + 1;
        xSpeed = roll.nextInt(7) + 1;
        canvas = workingCanvas;
        
    }
    
    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     * If a wall is encountered, the ball will "bounce", and reverse direction
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit any of the walls, if true, move ball back inside the box, and inverse the speed to reverse direction
        if (yPosition >= (bottomWall - diameter)) {
            yPosition = (int)(bottomWall - diameter);
            ySpeed = -ySpeed; 
        }
        if (xPosition >= (rightWall - diameter)){
            xPosition = (int)(rightWall - diameter);
            xSpeed = -xSpeed;
        }
        if (xPosition <= leftWall){
            xPosition = (int)(leftWall + diameter);
            xSpeed = -xSpeed;
        }
        if (yPosition <= topWall){
            yPosition =(int)(topWall + diameter);
            ySpeed = - ySpeed;
        }
        
        // draw again at new position
        draw();
    }
}
