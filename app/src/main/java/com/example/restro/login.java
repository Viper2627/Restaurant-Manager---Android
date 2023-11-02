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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity implements View.OnClickListener {
Button registerr,loginn;
    DatabaseReference databaseReference;
EditText name,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = login.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        registerr = (Button)findViewById(R.id.registerr);
        registerr.setOnClickListener(this);
        loginn = findViewById(R.id.loginn);
        loginn.setOnClickListener(this);
        name=findViewById(R.id.edit);
        pass = findViewById(R.id.Password);

    }

    @Override
    public void onClick(View v) {
        // Perform action on click
        switch(v.getId()) {
            case R.id.registerr:
                Intent i = new Intent(login.this, register.class);
                startActivity(i);


                break;
            case R.id.loginn:
               String admin="sahil@gmail.com";
                String Email = name.getText().toString();
                String Password = pass.getText().toString();


                Query qname3 = FirebaseDatabase.getInstance().getReference("post-data");

                qname3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Emp e = ds.getValue(Emp.class);
                            Log.i("this",e.post);
                            if(e.email.equals(Email) && e.password.equals(Password) && e.post.equals("Manager")){
                                Intent i =new Intent(login.this,Manager.class);
                                startActivity(i);
                            }
                            else if(e.email.equals(Email) && e.password.equals(Password) && e.post.equals("Waiter")){
                                Intent is =new Intent(login.this,waiter.class);
                                startActivity(is);
                            }
                        }
                      }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("yo", Email);
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String emai = user.getEmail();
                                    if (emai.equals(admin)) {
                                        Toast.makeText(login.this, "logged in as admin",
                                                Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(login.this, admin.class);
                                        startActivity(i);
                                    } else {
                                        Intent i = new Intent(login.this, userpage.class);
                                        startActivity(i);
                                    }
                                    name.setText("");
                                    pass.setText("");


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("yo", "signInWithEmail:failure", task.getException());


                                    // ...
                                }

                                // ...
                            }
                        });

        }

    }}