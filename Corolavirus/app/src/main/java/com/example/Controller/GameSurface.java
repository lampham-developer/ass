package com.example.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.Define.GameObjectDetail;
import com.example.corolavirus.R;
import com.example.entity.Bullet;
import com.example.entity.Doctor;
import com.example.entity.Vaccine;
import com.example.entity.Virus;
import com.example.entity.Vitamin;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Virus virus;
    private Doctor doctor;
    private Vaccine vaccine;
    private Vitamin vitamin;
    private Bullet bullet;
    private GameThread gameThread;

    public int SCREEN_WIDTH = this.getWidth();
    public int SCREEN_HEIGHT = this.getHeight();
    public int holderHeight = SCREEN_HEIGHT / 2;

    public GameSurface(Context context) {
        super(context);
        // Đảm bảo Game Surface có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);


        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }

    private Bitmap decodeBitmap(int image_id){
        return BitmapFactory.decodeResource(this.getResources(), image_id);
    }

    private void initGameObject(){
        Bitmap virus_bitmap = decodeBitmap(R.drawable.virus2);
        Bitmap doctor_bitmap = decodeBitmap(R.drawable.doctor);
        Bitmap vaccine_bitmap = decodeBitmap(R.drawable.doctor);
        Bitmap vitamin_bitmap = decodeBitmap(R.drawable.vitaminc);
        Bitmap bullet_bitmap = decodeBitmap(R.drawable.bullet);


        virus = new Virus(GameObjectDetail.VIRUS_VELOCITY, GameObjectDetail.VIRUS_VECTOR_X, GameObjectDetail.VIRUS_VECTOR_Y, 100, virus_bitmap, virus_bitmap.getWidth()/2, virus_bitmap.getHeight(), 0, 0, -1, this);
        doctor = new Doctor(0, 0, 0, 0, doctor_bitmap, doctor_bitmap.getWidth(), virus_bitmap.getHeight(), SCREEN_WIDTH/2, SCREEN_HEIGHT/2,  this);
        vaccine = new Vaccine(0, 0, 0, 0, vaccine_bitmap, vaccine_bitmap.getWidth(), vaccine_bitmap.getHeight(), SCREEN_WIDTH/3, SCREEN_HEIGHT/2,  this);
        vitamin = new Vitamin(0, 0, 0, 0, vitamin_bitmap, vitamin_bitmap.getWidth(), vitamin_bitmap.getHeight(), SCREEN_WIDTH, SCREEN_HEIGHT/2,  this);
        bullet = new Bullet(0, 0, 0, 10, bullet_bitmap, bullet_bitmap.getWidth(), bullet_bitmap.getHeight(), SCREEN_WIDTH*2/3, SCREEN_HEIGHT/2,  this);

    }

    public void update(){
        virus.update();

        vaccine.update();
        bullet.update();
        vitamin.update();
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        virus.draw(canvas);

        vaccine.draw(canvas);
        bullet.draw(canvas);
        vitamin.draw(canvas);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        SCREEN_WIDTH = this.getWidth();
        SCREEN_HEIGHT = this.getHeight();
        holderHeight = SCREEN_HEIGHT / 2;

        initGameObject();

        gameThread = new GameThread(this, holder);
        gameThread.setRunning(true);
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
