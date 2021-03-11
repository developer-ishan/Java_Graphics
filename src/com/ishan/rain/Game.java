package com.ishan.rain;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
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

    public  Game(){
        Dimension size = new Dimension(width*scale, height* scale);
        setPreferredSize(size);

        frame = new JFrame();
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