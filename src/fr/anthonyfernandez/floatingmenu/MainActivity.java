package fr.anthonyfernandez.floatingmenu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
