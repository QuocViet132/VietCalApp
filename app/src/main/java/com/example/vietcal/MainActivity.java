package com.example.vietcal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.vietcal.fragment.CalculateBmiFragment;
import com.example.vietcal.fragment.CalculateElectricityBillFragment;
import com.example.vietcal.fragment.CalculateWaterBillFragment;
import com.example.vietcal.fragment.CurrencyConvertFragment;
import com.example.vietcal.fragment.GrossToNetFragment;
import com.example.vietcal.fragment.NetToGrossFragment;
import com.example.vietcal.fragment.SpeedConvertFragment;
import com.example.vietcal.fragment.TemperatureConvertFragment;
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

    public void goToExtensionActivity(Extension extension) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (extension.getNameExtension()) {
            case "Hoá Đơn Tiền Điện":
                fragmentTransaction.add(R.id.layout_main_activity, new CalculateElectricityBillFragment()).commit();
                break;
            case "Hoá Đơn Tiền Nước":
                fragmentTransaction.add(R.id.layout_main_activity, new CalculateWaterBillFragment()).commit();
                break;
            case "Lương Gross -> Net":
                fragmentTransaction.add(R.id.layout_main_activity, new GrossToNetFragment()).commit();
                break;
            case "Lương Net -> Gross":
                fragmentTransaction.add(R.id.layout_main_activity, new NetToGrossFragment()).commit();
                break;
            case "Quy Đổi Tiền Tệ":
                fragmentTransaction.add(R.id.layout_main_activity, new CurrencyConvertFragment()).commit();
                break;
            case "Quy Đổi Nhiệt Độ":
                fragmentTransaction.add(R.id.layout_main_activity, new TemperatureConvertFragment()).commit();
                break;
            case "Quy Đổi Tốc Độ":
                fragmentTransaction.add(R.id.layout_main_activity, new SpeedConvertFragment()).commit();
                break;
            case "Tính BMI":
                fragmentTransaction.add(R.id.layout_main_activity, new CalculateBmiFragment()).commit();
                break;
        }
    }
}