package com.defenders.gimtadmin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
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
import java.util.UUID;

public class AddStudentActivity extends AppCompatActivity {

    TextInputEditText Semester,adate,bgroup,branch,caddress,course,dob,email,fname,gname,mname,mnumber,name,paddress,pmnumber,rollno;
    FloatingActionButton addimgbt;
    CircularImageView addimg;
    Button s_submit_bt;
    Uri selectedImg;
    DatabaseReference mdatabase;
    StorageReference storageReference,file_path;
    FirebaseStorage storage;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        bgroup = findViewById(R.id.s_bg);
        branch = findViewById(R.id.s_branch);
        caddress = findViewById(R.id.s_c_address);
        dob = findViewById(R.id.s_dob);
        email = findViewById(R.id.s_email);
        gname = findViewById(R.id.s_g_name);
        rollno = findViewById(R.id.s_rollno);
        adate = findViewById(R.id.s_doa);
        mnumber = findViewById(R.id.s_no);
        name = findViewById(R.id.s_name);
        Semester = findViewById(R.id.s_sem);
        paddress = findViewById(R.id.s_p_address);
        course = findViewById(R.id.s_course);
        fname = findViewById(R.id.s_f_name);
        mname = findViewById(R.id.s_m_name);
        pmnumber = findViewById(R.id.s_p_no);

        s_submit_bt = findViewById(R.id.s_submit_bt);

        storageReference = FirebaseStorage.getInstance().getReference();

        s_submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String dbgroup = bgroup.getText().toString();
                final String dbranch = branch.getText().toString().toLowerCase();
                final String dcaddress = caddress.getText().toString();
                final String ddob = dob.getText().toString();
                final String demail = email.getText().toString();
                final String dgname = gname.getText().toString();
                final String drollno = rollno.getText().toString();
                final String dadate = adate.getText().toString();
                final String dmnumber = mnumber.getText().toString();
                final String dname = name.getText().toString();
                final String dSemester = Semester.getText().toString().toLowerCase();
                final String dpaddress = paddress.getText().toString();
                final String dcourse = course.getText().toString().toLowerCase();
                final String dfname = fname.getText().toString();
                final String dmname = mname.getText().toString();
                final String dpmnumber = pmnumber.getText().toString();

                if (dbgroup.isEmpty() | dbranch.isEmpty() | dcaddress.isEmpty() | ddob.isEmpty() | demail.isEmpty() | dgname.isEmpty() | drollno.isEmpty()
                 | dadate.isEmpty() | dmnumber.isEmpty() | dname.isEmpty() | dSemester.isEmpty() | dpaddress.isEmpty() | dcourse.isEmpty() | dfname.isEmpty() | dmname.isEmpty() | dpmnumber.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill The Fields", Toast.LENGTH_SHORT).show();
                } else {


                    mdatabase = FirebaseDatabase.getInstance().getReference().child("students");
                    StudentModel studentModel = new StudentModel(dbgroup,dbranch,dcaddress,ddob,demail,dgname,drollno,dadate,dmnumber,dname,dSemester,dpaddress,dcourse,dfname,dmname,dpmnumber);
                    mdatabase.child(drollno).setValue(studentModel);

                    if(filePath != null) {
                        StorageReference ref = storageReference.child("student profile pic").child("images/" + UUID.randomUUID().toString());
                        ref.putFile(filePath)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                FirebaseDatabase.getInstance().getReference().child("students").child(drollno)
                                                        .child("profileImgUrl").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        Toast.makeText(AddStudentActivity.this, "upload successful", Toast.LENGTH_SHORT).show();
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
        getSupportActionBar().setTitle("Add Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}