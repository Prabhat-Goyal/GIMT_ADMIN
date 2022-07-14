package com.defenders.gimtadmin;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NoticeActivity extends AppCompatActivity {

    TextInputEditText date,details,heading;
    Button button;

    DatabaseReference mdatabase;

    String saveCurrentTime,orderRandomKey,saveCurrentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        date= findViewById(R.id.date);
        details = findViewById(R.id.details);
        heading = findViewById(R.id.heading);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String ddate = date.getText().toString();
                final String ddetails = details.getText().toString();
                final String dheading = heading.getText().toString().toUpperCase();

                if (ddate.isEmpty() | dheading.isEmpty() | ddetails.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                    saveCurrentDate = currentDate.format(calendar.getTime());
                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                    saveCurrentTime = currentTime.format(calendar.getTime());
                    orderRandomKey = saveCurrentDate + saveCurrentTime;
                    mdatabase = FirebaseDatabase.getInstance().getReference().child("notice");
                    NoticeModel noticeModel = new NoticeModel(ddate,ddetails,dheading);
                    mdatabase.child(orderRandomKey).setValue(noticeModel);
                    Toast.makeText(NoticeActivity.this, "upload successful", Toast.LENGTH_SHORT).show();
                }

            }
        });

        initToolbar();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Notice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}