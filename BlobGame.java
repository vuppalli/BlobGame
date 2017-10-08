import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

public class BlobGame extends JPanel implements KeyListener
{
    Blob playerBlob;
    ArrayList<Blob> evilBlobs;
    int delay;
    boolean gameOver;

    public BlobGame(){
        gameOver  = false;
        delay = 100;
        evilBlobs = new ArrayList<Blob>();

        for (int i=0; i<20; i++){
            createEvilBlob();
        }
        //create playerBlob
        playerBlob = new Blob(10, Color.BLACK, 400, 400, 0, 0);
    }

    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        char c;
        c = e.getKeyChar();
        if (c == 'w'){
            playerBlob.moveUp();      
        }
        if (c == 's'){
            playerBlob.moveDown();
        }
        if (c == 'd'){
            playerBlob.moveRight();
        }
        if (c == 'a'){
            playerBlob.moveLeft(); 
        }
    }

    public void checkForCollision(){
        if (playerBlob.getX()>=-100 && playerBlob.getX()<=900 && playerBlob.getY()>=-100 && playerBlob.getY()<=900){  
            gameOver = false;
        }else{
            gameOver = true;
        }
        for (int i=0; i<evilBlobs.size(); i++){
            if (!(evilBlobs.get(i).getX()>=-100 && evilBlobs.get(i).getX()<=900 && evilBlobs.get(i).getY()>=-100 && evilBlobs.get(i).getY()<=900)){  
                evilBlobs.remove(i); 
                createEvilBlob();
            }
        }
        for (int x=0; x<evilBlobs.size(); x++){
            int distanceBetween = distance(playerBlob.getX(), playerBlob.getY(), evilBlobs.get(x).getX(), evilBlobs.get(x).getY());
            if (distanceBetween <= playerBlob.getRadius()+evilBlobs.get(x).getRadius()){  
                if (playerBlob.getRadius() > evilBlobs.get(x).getRadius()){
                    evilBlobs.remove(x);
                    createEvilBlob();
                    playerBlob.eat();   
                    //change color of blob when eats
                    Color [] differentColors = {Color.GREEN, Color.RED, Color.MAGENTA, Color.BLUE, Color.BLACK, Color.CYAN, Color.GRAY, Color.ORANGE, Color.PINK, Color.YELLOW};  
                    Color newBlobColor = differentColors[(int)(Math.random()*10)]; 
                    newBlobColor = playerBlob.theColor; 
                    gameOver(); 
                }else{
                    gameOver = true;
                }
            }
        }
    }

    public void gameOver(){
        if (playerBlob.getRadius() > 90){
            gameOver = true;
            System.out.print("Congratulations! You WON!"); 
        }
    }

    public void createEvilBlob(){
        int evilBlobRadius = (int)(Math.random()*50);
        int evilBlobX = (int)(Math.random()*900);
        int evilBlobY;
        int evilBlobDeltaX;
        int evilBlobDeltaY;
        if (evilBlobX <= 70){
            evilBlobY = (int)(Math.random()*700);
            evilBlobDeltaX = (int)(Math.random()*10+5); 
            evilBlobDeltaY = (int)(Math.random()*7);
        }else if(evilBlobX >=800 && evilBlobX <= 900){
            evilBlobY = (int)(Math.random()*700);
            evilBlobDeltaX = (int)(Math.random()*-15-20);
            evilBlobDeltaY = (int)(Math.random()*7);
        }else{
            int evilBlobY1 = (int)(Math.random()*10);
            int evilBlobY2 = (int)(Math.random()*200 + 800); 
            int [] yCoord = {evilBlobY1, evilBlobY2};
            int randInt = (int)(Math.random()*2); 
            evilBlobY = yCoord[randInt]; 
            if (randInt == 0){
                evilBlobDeltaX = (int)(Math.random()*7);
                evilBlobDeltaY = (int)(Math.random()*15+5);
            }else{
                evilBlobDeltaX = (int)(Math.random()*7);
                evilBlobDeltaY = (int)(Math.random()*-15-20);
            }
            Blob evilBlob = new Blob(evilBlobRadius, Color.RED, evilBlobX, evilBlobY, evilBlobDeltaX, evilBlobDeltaY); 
            evilBlobs.add(evilBlob);
        }
    }

    public static int distance(int x1, int y1, int x2, int y2){
        int distance;
        distance = Math.abs((int)Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2))); 
        return distance; 
    }    

    ////////////////////////////////////////////////////////
    //Do not edit code below

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 800, 800);
        playerBlob.draw(g);
        for(int i = 0; i < evilBlobs.size(); i++){
            evilBlobs.get(i).draw(g);
        }
    }

    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        char c;
        c = e.getKeyChar();
    }

    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        char c;
        c = e.getKeyChar();     
    }

    public void run(){

        while(!gameOver){

            try {
                Thread.sleep(delay);
            }catch(InterruptedException e) {}

            playerBlob.move();
            for(int i = 0; i < evilBlobs.size(); i++){
                evilBlobs.get(i).move();    
            }

            //check for collision
            checkForCollision();

            paintImmediately(0, 0, 1000, 1000);
        }
        System.out.println("GAME OVER");
        System.out.println("Final Blob Size - " + playerBlob.getRadius());
    }

    public static void main(String [] arg){
        JFrame runner = new JFrame("Game Title");
        runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runner.setLocationRelativeTo(null);
        runner.setSize(800, 800);
        runner.setLayout(null);
        runner.setLocation(0, 0);

        BlobGame theGame = new BlobGame();
        theGame.setSize(800, 800);
        theGame.setLocation(0, 0);
        runner.getContentPane().add(theGame);

        runner.setVisible(true);

        runner.addKeyListener(theGame);
        theGame.run();
    }
}
