package com.example.windows10.newproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.windows10.newproject.Database.FavorDataSource;
import com.example.windows10.newproject.Database.FavorSQLHelper;
import com.example.windows10.newproject.Model.Food;
import com.example.windows10.newproject.Model.OrderRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodDetail extends AppCompatActivity {


    TextView food_name,food_price,food_description;
    ImageView food_image ;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton ;
    String num;
    int orderedQuantity;

    String foodId="" ;
    FirebaseDatabase database ;
    DatabaseReference foods;

    FavorSQLHelper orderdb;
    Food currentFood;

    OrderList orderList = new OrderList();
    List<OrderRecord> cart = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        setTitle("Food Detail");

        orderdb = new FavorSQLHelper(this);

        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Foods");




        //View
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart= (FloatingActionButton) findViewById(R.id.btn_cart);

        //num = numberButton.getNumber();
        //orderedQuantity = Integer.parseInt(num);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cart.add(new OrderRecord(
                       foodId,
                       currentFood.getName(),
                       numberButton.getNumber(),
                       currentFood.getPrice(),
                       currentFood.getDiscount()
               ));
                orderList.setCart(cart);


               //Library to convert object to json and json to object
                Gson gson = new Gson();

                //Json string from OrderRecord object
                String record1 = gson.toJson(orderList);


                //Create new sharedprefernces  or get existing
                SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
                SharedPreferences.Editor prefEditor = getSharedPreferences( "appData", Context.MODE_PRIVATE ).edit();
                // Save record1 as "order"
                prefEditor.putString( "order", record1 );
                prefEditor.commit();

                //Getting

                String recordStr = sharedPref.getString("order","");
                Log.v("recordStr is",""+ recordStr);

                //Use Gson to convert Json string to object
                OrderList or2 = gson.fromJson(recordStr, OrderList.class);
                Log.v("or2 is",""+ or2);

                Toast.makeText(FoodDetail.this, "Added to Cart : "+or2.getCart().get(0).getProductName(), Toast.LENGTH_SHORT).show();
            }
        });





        food_description= (TextView) findViewById(R.id.food_description);
        food_name= (TextView) findViewById(R.id.food_name);
        food_price= (TextView) findViewById(R.id.food_price);
        food_image= (ImageView) findViewById(R.id.img_food);

        collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout .setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Intent

        if(getIntent()!=null)
        {
            foodId=getIntent().getStringExtra("FoodId");
        }
        if(!foodId.isEmpty())
        {
            getDetailFood(foodId);
        }

    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood=dataSnapshot.getValue(Food.class) ;
                Picasso.with(getBaseContext()).load(currentFood.getImage()).into(food_image);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                food_price.setText(currentFood.getPrice());
                food_name.setText(currentFood.getName());
                food_description.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
