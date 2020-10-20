package com.shinmusic.shinmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.shinmusic.R;
import com.shinmusic.shinmusic.Activity.DanhsachbaihatActivity;
import com.shinmusic.shinmusic.Activity.DanhsachtatcachudeActivity;
import com.shinmusic.shinmusic.Activity.DanhsachtheloaitheochudeActivity;
import com.shinmusic.shinmusic.Model.ChuDe;
import com.shinmusic.shinmusic.Model.TheLoai;
import com.shinmusic.shinmusic.Model.TheLoaiTrongNgay;
import com.shinmusic.shinmusic.Service.APIService;
import com.shinmusic.shinmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDe_TheLoai_ToDay extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        txtxemthemchudetheloai = view.findViewById(R.id.textviewxemthem);
        txtxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return  view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<TheLoaiTrongNgay> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoaiTrongNgay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,250);
                layout.setMargins(10,20,10,30);

                for (int i=0; i <(chuDeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getImageChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getImageChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for (int j=0; j <(theLoaiArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getImageTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getImageTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }
}
