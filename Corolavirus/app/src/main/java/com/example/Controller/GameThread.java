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
            Canvas canvas= null;
            try {
                // Lấy ra đối tượng Canvas và khóa nó lại.
                canvas = this.surfaceHolder.lockCanvas();


                // Đồng bộ hóa.
                synchronized (canvas)  {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            }catch(Exception e)  {
                // Không làm gì
            } finally {
                if(canvas!= null)  {
                    // Mở khóa cho Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            try {
                this.sleep((int)100);
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
