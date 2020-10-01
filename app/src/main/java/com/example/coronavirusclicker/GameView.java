package com.example.coronavirusclicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    public static int maxX = 40;
    public static int maxY = 56;
    public static float unitW = 0;
    public static float unitH = 0;

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Target target;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Covid> covids = new ArrayList<>();
    private final int COVID_INTERVAL = 50;
    private int currentTime = 0;
    private FragmentManager fragmentManager;


    public GameView(Context context, FragmentManager fragmentManager) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        this.fragmentManager = fragmentManager;

        gameThread = new Thread(this);
        gameThread.start();

    }





    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewCovid();
            control();
        }
        restart();
    }

    private void restart() {
        new RestartDialog()
                .show(fragmentManager, "Restart");
    }

    private void update() {
        if(!firstTime) {
            target.update();
            for (Covid covid : covids) {
                covid.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if(firstTime){
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX;
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                target = new MaskTarget(getContext());
            }

            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            target.draw(paint, canvas);

            for(Covid covid : covids) {
                covid.draw(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){
        for (Covid asteroid : covids) {
            if(asteroid.isCollision(target.x, target.y, target.size)){
                gameRunning = false;
            }
        }
    }

    private void checkIfNewCovid(){
        if(currentTime >= COVID_INTERVAL){
            Covid asteroid = new Covid(getContext());
            covids.add(asteroid);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }
}
