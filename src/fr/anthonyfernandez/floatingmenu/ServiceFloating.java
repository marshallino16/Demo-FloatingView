package fr.anthonyfernandez.floatingmenu;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class ServiceFloating extends Service {

	private WindowManager windowManager;
	private ImageView chatHead;
	private PopupWindow pwindo;

	private Boolean _enable = true;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public void onCreate() {
		super.onCreate();

		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		chatHead = new ImageView(this);
		chatHead.setImageResource(R.drawable.floating2);

		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);

		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0;
		params.y = 100;

		windowManager.addView(chatHead, params);

		chatHead.setOnTouchListener(new View.OnTouchListener() {
			private WindowManager.LayoutParams paramsF = params;
			private int initialX;
			private int initialY;
			private float initialTouchX;
			private float initialTouchY;

			@Override public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					initialX = paramsF.x;
					initialY = paramsF.y;
					initialTouchX = event.getRawX();
					initialTouchY = event.getRawY();
					break;
				case MotionEvent.ACTION_UP:
					break;
				case MotionEvent.ACTION_MOVE:
					paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
					paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
					windowManager.updateViewLayout(chatHead, paramsF);
					break;
				}
				return false;
			}
		});

		chatHead.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				initiatePopupWindow();
				_enable = false;
				//				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				//				getApplicationContext().startActivity(intent);
			}
		});

	}


	private void initiatePopupWindow() {
		try {
			LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
			View popupView = layoutInflater.inflate(R.layout.popup, null);  
			pwindo = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT);  
			if(_enable == true) {
				pwindo.showAsDropDown(chatHead, 50, -30);
			} else {
				pwindo.dismiss();
			}
			Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
			btnDismiss.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {
					_enable = true;
					pwindo.dismiss();
				}});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (chatHead != null) windowManager.removeView(chatHead);
	}

}
