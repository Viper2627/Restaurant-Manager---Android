package com.example.restro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class admin extends AppCompatActivity implements View.OnClickListener {
MaterialButton create,viewemp,logout,viewwaiter;
    ViewPager viewPager;
    private FirebaseAuth mAuth;
    ViewFlipper vi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Window window = admin.this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(admin.this,R.color.teal_200));
        getSupportActionBar().hide();
        create = findViewById(R.id.create);
        viewwaiter = findViewById(R.id.viewwaiter);
        viewemp = findViewById(R.id.viewemp);

        logout = findViewById(R.id.logout);
        create.setOnClickListener(this);
        logout.setOnClickListener(this);
        viewwaiter.setOnClickListener(this);

        viewemp.setOnClickListener(this);int images[] = {R.drawable.heada, R.drawable.headb};
        vi = (ViewFlipper) findViewById(R.id.vi);
        for (int i = 0; i < images.length; i++) {
            slides(images[i]);
        }

    }

    public void slides(int image) {
        ImageView img = new ImageView(this);
        img.setBackgroundResource(image);
        vi.addView(img);
        vi.setFlipInterval(5000);
        vi.setAutoStart(true);


        vi.setInAnimation(this, android.R.anim.fade_in);
        vi.setOutAnimation(this, android.R.anim.fade_out);
    }





    @Override
    public void onClick(View view) {

            switch (view.getId()) {

                case R.id.create:
                    Intent ia = new Intent(admin.this, employee.class);
                    startActivity(ia);
                    break;
                case R.id.viewemp:
                    Intent ie = new Intent(admin.this, ViewEmployee.class);
                    startActivity(ie);
                    break;
                case R.id.logout:
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    Intent intent = new Intent(admin.this, login.class);
                    startActivity(intent);
                    break;
                case R.id.viewwaiter:

                    Intent intents = new Intent(admin.this, viewwaiter.class);
                    startActivity(intents);
                    break;


            }
        }
    }
