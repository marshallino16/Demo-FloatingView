package fr.anthonyfernandez.floatingmenu.Slide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.anthonyfernandez.floatingmenu.R;

public class SpanOne extends Fragment{
	
	private FragmentActivity fa;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fa = super.getActivity();
		View one = inflater.inflate(R.layout.span_one, container, false);
		
		
		return one;
	}

}
