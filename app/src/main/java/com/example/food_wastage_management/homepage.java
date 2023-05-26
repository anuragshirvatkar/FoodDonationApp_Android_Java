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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class homepage extends AppCompatActivity {
EditText name,phone,house,street,city,state;
Button submit;
ImageButton profpic;
ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        submit =findViewById(R.id.button3);
        name=findViewById(R.id.editTextTextPersonName3);
        phone=findViewById(R.id.editTextTextPersonName4);
        house=findViewById(R.id.editTextTextPersonName7);
        street=findViewById(R.id.editTextTextPersonName8);
        city=findViewById(R.id.editTextTextPersonName5);
        state=findViewById(R.id.editTextTextPersonName6);
        p1 = findViewById(R.id.progressBar5);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetwork = manager.getActiveNetworkInfo();
                if(null!=activenetwork){

                    if(name.length()<2 || name.length()>50){
                        name.setError("Enter Proper Name");
                    }
                    if(phone.length()<10  || phone.length()>10){
                        phone.setError("Enter 10 digit number");
                    }
                    if(house.length()<3  || house.length()>40){
                        house.setError("Enter Proper Address");
                    }
                    if(street.length()<3  || street.length()>40){
                        street.setError("Enter Proper Address");
                    }
                    if(city.length()<3  || city.length()>20){
                        city.setError("Enter Proper Address");
                    }
                    if(state.length()<3  || state.length()>20){
                        state.setError("Enter Proper Address");
                    }
                    else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(homepage.this);

                        alertDialog.setTitle("Submit"); // Sets title for your alertbox

                        alertDialog.setMessage("Is information correct?");


                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                p1.setVisibility(View.VISIBLE);
                                String sname = name.getText().toString();
                                String sphone = phone.getText().toString();
                                String shouse = house.getText().toString();
                                String sstreet = street.getText().toString();
                                String scity = city.getText().toString();
                                String sstate = state.getText().toString();
                                String link = "https://firebasestorage.googleapis.com/v0/b/food-wastage-management-63d00.appspot.com/o/bbcc.jpg?alt=media&token=db95f8ff-87b8-4818-a540-49e5bd79f671";

                                users u = new users(sname, sphone, shouse, sstreet, scity, sstate, link);
                                FirebaseDatabase.getInstance().getReference().child("users")
                                        .child(FirebaseAuth.getInstance().getUid()).
                                        setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {


                                                    Toast.makeText(homepage.this, "Profile created!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(homepage.this, homepage2.class));

                                                } else {

                                                }
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
                    Toast.makeText(homepage.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(homepage.this,loginregister.class);
        startActivity(intent);
    }
}