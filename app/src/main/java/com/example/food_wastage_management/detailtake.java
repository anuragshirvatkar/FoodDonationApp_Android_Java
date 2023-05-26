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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class detailtake extends AppCompatActivity {
CircleImageView c1;
ImageView i;
TextView t1,t2,t3,t4,t5;
Button b1;
String ke2,ke3;
    FirebaseUser vapar;
    private String userId;
    DatabaseReference acc2;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtake);
        p1 = findViewById(R.id.progressBar2);
        c1 = findViewById(R.id.fooditembig);
        i = findViewById(R.id.imageView36);
        t1 = findViewById(R.id.textView6);
        t2 = findViewById(R.id.textView7);
        t3 = findViewById(R.id.textView8);
        t4 = findViewById(R.id.textView9);
        t5 = findViewById(R.id.textView10);
        b1 = findViewById(R.id.button7);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        userId = vapar.getUid();
        Bundle mbundle = getIntent().getExtras();
        if(mbundle!=null){
            t1.setText(mbundle.getString("Name"));
            t2.setText(mbundle.getString("Phone"));
            t3.setText(mbundle.getString("Food Name"));
            t4.setText(mbundle.getString("Food Des"));
            t5.setText(mbundle.getString("Food Add"));
            Glide.with(this).load(mbundle.getString("Image2")).into(c1);
            Glide.with(this).load(mbundle.getString("Image")).into(i);
            ke2 = mbundle.getString("Image2");
            ke3 = mbundle.getString("Image");

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(detailtake.this);

                    alertDialog.setTitle("Delete"); // Sets title for your alertbox

                    alertDialog.setMessage("Are you sure you want to Take Donation?");


                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            p1.setVisibility(View.VISIBLE);
                            take();
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
                    Toast.makeText(detailtake.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }





        });
    }
    String myCurrentDateTime = DateFormat.getDateTimeInstance()
            .format(Calendar.getInstance().getTime());
    public void take(){
        prodata buy = new prodata(ke2,
                t1.getText().toString(),
                t2.getText().toString(),
                t3.getText().toString(),
                t4.getText().toString(),
                t5.getText().toString(),
                ke3,
                myCurrentDateTime
        );
        FirebaseDatabase.getInstance().getReference().child("food_taken"+FirebaseAuth.getInstance().getUid()).child(myCurrentDateTime)
                .setValue(buy).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(detailtake.this,"You successfully Taken food",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(detailtake.this,homepage2.class));
                        }
                        else{
                            Toast.makeText(detailtake.this,"Something went wrong",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}