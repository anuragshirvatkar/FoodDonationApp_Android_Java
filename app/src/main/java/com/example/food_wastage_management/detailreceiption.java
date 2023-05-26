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

public class detailreceiption extends AppCompatActivity {
    CircleImageView c1;
    ImageView i;
    TextView t1,t2,t3,t4,t5,t6;
    Button b1;
    String ke2,ke3;
    FirebaseUser vapar;
    private String userId;
    DatabaseReference acc3;
    String name="";
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p1 = findViewById(R.id.progressBar33);
        setContentView(R.layout.activity_detailreceiption);
        c1 = findViewById(R.id.fooditembigr);
        i = findViewById(R.id.imageView36r);
        t1 = findViewById(R.id.textView6r);
        t2 = findViewById(R.id.textView7r);
        t3 = findViewById(R.id.textView8r);
        t4 = findViewById(R.id.textView9r);
        t5 = findViewById(R.id.textView10r);
        b1 = findViewById(R.id.button7r);
        t6 = findViewById(R.id.textView12);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        acc3 = FirebaseDatabase.getInstance().getReference("food_taken"+FirebaseAuth.getInstance().getUid());
        userId = vapar.getUid();

        Bundle mbundle = getIntent().getExtras();
        if(mbundle!=null){
            t1.setText(mbundle.getString("Name"));
            t2.setText(mbundle.getString("Phone"));
            t3.setText(mbundle.getString("Food Name"));
            t4.setText(mbundle.getString("Food Des"));
            t5.setText(mbundle.getString("Food Add"));
            t6.setText(mbundle.getString("Key"));
            Glide.with(this).load(mbundle.getString("Image2")).into(c1);
            Glide.with(this).load(mbundle.getString("Image")).into(i);
            ke2 = mbundle.getString("Image2");
            ke3 = mbundle.getString("Image");


        }
name=t6.getText().toString();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(detailreceiption.this);

                    alertDialog.setTitle("Delete"); // Sets title for your alertbox

                    alertDialog.setMessage("Are you sure you want to Delete Data?");


                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            acc3.child(name).removeValue();

                            Toast.makeText(detailreceiption.this,"Your Data is Deleted!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),receiption.class));
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
                    Toast.makeText(detailreceiption.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }


        });
    }
}