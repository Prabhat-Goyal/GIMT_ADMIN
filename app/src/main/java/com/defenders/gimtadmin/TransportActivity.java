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

public class TransportActivity extends AppCompatActivity {

    TextInputEditText roll,busno,route,timing,busfees;
    Button button;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        roll = findViewById(R.id.roll);
        busno = findViewById(R.id.busno);
        route = findViewById(R.id.route);
        timing = findViewById(R.id.timing);
        busfees = findViewById(R.id.busfees);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String droll = roll.getText().toString();
                final String dbusno = busno.getText().toString();
                final String droute = route.getText().toString();
                final String dtiming = timing.getText().toString();
                final String dbusfees = busfees.getText().toString();


                if(droll.isEmpty() | dbusno.isEmpty() | droute.isEmpty() | dtiming.isEmpty() | dbusfees.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("students").child(droll);
                    mDatabase.child("busno").setValue(dbusno);
                    mDatabase.child("broute").setValue(droute);
                    mDatabase.child("bustime").setValue(dtiming);
                    mDatabase.child("busfees").setValue(dbusfees);

                    Toast.makeText(TransportActivity.this, "upload successful", Toast.LENGTH_SHORT).show();
                }
            }
        });


        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Transport");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
