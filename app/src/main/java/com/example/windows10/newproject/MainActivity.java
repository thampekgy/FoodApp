package com.example.windows10.newproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.windows10.newproject.Common.Common;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected FragmentManager fragmentManager;
    private TextView navEmail;
    //TextView txtFullName;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        //Init Firebase



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //tranfer nav de email
        //navEmail = (TextView) findViewById(R.id.nav_email);
        //navEmail.setText(getIntent().getExtras().getString("Email"));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView= navigationView.getHeaderView(0);
        //txtFullName= (TextView)headerView.findViewById(R.id.txtFullName);
        //txtFullName.setText(Common.currentMember.getName());


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

        } else */if(id == R.id.action_favoriteFoodList) {

            Intent intent = new Intent(MainActivity.this, favourite_food_list.class);

            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id)
    {
        Fragment fragment = null;

        switch(id)
        {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_editProfile:
                fragment = new editProfile();
                break;
            case R.id.nav_order:
                //fragment = new Restaurant();
                break;
            case R.id.nav_help:
                fragment = new help();
                break;
            case R.id.nav_about:
                fragment = new about();
                break;
        }
        if(fragment!=null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main_FrameLayout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_signOut)
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {

            displaySelectedScreen(id);
        }

        return true;
    }

}
