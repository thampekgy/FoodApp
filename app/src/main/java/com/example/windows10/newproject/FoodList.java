package com.example.windows10.newproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.windows10.newproject.Database.FavorDataSource;
import com.example.windows10.newproject.Interface.ItemClickListener;
import com.example.windows10.newproject.Model.Food;
import com.example.windows10.newproject.Model.OrderRecord;
import com.example.windows10.newproject.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView ;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database ;
    DatabaseReference foodList ;

    String categoryId="";

    FavorDataSource favor ;
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        setTitle("Food List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database =FirebaseDatabase.getInstance();
        foodList=database.getReference("Foods");

        recyclerView= (RecyclerView) findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this) ;
        recyclerView.setLayoutManager(layoutManager);


        //Intent
        if(getIntent()!=null){
            categoryId=getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty() && categoryId !=null){
            loadListFood(categoryId);
        }


    }

    private void loadListFood(String categoryId)  {
        adapter=new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_item,
                FoodViewHolder.class,foodList.orderByChild("MenuId").equalTo(categoryId))  //like : SELECT * from Foods where MenuId =
        {
            @Override
            protected void populateViewHolder(final FoodViewHolder viewHolder, final Food model, final int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);

                //add favor

               /* if (favor.isFavor(adapter.getRef(position).getKey()))
                    viewHolder.fav.setImageResource(R.drawable.ic_favorite_black_24dp);

                //click to change state of favor
                viewHolder.fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (favor.isFavor(adapter.getRef(position).getKey())){

                            favor.insertFavor(adapter.getRef(position).getKey());
                            viewHolder.fav.setImageResource(R.drawable.ic_favorite_black_24dp);
                            Toast.makeText(FoodList.this, ""+model.getName()+" was added to Favorites.", Toast.LENGTH_SHORT);
                        }else
                        {
                            favor.removeFavor(adapter.getRef(position).getKey());
                            viewHolder.fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                            Toast.makeText(FoodList.this, ""+model.getName()+" was remove from Favorites.", Toast.LENGTH_SHORT);
                        }

                    }
                });*/

                final Food local =model ;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, final int position, boolean isLongClick) {
                        //Get CategoryId and send to new Activity
                        final Intent foodDetail= new Intent(FoodList.this,FoodDetail.class);

                            foodDetail.putExtra("FoodId",adapter.getRef(position).getKey()); //send foodId to new Activity
                        startActivity(foodDetail);
                        //Toast.makeText(FoodList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();


                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);

    }

}
