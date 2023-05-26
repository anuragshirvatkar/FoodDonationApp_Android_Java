package com.example.food_wastage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile_edit extends AppCompatActivity {
CircleImageView change;
TextView t1;
EditText e1,e2,e3,e4,e5;
    FirebaseUser vapar;
    DatabaseReference acc2;
    FirebaseAuth fauth;
    Button update,delete;
    private String userId,name,number,house,street,city,state,url,abc;
    String x1,x2,x3,x4,x5,x6;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        change = findViewById(R.id.loadface);
        fauth = FirebaseAuth.getInstance();
        p1 = findViewById(R.id.progressBar7);
        t1 = findViewById(R.id.textView5);
        e1 = findViewById(R.id.editTextTextPersonName13);
        e2 = findViewById(R.id.editTextTextPersonName14);
        e3 = findViewById(R.id.editTextTextPersonName15);
        e4 = findViewById(R.id.editTextTextPersonName16);
        e5 = findViewById(R.id.editTextTextPersonName17);
        update = findViewById(R.id.button5);
        delete = findViewById(R.id.button6);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.yellow));
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        userId = vapar.getUid();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        acc2.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users profile = snapshot.getValue(users.class);

                number = profile.sphone;
                t1.setText(number);

                name = profile.sname;
                e1.setText(name);

                house = profile.shouse;
                e2.setText(house);

                street = profile.sstreet;
                e3.setText(street);

                city = profile.scity;
                e4.setText(city);

                state = profile.sstate;
                e5.setText(state);

                url = profile.slink;
                Glide.with(profile_edit.this).load(url).into(change);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    startActivity(new Intent(profile_edit.this,profilephoto.class));
                }
                else
                {
                    Toast.makeText(profile_edit.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    if(e1.length()<2 || e1.length()>50){
                        e1.setError("Enter Proper Name");
                    }

                    if(e2.length()<3  || e2.length()>40){
                        e2.setError("Enter Proper Address");
                    }
                    if(e3.length()<3  || e3.length()>40){
                        e3.setError("Enter Proper Address");
                    }
                    if(e4.length()<3  || e4.length()>20){
                        e4.setError("Enter Proper Address");
                    }
                    if(e5.length()<3  || e5.length()>20){
                        e5.setError("Enter Proper Address");
                    }
                    else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(profile_edit.this);

                        alertDialog.setTitle("Delete"); // Sets title for your alertbox

                        alertDialog.setMessage("Are you sure you want to Update Account?");


                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                p1.setVisibility(View.VISIBLE);
                                acc2.child(userId).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        users profile = snapshot.getValue(users.class);
                                        abc = profile.slink;

                                        x1 = e1.getText().toString();
                                        x2 = t1.getText().toString();
                                        x3 = e2.getText().toString();
                                        x4 = e3.getText().toString();
                                        x5 = e4.getText().toString();
                                        x6 = e5.getText().toString();


                                        users u = new users(x1, x2, x3, x4, x5, x6, abc);
                                        FirebaseDatabase.getInstance().getReference().child("users")
                                                .child(FirebaseAuth.getInstance().getUid()).
                                                setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {


                                                            Toast.makeText(profile_edit.this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                                                            startActivity(new Intent(profile_edit.this, slideactivity.class));

                                                        } else {

                                                        }
                                                    }
                                                });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }
                                });
                            }
                        });
                        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }
                }
                else
                {
                    Toast.makeText(profile_edit.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(profile_edit.this);

                    alertDialog.setTitle("Delete"); // Sets title for your alertbox

                    alertDialog.setMessage("Are you sure you want to Delete Account?");


                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
if(vapar!=null) {
    vapar.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            Toast.makeText(profile_edit.this, "Account Deleted Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(profile_edit.this, loginregister.class);
            startActivity(intent);
        }
    });
}
                        }
                    });
                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                }
                else
                {
                    Toast.makeText(profile_edit.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(profile_edit.this,slideactivity.class);
        startActivity(intent);
    }
}