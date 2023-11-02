package com.example.restro;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.restro.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private int splash_screen_time =3500;
private ProgressBar progess;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = MainActivity.this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.purple_200));
        setContentView(R.layout.activity_main);
progess = (ProgressBar)findViewById(R.id.progressBar);
textView = findViewById(R.id.textView);
new Thread(new Runnable() {
    @Override
    public void run() {
        doWork();
        startactivity();
    }
}).start();
}
private void doWork(){
        for(int i=0;i<=50;i+=10){
try{
    Thread.sleep(200);
    progess.setProgress(i);
    if(i==10){
        textView.setText("Loading.");
    }else if(i==20){
        textView.setText("Loading..");
    }else if(i==30){
        textView.setText("Loading...");
    }
}catch (Exception e) {
    e.printStackTrace();

}
        }
}
private void startactivity(){
        Intent intent = new Intent(MainActivity.this,login.class);
        startActivity(intent);
}}