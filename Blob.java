import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class Blob
{
    int radius;
    Color theColor;
    int x, y;
    int deltaX, deltaY;
    
    public Blob(int aRadius, Color aColor, int aX, int aY, int aDeltaX, int aDeltaY){
        radius = aRadius;
        theColor = aColor;
        x = aX;
        y = aY;
        deltaX = aDeltaX;
        deltaY = aDeltaY;
    }
    
    //returns the x coordinate of the blob
    public int getX(){
        return x;
    }
    
    //returns the y coordinate of the blob
    public int getY(){
        return y;
    }
    
    //returns the radius of the blob
    public int getRadius(){
        return radius;
    }
    
    //increases the size(radius) of the blob
    public void eat(){
        radius += 10;
    }
    
    //moves the blob on its current path
    public void move(){
       x += deltaX;
       y += deltaY;
    }
    
    //has the blob increase its direction to the right
    public void moveRight(){
        //deltaX = 10;
        x += 10;
    }
    
    //has the blob increase its direction to the left
    public void moveLeft(){
        //deltaX = -50;
        x -= 10;
    }
    
    //has the blob increase its direction up
    public void moveUp(){
        //deltaY = -50;
        y -= 10;
    }
    
    //has the blob increase its direction down
    public void moveDown(){
        //deltaY = 50;
        y += 10;
    }
    
    public void draw(Graphics g){
        g.setColor(theColor);
        g.fillOval(x-radius, y-radius, 2*radius+1, 2*radius+1);
    }
}
