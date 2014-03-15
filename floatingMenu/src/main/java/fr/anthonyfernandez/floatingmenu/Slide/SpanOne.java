package fr.anthonyfernandez.floatingmenu.Slide;

import fr.anthonyfernandez.floatingmenu.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

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
