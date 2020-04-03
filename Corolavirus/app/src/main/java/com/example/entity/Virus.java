package com.example.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;


import com.example.Controller.GameSurface;

public class Virus extends GameObject {

    private long lastTime;
    private GameSurface gameSurface;
    private Bitmap[] listBitmap;
    private int curentBitmap = 1;


    public Virus(float VELOCITY, int movingVectorX, int movingVectorY, int hp, Bitmap image, int width, int height, int x, int y, long lastTime, GameSurface gameSurface) {
        super(VELOCITY, movingVectorX, movingVectorY, hp, image, width, height, x, y);
        this.lastTime = lastTime;
        this.gameSurface = gameSurface;

        listBitmap = new Bitmap[2];
        listBitmap[0] = this.createSubImageAt(1, 0);
        listBitmap[1] = this.createSubImageAt(1, 1);
    }

    public void update(){
        if(curentBitmap == 0) curentBitmap = 1;
        else curentBitmap = 0;

        long now = System.nanoTime();
        // Chưa vẽ lần nào.
        if(lastTime==-1) {
            lastTime= now;
        }

        // Đổi nano giây ra mili giây (1 nanosecond = 1000000 millisecond).
        int deltaTime = (int) ((now - lastTime)/ 1000000 );


        // Quãng đường mà nhân vật đi được (fixel).
        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);


        // Tính toán vị trí mới của nhân vật.
        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);

        // Khi nhân vật của game chạm vào cạnh của màn hình thì đổi hướng.

        if(this.x < 0 )  {
            this.x = 0;
            this.movingVectorX = - this.movingVectorX;
        } else if(this.x > this.gameSurface.getWidth() - width)  {
            this.x= this.gameSurface.getWidth()-width;
            this.movingVectorX = - this.movingVectorX;
        }

        if(this.y < 0 )  {
            this.y = 0;
            this.movingVectorY = - this.movingVectorY;
        } else if(this.y > this.gameSurface.getHeight()/2)  {
            this.y= this.gameSurface.getHeight()/2;
            this.movingVectorY = - this.movingVectorY ;
        }

    }


    public void draw(Canvas canvas){

        Bitmap bitmap = listBitmap[curentBitmap];
        canvas.drawBitmap(bitmap , x , y , null);
        lastTime = System.nanoTime();
    }

    public int getMovingVectorX() {
        return movingVectorX;
    }

    public void setMovingVectorX(int movingVectorX) {
        this.movingVectorX = movingVectorX;
    }

    public int getMovingVectorY() {
        return movingVectorY;
    }

    public void setMovingVectorY(int movingVectorY) {
        this.movingVectorY = movingVectorY;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getVELOCITY() {
        return VELOCITY;
    }

    public void setVELOCITY(float VELOCITY) {
        this.VELOCITY = VELOCITY;
    }
}
