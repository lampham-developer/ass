package com.example.entity;

import android.content.Context;
import android.graphics.Canvas;


import com.example.Controller.GameSurface;

public class Virus extends GameObject {

    public static final float VELOCITY = 0.1f;

    private int movingVectorX;
    private int movingVectorY;
    private long lastTime = -1;
    private GameSurface gameSurface;


    public Virus(Context context, int movingVectorX, int movingVectorY, long lastTime, GameSurface gameSurface) {
        super(context);
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
        this.lastTime = lastTime;
        this.gameSurface = gameSurface;
    }

    public void update(){
        // Thời điểm hiện tại theo nano giây.
        long now = System.nanoTime();
        // Đổi nano giây ra mili giây (1 nanosecond = 1000000 millisecond).
        int deltaTime = (int) (1000/60);


        // Quãng đường mà nhân vật đi được (fixel).
        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);


        // Tính toán vị trí mới của nhân vật.
        this.setX(this.getX() +  (int)(distance* movingVectorX / movingVectorLength));
        this.setY(this.getY()+  (int)(distance* movingVectorY / movingVectorLength));

        // Khi nhân vật của game chạm vào cạnh của màn hình thì đổi hướng.

        if(this.getX() < 0 )  {
            this.setX(0);
            this.movingVectorX = - this.movingVectorX;
        } else if(this.getX() > this.gameSurface.getWidth() - this.getWidth())  {
            this.setX(this.gameSurface.getWidth()-this.getWidth());
            this.movingVectorX = - this.movingVectorX;
        }

        if(this.getY() < 0 )  {
            this.setY(0);
            this.movingVectorY = - this.movingVectorY;
        } else if(this.getY() > this.gameSurface.getHeight()- this.gameSurface.holderHeight)  {
            this.setY(this.gameSurface.getHeight()- this.gameSurface.holderHeight);
            this.movingVectorY = - this.movingVectorY ;
        }


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
}
