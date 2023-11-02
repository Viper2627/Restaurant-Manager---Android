package com.example.restro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class booktable extends AppCompatActivity implements View.OnClickListener,  DatePickerDialog.OnDateSetListener {
Button date;
MaterialButton submit;
TextView dates,id,price;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booktable);
        Window window = booktable.this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(booktable.this,R.color.teal_200));
        date = findViewById(R.id.button3);
        radioGroup = (RadioGroup) findViewById(R.id.grp);
        dates =  findViewById(R.id.dates);
        submit = findViewById(R.id.submit);
        price = findViewById(R.id.editTextTextPersonName2);
        id = findViewById(R.id.editTextTextPersonName);
        date.setOnClickListener(this);
        submit.setOnClickListener(this);
        String[] type = new String[]{"SET TIME","Morning 9AM - 11AM", "MORNING 11AM - 9AM","NOON 12PM - 2PM","NOON 2PM-4PM"
        ,"EVENING 4PM-6PM","EVENING 6PM-8PM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.dropdown,type);
  autoCompleteTextView = findViewById(R.id.fillexposed);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button3:
               showDatePicker();
                break;

            case R.id.submit:
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(booktable.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();

                Table table= new Table();
                table.setid(Integer.parseInt(id.getText().toString()));
                table.setdate(dates.getText().toString());
                table.settime(autoCompleteTextView.getText().toString());
                table.setprice(Integer.parseInt(price.getText().toString()));
                table.setStatus(radioButton.getText().toString());

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("table-data").child(id.getText().toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ref.setValue(table);
                        Toast.makeText(booktable.this, "data added", Toast.LENGTH_SHORT).show();
                        dates.setText("");
                        id.setText("");
                        price.setText("");
Intent ia = new Intent(booktable.this,Manager.class);
startActivity(ia);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(booktable.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                    }
                });

                break;

        }
    }
    public void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }



    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i + "/" + i2 + "/" + i1;
        dates.setText(date);
    }
}