package com.ishan.rain.graphics;

import java.util.Random;

public class Screen {

    private int width, height;
    public int[] pixels;
    private int tileSize = 128;
    private int patternSize = 2;
    public int[][] tilePattern = new int[patternSize][patternSize];
    private Random random = new Random();

    public Screen(int width, int height){
        this.height = height;
        this.width = width;
        pixels = new int[width * height];
        /**
         * Check pattern
         * B W
         * W B
         * */
        tilePattern[0][0] = 0x000000;
        tilePattern[0][1] = 0xffffff;
        tilePattern[1][0] = 0xffffff;
        tilePattern[1][1] = 0x000000;
    }
    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    public void render(int horizontalOffset, int verticalOffset){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
                pixels[j + i*width] = tilePattern[((i+verticalOffset+height)/tileSize)%patternSize][((j+horizontalOffset+width)/tileSize)%patternSize];
            }
        }
    }
}