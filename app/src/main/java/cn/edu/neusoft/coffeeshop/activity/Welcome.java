package cn.edu.neusoft.coffeeshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import cn.edu.neusoft.coffeeshop.R;

public class Welcome extends Activity implements
		android.view.GestureDetector.OnGestureListener {

	private int[] imgs = { R.drawable.coffe1, R.drawable.coffe2 };

	private GestureDetector gestureDetector = null;
	private ViewFlipper viewFlipper = null;
	private Activity iActivity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.welcome);

		iActivity = this;
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		gestureDetector = new GestureDetector(this);

		// 将图片添加到ViewFlipper控件中
		for (int i = 0; i < imgs.length; i++) {
			ImageView img = new ImageView(this);
			img.setImageResource(imgs[i]);
			img.setScaleType(ImageView.ScaleType.FIT_XY);
			viewFlipper.addView(img);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		if (e2.getX() - e1.getX() > 0) { // 从左向右滑动
			Animation rightInAnim = AnimationUtils.loadAnimation(iActivity,
					R.anim.push_right_in);
			Animation rightOutAnim = AnimationUtils.loadAnimation(iActivity,
					R.anim.push_right_out);

			viewFlipper.setInAnimation(rightInAnim);
			viewFlipper.setOutAnimation(rightOutAnim);
			viewFlipper.showPrevious();

			if (viewFlipper.getDisplayedChild() == 1) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Welcome.this,
								MainActivity.class);
						startActivity(intent);
						finish();
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);

					}
				}, 500);
			}
		} else if (e2.getX() - e1.getX() < 0) { // 从右向左滑动（右进左出）
			Animation leftInAnim = AnimationUtils.loadAnimation(iActivity,
					R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）
			Animation leftOutAnim = AnimationUtils.loadAnimation(iActivity,
					R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）

			viewFlipper.setInAnimation(leftInAnim);
			viewFlipper.setOutAnimation(leftOutAnim);
			viewFlipper.showNext();

			if (viewFlipper.getDisplayedChild() == 1) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {

						Intent intent = new Intent(Welcome.this,
								MainActivity.class);
						startActivity(intent);
						finish();
						overridePendingTransition(R.anim.alpha_in,
								R.anim.alpha_out);
					}
				}, 500);

			}
		}

		return true;
	}

}
