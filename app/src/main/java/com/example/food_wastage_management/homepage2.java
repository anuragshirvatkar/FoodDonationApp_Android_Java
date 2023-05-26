package com.example.food_wastage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class homepage2 extends AppCompatActivity {
public CircleImageView f1;

    CircleImageView i1,slide;
    RecyclerView recycle;
    List<prodata> prolist;
    EditText txt;
    FirebaseUser vapar;
    DatabaseReference acc2;
    private String userId,abc;
    ProgressBar p1;
    MyAdapter myadapter;
    private DatabaseReference refrence;
    private ValueEventListener eventListener;
    prodata datap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
checkconnection();
        setContentView(R.layout.activity_homepage2);

        f1=findViewById(R.id.floatingActionButton);
        txt = (EditText)findViewById(R.id.search);
        recycle = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycle.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setReverseLayout(true);
        gridLayoutManager.setStackFromEnd(true);
p1 = findViewById(R.id.progressBar61);
        slide=findViewById(R.id.imageButton9);
        vapar = FirebaseAuth.getInstance().getCurrentUser();
        userId = vapar.getUid();
        acc2 = FirebaseDatabase.getInstance().getReference().child("users");
        prolist = new ArrayList<>();
        myadapter = new MyAdapter(homepage2.this, prolist);
        recycle.setAdapter(myadapter);
        refrence = FirebaseDatabase.getInstance().getReference("products");

        acc2.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                users profile = snapshot.getValue(users.class);
                abc = profile.slink;
                Glide.with(homepage2.this).load(abc).into(slide);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
f1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(homepage2.this, upload.class));
    }

});
        eventListener = refrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prolist.clear();

                for(DataSnapshot itemSnapshot: snapshot.getChildren()){

                    prodata dete = itemSnapshot.getValue(prodata.class);
                    prolist.add(dete);
                    p1.setVisibility(View.INVISIBLE);
                }
                myadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    startActivity(new Intent(homepage2.this, slideactivity.class));


            }
        });
    }

    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(homepage2.this);
        alertDialog.setTitle("Exit"); // Sets title for your alertbox
        alertDialog.setMessage("Do you really want to exit app ?"); // Message to be displayed on alertbox
        /* When positive (yes/ok) is clicked */

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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

    private void filter(String text){
        ArrayList<prodata> filterlist = new ArrayList<>();
        for(prodata item:prolist){
            if(item.getPname().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(item);

            }
        }
        myadapter.filteredlist(filterlist);
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
