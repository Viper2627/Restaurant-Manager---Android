package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
EditText editTextTextEmailAddress,
    PersonName,password,number;

FirebaseAuth mAuth;
DatabaseReference mDatabase;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = register.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextTextEmailAddress = (EditText)findViewById(R.id.editTextTextEmailAddress);
        password = (EditText)findViewById(R.id.password);
        PersonName=(EditText)findViewById(R.id.PersonName);
        number = (EditText)findViewById(R.id.number);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = editTextTextEmailAddress.getText().toString();
                String Password = password.getText().toString();


                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("sucess", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    User u= new User();
                                    u.setName(PersonName.getText().toString());
                                    u.setPhone(number.getText().toString());

                                    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.getReference().child(userid).setValue(u);
                                    Intent i =new Intent(register.this,userpage.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("ok", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(register.this, "Auth",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });
    }
}