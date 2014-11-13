package com.diamed.DiaMed.tutorial;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private Context _context;
	public static int totalPage=9;
	public ViewPagerAdapter(Context context, FragmentManager fm) {
		super(fm);	
		_context=context;
		
		}
	@Override
	public Fragment getItem(int position) {
		Fragment f = new Fragment();
		switch(position){
		case 0:
			f=LayoutSix.newInstance(_context);	
			break;
		case 1:
			f=LayoutOne.newInstance(_context);	
			break;
		case 2:
			f=LayoutTwo.newInstance(_context);	
			break;
		case 3:
			f=LayoutThree.newInstance(_context);	
			break;
		case 4:
			f=LayoutFour.newInstance(_context);	
			break;
		case 5:
			f=LayoutFive.newInstance(_context);	
			break;
		case 6:
			f=LayoutSeven.newInstance(_context);	
			break;
		case 7:
			f=LayoutEight.newInstance(_context);	
			break;
		case 8:
			f=LayoutNine.newInstance(_context);	
			break;
			
			
		}
		return f;
	}
	@Override
	public int getCount() {
		return totalPage;
	}

}
