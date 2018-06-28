package com.example.nishan.reelnepal.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.nishan.reelnepal.R;

public class CustomView extends View {

    private Bitmap mImage;

    public CustomView(Context context) {
        super(context);

      //  init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //init(attrs);
    }



    private void init(@Nullable AttributeSet set){

      // mImage =  BitmapFactory.decodeResource(getResources(), R.id.movie_image);
//       mImage = getResizedBitmap (mImage, getWidth(),getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawBitmap(mImage, 0, 0, null);

       getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
           @Override
           public void onGlobalLayout() {

               getViewTreeObserver().removeOnGlobalLayoutListener(this);

            //   mImage = getResizedBitmap (mImage, getWidth(),getHeight());

           }
       });
    }

   /* private Bitmap getResizedBitmap (Bitmap bitmap, int reqWidth, int reqHeigth){
        Matrix matrix = new Matrix();

        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, reqWidth, reqHeigth);

        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix, true);


    }*/
}
