package com.example.vietcal.fragment;

import android.icu.text.NumberFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietcal.R;

import java.util.Locale;

public class GrossToNetFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private View view;
    private String indexLocal = "I";
    private final Locale sk = Locale.forLanguageTag("sk-SK");
    NumberFormat numberFormat = NumberFormat.getInstance(sk);

    public GrossToNetFragment() {
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
        view = inflater.inflate(R.layout.fragment_gross_to_net, container, false);
        spinnerLocal();
        clickListener();

        return view;
    }

    private void clickListener() {
        Button btnCalculate = view.findViewById(R.id.btn_calculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSalaryGross = view.findViewById(R.id.et_salary_gross);
                EditText etDependentPerson = view.findViewById(R.id.et_dependent_person);
                int salaryGross;
                int dependentPerson;
                if(!etSalaryGross.getText().toString().isEmpty()) {
                    salaryGross = Integer.parseInt(etSalaryGross.getText().toString());
                }else {
                    Toast.makeText(view.getContext(),"Chưa nhập lương Gross",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!etDependentPerson.getText().toString().isEmpty()) {
                    dependentPerson = Integer.parseInt(etDependentPerson.getText().toString());
                }else {
                    Toast.makeText(view.getContext(),"Chưa nhập số người phụ thuộc",Toast.LENGTH_SHORT).show();
                    return;
                }
                convertGrossToNet(salaryGross, dependentPerson);
            }
        });
    }

    private void spinnerLocal() {
        Spinner spinner = view.findViewById(R.id.spinner_local);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.index_local, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        indexLocal = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void convertGrossToNet(int salaryGross, int dependentPerson) {

        int incomeBeforeTax = calculateIncomeBeforeTax(salaryGross);
        int incomeTax = incomeBeforeTax - 11000000 - dependentPerson*4400000;
        int personalIncomeTax = calculatePersonalIncomeTax(incomeTax, incomeBeforeTax);
        int salaryNet = incomeBeforeTax-personalIncomeTax;

        TextView tvSalaryNetResult = view.findViewById(R.id.tv_salary_net_result);
        tvSalaryNetResult.setText(numberFormat.format(salaryNet));
        Log.e("incomeBeforeTax",numberFormat.format(incomeBeforeTax));
        Log.e("incomeTax",numberFormat.format(incomeTax));
        Log.e("personalIncomeTax",numberFormat.format(personalIncomeTax));
    }

    private int calculateIncomeBeforeTax(int salaryGross) {
        int incomeBeforeTax = 0;
        int socialInsurance = (int) (salaryGross*0.08);
        int healthInsurance = (int) (salaryGross*0.015);
        int accidentInsurance = (int) (salaryGross*0.01);
        if(socialInsurance > 2880000) {
            socialInsurance = 2880000;
        }
        if(healthInsurance > 540000) {
            healthInsurance = 540000;
        }
        if (accidentInsurance > 832000) {
            accidentInsurance = 832000;
        }
        incomeBeforeTax = salaryGross - (socialInsurance + healthInsurance + accidentInsurance);
        return incomeBeforeTax;
    }
    private int calculatePersonalIncomeTax(int incomeTax, int incomeBeforeTax) {
        int personalIncomeTax = 0;
        int amountRedundant = incomeTax;
        if(incomeTax > 80000000) {
//            personalIncomeTax = (incomeBeforeTax*35/100) - 9850000;
            amountRedundant -= 80000000;
            personalIncomeTax = 250000 + 500000 + 1200000 + 2800000 + 5000000 + 8400000 + (int)(amountRedundant*0.35);
            Log.e("Run",numberFormat.format(personalIncomeTax));
        }else if(incomeTax > 52000000) {
            amountRedundant -= 52000000;
            personalIncomeTax = 250000 + 500000 + 1200000 + 2800000 + 5000000 + (int)(amountRedundant*0.3);
        }else if(incomeTax > 32000000) {
            amountRedundant -= 32000000;
            personalIncomeTax = 250000 + 500000 + 1200000 + 2800000 + (int)(amountRedundant*0.25);
        }else if(incomeTax > 18000000) {
            amountRedundant -= 18000000;
            personalIncomeTax = 250000 + 500000 + 1200000 + (int)(amountRedundant*0.2);
        }else if(incomeTax > 10000000) {
            amountRedundant -= 10000000;
            personalIncomeTax = 250000 + 500000 + (int)(amountRedundant*0.15);
        }else if(incomeTax > 5000000) {
            amountRedundant -= 5000000;
            personalIncomeTax = 250000 + (int)(amountRedundant*0.1);
        }else if(incomeTax > 0){
            personalIncomeTax = (int)(incomeTax*0.05);
        }else {
            personalIncomeTax = 0;
        }
        return personalIncomeTax;
    }
}