package com.example.windows10.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.windows10.newproject.Common.Common;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected FragmentManager fragmentManager;
    TextView navPhone;
    //String phone;
    TextView txtFullName;
    String name, email, phone, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Category");
        setSupportActionBar(toolbar);

        //Init Firebase

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cartIntent = new Intent(MainActivity.this, Cart.class);
                cartIntent.putExtra("Phone", phone);
                cartIntent.putExtra("Name", name);
                startActivity(cartIntent);


            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView= navigationView.getHeaderView(0);
        navPhone = (TextView)headerView.findViewById(R.id.navPhone);
        Intent myIntent = getIntent();
        phone = myIntent.getStringExtra("Phone");
        navPhone.setText(phone);
        //navPhone.setText(Common.currentMember.getContact());

        txtFullName= (TextView)headerView.findViewById(R.id.navFullName);
        name = myIntent.getStringExtra("name");
        txtFullName.setText(name);
        //txtFullName.setText(Common.currentMember.getName());

        email = myIntent.getStringExtra("Email");
        pwd = myIntent.getStringExtra("Password");
        //txtFullName.setText(Common.currentMember.getName());





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        //View headerView= navigationView.getHeaderView(0);
        //View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        //


        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.content_main_FrameLayout)!=null)
        {

            if(savedInstanceState!=null)
            {
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.content_main_FrameLayout,homeFragment, null);
            fragmentTransaction.commit();

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        /*if (id == R.id.action_chat) {
            //return true;
        } else if(id == R.id.action_notification) {

        } else */

        if (id == R.id.waitOrder){
            Intent intent1 = new Intent(MainActivity.this, WaitOrder.class);

            startActivity(intent1);
        }

        if(id == R.id.action_favoriteFoodList) {

            Intent intent = new Intent(MainActivity.this, favourite_food_list.class);

            intent.putExtra("Name", name);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id)
    {
        Fragment fragment = null;

        if(fragment!=null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main_FrameLayout, fragment);
            ft.commit();
        }

        switch(id)
        {
            case R.id.nav_home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

                break;
            case R.id.nav_editProfile:
                Intent intent2 = new Intent(MainActivity.this, EditProfile.class);
                intent2.putExtra("Email", email);
                intent2.putExtra("Phone", phone);
                intent2.putExtra("Name", name);
                intent2.putExtra("Password", pwd);
                startActivity(intent2);

                break;
            case R.id.nav_order:
                //fragment = new Restaurant();
                break;
            case R.id.nav_help:
                Intent intent4 = new Intent(MainActivity.this, Help1.class);
                intent4.putExtra("Email", email);
                intent4.putExtra("Phone", phone);
                intent4.putExtra("Name", name);
                startActivity(intent4);

                break;
            case R.id.nav_about:
                //fragment = new about();
                Intent intent5 = new Intent(MainActivity.this, about.class);
                startActivity(intent5);

                break;

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


         if (id == R.id.nav_signOut)
        {
            Intent intent = new Intent(MainActivity.this, LoginMember.class);
            startActivity(intent);
        } else {

            displaySelectedScreen(id);
        }

        return true;
    }

}
