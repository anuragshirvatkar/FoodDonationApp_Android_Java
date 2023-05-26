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

public class MyAdapter2 extends RecyclerView.Adapter<productviewholder2> {
    private Context pcontext2;
    private List<prodata> prolist2;


    public MyAdapter2(Context pcontext2, List<prodata> prolist2) {
        this.pcontext2 = pcontext2;
        this.prolist2 = prolist2;
    }

    @Override
    public productviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.donationxml,parent,false);
        return new productviewholder2(mView2);
    }

    @Override
    public void onBindViewHolder(@NonNull productviewholder2 holder, int position) {
        Glide.with(pcontext2).load(prolist2.get(position).getImageurll()).into(holder.foodpic);
        Glide.with(pcontext2).load(prolist2.get(position).getPhotokey()).into(holder.z1);
        holder.ztitle.setText(prolist2.get(position).getPname());
        holder.zdes.setText(prolist2.get(position).getPdes());
        holder.zaddre.setText(prolist2.get(position).getPmail());
        holder.zname.setText(prolist2.get(position).getDname());
        holder.zph.setText(prolist2.get(position).getPprice());
        holder.zdel.setText(prolist2.get(position).getKey());

        holder.mccard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pcontext2,detaildonation.class);
                intent.putExtra("Image",prolist2.get(holder.getAdapterPosition()).getImageurll());
                intent.putExtra("Image2",prolist2.get(holder.getAdapterPosition()).getPhotokey());
                intent.putExtra("Name",prolist2.get(holder.getAdapterPosition()).getDname());
                intent.putExtra("Phone",prolist2.get(holder.getAdapterPosition()).getPprice());
                intent.putExtra("Food Name",prolist2.get(holder.getAdapterPosition()).getPname());
                intent.putExtra("Food Des",prolist2.get(holder.getAdapterPosition()).getPdes());
                intent.putExtra("Food Add",prolist2.get(holder.getAdapterPosition()).getPmail());
                intent.putExtra("Key",prolist2.get(holder.getAdapterPosition()).getKey());
                pcontext2.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return prolist2.size();
    }

    public void filteredlist2(ArrayList<prodata> filterlist2) {
        prolist2 = filterlist2;
        notifyDataSetChanged();
    }
}

class  productviewholder2 extends RecyclerView.ViewHolder {
    CircleImageView z1;
    ImageView foodpic;
    TextView ztitle,zdes,zph,zaddre,zname,zdel;
    CardView mccard;


    public productviewholder2( View itemView) {
        super(itemView);

        z1 = itemView.findViewById(R.id.dabc1);
        foodpic = itemView.findViewById(R.id.divmage);
        zname= itemView.findViewById(R.id.dinstaid);
        zph=itemView.findViewById(R.id.dphonenum7);
        ztitle = itemView.findViewById(R.id.dnameoffood);
        zdes = itemView.findViewById(R.id.ddesoffood);
        zaddre = itemView.findViewById(R.id.dadsoffood);
        mccard = itemView.findViewById(R.id.dmycard);
        zdel = itemView.findViewById(R.id.keyofdeletin);


    }
}
