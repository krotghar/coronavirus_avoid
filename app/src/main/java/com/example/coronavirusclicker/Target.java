package com.example.coronavirusclicker;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Random;

import static android.content.ContentValues.TAG;

public class Target {
    protected float x;
    protected float y;
    protected float size;
    protected float speed;
    protected int bitmapId;
    protected Bitmap bitmap;

    void init(Context context) {
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * GameView.unitW), (int)(size * GameView.unitH), false);
        cBitmap.recycle();
    }

    public void update() {
    }

    public void draw(Paint paint, Canvas canvas) {
        canvas.drawBitmap(bitmap, x*GameView.unitW, y*GameView.unitH , paint);
    }


}
