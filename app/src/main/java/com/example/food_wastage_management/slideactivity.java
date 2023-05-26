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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class slideactivity extends AppCompatActivity {
CircleImageView c1;
TextView t1,t2;
    FirebaseUser vapar;
    DatabaseReference acc2;
    private String userId,name,number,url;
    Button donation,receiption,logout,aboutus;
    FirebaseAuth f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideactivity);
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.yellow));

        c1=findViewById(R.id.loadface);
        t1=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView4);
        f1=FirebaseAuth.getInstance();
        donation = findViewById(R.id.button8);
        receiption = findViewById(R.id.button9);
        logout = findViewById(R.id.button11);
        aboutus = findViewById(R.id.button10);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        userId = vapar.getUid();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        acc2.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users profile = snapshot.getValue(users.class);

                name = profile.sname;
                t1.setText(name);
                number = profile.sphone;
                t2.setText(number);
                url = profile.slink;
                Glide.with(slideactivity.this).load(url).into(c1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    startActivity(new Intent(slideactivity.this, com.example.food_wastage_management.donation.class));
                }
                else
                {
                    Toast.makeText(slideactivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                   openDialog();
                }
                else
                {
                    Toast.makeText(slideactivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        receiption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    startActivity(new Intent(slideactivity.this, com.example.food_wastage_management.receiption.class));
                }
                else
                {
                    Toast.makeText(slideactivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }

        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(slideactivity.this, com.example.food_wastage_management.aboutus.class));
            }
        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    startActivity(new Intent(slideactivity.this,profile_edit.class));
                }
                else
                {
                    Toast.makeText(slideactivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    startActivity(new Intent(slideactivity.this,profile_edit.class));
                }
                else
                {
                    Toast.makeText(slideactivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    startActivity(new Intent(slideactivity.this,profile_edit.class));
                }
                else
                {
                    Toast.makeText(slideactivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void onBackPressed() {
        Intent intent = new Intent(slideactivity.this,homepage2.class);
        startActivity(intent);
    }

        public void openDialog()
        {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(slideactivity.this);

            alertDialog.setTitle("Logout"); // Sets title for your alertbox

            alertDialog.setMessage("Are you sure you want to Logout ?"); // Message to be displayed on alertbox



            /* When positive (yes/ok) is clicked */
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {

                    f1.signOut();
                    Intent intent=new Intent(slideactivity.this,loginregister.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(slideactivity.this,"Successfully Logged Out", Toast.LENGTH_LONG).show();
                }
            });

            /* When negative (No/cancel) button is clicked*/
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }

}