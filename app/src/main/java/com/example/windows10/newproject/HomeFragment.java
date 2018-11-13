package com.example.windows10.newproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.newproject.Interface.ItemClickListener;
import com.example.windows10.newproject.Model.Category;
import com.example.windows10.newproject.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    FirebaseDatabase database ;
    DatabaseReference category;

    RecyclerView recycler_menu ;
    RecyclerView.LayoutManager layoutManager ;

    FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_home, container, false);
        database = FirebaseDatabase.getInstance();
        category=database.getReference("Category");

        recycler_menu= (RecyclerView) view.findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this.getActivity());
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();

        return view;

    }

    private void loadMenu() {
        adapter  = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getContext()).load(model.getImage()).into(viewHolder.imageView);
                final Category clickItem =model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent foodList = new Intent(getActivity(),FoodList.class) ;
                        foodList.putExtra("CategoryId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                        //Toast.makeText(getActivity(), ""+clickItem.getName(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter);
    }

}
