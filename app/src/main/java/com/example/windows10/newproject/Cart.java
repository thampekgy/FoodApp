package com.example.windows10.newproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.newproject.Common.Common;
import com.example.windows10.newproject.Database.OrderContract;
import com.example.windows10.newproject.Database.OrderDataSource;
import com.example.windows10.newproject.Database.OrderRecordAdapter;
import com.example.windows10.newproject.Model.OrderRecord;
import com.example.windows10.newproject.Model.Request;
import com.example.windows10.newproject.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    Button btnPlace;

    //OrderDataSource dataSource;
    OrderList orderList = new OrderList();
    List<OrderRecord> cart = new ArrayList<>();
    CartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Cart");

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Init
        recyclerView = (RecyclerView) findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView) findViewById(R.id.total);
        btnPlace = (Button) findViewById(R.id.btn_place_order);

        loadListFood();

        /*btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();


            }
        });*/

    }

    private void loadListFood(){

        //dataSource.open();
        //final List<OrderRecord> cart = dataSource.getAllOrder();
        //OrderRecordAdapter adapter = new OrderRecordAdapter(this,R.layout.activity_cart, cart);
        //cart = new OrderDataSource(this).getAllOrder();

        adapter = new CartAdapter(cart,this);
        //orderList.getCart();

        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );

        ///Getting

        String recordStr = sharedPref.getString("order","");

        //Use Gson to convert Json string to object
        OrderRecord or2 = gson.fromJson(recordStr, OrderRecord.class);
        //Log.v("or2 is",""+ or2);
        //Toast.makeText(Cart.this, "Added to Cart : "+or2.getProductName(), Toast.LENGTH_SHORT).show();

        cart.add(or2);
        recyclerView.setAdapter(adapter);



    }

    private  void showAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(Cart.this);
        alert.setTitle("One more step");
        alert.setMessage("Enter your Address: ");

        final EditText add = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        add.setLayoutParams(lp);
        alert.setView(add); //add edit text to alert dialog
        alert.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //Create new Request
                Request re = new Request(
                        Common.currentMember.getContact(),
                        Common.currentMember.getName(),
                        add.getText().toString(),
                        //txtTotalPrice.getText().toString(),
                        cart
                );

                Gson gson = new Gson();
                //Submit to Firebase
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(re);
                //Delete Cart
                String record1 = " ";
                SharedPreferences sharedPref = getSharedPreferences( "appData", Context.MODE_PRIVATE );
                SharedPreferences.Editor prefEditor = getSharedPreferences( "appData", Context.MODE_PRIVATE ).edit();
                // Save record1 as "order"
                prefEditor.putString( "order", record1 );
                prefEditor.commit();
                Toast.makeText(Cart.this, "Thank You, Order Place", Toast.LENGTH_SHORT).show();;
                finish();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                dialog.dismiss();
            }
        });

        alert.show();
    }



}
