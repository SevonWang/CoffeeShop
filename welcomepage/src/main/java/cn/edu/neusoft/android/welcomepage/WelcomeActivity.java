package cn.edu.neusoft.android.welcomepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class WelcomeActivity extends Activity implements GestureDetector.OnGestureListener {

    private int[] imgs = {R.drawable.coffee1, R.drawable.coffee2,
            R.drawable.coffee1, R.drawable.coffee2};

    private GestureDetector gestureDetector;
    private ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.welcome);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        gestureDetector = new GestureDetector(this);

        for (int i = 0; i < imgs.length; i++) {
            ImageView imgView = new ImageView(this);
            imgView.setImageResource(imgs[i]);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imgView);
        }
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {

        //从左向右滑动，图片：右侧滑出，左侧滑进
        if (e2.getX() - e1.getX() > 0) {
            Animation rightIn = AnimationUtils.loadAnimation
                    (WelcomeActivity.this, R.anim.push_right_in);
            Animation rightOut = AnimationUtils.loadAnimation
                    (WelcomeActivity.this, R.anim.push_right_out);
            viewFlipper.setInAnimation(rightIn);
            viewFlipper.setOutAnimation(rightOut);
            viewFlipper.showPrevious();
            if (viewFlipper.getDisplayedChild() == 3) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                    }
                }, 500);
            }

        } else if (e2.getX() - e1.getX() < 0) {
            //从右向左滑动，图片：左侧滑出，右侧滑进
            Animation leftIn = AnimationUtils.loadAnimation
                    (WelcomeActivity.this, R.anim.push_left_in);
            Animation leftOut = AnimationUtils.loadAnimation
                    (WelcomeActivity.this, R.anim.push_left_out);
            viewFlipper.setInAnimation(leftIn);
            viewFlipper.setOutAnimation(leftOut);
            viewFlipper.showNext();
            if (viewFlipper.getDisplayedChild() == 3) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass
                                (WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition
                                (R.anim.alpha_in, R.anim.alpha_out);
                    }
                }, 500);
            }
        }


        return true;
    }
}
