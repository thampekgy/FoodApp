package com.example.windows10.newproject.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.windows10.newproject.Interface.ItemClickListener;
import com.example.windows10.newproject.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{



    //String[] number = {"1", "2", "3", "4", "5"};
    public TextView food_name;
    public ImageView food_image;
    //public Spinner num;
    //public ImageButton btnadd;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);
        food_name=itemView.findViewById(R.id.food_name) ;
        food_image = itemView.findViewById(R.id.food_image);

        /*num = itemView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter1 = ArrayAdapter(FoodViewHolder.this, android.R.layout.activity_list_item, number);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        num.setAdapter(adapter1);*/

        //btnadd = itemView.findViewById(R.id.add);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

