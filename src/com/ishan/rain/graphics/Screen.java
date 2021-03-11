package com.ishan.rain.graphics;

public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height){
        this.height = height;
        this.width = width;
        pixels = new int[width * height];
    }
}
