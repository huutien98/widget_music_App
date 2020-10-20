package com.shinmusic.shinmusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shinmusic.R;
import com.shinmusic.shinmusic.Model.BaiHat;

import java.util.ArrayList;

public class PlaynhacAdapter extends  RecyclerView.Adapter<PlaynhacAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public PlaynhacAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_playbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txtindex.setText(position + 1 + "");
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txtcasy.setText(baiHat.getCaSy());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex, txttenbaihat, txtcasy;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
             txttenbaihat = itemView.findViewById(R.id.textviewplaynhactenbaihat);
             txtcasy = itemView.findViewById(R.id.textviewplaynhactencasy);

         }
     }
}
