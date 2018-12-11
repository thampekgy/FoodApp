package com.example.windows10.newproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.windows10.newproject.Database.FavorDataSource;
import com.example.windows10.newproject.Interface.ItemClickListener;
import com.example.windows10.newproject.Model.Food;
import com.example.windows10.newproject.Model.OrderRecord;
import com.example.windows10.newproject.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;
    //static Spinner  numberButton1;
    static ImageButton btnCart;


    //String[] num1 ={"1", "2", "3", "4", "5"};

    String categoryId = "";
    String foodId = "";
    Food currentFood;
    static String num;

    //FavorDataSource favor;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    OrderList orderList = new OrderList();
    List<OrderRecord> cart = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        setTitle("Food List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        //numberButton1 = (Spinner) findViewById(R.id.spinner);
        //num = numberButton1.getSelectedItem().toString();

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, num1);
        //ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(FoodList.this, R.array.number_array, android.R.layout.activity_list_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //numberButton1.setAdapter(adapter);
        /*numberButton1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String num = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), num, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        btnCart = (ImageButton) findViewById(R.id.add);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*orderList = (OrderList) getIntent().getSerializableExtra("OrderList");
        cart = (List<OrderRecord>) getIntent().getSerializableExtra("Cart");*/


        //Intent
        if (getIntent() != null) {
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if (!categoryId.isEmpty() && categoryId != null) {
            loadListFood(categoryId);
        }

        /*if (getIntent() != null) {
            foodId = getIntent().getStringExtra("FoodId");
        }
        if (!foodId.isEmpty()) {
            getDetailFood(foodId);
        }*/
    }


    public void btnCart_Click(View v) {

        //num = numberButton1.getSelectedItem().toString();

        //Log.v("Your number is ", "" + num);
        //Toast.makeText(FoodList.this, "" + num, Toast.LENGTH_SHORT).show();
        /*num = numberButton1.getSelectedItem().toString();
        Toast.makeText(FoodList.this,num,Toast.LENGTH_SHORT).show();*/

        //numberButton1 = findViewById(R.id.spinner);

        //num = numberButton1.getSelectedItem().toString();

        cart.add(new OrderRecord(
                foodId,
                currentFood.getName(),
                currentFood.getPrice(),
                currentFood.getDiscount()
        ));
        orderList.setCart(cart);


        //Library to convert object to json and json to object
        Gson gson = new Gson();

        //Json string from OrderRecord object
        String record1 = gson.toJson(orderList);


        //Create new sharedprefernces  or get existing
        SharedPreferences sharedPref = getSharedPreferences("appData", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = getSharedPreferences("appData", Context.MODE_PRIVATE).edit();
        // Save record1 as "order"
        prefEditor.putString("order", record1);
        prefEditor.commit();

        //Getting

        String recordStr = sharedPref.getString("order", "");
        Log.v("recordStr is", "" + recordStr);

        //Use Gson to convert Json string to object
        OrderList or2 = gson.fromJson(recordStr, OrderList.class);
        Log.v("or2 is", "" + or2);

        Toast.makeText(FoodList.this, "Added to Cart. ", Toast.LENGTH_SHORT).show();


    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.food_item,
                FoodViewHolder.class, foodList.orderByChild("menuId").equalTo(categoryId))  //like : SELECT * from Foods where MenuId =

        {
            @Override
            protected void populateViewHolder(final FoodViewHolder viewHolder, final Food model, final int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);
                viewHolder.food_price.setText((model.getPrice()));

                //numberButton1 = viewHolder.num;
                //Toast.makeText(FoodList.this,""+numberButton1,Toast.LENGTH_SHORT).show();

                /*ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(FoodList.this, R.array.number_array, android.R.layout.activity_list_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                viewHolder.num.setAdapter(adapter1);*/

                final Food local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, final int position, boolean isLongClick) {

                        //Get CategoryId and send to new Activity
                       /* final Intent foodDetail= new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("OrderList", orderList);
                        foodDetail.putExtra("Cart", (Serializable) cart);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey()); //send foodId to new Activity
                        startActivity(foodDetail);
                        Toast.makeText(FoodList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();*/

                        foodId = adapter.getRef(position).getKey();
                        foodList.child(foodId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                currentFood = dataSnapshot.getValue(Food.class);
                                Picasso.with(getBaseContext()).load(currentFood.getImage());
                                currentFood.getName();
                                currentFood.getPrice();
                                currentFood.getName();
                                currentFood.getDescription();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }


}


