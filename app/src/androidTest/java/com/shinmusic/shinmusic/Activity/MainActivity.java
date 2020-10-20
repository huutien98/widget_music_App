package com.shinmusic.shinmusic.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.shinmusic.R;
import com.shinmusic.shinmusic.Adapter.MainViewPagerAdapter;
import com.shinmusic.shinmusic.Fragment.Fragment_Ca_Nhan;
import com.shinmusic.shinmusic.Fragment.Fragment_Them;
import com.shinmusic.shinmusic.Fragment.Fragment_Trang_Chu;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(),"Trang chủ");
        mainViewPagerAdapter.addFragment(new Fragment_Ca_Nhan(),"Cá nhân");
        mainViewPagerAdapter.addFragment(new Fragment_Them(),"Thêm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //set icon cho tabLayout
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconcanhan);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconthem);

    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
