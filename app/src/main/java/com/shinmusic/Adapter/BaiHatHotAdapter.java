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

public class BaiHatHotAdapter extends  RecyclerView.Adapter<BaiHatHotAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> baiHatYeuThichArrayList;

    public BaiHatHotAdapter(Context context, ArrayList<BaiHat> baiHatYeuThichArrayList) {
        this.context = context;
        this.baiHatYeuThichArrayList = baiHatYeuThichArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHatYeuThich = baiHatYeuThichArrayList.get(position);
        holder.txtcasy.setText(baiHatYeuThich.getCaSy());
        holder.txtten.setText(baiHatYeuThich.getTenBaiHat());
        Picasso.with(context).load(baiHatYeuThich.getImageBaiHat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baiHatYeuThichArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten,txtcasy;
        ImageView imghinh,imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewtenbaihathot);
            txtcasy = itemView.findViewById(R.id.textviewcasybaihathot);
            imghinh = itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich = itemView.findViewById(R.id.imageviewlotthich);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("baihat",baiHatYeuThichArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", baiHatYeuThichArrayList.get(getPosition()).getIdBaiHat());
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
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
