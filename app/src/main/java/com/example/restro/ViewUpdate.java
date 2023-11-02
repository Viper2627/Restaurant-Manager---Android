package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUpdate extends AppCompatActivity {
TextView sta;
String s;
    List<Stat> statss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_update);
        sta = findViewById(R.id.sta);
        statss = new ArrayList<>();
        Window window = ViewUpdate.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();

        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference schoolsRef = FirebaseDatabase.getInstance().getReference(curr.getUid());
        schoolsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                    final User user = snapshot.getValue(User.class);



                    s = user.name;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query qname3 = FirebaseDatabase.getInstance().getReference("status-data");
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Stat st = ds.getValue(Stat.class);
                    if ( st.id.equals(s)) {

sta.setText(st.status);
                    }
                    statss.add(st);


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}