package com.example.Controller;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
        private boolean running;
        private GameSurface gameSurface;
        private SurfaceHolder surfaceHolder;

        public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder)  {
            this.gameSurface= gameSurface;
            this.surfaceHolder= surfaceHolder;
        }

    @Override
    public void run() {
        super.run();
        while (running){
            this.gameSurface.update();
            try {
                this.sleep((int)1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
