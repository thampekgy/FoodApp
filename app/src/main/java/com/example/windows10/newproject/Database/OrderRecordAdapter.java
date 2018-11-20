package com.example.windows10.newproject.Database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.windows10.newproject.Model.OrderRecord;
import com.example.windows10.newproject.R;

import java.util.List;

public class OrderRecordAdapter {

  /*  public class OrderRecordAdapter extends ArrayAdapter<OrderRecord> {
        public OrderRecordAdapter(Activity context, int resource, List<OrderRecord>
                list) {
            super(context, resource, list)
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            OrderRecord userRecord = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).
                        inflate(R.layout.user_record,
                                parent,
                                false);
            }
            TextView textViewName, textViewEmail;
            textViewName = (TextView)convertView.findViewById(R.id.textViewName);
            textViewEmail = (TextView)convertView.findViewById(R.id.textViewEmail);
            textViewName.setText("Name:"+userRecord.getName());
            textViewEmail.setText("Email:" + userRecord.getEmail());
            return convertView;
        }
}*/
}
