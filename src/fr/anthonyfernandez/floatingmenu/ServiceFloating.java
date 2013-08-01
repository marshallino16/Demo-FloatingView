package fr.anthonyfernandez.floatingmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
public class ServiceFloating extends Service {

	private WindowManager windowManager;
	private ImageView chatHead;
	private PopupWindow pwindo;

	private Boolean _enable = true;

	ArrayList<String> myArray;
	ArrayList<PInfo> apps;
	List listCity;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public void onCreate() {
		super.onCreate();

		RetrievePackages getInstalledPackages = new RetrievePackages(getApplicationContext());
		apps = getInstalledPackages.getInstalledApps(false);
		myArray = new ArrayList<String>();

		for(int i=0 ; i<apps.size() ; ++i) {
			myArray.add(apps.get(i).appname);
		}

		listCity = new ArrayList();
		for(int i=0 ; i<apps.size() ; ++i) {
			listCity.add(apps.get(i));
		}

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
				initiatePopupWindow(chatHead);
				_enable = false;
				//				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				//				getApplicationContext().startActivity(intent);
			}
		});

	}


	private void initiatePopupWindow(View anchor) {
		try {
			Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			ListPopupWindow popup = new ListPopupWindow(this);
			popup.setAnchorView(anchor);
			popup.setWidth((int) (display.getWidth()/(1.5)));
			//ArrayAdapter<String> arrayAdapter = 
			//new ArrayAdapter<String>(this,R.layout.list_item, myArray);
			popup.setAdapter(new CustomAdapter(getApplicationContext(), R.layout.row, listCity));
			popup.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long id3) {
					//Log.w("tag", "package : "+apps.get(position).pname.toString());
					Intent i;
					PackageManager manager = getPackageManager();
					try {
						i = manager.getLaunchIntentForPackage(apps.get(position).pname.toString());
						if (i == null)
							throw new PackageManager.NameNotFoundException();
						i.addCategory(Intent.CATEGORY_LAUNCHER);
						startActivity(i);
					} catch (PackageManager.NameNotFoundException e) {

					}
				}
			});
			popup.show();

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