package com.defenders.gimtadmin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class HostelActivity extends AppCompatActivity {

    TextInputEditText roll,roomno,floor,food,fees,jdate;
    Button button;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);

        roll = findViewById(R.id.roll);
        roomno = findViewById(R.id.roomno);
        floor = findViewById(R.id.floor);
        food = findViewById(R.id.food);
        fees = findViewById(R.id.fees);
        jdate = findViewById(R.id.jdate);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String droll = roll.getText().toString();
                final String droomno = roomno.getText().toString();
                final String dfloor = floor.getText().toString();
                final String dfood = food.getText().toString();
                final String dfees = fees.getText().toString();
                final String djdate = jdate.getText().toString();

                if(droll.isEmpty() | droomno.isEmpty() | dfloor.isEmpty() | dfood.isEmpty() | dfees.isEmpty() | djdate.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("students").child(droll);
                    mDatabase.child("jhosdate").setValue(djdate);
                    mDatabase.child("floor").setValue(dfloor);
                    mDatabase.child("food").setValue(dfood);
                    mDatabase.child("hosfees").setValue(dfees);
                    mDatabase.child("roomno").setValue(droomno);

                    Toast.makeText(HostelActivity.this, "upload successful", Toast.LENGTH_SHORT).show();
                }
            }
        });


        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hostel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
