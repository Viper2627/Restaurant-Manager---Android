package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class userpage extends AppCompatActivity {
MaterialButton book,menu,update,view;
ImageView welcome;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        Window window = userpage.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();
        textView = findViewById(R.id.textView5);
menu = findViewById(R.id.menu);
update = findViewById(R.id.update);
view = findViewById(R.id.view);
welcome = findViewById(R.id.button7);
welcome.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent ins = new Intent(userpage.this,login.class);
        startActivity(ins);
    }
});
view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent a = new Intent(userpage.this,ViewUserTable.class);
        startActivity(a);
    }
});
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent ib = new Intent(userpage.this,ViewUpdate.class);
startActivity(ib);
    }
});
menu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent in = new Intent(userpage.this,menu.class);
        startActivity(in);
    }
});
        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference schoolsRef = FirebaseDatabase.getInstance().getReference(curr.getUid());
        schoolsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    final User user = snapshot.getValue(User.class);




                    textView.setText(user.name);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        book = findViewById(R.id.book);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(userpage.this,customerbooktable.class);
                startActivity(in);
            }
        });  Query qname3 = FirebaseDatabase.getInstance().getReference("table-data");
        qname3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Table t = ds.getValue(Table.class);
                    Log.i("hi",t.gettime());

                }}


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}