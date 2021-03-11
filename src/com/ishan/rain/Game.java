package com.ishan.rain;

import com.ishan.rain.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serial;

//blank input area which can be manipulated
public class Game extends Canvas implements Runnable{
    @Serial
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    private boolean running = false;

    private Screen screen;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//this is the view of our game
    //write data on every pixel of this image
    //get the wr9iatable rastre of the image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public  Game(){
        Dimension size = new Dimension(width*scale, height* scale);
        setPreferredSize(size);

        frame = new JFrame();
        screen = new Screen(width, height);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //game loop
        while (running){
            update();//handle logic //a speed cap
            render();//handle display no spped cap
        }
    }

    private void update() {

    }

    private void render() {
        //creating buffer strategy
        //buffer is a temporary storate space
        //calcuate date but no use immidaetily
        //render the image and ready to put the image on screen but store the image on buffer
        //as prevous frame is still going on
        //calculate the image pixel array and then popup the whole image together
        //not display pixel by pixel we ned to see whole image together
        //no live rendering
        //basically create whole frasme and popup on the screen

        BufferStrategy bs = getBufferStrategy(); //get the buffer strategy of the canvas
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        //apply data to the buffers
        Graphics g = bs.getDrawGraphics();//craetes a linkk between data an actual bs

        //making our graphics
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());

        g.dispose(); //releases all the system resources by removing the grasphics after rendering the frame
        //buffer swapping
        bs.show(); //make the next available buffer visible
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
