package com.jonas.metrodemo;

import java.util.Timer;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MetroDemoActivity extends Activity {
	/** Called when the activity is first created. */
	private Timer timer;
	private Handler handler;
	private Button btnStart;
	private ImageView ivIcon;
	private Animation alphaAnim;
	private int iconState = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initUI();
		
	}
	private Runnable runnable = new Runnable( ) {
		public void run ( ) {
		changeSrc();

		handler.postDelayed(this,2000); 
		//postDelayed(this,1000)方法安排一个Runnable对象到主线程队列中
		}
		};
		
	private void changeSrc() {
		if (iconState==0) {
			ivIcon.startAnimation(alphaAnim);
			ivIcon.setImageResource(R.drawable.icon);
			iconState=1;
			
		} else {
			ivIcon.startAnimation(alphaAnim);
			ivIcon.setImageResource(R.drawable.ic_launcher);
			iconState=0;
		}
	}

	private OnClickListener btnListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnStart:
				handler.postDelayed(runnable,1000); // 开始Timer
				break;
			case R.id.btnStop:
				handler.removeCallbacks(runnable);
				break;
			default:
				break;
			}
			
		}
	};
	private Button btnStop;

	private void initUI() {
		// TODO Auto-generated method stub
		btnStart = (Button) this.findViewById(R.id.btnStart);
		btnStart.setOnClickListener(btnListener);
		btnStop = (Button) this.findViewById(R.id.btnStop);
		btnStop.setOnClickListener(btnListener);
		ivIcon = (ImageView) this.findViewById(R.id.iv);
		alphaAnim = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
		handler = new Handler();
	}
}