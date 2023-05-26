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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class detaildonation extends AppCompatActivity {
    CircleImageView c1;
    ImageView i;
    TextView t1, t2, t3, t4, t5, t6;
    Button b1;
    String ke2, ke3;
    FirebaseUser vapar;
    String userId, name = "";
    DatabaseReference acc2, acc3, acc4;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildonation);
        p1 = findViewById(R.id.progressBar8);
        c1 = findViewById(R.id.fooditembigd);
        i = findViewById(R.id.imageView36d);
        t1 = findViewById(R.id.textView6d);
        t2 = findViewById(R.id.textView7d);
        t3 = findViewById(R.id.textView8d);
        t4 = findViewById(R.id.textView9d);
        t5 = findViewById(R.id.textView10d);
        t6 = findViewById(R.id.textView11);
        b1 = findViewById(R.id.button7d);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");

        acc3 = FirebaseDatabase.getInstance().getReference("donationlist" + FirebaseAuth.getInstance().getUid());
        acc4 = FirebaseDatabase.getInstance().getReference("products");
        userId = vapar.getUid();


        t6.setText(name);
        Bundle mbundle = getIntent().getExtras();
        if (mbundle != null) {
            t1.setText(mbundle.getString("Name"));
            t2.setText(mbundle.getString("Phone"));
            t3.setText(mbundle.getString("Food Name"));
            t4.setText(mbundle.getString("Food Des"));
            t5.setText(mbundle.getString("Food Add"));
            Glide.with(this).load(mbundle.getString("Image2")).into(c1);
            Glide.with(this).load(mbundle.getString("Image")).into(i);
            ke2 = mbundle.getString("Image2");
            ke3 = mbundle.getString("Image");
            name = mbundle.getString("Key");

        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(detaildonation.this);

                    alertDialog.setTitle("Delete"); // Sets title for your alertbox

                    alertDialog.setMessage("Are you sure you want to Delete Donation?");


                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            acc3.child(name).removeValue();
                            Toast.makeText(detaildonation.this, "Your Donation has been Deleted!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), donation.class));
                            delete2();
                            finish();
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
                    Toast.makeText(detaildonation.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void delete2() {

        acc4.child(name).removeValue();
        Toast.makeText(detaildonation.this, "Your Donation has been Deleted!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), donation.class));
        finish();
    }
}
