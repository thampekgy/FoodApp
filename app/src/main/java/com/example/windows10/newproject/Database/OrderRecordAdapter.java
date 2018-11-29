package com.example.windows10.newproject.Database;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.windows10.newproject.Model.OrderRecord;
import com.example.windows10.newproject.R;

import java.util.List;

    public class OrderRecordAdapter extends ArrayAdapter<OrderRecord> {
        public OrderRecordAdapter(Activity context, int resource, List<OrderRecord>
                list) {
            super(context, resource, list);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            OrderRecord orderRecord = getItem(position);
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).
                        inflate(R.layout.cart_layout,
                                parent,
                                false);
            }




            TextView txtFoodName, txtFoodPrice, numberButton;
            txtFoodName = (TextView)convertView.findViewById(R.id.cart_item_name);
            txtFoodPrice = (TextView) convertView.findViewById(R.id.cart_item_price);

            txtFoodName.setText(orderRecord.getProductName());
            txtFoodPrice.setText(orderRecord.getPrice());


//            textViewName.setText("Name:"+userRecord.getName());
//            textViewEmail.setText("Email:" + userRecord.getEmail());
            return convertView;
        }
}