package com.diamed.DiaMed;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapter3 extends BaseAdapter {

	static ImageView image;
	ViewPressure cp = new ViewPressure();

	private ArrayList<MessageInRow> _data;
	Context _c;

	customAdapter3(ArrayList<MessageInRow> data, Context c) {
		_data = data;
		_c = c;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) _c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.tablelayout, null);
		}

		// ImageView image = (ImageView) v.findViewById(R.id.icon);
		TextView disease = (TextView) v.findViewById(R.id.disease);
		TextView medication = (TextView) v.findViewById(R.id.medication);
		TextView presc = (TextView) v.findViewById(R.id.prescription);
		TextView duration = (TextView) v.findViewById(R.id.duration);
		TextView startDate = (TextView) v.findViewById(R.id.startdate);
		TextView timeView = (TextView) v.findViewById(R.id.time);

		MessageInRow msg = _data.get(position);
		// image.setImageResource(msg.icon);
		disease.setText("Disease    : " + msg.name);
		medication.setText("Medication : " + msg.sub);
		presc.setText("Prescription: " + msg.desc);
		duration.setText("Duration: " + msg.duration);
		startDate.setText("Start Date: " + msg.startDate);
		try {
			timeView.setText(msg.time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("settime", e.toString());
		}

		return v;
	}
}
