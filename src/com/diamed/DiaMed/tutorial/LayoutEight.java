

package com.diamed.DiaMed.tutorial;

import com.diamed.DiaMed.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutEight extends Fragment {


	public static Fragment newInstance(Context context) {
		LayoutEight f = new LayoutEight();	
		
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_eight, null);		
		return root;
	}
	
}


