package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class customerbooktable extends AppCompatActivity {

    List<Table> tables;
    Button button;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerbooktable);
        Window window = customerbooktable.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayout = new LinearLayoutManager(customerbooktable.this, LinearLayoutManager.VERTICAL,false
        );
        recyclerView.setLayoutManager(linearLayout);
        tables = new ArrayList<>();

        Query qname3 = FirebaseDatabase.getInstance().getReference("table-data");
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Table t = ds.getValue(Table.class);
                    tables.add(t);

                }
                adapter = new adapter(customerbooktable.this,tables);
                recyclerView.setAdapter(adapter);

                recyclerView.getAdapter().notifyDataSetChanged();}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}