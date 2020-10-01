package com.example.coronavirusclicker;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ResizedBitmap {
    private Bitmap bm;
    private int newHeight;
    private int newWidth;

    public ResizedBitmap(Bitmap bitmap, int newHeight, int newWidth) {
        bm = bitmap;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    public  Bitmap getResizedBitmap() {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }
}
