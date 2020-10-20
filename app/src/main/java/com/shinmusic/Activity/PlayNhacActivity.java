package com.shinmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
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
import androidx.appcompat.widget.Toolbar;

import com.shinmusic.Adapter.ViewPagerPlaylistNhacAdapter;
import com.shinmusic.Fragment.Fragment_Dianhac;
import com.shinmusic.Fragment.Fragment_Play_Danhsach_Baihat;
import com.shinmusic.Model.BaiHat;
import com.shinmusic.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txttimesong,txttotaltimesong;
    SeekBar seektime;
    ImageButton imgrandom, imgpre,imgplay,imgnext,imgrepeat;
    ViewPager viewPagerplaynhac;
    public  static  ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public  static ViewPagerPlaylistNhacAdapter viewPagerPlaylistNhacAdapter;
    Fragment_Dianhac fragment_dianhac;
    Fragment_Play_Danhsach_Baihat fragment_play_danhsach_baihat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

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
                    if (fragment_dianhac.objectAnimator !=null){
                        fragment_dianhac.objectAnimator.pause();
                    }
                }
                else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if (fragment_dianhac.objectAnimator!=null){
                        fragment_dianhac.objectAnimator.resume();
                    }
                }

            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false){
                    if (checkrandom == true){
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }
                else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }
                else {
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        seektime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < mangbaihat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = mangbaihat.size();
                            }
                            position -=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }
                        if (position > (mangbaihat.size()) - 1){
                            position = 0;
                        }
                         new PlayMp3().execute(mangbaihat.get(position).getDuongDan());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getImageBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangbaihat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < mangbaihat.size()){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0){
                            position = mangbaihat.size() - 1;
                        }
                        if (repeat == true){
                            position +=1;
                        }
                        if (checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position){
                                position = index -1;
                            }
                            position = index;
                        }

                        new PlayMp3().execute(mangbaihat.get(position).getDuongDan());
                        fragment_dianhac.Playnhac(mangbaihat.get(position).getImageBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
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
        imgpre = findViewById(R.id.imagebuttonplaybefore);
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
                mediaPlayer.stop();
                mangbaihat.clear();
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
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seektime.setMax(mediaPlayer.getDuration());
    }

    private  void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seektime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    final Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (next == true){
                                if (mangbaihat.size() > 0){
                                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                                        mediaPlayer.stop();
                                        mediaPlayer.release();
                                        mediaPlayer = null;
                                    }
                                    if (position < mangbaihat.size()){
                                        imgplay.setImageResource(R.drawable.iconpause);
                                        position--;

                                        if (position < 0){
                                            position = mangbaihat.size() - 1;
                                        }
                                        if (repeat == true){
                                            position +=1;
                                        }
                                        if (checkrandom == true){
                                            Random random = new Random();
                                            int index = random.nextInt(mangbaihat.size());
                                            if (index == position){
                                                position = index -1;
                                            }
                                            position = index;
                                        }

                                        new PlayMp3().execute(mangbaihat.get(position).getDuongDan());
                                        fragment_dianhac.Playnhac(mangbaihat.get(position).getImageBaiHat());
                                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                                        UpdateTime();
                                    }
                                }
                                imgpre.setClickable(false);
                                imgnext.setClickable(false);
                                Handler handler1 = new Handler();
                                handler1.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        imgpre.setClickable(true);
                                        imgnext.setClickable(true);
                                    }
                                },5000);
                                next = false;
                                handler1.removeCallbacks(this);
                            } else {
                                handler1.postDelayed(this,1000);
                            }
                        }
                    },1000);
                }
            }
        },300);
    }

}
