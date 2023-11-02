package com.example.restro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class vadapter extends RecyclerView.Adapter<vadapter.MyViewHolder> {
    private List<BookDetail> mUploads;
    Context co;
    public vadapter (Context con, List<BookDetail> mUploads) {

        co=con;
        this.mUploads = mUploads;


    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewtable,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        final BookDetail book = mUploads.get(position);

        holder.id.setText(book.getid());

        holder.name.setText(book.getName());

        holder.number.setText(book.getPhone());
        holder.price.setText(book.getprice());
holder.paa.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        CharSequence options[]=new CharSequence[]{
                // select any from the value
                "Delete",
                "Cancel",
        };
        AlertDialog.Builder builder=new AlertDialog.Builder(holder.itemView.getContext());
        builder.setTitle("Delete Content");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // if delete option is choosed
                // then call delete function
                if(which==0) {
                    delete(position,mUploads.get(position).getid());
                }

            }
        });
        builder.show();
    }
});

    }
    private void delete(int position,String id) {
        // creating a variable for our Database
        // Reference for Firebase.
        Toast.makeText(co, id, Toast.LENGTH_SHORT).show();
        // we are use add listerner
        // for event listener method
        // which is called with query.
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("tablebook-data");
        // we are use add listerner
        // for event listener method
        // which is called with query.
        Query query=dbref.child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // remove the value at reference
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public int getItemCount() {

        return mUploads.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,number,price;


        LinearLayout paa;
        public MyViewHolder(View view) {
            super(view);


            id = view.findViewById(R.id.id);
            name= view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);

          number = view.findViewById(R.id.number);
            paa = view.findViewById(R.id.pas);
        }

    }

}
