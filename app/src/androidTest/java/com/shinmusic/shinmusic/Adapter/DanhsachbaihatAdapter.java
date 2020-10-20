package com.shinmusic.shinmusic.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shinmusic.R;
import com.shinmusic.shinmusic.Activity.PlayNhacActivity;
import com.shinmusic.shinmusic.Model.BaiHat;

import java.util.ArrayList;

public class DanhsachbaihatAdapter extends  RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHat> mangbaihat;

    public DanhsachbaihatAdapter(@NonNull Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }
    @NonNull
    @Override
    //Khởi tạo khi RecyclerView được hiển thị lên, hình thành RecyclerView
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //Tương tac, gắn dữ liệu vào
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

        TextView txtindex,txttenbaihat,txtcasy;
        ImageView imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.textviewdsindex);
            txttenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
            txtcasy = itemView.findViewById(R.id.textviewtencasy);
            imgluotthich = itemView.findViewById(R.id.imageviewthichdsbaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("baihat",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }

}
