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

public class ExamActivity extends AppCompatActivity {

    TextInputEditText course, branch, sem, examheading,subject,stime, etime, room, date;
    DatabaseReference mdatabase;
    Button button;

    String saveCurrentTime,orderRandomKey,saveCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        course = findViewById(R.id.course);
        branch = findViewById(R.id.branch);
        sem = findViewById(R.id.sem);
        examheading = findViewById(R.id.examheading);
        subject = findViewById(R.id.subject);
        stime = findViewById(R.id.stime);
        etime = findViewById(R.id.etime);
        room = findViewById(R.id.room);
        date = findViewById(R.id.date);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String dcourse = course.getText().toString().toLowerCase();
                final String dbranch = branch.getText().toString().toLowerCase();
                final String dsem = sem.getText().toString().toLowerCase();
                final String dexamheading = examheading.getText().toString().toUpperCase();
                final String dsubject = subject.getText().toString().toUpperCase();
                final String dstime = stime.getText().toString().toLowerCase();
                final String detime = etime.getText().toString().toLowerCase();
                final String droom = room.getText().toString();
                final String ddate = date.getText().toString();

                if (dcourse.isEmpty() | dbranch.isEmpty() | dsem.isEmpty() | dexamheading.isEmpty() | dsubject.isEmpty() | dstime.isEmpty() | detime.isEmpty() | droom.isEmpty() | ddate.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                    saveCurrentDate = currentDate.format(calendar.getTime());
                    orderRandomKey = saveCurrentDate;
                    mdatabase = FirebaseDatabase.getInstance().getReference().child("course").child(dcourse).child("branch").child(dbranch).child("semester").child(dsem).child("exams");

                    mdatabase.child(orderRandomKey).child("heading").setValue(dexamheading);
                    mdatabase.child(orderRandomKey).child("id").setValue(orderRandomKey);
                    mdatabase.child(orderRandomKey).child("subjects").child(dsubject).child("name").setValue(dsubject);
                    mdatabase.child(orderRandomKey).child("subjects").child(dsubject).child("etime").setValue(detime);
                    mdatabase.child(orderRandomKey).child("subjects").child(dsubject).child("stime").setValue(dstime);
                    mdatabase.child(orderRandomKey).child("subjects").child(dsubject).child("room").setValue(droom);
                    mdatabase.child(orderRandomKey).child("subjects").child(dsubject).child("date").setValue(ddate);

                    Toast.makeText(ExamActivity.this, "upload successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
        initToolbar();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Exam");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}