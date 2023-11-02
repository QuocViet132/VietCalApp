package com.example.vietcal.fragment;

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

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class CalculateBmiFragment extends Fragment {
    private View view;
    private EditText etHeight;
    private EditText etWeight;
    private Button btnCalculate;
    private double mHeight;
    private double mWeight;
    DecimalFormat decimalFormat = new DecimalFormat("0.#");

    public CalculateBmiFragment() {
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
        view = inflater.inflate(R.layout.fragment_calculate_bmi, container, false);
        etHeight = view.findViewById(R.id.et_height);
        etWeight = view.findViewById(R.id.et_weight);
        btnCalculate = view.findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueHeight = etHeight.getText().toString();
                String valueWeight = etWeight.getText().toString();
                if(!valueHeight.isEmpty() && !valueWeight.isEmpty()) {
                    mHeight = Double.parseDouble(etHeight.getText().toString());
                    mWeight = Double.parseDouble(etWeight.getText().toString());
                    classifyResultBmi(calculateBmi(mHeight,mWeight));
                    calculateBmiIdeal(mHeight);
                }else {
                    Toast.makeText(view.getContext(), "Hãy nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public double calculateBmi(double height, double weight) {
        TextView tvResultBmi = view.findViewById(R.id.tv_result_bmi);
        TextView tvResult = view.findViewById(R.id.tv_result);
        double bmi;
        height /= 100;
        bmi = weight / (height * height);
        tvResult.setText("Kết Quả");
        tvResultBmi.setText(String.format("BMI: %s", decimalFormat.format(bmi)));
        return bmi;
    }

    public void classifyResultBmi(double bmi) {
        TextView tvClassifyResultBmi = view.findViewById(R.id.tv_classify_result_bmi);
        if(bmi < 18.5) {
            tvClassifyResultBmi.setText("Gầy");
        }else if(bmi >= 23) {
            tvClassifyResultBmi.setText("Thừa Cân");
        }else {
            tvClassifyResultBmi.setText("Bình Thường");
        }
    }

    public void calculateBmiIdeal(double height) {
        TextView tvAnalysis = view.findViewById(R.id.tv_analysis);
        TextView tvAnalysisHeight = view.findViewById(R.id.tv_analysis_height);
        TextView tvAnalysisWeight = view.findViewById(R.id.tv_analysis_weight);

        double lowerBmi = 18.5;
        double upperBmi = 22.9;
        height /= 100;
        double lowerWeightIdeal = lowerBmi * (height*height);
        double upperWeightIdeal = upperBmi * (height*height);

        tvAnalysis.setText("Phân Tích");
        tvAnalysisHeight.setText(MessageFormat.format("Chiều Cao (Cm): {0}", etHeight.getText().toString()));
        tvAnalysisWeight.setText(MessageFormat.format("Cân Nặng Lý Tưởng (Kg):\n{0} - {1}", decimalFormat.format(lowerWeightIdeal), decimalFormat.format(upperWeightIdeal)));
    }
}