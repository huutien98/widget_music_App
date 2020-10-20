package com.shinmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shinmusic.Activity.PlayNhacActivity;
import com.shinmusic.Model.BaiHat;
import com.shinmusic.R;
import com.shinmusic.Service.APIService;
import com.shinmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBaihatAdapter extends RecyclerView.Adapter<SearchBaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> mangbaihat;

    public SearchBaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_searchbaihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = mangbaihat.get(position);
        holder.txttenbaihat.setText(baiHat.getTenBaiHat());
        holder.txttencasy.setText(baiHat.getCaSy());
        Picasso.with(context).load(baiHat.getDuongDan()).into(holder.imgbaihat);
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttenbaihat,txttencasy;
        ImageView imgbaihat, imgthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenbaihat = itemView.findViewById(R.id.textviewsearchtenbaihat);
            txttencasy = itemView.findViewById(R.id.textviewsearchtencasy);
            imgbaihat = itemView.findViewById(R.id.imageviewsearchbaihat);
            imgthich = itemView.findViewById(R.id.imageviewsearchluotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("baihat",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", mangbaihat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("success")){
                                Toast.makeText(context,"Đã thích", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context,"Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgthich.setEnabled(false);
                }
            });
        }
    }
}
