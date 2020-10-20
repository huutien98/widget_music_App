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
import com.shinmusic.shinmusic.Activity.DanhsachbaihatActivity;
import com.shinmusic.shinmusic.Model.TheLoai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> mangtheloai;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<TheLoai> mangtheloai) {
        this.context = context;
        this.mangtheloai = mangtheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloaitheochude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = mangtheloai.get(position);
        Picasso.with(context).load(theLoai.getImageTheLoai()).into(holder.imghinhnen);
        holder.txttentheloai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mangtheloai.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txttentheloai = itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("idtheloai",mangtheloai.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
