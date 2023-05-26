package com.example.food_wastage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class profilephoto extends AppCompatActivity {
ImageButton i1,i2,i3,i4,i5,i6,i7,i8,i9;
String l1,l2,l3,l4,l5,l6,l7,l8,l9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilephoto);
        i1=findViewById(R.id.imageButton);
        i2=findViewById(R.id.imageButton2);
        i3=findViewById(R.id.imageButton3);
        i4=findViewById(R.id.imageButton4);
        i5=findViewById(R.id.imageButton5);
        i6=findViewById(R.id.imageButton6);
        i7=findViewById(R.id.imageButton7);
        i8=findViewById(R.id.imageButton8);
        i9=findViewById(R.id.imageButton10);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.yellow));
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l1="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/bbcc.jpg?alt=media&token=db95f8ff-87b8-4818-a540-49e5bd79f671";
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));

                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l2="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/burger.png?alt=media&token=8c365856-9743-4508-ae06-1532efa86a3c";

                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
        }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l3="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/juice.png?alt=media&token=95282299-e231-48bc-9893-1dbbbf8df87c";
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l3).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l4="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/pizza.png?alt=media&token=f4c980dc-7942-412d-919d-2811b1023c55";

                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l4).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
       }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l5="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/pavbhaji.png?alt=media&token=b82ba242-8d01-4def-afe8-1566f023cc1d";
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l5).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l6="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/rash.png?alt=media&token=3c2ec6c0-1403-477d-9439-d4f8e040a5ec";
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l6).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l7="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/doughnut.png?alt=media&token=4598ecd4-ca59-4b90-8e57-655a8543f116";
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l7).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    l8="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/gulab.png?alt=media&token=391df800-a545-4666-83b5-0e7bd2e07004";
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l8).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        i9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("slink")
                            .setValue(l9).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(profilephoto.this, "Profile pic uploaded successfully!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(profilephoto.this,profile_edit.class));
                                    } else {
                                        Toast.makeText(profilephoto.this, "Action failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(profilephoto.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
l9="https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/ramen.png?alt=media&token=2ca9851f-34d8-4b61-97b4-4f796e1c170f";

                 }
        });

    }
}