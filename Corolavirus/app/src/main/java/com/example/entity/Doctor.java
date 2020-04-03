package com.example.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.Controller.GameSurface;

public class Doctor extends GameObject {
    private GameSurface gameSurface;

    public Doctor(float VELOCITY, int movingVectorX, int movingVectorY, int hp, Bitmap image, int width, int height, int x, int y,GameSurface gameSurface) {
        super(VELOCITY, movingVectorX, movingVectorY, hp, image, width, height, x, y);
        this.gameSurface = gameSurface;
    }
}
