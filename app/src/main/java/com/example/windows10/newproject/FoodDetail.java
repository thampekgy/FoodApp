package com.example.windows10.newproject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.windows10.newproject.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {


    TextView food_name,food_price,food_description;
    ImageView food_image ;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton ;
    String num;


    String foodId="" ;
    FirebaseDatabase database ;
    DatabaseReference foods;

    Food currentFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        setTitle("Food Detail");

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_member = database.getReference("Member");


        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Foods");


        //View
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnCart= (FloatingActionButton) findViewById(R.id.btn_cart);


        /*numberButton.setOnClickListener(new ElegantNumberButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                num = numberButton.getNumber();
                //Toast.makeText(FoodDetail.this, "welcome "+num, Toast.LENGTH_SHORT).show();
            }

        });*/

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(FoodDetail.this);
                mDialog.setMessage(("Please wait..."));
                mDialog.show();
                //Query query = table_member.child("Member").orderByChild();
                table_member.addValueEventListener(new ValueEventListener() {
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
