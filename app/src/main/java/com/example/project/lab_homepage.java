package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class lab_homepage extends AppCompatActivity {
    Button btn1,btn2;
    public Uri imageurl;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_homepage);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        btn1 = findViewById(R.id.test1btn);
        btn2 = findViewById(R.id.test2button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageurl = data.getData();
            uploadPicture();


        }
    }

    private void uploadPicture() {
        final ProgressDialog pd  = new ProgressDialog(this);
        pd.setTitle("uploading image");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();



// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageReference.child("images/"+randomKey);

        mountainImagesRef.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Toast.makeText(lab_homepage.this, "report uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100*snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Percentage" + (int) progressPercent+ "%");

            }
        });

// While the file names are the same, the references point to different files

    }
}