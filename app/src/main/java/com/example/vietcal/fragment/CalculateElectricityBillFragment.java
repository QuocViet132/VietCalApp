package com.example.vietcal.fragment;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietcal.R;

import java.util.Locale;

public class CalculateElectricityBillFragment extends Fragment {
    private View view;
    private EditText etStartNumber;
    private EditText etEndNumber;
    private EditText etTotalNumber;
    private TextView tvResultAmountBeforeTax;
    private TextView tvResultAmountTax;
    private TextView tvResultAmountTotal;


    private Button btnCalculate;
    private Button btnDelete;
    private int startNumber = 0;
    private int endNumber = 0;
    private int totalNumber = 0;
    private int amountBeforeTax = 0;
    private Double amountTax = 0.0;
    private Double amountTotal = 0.0;
    private final Locale sk = Locale.forLanguageTag("sk-SK");
    private final NumberFormat numberFormat = NumberFormat.getInstance(sk);
    public CalculateElectricityBillFragment() {
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
        view = inflater.inflate(R.layout.fragment_calculate_electricity_bill, container, false);
        etStartNumber = view.findViewById(R.id.et_start_number);
        etEndNumber = view.findViewById(R.id.et_end_number);
        etTotalNumber = view.findViewById(R.id.et_total_number);
        tvResultAmountBeforeTax = view.findViewById(R.id.tv_result_amount_before_tax);
        tvResultAmountTax = view.findViewById(R.id.tv_result_amount_tax);
        tvResultAmountTotal = view.findViewById(R.id.tv_result_amount_total);
        btnCalculate = view.findViewById(R.id.btn_calculate_electricity);
        btnDelete = view.findViewById(R.id.btn_delete_electricity);

        clickListener();
        return view;
    }

    private void clickListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etStartNumber.getText().toString().isEmpty() && !etEndNumber.getText().toString().isEmpty()) {
                    startNumber = Integer.parseInt(etStartNumber.getText().toString());
                    endNumber = Integer.parseInt(etEndNumber.getText().toString());
                    if(startNumber <= endNumber) {
                        totalNumber = endNumber - startNumber;
                        calculateTotalAmount();
                    }else {
                        Toast.makeText(view.getContext(),"Số cuối phải lớn hơn số đầu",Toast.LENGTH_SHORT).show();
                    }
                }else if(!etTotalNumber.getText().toString().isEmpty()) {
                    totalNumber = Integer.parseInt(etTotalNumber.getText().toString());
                    calculateTotalAmount();
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
                tvResultAmountBeforeTax.setText("");
                tvResultAmountTax.setText("");
                tvResultAmountTotal.setText("");
            }
        });
    }

    private int calculateElectricityBeforeTax(int mTotalNumber) {
        int result = 0;
        int amountLevel1 = 1728;
        int amountLevel2 = 1786;
        int amountLevel3 = 2074;
        int amountLevel4 = 2612;
        int amountLevel5 = 2919;
        int amountLevel6 = 3015;

        switch (checkAmountLevel(mTotalNumber)) {
            case 1:
                result = amountLevel1*mTotalNumber;
                break;
            case 2:
                result = amountLevel1*50 + amountLevel2*(mTotalNumber-50);
                break;
            case 3:
                result = amountLevel1*50 + amountLevel2*50 + amountLevel3*(mTotalNumber-100);
                break;
            case 4:
                result = amountLevel1*50 + amountLevel2*50 + amountLevel3*100 + amountLevel4*(mTotalNumber-200);
                break;
            case 5:
                result = amountLevel1*50 + amountLevel2*50 + amountLevel3*100 + amountLevel4*100 + amountLevel5*(mTotalNumber-300);
                break;
            case 6:
                result = amountLevel1*50 + amountLevel2*50 + amountLevel3*100 + amountLevel4*100 + amountLevel5*100 + amountLevel6*(mTotalNumber-400);
                break;
        }
        return result;
    }

    private int checkAmountLevel(int mTotalNumber) {
        int level = 0;
        int count = 0;
        for(int i=0; i<6; i++) {
            if (count<2 && mTotalNumber>0) {
                mTotalNumber -= 50;
                level++;
                count++;
            }else if(count<6 && mTotalNumber>0) {
                mTotalNumber -= 100;
                level++;
                count++;
            }else {
                break;
            }
        }
        return level;
    }

    private void calculateTotalAmount() {
        amountBeforeTax = calculateElectricityBeforeTax(totalNumber);
        amountTax = amountBeforeTax * 0.08;
        amountTotal = amountBeforeTax + amountTax;

        tvResultAmountBeforeTax.setText(numberFormat.format(amountBeforeTax));
        tvResultAmountTax.setText(numberFormat.format(Math.round(amountTax)));
        tvResultAmountTotal.setText(numberFormat.format(Math.round(amountTotal)));
    }
}