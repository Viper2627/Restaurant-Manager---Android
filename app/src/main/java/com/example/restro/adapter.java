package com.example.restro;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    private List<Table> mUploads;
    Context co;
    public adapter (Context con, List<Table> mUploads) {

        co=con;
        this.mUploads = mUploads;


    }

    public adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull adapter.MyViewHolder holder, int position) {


        final Table table = mUploads.get(position);

        holder.id.setText(Integer.toString(table.getid()));
        holder.date.setText(table.getdate());

        holder.status.setText(table.getstatus());
        holder.price.setText(Integer.toString(table.getprice()));
        holder.time.setText(table.gettime());
        for(int i = 0;i<mUploads.size();i++) {
            holder.paa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mUploads.get(position).getstatus().equals("BOOKED")){
                        Toast.makeText(co,"Table already booked",Toast.LENGTH_SHORT).show();
                    }else{
                    Intent i = new Intent(co, bookingdetail.class);
                    i.putExtra("dates", mUploads.get(position).getdate());
                    i.putExtra("price",Integer.toString(mUploads.get(position).getprice()));
                    i.putExtra("id",Integer.toString(mUploads.get(position).getid()));
                    co.startActivity(i);
                }}
            });
        }
    }

    @Override
    public int getItemCount() {

        return mUploads.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,status,price,time,date;


        LinearLayout paa;
        public MyViewHolder(View view) {
            super(view);


            id = view.findViewById(R.id.id);
            status= view.findViewById(R.id.status);
            price = view.findViewById(R.id.price);

            time = view.findViewById(R.id.time);
            date = view.findViewById(R.id.date);
        paa = view.findViewById(R.id.paa);
        }

    }

}





