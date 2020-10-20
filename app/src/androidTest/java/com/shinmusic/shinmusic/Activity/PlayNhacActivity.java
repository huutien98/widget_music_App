package com.shinmusic.shinmusic.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.shinmusic.R;
import com.shinmusic.shinmusic.Adapter.ViewPagerPlaylistNhacAdapter;
import com.shinmusic.shinmusic.Fragment.Fragment_Dianhac;
import com.shinmusic.shinmusic.Fragment.Fragment_Play_Danhsach_Baihat;
import com.shinmusic.shinmusic.Model.BaiHat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txttimesong,txttotaltimesong;
    SeekBar seektime;
    ImageButton imgrandom, imgre,imgplay,imgnext,imgrepeat;
    ViewPager viewPagerplaynhac;
    public  static  ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public  static ViewPagerPlaylistNhacAdapter viewPagerPlaylistNhacAdapter;
    Fragment_Dianhac fragment_dianhac;
    Fragment_Play_Danhsach_Baihat fragment_play_danhsach_baihat;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(viewPagerPlaylistNhacAdapter.getItem(1) != null){
                    if (mangbaihat.size() > 0){
                        fragment_dianhac.Playnhac(mangbaihat.get(0).getImageBaiHat());
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null){
            if (intent.hasExtra("baihat")){
                BaiHat baiHat = intent.getParcelableExtra("baihat");
                mangbaihat.add(baiHat);
            }

            if (intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baiHatArrayList;
            }
        }

    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.textviewtimesong);
        txttotaltimesong = findViewById(R.id.textviewtotaltimesong);
        seektime = findViewById(R.id.seekbarsong);
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgre = findViewById(R.id.imagebuttonplaybefore);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgnext = findViewById(R.id.imagebuttonplaynext);
        imgrepeat = findViewById(R.id.imagebuttonplayrepeat);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);

        fragment_dianhac = new Fragment_Dianhac();
        fragment_play_danhsach_baihat = new Fragment_Play_Danhsach_Baihat();
        viewPagerPlaylistNhacAdapter = new ViewPagerPlaylistNhacAdapter(getSupportFragmentManager());
        viewPagerPlaylistNhacAdapter.AddFragment(fragment_play_danhsach_baihat);
        viewPagerPlaylistNhacAdapter.AddFragment(fragment_dianhac);
        viewPagerplaynhac.setAdapter(viewPagerPlaylistNhacAdapter);
        fragment_dianhac = (Fragment_Dianhac) viewPagerPlaylistNhacAdapter.getItem(1);
        if (mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getDuongDan());
            imgplay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seektime.setMax(mediaPlayer.getDuration());
    }

}
