package com.defenders.gimtadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class TimeTableTActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button s_submit_bt;
    Uri selectedImg;
    ProgressBar pb;
    ProgressDialog pg;

    DatabaseReference mdatabase;
    StorageReference storageReference,file_path;
    FirebaseStorage storage;
    EditText tid;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_tactivity);

        uploadImage = findViewById(R.id.btn_upload);
        pb = findViewById(R.id.pb_upload);
        pb.setVisibility(View.GONE);
        tid = findViewById(R.id.tid);
        s_submit_bt = findViewById(R.id.btn_submit_pdf);

        storageReference = FirebaseStorage.getInstance().getReference();





        s_submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String dtid = tid.getText().toString();

                pb.setVisibility(View.VISIBLE);
                if (filePath != null) {
                    StorageReference ref = storageReference.child("timetable").child("teacher/" + UUID.randomUUID().toString());
                    ref.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            FirebaseDatabase.getInstance().getReference().child("teachers").child(dtid).child("timetable").child("img").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener() {
                                                @Override
                                                public void onComplete(@NonNull Task task) {
                                                    Toast.makeText(TimeTableTActivity.this, "upload successful", Toast.LENGTH_SHORT).show();
                                                    selectedImg = null;
                                                    pb.setVisibility(View.GONE);
                                                }
                                            });
                                        }
                                    });

                                }
                            });


                }
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
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
                    uploadImage.setImageBitmap(bitmap);
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
            getSupportActionBar().setTitle("Teacher Timetable Upload");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }




    }

