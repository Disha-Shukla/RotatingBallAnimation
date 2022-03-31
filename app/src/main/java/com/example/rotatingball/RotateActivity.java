package com.example.rotatingball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class RotateActivity extends AppCompatActivity {

    private int i = 1;
    CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rootView);
        customView = new CustomView(this);
        relativeLayout.addView(customView);
        //relativeLayout.setBackgroundColor(Color.BLACK);
        setContentView(relativeLayout);
        startViewAnimation();
        Animation rotation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, .5f,
                Animation.RELATIVE_TO_SELF, .5f);

        //Animation rotation = new RotateAnimation(0, 360);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setDuration(2000);
        rotation.setFillAfter(true);
        relativeLayout.startAnimation(rotation);
    }

    public void startViewAnimation() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (i < 70) { // Please change '70' according to how long you want to go
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //int baseRadius=30; // base radius is basic radius of circle from which to start animation
                            //customView.updateView(i+baseRadius);

                            //897 1080 224 540
                            int basex1 = 897;
                            int basey1 = 1080;
                            int basex2 = 224;
                            int basey2 = 540;
                            customView.updateView(basex1-i,basey1-i, i+basex2, i+basey2);
                            i=i+30;
                        }
                    });
                } else {
                    i = 0;
                }
            }
        }, 0, 1500); // change '500' to milliseconds for how frequent you want to update radius
    }

    public class CustomView extends View {
        Paint paint;
        int x1, x2, y1, y2 = 0;
        //int radius = 0;

        public CustomView(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5f);
        }
        public void updateView(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            invalidate();
        }

       @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            //////////
            Display display = getWindowManager().getDefaultDisplay();

            int width = display.getHeight();
            int height = display.getWidth();

            Bitmap bitmap = Bitmap.createBitmap(width, height/3, Bitmap.Config.ARGB_4444);

            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            //Log.v("DS",""+x1+" "+y1+" "+x2+" "+y2);

            // 856 1039 265 581
           // 836 1019 285 601
           // 897 1080 224 540
           // 877 1060 244 560
           if (x1 >= 836 && x1<856) {
               paint.setColor(Color.RED);
           }
            if(x1 >= 856 && x1 < 877){
               paint.setColor(Color.BLUE);
           }
            if(x1 >=877 ){
               paint.setColor(Color.GREEN);
           }
            ///////////////
           // canvas.drawCircle(width / 2, height * 6, radius, paint);
           //canvas.drawCircle(width/2 , height/2, radius, paint);
          // canvas.drawRect(width/2, height, width/8, height/2, paint );
           canvas.drawRect(x1, y1, x2, y2, paint );

       }
    }



}