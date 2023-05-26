package com.example.food_wastage_management;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class upload extends AppCompatActivity {
public ImageView b1;
public EditText e1,e2,e3,e4,name;
public Button donate;
    private static final int PICK_IMAGE =1;
    Uri imageUri;
    DatabaseReference acc2,acc3;
    StorageReference sf;
    FirebaseUser vapar;
    private String userId;
    FirebaseAuth au;
TextView textView,photolink;
    String imageUrl,imageUrlg;
    String photochilink,mel;
ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.yellow));
        setContentView(R.layout.activity_upload);
        p1 = findViewById(R.id.progressBar11);
textView=findViewById(R.id.textView);
        b1=findViewById(R.id.profile_image);
        e1=findViewById(R.id.editTextTextPersonName9);
        e2=findViewById(R.id.editTextTextPersonName10);
        e3=findViewById(R.id.editTextTextPersonName11);
        e4=findViewById(R.id.editTextTextPersonName12);
        donate=findViewById(R.id.button4);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        acc3 = FirebaseDatabase.getInstance().getReference().child("users");
        au = FirebaseAuth.getInstance();
        userId = vapar.getUid();
photolink=findViewById(R.id.textView2);


        acc2.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users profile = snapshot.getValue(users.class);

            mel = profile.sname;
            textView.setText(mel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        acc3.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users profile2 = snapshot.getValue(users.class);

                photochilink = profile2.slink;
                photolink.setText(photochilink);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        b1 = (ImageView) findViewById(R.id.profile_image);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture!"), PICK_IMAGE);
            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    if(e1.length()<3 || e1.length()>20){
                        e1.setError("Enter Proper Name of Food");
                    }

                    if(e2.length()<3  || e2.length()>50){
                        e2.setError("Enter Proper Description");
                    }
                    if(e3.length()<10  || e3.length()>10){
                        e3.setError("Enter Proper Phone number");
                    }
                    if(e4.length()<5  || e4.length()>50){
                        e4.setError("Enter Proper Address");
                    }else {
                        p1.setVisibility(View.VISIBLE);
                        upload();
                    }
                }
                else
                {
                    Toast.makeText(upload.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }
        });
        Bundle mbundle2 = getIntent().getExtras();
        if(mbundle2!=null){
            e1.setText(mbundle2.getString("Name1"));
            e2.setText(mbundle2.getString("Description1"));
            e3.setText(mbundle2.getString("Price1"));
            Glide.with(this).load(mbundle2.getString("Image1")).into(b1);
            imageUrl = mbundle2.getString("Image1");
        }

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();

            b1.setImageURI(imageUri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                b1.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }
    public void upload(){

        StorageReference storage = FirebaseStorage.getInstance()
                .getReference().child("Images").child(imageUri.getLastPathSegment());
        storage.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploaddata();
            }
        });
    }


    public void uploaddata(){

        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        prodata pro2 = new prodata(
                photolink.getText().toString(),
                textView.getText().toString(),
                e1.getText().toString(),
                e2.getText().toString(),
                e3.getText().toString(),
                e4.getText().toString(),
                imageUrl,
                myCurrentDateTime

        );
        FirebaseDatabase.getInstance().getReference().child("donationlist"+FirebaseAuth.getInstance().getUid()).child(myCurrentDateTime)
                .setValue(pro2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                        else{
                        }
                    }
                });



        FirebaseDatabase.getInstance().getReference("products")
                .child(myCurrentDateTime).setValue(pro2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(upload.this,"Your Donation is now online!",Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(upload.this,"Something went wrong!",Toast.LENGTH_LONG).show();

                    }
                });

        Bundle mbundle = getIntent().getExtras();
        if(mbundle!=null){
            e1.setText(mbundle.getString("Name"));
            e2.setText(mbundle.getString("Description"));
            e3.setText(mbundle.getString("Price"));
            Glide.with(this).load(mbundle.getString("Image")).into(b1);
            imageUrlg = mbundle.getString("Image");

        }
    }

}