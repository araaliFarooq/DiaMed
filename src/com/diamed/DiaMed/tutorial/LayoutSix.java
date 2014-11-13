package com.diamed.DiaMed.tutorial;

import android.content.Context;
import com.diamed.DiaMed.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutSix extends Fragment {


	public static Fragment newInstance(Context context) {
		LayoutSix f = new LayoutSix();	
		
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_six, null);		
		return root;
	}
	
}


