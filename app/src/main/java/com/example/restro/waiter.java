package com.example.restro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class waiter extends AppCompatActivity implements View.OnClickListener {
MaterialButton view,update;
    ImageView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = waiter.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_waiter);
        view = findViewById(R.id.view);

        view.setOnClickListener(this);
        update = findViewById(R.id.update);
        update.setOnClickListener(this);
        welcome = findViewById(R.id.button7);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ins = new Intent(waiter.this,login.class);
                startActivity(ins);
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.view:
                Intent i = new Intent(waiter.this, foodview.class);
                startActivity(i);
                break;

            case R.id.update:
                Intent is = new Intent(waiter.this, status.class);
                startActivity(is);
                break;



        }
    }
}


