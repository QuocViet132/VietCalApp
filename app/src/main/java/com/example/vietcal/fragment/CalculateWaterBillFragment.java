package com.example.vietcal.fragment;

import android.icu.number.FormattedNumber;
import android.icu.text.NumberFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietcal.R;

import java.util.Locale;

public class CalculateWaterBillFragment extends Fragment {
    private View view;
    private Button btnCalculate;
    private Button btnDelete;
    private EditText etStartNumber;
    private EditText etEndNumber;
    private EditText etTotalNumber;
    private TextView tvResultAmountTotal;
    private int startNumber;
    private int endNumber;
    private int totalNumber;
    private Locale sk = Locale.forLanguageTag("sk-SK");
    private NumberFormat numberFormat = NumberFormat.getInstance(sk);
    public CalculateWaterBillFragment() {
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
        view = inflater.inflate(R.layout.fragment_calculate_water_bill, container, false);
        btnCalculate = view.findViewById(R.id.btn_calculate_water);
        btnDelete = view.findViewById(R.id.btn_delete_water);
        etStartNumber = view.findViewById(R.id.et_start_number);
        etEndNumber = view.findViewById(R.id.et_end_number);
        etTotalNumber = view.findViewById(R.id.et_total_number);
        tvResultAmountTotal = view.findViewById(R.id.tv_result_amount_total);

        clickListener();
        return view;
    }

    // The clickListener method is used to listen for click events from user
    private void clickListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etStartNumber.getText().toString().isEmpty() && !etEndNumber.getText().toString().isEmpty()) {
                    startNumber = Integer.parseInt(etStartNumber.getText().toString());
                    endNumber = Integer.parseInt(etEndNumber.getText().toString());
                    if(startNumber <= endNumber) {
                        totalNumber = endNumber - startNumber;
                        calculateTotalAmount(totalNumber);
                    }else {
                        Toast.makeText(view.getContext(),"Số cuối phải lớn hơn số đầu",Toast.LENGTH_SHORT).show();
                    }
                }else if (!etTotalNumber.getText().toString().isEmpty()) {
                    totalNumber = Integer.parseInt(etTotalNumber.getText().toString());
                    calculateTotalAmount(totalNumber);
                }else {
                    Toast.makeText(view.getContext(),"Hãy nhập đủ thông số",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etStartNumber.setText("");
                etEndNumber.setText("");
                etTotalNumber.setText("");
                tvResultAmountTotal.setText("");
            }
        });
    }

    // The calculateTotalAmount method is used to calculate amount water and display result on screen
    private void calculateTotalAmount(int totalNumber) {
        int result =0;
        int amountLevel1 = 6869;
        int amountLevel2 = 8110;
        int amountLevel3 = 9969;
        int amountLevel4 = 18318;

        switch (checkLevel(totalNumber)) {
            case 1:
                result = amountLevel1*totalNumber;
                break;
            case 2:
                result = amountLevel1*10 + amountLevel2*(totalNumber-10);
                break;
            case 3:
                result = amountLevel1*10 + amountLevel2*10 + amountLevel3*(totalNumber-20);
                break;
            case 4:
                result = amountLevel1*10 + amountLevel2*10 + amountLevel3*10 + amountLevel4*(totalNumber-30);
                break;
        }
        tvResultAmountTotal.setText(numberFormat.format(result));
    }

    private int checkLevel(int totalNumber) {
        int level=0;
        for(int i=0; i<4; i++) {
            if(totalNumber > 0) {
                totalNumber -= 10;
                level++;
            }
        }
        return level;
    }
}