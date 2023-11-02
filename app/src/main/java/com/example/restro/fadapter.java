package com.example.restro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class fadapter extends RecyclerView.Adapter<fadapter.MyViewHolder> {
    private List<Food> mUploads;
    Context co;
    String output;
    public fadapter (Context con, List<Food> mUploads) {

        co=con;
        this.mUploads = mUploads;


    }

    public fadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull fadapter.MyViewHolder holder, int position) {


        final Food food = mUploads.get(position);

        holder.name.setText(food.getName());
        holder.price.setText(Integer.toString(food.getPrice()));

        ArrayList<String> list = food.getList();
        output = "";
        for (int i = 0; i < list.size(); i++) {

            //Append all the values to a string
            if(!list.get(i).equals("null")){
                output += list.get(i) + "," + "  " ;
            }

        }
        holder.food.setText(output.replace("null",""));


    }

    @Override
    public int getItemCount() {

        return mUploads.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,food,price;


        LinearLayout paf;
        public MyViewHolder(View view) {
            super(view);


            name = view.findViewById(R.id.name);
            food= view.findViewById(R.id.food);
            price = view.findViewById(R.id.price);


            paf = view.findViewById(R.id.paf);
        }

    }

}






