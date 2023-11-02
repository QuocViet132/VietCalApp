package com.example.vietcal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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

public class ExtensionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension);
        getDataFromIntent();
    }
    public void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            Extension extension = (Extension) bundle.get("nameExtension");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Log.e("Extension getName",extension.getNameExtension());
            switch (extension.getNameExtension()) {
                case "Hoá Đơn Tiền Điện":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new CalculateElectricityBillFragment()).addToBackStack(null).commit();
                    break;
                case "Hoá Đơn Tiền Nước":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new CalculateWaterBillFragment()).addToBackStack(null).commit();
                    break;
                case "Lương Gross -> Net":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new GrossToNetFragment()).addToBackStack(null).commit();
                    break;
                case "Lương Net -> Gross":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new NetToGrossFragment()).addToBackStack(null).commit();
                    break;
                case "Quy Đổi Tiền Tệ":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new CurrencyConvertFragment()).addToBackStack(null).commit();
                    break;
                case "Quy Đổi Nhiệt Độ":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new TemperatureConvertFragment()).addToBackStack(null).commit();
                    break;
                case "Quy Đổi Tốc Độ":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new SpeedConvertFragment()).addToBackStack(null).commit();
                    break;
                case "Tính BMI":
                    fragmentTransaction.replace(R.id.layout_frame_extension, new CalculateBmiFragment()).addToBackStack(ExtensionFragment.TAG).commit();
                    Log.e("Run Fragment Bmi","Done");
                    break;
            }
        }
    }
}