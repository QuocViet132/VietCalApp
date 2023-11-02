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

public class TemperatureConvertFragment extends Fragment {
    private View view;
    private EditText etCelsius;
    private EditText etKelvin;
    private EditText etFahrenheit;
    private Button btnCalculate;
    private Button btnDelete;
    private Double celsius;
    private Double kelvin;
    private Double fahrenheit;
    DecimalFormat decimalFormat = new DecimalFormat("0.######");
    public TemperatureConvertFragment() {
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
        view = inflater.inflate(R.layout.fragment_temperature_convert, container, false);
        etCelsius = view.findViewById(R.id.et_celsius);
        etKelvin = view.findViewById(R.id.et_kelvin);
        etFahrenheit = view.findViewById(R.id.et_fahrenheit);
        btnCalculate = view.findViewById(R.id.btn_calculate_temperature);
        btnDelete = view.findViewById(R.id.btn_delete_temperature);

        clickListener();
        return view;
    }

    private void clickListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etCelsius.getText().toString().isEmpty()) {
                    celsius = Double.parseDouble(etCelsius.getText().toString());
                    kelvin = celsius + 273.15;
                    fahrenheit = celsius*1.8+32;

                    etKelvin.setText(decimalFormat.format(kelvin));
                    etFahrenheit.setText(decimalFormat.format(fahrenheit));
                }else if(!etKelvin.getText().toString().isEmpty()) {
                    kelvin = Double.parseDouble(etKelvin.getText().toString());
                    celsius = kelvin - 273.15;
                    fahrenheit = celsius*1.8+32;

                    etCelsius.setText(decimalFormat.format(celsius));
                    etFahrenheit.setText(decimalFormat.format(fahrenheit));
                }else if(!etFahrenheit.getText().toString().isEmpty()) {
                    fahrenheit = Double.parseDouble(etFahrenheit.getText().toString());
                    celsius = (float) (5/9) * (fahrenheit-32);
                    kelvin = celsius + 273.15;

                    etCelsius.setText(decimalFormat.format(celsius));
                    etKelvin.setText(decimalFormat.format(kelvin));
                }else {
                    Toast.makeText(view.getContext(),"Hãy nhập ít nhất 1 đơn vị để thực hiện quy đổi",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCelsius.setText("");
                etKelvin.setText("");
                etFahrenheit.setText("");
            }
        });
    }
}