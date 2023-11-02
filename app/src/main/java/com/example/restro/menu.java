package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menu extends AppCompatActivity {
ImageView b,c,d,e,f,mb,mc,mp,mmp;
static  int a=0;
MaterialButton submit;
String name,phone;
ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Window window = menu.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getSupportActionBar().hide();
        list = new ArrayList<>();
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);
        e = findViewById(R.id.e);
        f = findViewById(R.id.f);

        mp = findViewById(R.id.mp);
        mb = findViewById(R.id.mb);
        mc = findViewById(R.id.mc);
        mmp = findViewById(R.id.mmp);
            submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food food= new Food();
                food.setName(name);
                food.setList(list);

                food.setPrice(a);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("ordered-food").child(name);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ref.setValue(food);
                        Toast.makeText(menu.this, "Food Ordered", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(menu.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        FirebaseUser curr = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference schoolsRef = FirebaseDatabase.getInstance().getReference(curr.getUid());
        schoolsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {

                    final User user = snapshot.getValue(User.class);




           name=user.name;




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Black Tea");
                Toast.makeText(getApplicationContext(),"Black Tea Added", Toast.LENGTH_SHORT).show();
                a=a+45;
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Milk Tea");
                Toast.makeText(getApplicationContext(),"Milk Tea",Toast.LENGTH_SHORT).show();

                a=a+60;
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Black Coffee");
                Toast.makeText(getApplicationContext(),"Black Coffee", Toast.LENGTH_SHORT).show();
                a=a+80;

            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Coffee");
                Toast.makeText(getApplicationContext(),"Coffee Added", Toast.LENGTH_SHORT).show();
                a=a+120;

            }
        });
        mp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Paneer Masala");
                Toast.makeText(getApplicationContext(),"Paneer Masala Added", Toast.LENGTH_SHORT).show();
                a=a+250;

            }
        });
        mc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Chole Bahture");
                Toast.makeText(getApplicationContext(),"Chole Bahture Added", Toast.LENGTH_SHORT).show();
                a=a+150;

            }
        });
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Butter Chicken");
                Toast.makeText(getApplicationContext(),"Butter Chicken Added", Toast.LENGTH_SHORT).show();
                a=a+350;

            }
        });
        mmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Momo");
                Toast.makeText(getApplicationContext(),"Momo Added", Toast.LENGTH_SHORT).show();
                a=a+180;

            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("Cold Drink");



                a=a+50;


                Toast.makeText(getApplicationContext(),"Cold Drink Added", Toast.LENGTH_LONG).show();
            }

        });
    }
}