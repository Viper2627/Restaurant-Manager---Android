package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class status extends AppCompatActivity {
    MaterialButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = status.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_status);
        TextInputLayout id = findViewById(R.id.id);
        TextInputLayout status = findViewById(R.id.status);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stat s = new Stat();
                s.setid(id.getEditText().getText().toString());
                s.setstatus(status.getEditText().getText().toString());

                FirebaseDatabase databases = FirebaseDatabase.getInstance();
                DatabaseReference ref = databases.getReference("status-data").child(id.getEditText().getText().toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ref.setValue(s);
                        Toast.makeText(status.this, "STATUS UPDATED", Toast.LENGTH_SHORT).show();
                        id.getEditText().setText("");
                        status.getEditText().setText("");



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(status.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}