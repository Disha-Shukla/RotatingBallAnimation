package com.example.rotatingball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private int c = Color.BLACK;
    private Canvas g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(new SampleView(this));
        draw();
    }

   /* public class SampleView extends View {

        Paint mPaint = new Paint();
        private Animation anim;

        public SampleView(Context context) {
            super(context);
            mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            mPaint.setStrokeWidth(10);
            mPaint.setColor(Color.RED);
        }

        private void createAnimation(Canvas canvas) {

            Animation rotation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, .5f,
                    Animation.RELATIVE_TO_SELF, .5f);
            rotation.setInterpolator(new LinearInterpolator());
            rotation.setRepeatCount(Animation.INFINITE);
            rotation.setDuration(2000);
            startAnimation(rotation);
        }

        protected void onDraw(Canvas canvas) {

            int cx = getWidth()/2; // x-coordinate of center of the screen
            int cy = getHeight()/2; // y-coordinate of the center of the screen

            // Starts the animation to rotate the circle.
            if (anim == null)
                createAnimation(canvas);

            canvas.drawCircle(cx, cy, 150, mPaint); // drawing the circle.
        }
    }*/



    // draws the ball
    public void draw ()
    {
        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getHeight();
        int height = display.getWidth();

        Bitmap bitmap = Bitmap.createBitmap(width, height*3, Bitmap.Config.ARGB_4444);

        g = new Canvas(bitmap);
        g.drawColor(c);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        //g.drawOval(new RectF(0, 0, width * 3 / 4, height * 3 / 4), paint);
        g.drawCircle(1000, 1500, 350, paint); //Put your values

        Animation rotation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, .5f,
                Animation.RELATIVE_TO_SELF, .5f);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setRepeatCount(Animation.INFINITE);
        rotation.setDuration(2000);

        ImageView imageView = new ImageView(this);

        // Set this ImageView's bitmap to ours
        imageView.setImageBitmap(bitmap);

        // Create a simple layout and add imageview to it.
        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        layout.addView(imageView, params);
        imageView.startAnimation(rotation);

        layout.setBackgroundColor(Color.BLACK);

        // Show layout in activity.
        setContentView(layout);
    }


}