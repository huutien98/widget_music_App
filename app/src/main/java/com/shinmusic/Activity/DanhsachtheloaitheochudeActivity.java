package com.shinmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shinmusic.Adapter.DanhsachtheloaitheochudeAdapter;
import com.shinmusic.Model.ChuDe;
import com.shinmusic.Model.TheLoai;
import com.shinmusic.R;
import com.shinmusic.Service.APIService;
import com.shinmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerView;
    Toolbar toolbar;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);
        GetIntent();
        init();
        getData();

    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback = dataservice.GetTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter = new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangtheloai);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this,2));//2 cột
                recyclerView.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerviewtheloaitheochude);
        toolbar = findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}
