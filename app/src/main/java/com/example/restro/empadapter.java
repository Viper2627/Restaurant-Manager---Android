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

public class empadapter extends
        RecyclerView.Adapter<empadapter.MyViewHolder> {
    private List<Emp> mUploads;
    Context co;
    public empadapter (Context con, List<Emp> mUploads) {

        co=con;
        this.mUploads = mUploads;


    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        final Emp emp = mUploads.get(position);

        holder.id.setText(emp.getname());

        holder.post.setText(emp.getpost());


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
                            delete(position,mUploads.get(position).getname());
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
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference().child("post-data");
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

        TextView id,post;


        LinearLayout paa;
        public MyViewHolder(View view) {
            super(view);


            id = view.findViewById(R.id.id);
            post= view.findViewById(R.id.post);

            paa = view.findViewById(R.id.pae);
        }

    }

}
