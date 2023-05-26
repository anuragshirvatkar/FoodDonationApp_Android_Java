package com.example.food_wastage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class donation extends AppCompatActivity {
    RecyclerView recycle2;
    List<prodata> prolist2;
    FirebaseUser vapar2;
    DatabaseReference acc2;
    private String userId,abc;
    MyAdapter myadapter2;
    private DatabaseReference refrence2;
    private ValueEventListener eventListener2;
    prodata datap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        checkconnection();

        recycle2 = (RecyclerView) findViewById(R.id.recyclerView2);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycle2.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setReverseLayout(true);
        gridLayoutManager.setStackFromEnd(true);
        vapar2 = FirebaseAuth.getInstance().getCurrentUser();
        userId = vapar2.getUid();
        prolist2 = new ArrayList<>();

        MyAdapter2 a = new MyAdapter2(donation.this,prolist2);
        recycle2.setAdapter(a);
        refrence2 = FirebaseDatabase.getInstance().getReference("donationlist"+userId);
        eventListener2 = refrence2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prolist2.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    prodata dete = itemSnapshot.getValue(prodata.class);
                    prolist2.add(dete);

                }
                a.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(donation.this,slideactivity.class);
        startActivity(intent);
    }
    public void checkconnection(){
        ConnectivityManager manager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenetwork = manager.getActiveNetworkInfo();
        if(null!=activenetwork){
        }
        else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }
}