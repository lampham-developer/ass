package com.example.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.Controller.GameSurface;

public class Vitamin extends GameObject {
    private long lastTime = -1;
    private GameSurface gameSurface;

    public Vitamin(float VELOCITY, int movingVectorX, int movingVectorY, int hp, Bitmap image, int width, int height, int x, int y,GameSurface gameSurface) {
        super(VELOCITY, movingVectorX, movingVectorY, hp, image, width, height, x, y);
        this.gameSurface = gameSurface;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Bitmap bitmap = this.createSubImageAt(1, 0);
        canvas.drawBitmap(bitmap, x,y,null);
    }

    @Override
    public void update() {
        super.update();

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

        // Khi vtamin của game chạm ra khỏi màn hình thì đợi
        if(this.y > this.gameSurface.getHeight() + height)  {
            this.y= - height - 10;
            this.movingVectorY = 0 ;
        }
    }
}
