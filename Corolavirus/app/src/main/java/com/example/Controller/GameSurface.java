package com.example.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.corolavirus.R;
import com.example.entity.Virus;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    public int holderHeight = this.getHeight() / 2;
    private Virus virus;
    private GameThread gameThread;

    public GameSurface(Context context) {
        super(context);
        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);


        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }

    public void update(){
            virus.update();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        gameThread = new GameThread(this, holder);

        virus.setImageResource(R.drawable.virus);
        gameThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);


                // Luồng cha, cần phải tạm dừng chờ GameThread kết thúc.
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }
    }
}
