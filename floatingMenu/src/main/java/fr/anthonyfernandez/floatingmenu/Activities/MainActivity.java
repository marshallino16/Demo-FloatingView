package fr.anthonyfernandez.floatingmenu.Activities;

import fr.anthonyfernandez.floatingmenu.R;
import fr.anthonyfernandez.floatingmenu.R.id;
import fr.anthonyfernandez.floatingmenu.R.layout;
import fr.anthonyfernandez.floatingmenu.Service.ServiceFloating;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class MainActivity extends Activity {
	
	public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Bundle bundle = getIntent().getExtras();

		if(bundle != null && bundle.getString("LAUNCH").equals("YES")) {
			startService(new Intent(MainActivity.this, ServiceFloating.class));
		}

		Button launch = (Button)findViewById(R.id.button1);
		launch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startService(new Intent(MainActivity.this, ServiceFloating.class));
			}
		});

		Button stop = (Button)findViewById(R.id.button2);
		stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopService(new Intent(MainActivity.this, ServiceFloating.class));
			}
		});

		ImageView github = (ImageView)findViewById(R.id.imageView1);
		github.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "https://github.com/marshallino16/FloatingNotification";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		
		Button config = (Button)findViewById(R.id.button_config);
		config.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, Configurations.class);
				startActivity(intent);
				stopService(new Intent(MainActivity.this, ServiceFloating.class));
			}
		});
	checkPermission();	
		
	}
	
	
	public void checkPermission() {
		if (!Settings.canDrawOverlays(MainActivity.this)) {
			Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
					Uri.parse("package:" + getPackageName()));
			startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
		}

	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
			if (!Settings.canDrawOverlays(this)) {
				// SYSTEM_ALERT_WINDOW permission not granted...
			}
		}
	}

	@Override
	protected void onResume() {
		Bundle bundle = getIntent().getExtras();

		if(bundle != null && bundle.getString("LAUNCH").equals("YES")) {
			startService(new Intent(MainActivity.this, ServiceFloating.class));
		}
		super.onResume();
	}
}
