package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

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

public class ViewUserTable extends AppCompatActivity {
    TextView id,name,num,price;
    String s;
    List<BookDetail> statss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_table);
        Window window = ViewUserTable.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();
statss=new ArrayList<>();
id = findViewById(R.id.id);
        name= findViewById(R.id.name);
        num = findViewById(R.id.num);
        price = findViewById(R.id.price);

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
        Query qname3 = FirebaseDatabase.getInstance().getReference("tablebook-data");
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    BookDetail t = ds.getValue
                            (BookDetail.class);
                    if ( t.name.equals(s)) {
                        id.setText(t.id);
                        name.setText(t.name);
                        num.setText(t.phone);
                        price.setText(t.price);


                    }



                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}