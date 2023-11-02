package com.example.restro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class Manager extends AppCompatActivity implements View.OnClickListener {
    MaterialButton createbutton, create,logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        Window window = Manager.this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color

        window.setStatusBarColor(ContextCompat.getColor(Manager.this,R.color.teal_200));
        getSupportActionBar().hide();
        createbutton = findViewById(R.id.createtable);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ins = new Intent(Manager.this,login.class);
                startActivity(ins);
            }
        });

        createbutton.setOnClickListener(this);
        create = findViewById(R.id.create);
        create.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.createtable:
                Intent i = new Intent(Manager.this, booktable.class);
                startActivity(i);
                break;
            case R.id.create:

Intent in = new Intent(Manager.this,ViewTable.class);
startActivity(in);
        }
    }
}



