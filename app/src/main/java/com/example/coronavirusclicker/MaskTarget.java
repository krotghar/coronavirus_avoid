package com.example.coronavirusclicker;

import android.content.Context;

import java.util.Random;

public class MaskTarget extends Target {

    public MaskTarget(Context context) {
        bitmapId = R.drawable.man;
        size = 5;
        x = 7;
        y=GameView.maxY - size - 1;
        speed = 0.2f;

        init(context);
    }

    @Override
    public void update() {
        if(Activity2.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(Activity2.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
    }
}
