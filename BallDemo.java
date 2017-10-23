import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class.
 * 
 * @author Matthew Schilling
 * @version 2017.10.23
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    /**
     * Simulate a number of balls bouncing about a drawn box
     * Requires input for the number of balls, and how far inward 
     * the boundries of the box will be. This method beings an
     * infinite loop.
     */
    public void boxBounce(int ballCount, int padding) {
        //start with local variables and an array list for the circles
        ArrayList<BoxBall> balls = new ArrayList<>();
        int run = ballCount;
        int leftWall = 0 + padding;
        int topWall = 0 + padding;
        int rightWall = 600 - padding;
        int bottomWall = 500 - padding;
        Random roll = new Random();
        myCanvas.setVisible(true);
        
        // create a ball and add it to the ArrayList
        while(run >0) {
            BoxBall ball = new BoxBall(leftWall, topWall, rightWall, bottomWall, myCanvas);
            balls.add(ball);
            run -= 1;
        }
        // loop through all the balls in the collection
        for (BoxBall ball : balls) {
            ball.draw();          
        }
        while (1>0) {
        for (BoxBall ball :balls) {
            myCanvas.wait(5);       //used a shorter wait time to smooth animation of larger numbers
            ball.move();
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.drawLine(leftWall, bottomWall, rightWall, bottomWall);
            myCanvas.drawLine(leftWall, bottomWall, leftWall, topWall);
            myCanvas.drawLine(rightWall, bottomWall, rightWall, topWall);
            myCanvas.drawLine(rightWall, topWall, leftWall, topWall);
        }
    }
    }
}


