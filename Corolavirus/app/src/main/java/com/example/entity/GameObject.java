package com.example.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameObject {

    protected float VELOCITY;

    protected int movingVectorX;
    protected int movingVectorY;
    protected int hp;

    protected Bitmap image;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    private Bitmap[] listBitmap;

    public GameObject(float VELOCITY, int movingVectorX, int movingVectorY, int hp, Bitmap image, int width, int height, int x, int y) {
        this.VELOCITY = VELOCITY;
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
        this.hp = hp;
        this.image = image;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    protected Bitmap createSubImageAt(int row, int col)  {
        // createBitmap(bitmap, x, y, width, height).

        Bitmap subImage = Bitmap.createBitmap(image, col*width, 0 ,width,height);
        return subImage;
    }

    public void draw(Canvas canvas){

    }

    public void update(){

    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
