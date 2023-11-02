package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class foodview extends AppCompatActivity {
    List<Food> foods;
    Button button;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    fadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodview);  recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayout = new LinearLayoutManager(foodview.this, LinearLayoutManager.VERTICAL,false
        );
        recyclerView.setLayoutManager(linearLayout);


        Query qname3 = FirebaseDatabase.getInstance().getReference("ordered-food");
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foods = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {


                    Food t = ds.getValue(Food.class);

                    foods.add(t);


                }
                adapter = new fadapter(foodview.this,foods);
                recyclerView.setAdapter(adapter);

                recyclerView.getAdapter().notifyDataSetChanged();}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}

