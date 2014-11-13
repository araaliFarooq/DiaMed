package com.diamed.DiaMed;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapter2 extends BaseAdapter {
	 
	static ImageView image;
	ViewPressure cp = new ViewPressure();
	
    private ArrayList<MessageInRow> _data;
    Context _c;
    
    customAdapter2 (ArrayList<MessageInRow> data, Context c){
    	
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
         if (v == null)
         {
            LayoutInflater vi = (LayoutInflater)_c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listviewrow1, null);
         }
 
           
           TextView fromView = (TextView)v.findViewById(R.id.From);
           //TextView subView = (TextView)v.findViewById(R.id.subject);
           TextView descView = (TextView)v.findViewById(R.id.description);
           TextView timeView = (TextView)v.findViewById(R.id.time);
 
           MessageInRow msg = _data.get(position);
          // image.setImageResource(msg.icon);
           fromView.setText("Sugar Level: "+msg.name);
           //subView.setText("Diastolic: "+msg.sub);
           descView.setText("Activity: "+msg.desc);
           timeView.setText(msg.time);   
           
           
          
                        
        return v;
}
}


