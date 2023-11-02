package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class bookingdetail extends AppCompatActivity {
MaterialTextView dates,prices,email,id;
MaterialButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingdetail);
        String value = getIntent().getStringExtra("dates");
        String pricess = getIntent().getStringExtra("price");
        String ids = getIntent().getStringExtra("id");
        Log.i("yo",value);
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
        dates = findViewById(R.id.date);
        prices = findViewById(R.id.price);
        email = findViewById(R.id.phone);
        id = findViewById(R.id.id);
submit = findViewById(R.id.submit);
        dates.setText(value);
        prices.setText(pricess);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            email.setText(user.getEmail());
        } else {
            // No user is signed in
        }
        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference schoolsRef = FirebaseDatabase.getInstance().getReference(curr.getUid());
        schoolsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    final User user = snapshot.getValue(User.class);


                    Toast.makeText(bookingdetail.this, user.phone, Toast.LENGTH_SHORT).show();
                    Log.i("phone",user.phone);
                    email.setText(user.phone);
                    id.setText(user.name);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("table-data").child(ids);
        mDatabase.child("status").setValue("BOOKED");





        BookDetail books= new BookDetail();
    books.setid(ids);
    books.setName(id.getText().toString());
    books.setPhone(email.getText().toString());
    books.setprice(pricess);


        FirebaseDatabase databases = FirebaseDatabase.getInstance();
        DatabaseReference ref = databases.getReference("tablebook-data").child(ids);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref.setValue(books);
                Toast.makeText(bookingdetail.this, "data added", Toast.LENGTH_SHORT).show();
                dates.setText("");
                id.setText("");
                email.setText("");

                prices.setText("");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(bookingdetail.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });


Intent in = new Intent(bookingdetail.this,userpage.class);
startActivity(in);
}

});


//Receiving data inside onCreate() method of Second Activity


    }
}