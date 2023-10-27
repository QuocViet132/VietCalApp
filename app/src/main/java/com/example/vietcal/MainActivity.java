package com.example.vietcal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager2 = findViewById(R.id.view_pager);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager2.setAdapter(mViewPagerAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 1:
                        tab.setText("TIỆN ÍCH");
                        break;
                    case 0:
                    default:
                        tab.setText("MÁy TÍNH");
                }
            }
        }).attach();
    }
}