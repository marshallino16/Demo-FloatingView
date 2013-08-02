package fr.anthonyfernandez.floatingmenu.Slide;

import java.util.List;
import java.util.Vector;

import fr.anthonyfernandez.floatingmenu.R;

import fr.anthonyfernandez.floatingmenu.Activities.MainActivity;
import fr.anthonyfernandez.floatingmenu.Adapter.InitPagerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class SlideLaunch extends FragmentActivity {
	
	private PagerAdapter mPagerAdapter;
	private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            Intent i = new Intent(SlideLaunch.this, MainActivity.class);
            SlideLaunch.this.startActivity(i);
            finish();
        }
    };

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("first_time", false))
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
            setContentView(R.layout.activity_launch);

    		// Création de la liste de Fragments que fera défiler le PagerAdapter
    		List fragments = new Vector();

    		// Ajout des Fragments dans la liste
    		fragments.add(Fragment.instantiate(this,SpanOne.class.getName()));
    		fragments.add(Fragment.instantiate(this,SpanTwo.class.getName()));
    		fragments.add(Fragment.instantiate(this,SpanThree.class.getName()));

    		// Création de l'adapter qui s'occupera de l'affichage de la liste de
    		// Fragments
    		this.mPagerAdapter = new InitPagerAdapter(super.getSupportFragmentManager(), fragments);

    		ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
    		// Affectation de l'adapter au ViewPager
    		pager.setAdapter(this.mPagerAdapter);

        }
        else
        {
            handler.sendEmptyMessage(0);
        }
	}
}
