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

public class receiption extends AppCompatActivity {
    RecyclerView recycle;
    List<prodata> prolist3;
    FirebaseUser vapar;
    DatabaseReference acc2;
    private String userId,abc;
    MyAdapter myadapter;
    private DatabaseReference refrence;
    private ValueEventListener eventListener;
    prodata datap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiption);
        checkconnection();

        recycle = (RecyclerView) findViewById(R.id.recyclerView3);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycle.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setReverseLayout(true);
        gridLayoutManager.setStackFromEnd(true);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        userId = vapar.getUid();
        prolist3 = new ArrayList<>();
        MyAdapter3 a = new MyAdapter3(receiption.this,prolist3);
        recycle.setAdapter(a);
        refrence = FirebaseDatabase.getInstance().getReference("food_taken"+userId);
        eventListener = refrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prolist3.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    prodata dete = itemSnapshot.getValue(prodata.class);
                    prolist3.add(dete);

                }
               a.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent(receiption.this,slideactivity.class);
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
