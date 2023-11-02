package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewEmployee extends AppCompatActivity {
    List<Emp> emp;

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    empadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);
        Window window = ViewEmployee.this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(ViewEmployee.this,R.color.purple_500));
        getSupportActionBar().hide();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayout = new LinearLayoutManager(ViewEmployee.this, LinearLayoutManager.VERTICAL,false
        );
        recyclerView.setLayoutManager(linearLayout);
        emp = new ArrayList<>();

        Query qname3 = FirebaseDatabase.getInstance().getReference("post-data");
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Emp t = ds.getValue(Emp.class);

                    emp.add(t);


                }
                adapter = new empadapter(ViewEmployee.this,emp);
                recyclerView.setAdapter(adapter);

                recyclerView.getAdapter().notifyDataSetChanged();}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}


