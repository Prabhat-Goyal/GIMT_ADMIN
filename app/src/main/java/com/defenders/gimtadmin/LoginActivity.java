package com.defenders.gimtadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.defenders.gimtadmin.HomeActivity;
import com.defenders.gimtadmin.IdSave;
import com.defenders.gimtadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private TextInputEditText idno;
    private DatabaseReference mDatabase;

    IdSave idSave;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fab= (FloatingActionButton)findViewById(R.id.fab);
        idno=(TextInputEditText)findViewById(R.id.idno);


        idSave = new IdSave(this);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idno.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "enter your id no", Toast.LENGTH_SHORT).show();
                }else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("admin").child(idno.getText().toString());

                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.exists()){

                                Boolean result = idSave.insertData(idno.getText().toString());

                                if(result) {

                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Invalid Id No", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }
        });

    }

}
