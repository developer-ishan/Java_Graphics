package com.ishan.rain.graphics;

public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height){
        this.height = height;
        this.width = width;
        pixels = new int[width * height];
    }
    
    public void render(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++){
                pixels[x + y*width] = 0xff00ff;
            }
        }
    }
}