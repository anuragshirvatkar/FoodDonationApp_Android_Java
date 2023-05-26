package com.example.food_wastage_management;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter3 extends RecyclerView.Adapter<productviewholder3> {
    private Context pcontext;
    private List<prodata> prolist;


    public MyAdapter3(Context pcontext, List<prodata> prolist3) {
        this.pcontext = pcontext;
        this.prolist = prolist3;
    }

    @Override
    public productviewholder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiptionxml,parent,false);
        return new productviewholder3(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull productviewholder3 holder, int position) {
        Glide.with(pcontext).load(prolist.get(position).getImageurll()).into(holder.foodpic);
        Glide.with(pcontext).load(prolist.get(position).getPhotokey()).into(holder.z1);
        holder.ztitle.setText(prolist.get(position).getPdes());
        holder.zdes.setText(prolist.get(position).getPprice());
        holder.zaddre.setText(prolist.get(position).getPmail());

        holder.zname.setText(prolist.get(position).getDname());
        holder.zph.setText(prolist.get(position).getPname());
        holder.zdel.setText(prolist.get(position).getKey());

        holder.mccard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pcontext,detailreceiption.class);
                intent.putExtra("Image",prolist.get(holder.getAdapterPosition()).getImageurll());
                intent.putExtra("Image2",prolist.get(holder.getAdapterPosition()).getPhotokey());
                intent.putExtra("Name",prolist.get(holder.getAdapterPosition()).getDname());
                intent.putExtra("Phone",prolist.get(holder.getAdapterPosition()).getPprice());
                intent.putExtra("Food Name",prolist.get(holder.getAdapterPosition()).getPname());
                intent.putExtra("Food Des",prolist.get(holder.getAdapterPosition()).getPdes());
                intent.putExtra("Food Add",prolist.get(holder.getAdapterPosition()).getPmail());
                intent.putExtra("Key",prolist.get(holder.getAdapterPosition()).getKey());
                pcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return prolist.size();
    }

    public void filteredlist(ArrayList<prodata> filterlist) {
        prolist = filterlist;
        notifyDataSetChanged();
    }
}

class  productviewholder3 extends RecyclerView.ViewHolder {
    CircleImageView z1;
    ImageView foodpic;
    TextView ztitle,zdes,zph,zaddre,zname,zdel;
    CardView mccard;


    public productviewholder3( View itemView) {
        super(itemView);

        z1 = itemView.findViewById(R.id.rabc1);
        foodpic = itemView.findViewById(R.id.rivmage);
        zname= itemView.findViewById(R.id.rinstaid);
        zph=itemView.findViewById(R.id.rphonenum7);
        ztitle = itemView.findViewById(R.id.rnameoffood);
        zdes = itemView.findViewById(R.id.rdesoffood);
        zaddre = itemView.findViewById(R.id.radsoffood);
        mccard = itemView.findViewById(R.id.rmycard);
        zdel = itemView.findViewById(R.id.keyofdeletin2);


    }
}

