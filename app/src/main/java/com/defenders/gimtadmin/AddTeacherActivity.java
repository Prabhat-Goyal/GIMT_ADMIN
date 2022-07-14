package com.defenders.gimtadmin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class AddTeacherActivity extends AppCompatActivity {

    TextInputEditText bgroup,branch,caddress,dob,email,gname,id,jdate,mnumber,name,onumber,paddress,qualification;
    FloatingActionButton addimgbt;
    CircularImageView addimg;
    Button t_submit_bt;
    Uri selectedImg;
    DatabaseReference mdatabase;
    StorageReference storageReference,file_path;
    FirebaseStorage storage;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        bgroup = findViewById(R.id.t_bg);
        branch = findViewById(R.id.t_branch);
        caddress = findViewById(R.id.t_c_address);
        dob = findViewById(R.id.t_dob);
        email = findViewById(R.id.t_email);
        gname = findViewById(R.id.t_g_name);
        id = findViewById(R.id.t_id);
        jdate = findViewById(R.id.t_doj);
        mnumber = findViewById(R.id.t_no);
        name = findViewById(R.id.t_name);
        onumber = findViewById(R.id.t_degree);
        paddress = findViewById(R.id.t_p_address);
        qualification = findViewById(R.id.t_qualification);

        t_submit_bt = findViewById(R.id.t_submit_bt);

        storageReference = FirebaseStorage.getInstance().getReference();

        t_submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String dbgroup = bgroup.getText().toString();
                final String dbranch = branch.getText().toString();
                final String dcaddress = caddress.getText().toString();
                final String ddob = dob.getText().toString();
                final String demail = email.getText().toString();
                final String dgname = gname.getText().toString();
                final String did = id.getText().toString();
                final String djdate = jdate.getText().toString();
                final String dmnumber = mnumber.getText().toString();
                final String dname = name.getText().toString();
                final String donumber = onumber.getText().toString();
                final String dpaddress = paddress.getText().toString();
                final String dqualification = qualification.getText().toString();

                if (dbgroup.isEmpty() | dbranch.isEmpty() | dcaddress.isEmpty() | ddob.isEmpty() | demail.isEmpty() | dgname.isEmpty() | did.isEmpty()
                 | djdate.isEmpty() | dmnumber.isEmpty() | dname.isEmpty() | donumber.isEmpty() | dpaddress.isEmpty() | dqualification.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {


                    mdatabase = FirebaseDatabase.getInstance().getReference().child("teachers");
                    TeacherModel teacherModel = new TeacherModel(dbgroup,dbranch,dcaddress,ddob,demail,dgname,did,djdate,dmnumber,dname,donumber,dpaddress,dqualification);
                    mdatabase.child(did).setValue(teacherModel);

                    if(filePath != null) {
                        StorageReference ref = storageReference.child("teacher profile pic").child("images/" + UUID.randomUUID().toString());
                        ref.putFile(filePath)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                FirebaseDatabase.getInstance().getReference().child("teachers").child(did)
                                                        .child("profileImgUrl").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        Toast.makeText(AddTeacherActivity.this, "upload successful", Toast.LENGTH_SHORT).show();
                                                        selectedImg = null;
                                                    }
                                                });
                                            }
                                        });

                                    }
                                });


                    }

                }

            }
        });

        addimg = findViewById(R.id.addimg);
        addimgbt= findViewById(R.id.addimgbt);
        addimgbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });


        initToolbar();






    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                addimg.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Teacher");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}