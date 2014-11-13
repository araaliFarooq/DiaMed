package com.diamed.DiaMed.tutorial;

import android.content.Context;
import com.diamed.DiaMed.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutSeven extends Fragment {


	public static Fragment newInstance(Context context) {
		LayoutSeven f = new LayoutSeven();	
		
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_seven, null);		
		return root;
	}
	
}


