package fr.anthonyfernandez.floatingmenu;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ServiceFloating extends Service {
	
	private WindowManager windowManager;
	private ImageView chatHead;

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
	    chatHead.setImageResource(R.drawable.floating);

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
	    	        return true;
	    	      case MotionEvent.ACTION_UP:
	    	        return true;
	    	      case MotionEvent.ACTION_MOVE:
	    	    	  paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
	    	    	  paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
	    	        windowManager.updateViewLayout(chatHead, paramsF);
	    	        return true;
	    	    }
	    	    return false;
	    	  }
	    	});

	  }

	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    if (chatHead != null) windowManager.removeView(chatHead);
	  }

}
