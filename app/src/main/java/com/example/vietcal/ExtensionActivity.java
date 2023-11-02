package com.example.vietcal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

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

        getObjectExtension();

    }

    private void getObjectExtension() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Extension extension = (Extension) bundle.get("extension");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(extension != null) {
            switch (extension.getNameExtension()) {
                case "Hoá Đơn Tiền Điện":
                    fragmentTransaction.add(R.id.layout_extension_activity, new CalculateElectricityBillFragment()).commit();
                    break;
                case "Hoá Đơn Tiền Nước":
                    fragmentTransaction.add(R.id.layout_extension_activity, new CalculateWaterBillFragment()).commit();
                    break;
                case "Lương Gross -> Net":
                    fragmentTransaction.add(R.id.layout_extension_activity, new GrossToNetFragment()).commit();
                    break;
                case "Lương Net -> Gross":
                    fragmentTransaction.add(R.id.layout_extension_activity, new NetToGrossFragment()).commit();
                    break;
                case "Quy Đổi Tiền Tệ":
                    fragmentTransaction.add(R.id.layout_extension_activity, new CurrencyConvertFragment()).commit();
                    break;
                case "Quy Đổi Nhiệt Độ":
                    fragmentTransaction.add(R.id.layout_extension_activity, new TemperatureConvertFragment()).commit();
                    break;
                case "Quy Đổi Tốc Độ":
                    fragmentTransaction.add(R.id.layout_extension_activity, new SpeedConvertFragment()).commit();
                    break;
                case "Tính BMI":
                    fragmentTransaction.add(R.id.layout_extension_activity, new CalculateBmiFragment()).commit();
                    break;
            }
        }
    }
}