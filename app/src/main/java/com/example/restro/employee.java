package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class employee extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;

    MaterialButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        Window window = employee.this.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(employee.this,R.color.white));
        getSupportActionBar().hide();
        String[] type = new String[]{"Manager","Waiter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.dropdown,type);
        TextInputLayout name = findViewById(R.id.name);
        TextInputLayout password = findViewById(R.id.password);
        TextInputLayout email = findViewById(R.id.email);
        autoCompleteTextView = findViewById(R.id.fillexposed);
        autoCompleteTextView.setAdapter(adapter);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Emp emp = new Emp();
                emp.setname(name.getEditText().getText().toString());
                emp.setemail(email.getEditText().getText().toString());
                emp.setpassword(password.getEditText().getText().toString());
                emp.setpost(autoCompleteTextView.getText().toString());


                FirebaseDatabase databases = FirebaseDatabase.getInstance();
                DatabaseReference ref = databases.getReference("post-data").child(name.getEditText().getText().toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ref.setValue(emp);
                        Toast.makeText(employee.this, "data added", Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(employee.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    }
