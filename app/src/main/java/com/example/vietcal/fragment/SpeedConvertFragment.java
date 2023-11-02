package com.example.vietcal.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vietcal.R;

import java.text.DecimalFormat;

public class SpeedConvertFragment extends Fragment {
    private View view;
    private EditText etKmPerHour;
    private EditText etMPerSecond;
    private EditText etNauticalMilePerHour;
    private Button btnCalculate;
    private DecimalFormat decimalFormat = new DecimalFormat("0.######");

    public SpeedConvertFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_speed_convert, container, false);
        etKmPerHour = view.findViewById(R.id.et_km_per_h);
        etMPerSecond = view.findViewById(R.id.et_m_per_s);
        etNauticalMilePerHour = view.findViewById(R.id.et_nautical_mile);
        btnCalculate = view.findViewById(R.id.btn_calculate_speed);

        clickListener();
        return view;
    }

    private void clickListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            Double kmPerHour = 0.0;
            Double mPerSecond = 0.0;
            Double nauticalPerHour = 0.0;
            @Override
            public void onClick(View v) {
                if(!etKmPerHour.getText().toString().isEmpty()) {
                    kmPerHour = Double.parseDouble(etKmPerHour.getText().toString());
                    mPerSecond = kmPerHour * (1/3.6);
                    nauticalPerHour = kmPerHour / 1.852;

                    etMPerSecond.setText(decimalFormat.format(mPerSecond));
                    etNauticalMilePerHour.setText(decimalFormat.format(nauticalPerHour));
                }else if(!etMPerSecond.getText().toString().isEmpty()) {
                    mPerSecond = Double.parseDouble(etMPerSecond.getText().toString());
                    kmPerHour = mPerSecond / (1/3.6);
                    nauticalPerHour = kmPerHour / 1.852;

                    etKmPerHour.setText(decimalFormat.format(kmPerHour));
                    etNauticalMilePerHour.setText(decimalFormat.format(nauticalPerHour));
                }else if(!etNauticalMilePerHour.getText().toString().isEmpty()) {
                    nauticalPerHour = Double.parseDouble(etNauticalMilePerHour.getText().toString());
                    kmPerHour = nauticalPerHour * 1.852;
                    mPerSecond = kmPerHour * (1/3.6);

                    etKmPerHour.setText(decimalFormat.format(kmPerHour));
                    etMPerSecond.setText(decimalFormat.format(mPerSecond));
                }else {
                    Toast.makeText(view.getContext(),"Hãy nhập ít nhất 1 đơn vị để thực hiện quy đổi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}