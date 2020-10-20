package com.shinmusic.shinmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shinmusic.R;
import com.shinmusic.shinmusic.Activity.DanhsachtheloaitheochudeActivity;
import com.shinmusic.shinmusic.Model.ChuDe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachcacchudeAdapter extends RecyclerView.Adapter<DanhsachcacchudeAdapter.ViewHolder>{
    Context context;
    ArrayList<ChuDe> mangchude;

    public DanhsachcacchudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cacchude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = mangchude.get(position);
        Picasso.with(context).load(chuDe.getImageChuDe()).into(holder.imgchude);
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             imgchude = itemView.findViewById(R.id.imageviewcacchude);
             imgchude.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                     intent.putExtra("chude",mangchude.get(getPosition()));
                     context.startActivity(intent);
                 }
             });
         }
     }
}
