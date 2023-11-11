package com.example.vietcal.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vietcal.R;
import com.example.vietcal.interfaces.ApiCurrencyRates;
import com.example.vietcal.model.Currency;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyConvertFragment extends Fragment {
    private View view;
    private TextView tvEurRate;
    private TextView tvUsdRate;
    private TextView tvVndRate;
    private EditText etEur;
    private EditText etUsd;
    private EditText etVnd;
    private Button btnCalculate;
    private Button btnDelete;
    private float amountEur;
    private float amountUsd;
    private float amountVnd;
    private float eurRate;
    private float usdRate;
    private float vndRate;
    public CurrencyConvertFragment() {
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
        view = inflater.inflate(R.layout.fragment_currency_convert, container, false);

        initial();
        clickListener();
        return view;
    }

    private void initial() {
        etEur = view.findViewById(R.id.et_eur);
        etUsd = view.findViewById(R.id.et_usd);
        etVnd = view.findViewById(R.id.et_vnd);
        tvEurRate = view.findViewById(R.id.tv_eur_rate_api);
        tvUsdRate = view.findViewById(R.id.tv_usd_rate_api);
        tvVndRate = view.findViewById(R.id.tv_vnd_rate_api);
        btnCalculate = view.findViewById(R.id.btn_result);
        btnDelete = view.findViewById(R.id.btn_delete_currency);

        ApiCurrencyRates.apiCurrencyRates.getCurrencyRate("fe3ffe539d9de90fdf6f29fe449ec00f","VND,USD",1).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(view.getContext(),"Get API Success",Toast.LENGTH_SHORT).show();
                Currency currency = response.body();
                if(currency!=null && currency.getSuccess()) {
                    eurRate = 1;
                    usdRate = currency.getRates().getUsd();
                    vndRate = currency.getRates().getVnd();

                    tvEurRate.setText("1");
                    tvUsdRate.setText(String.valueOf(currency.getRates().getUsd()));
                    tvVndRate.setText(String.valueOf(currency.getRates().getVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(view.getContext(),"Get API Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickListener() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            Locale vn = Locale.forLanguageTag("vi-VN");
            NumberFormat numberFormat = NumberFormat.getInstance(vn);
            @Override
            public void onClick(View v) {
                try {
                    if(!etEur.getText().toString().isEmpty()){
                        amountEur = Float.parseFloat(etEur.getText().toString());
                        amountUsd = amountEur * usdRate;
                        amountVnd = amountEur * vndRate;

                        etUsd.setText(numberFormat.format(amountUsd));
                        etVnd.setText(numberFormat.format(amountVnd));
                    }else if(!etUsd.getText().toString().isEmpty()) {
                        amountUsd = Float.parseFloat(etUsd.getText().toString());
                        amountEur = amountUsd / usdRate;
                        amountVnd = amountEur * vndRate;

                        etEur.setText(numberFormat.format(amountEur));
                        etVnd.setText(numberFormat.format(amountVnd));
                    }else if(!etVnd.getText().toString().isEmpty()) {
                        amountVnd = Float.parseFloat(etVnd.getText().toString());
                        amountEur = amountVnd /vndRate;
                        amountUsd = amountEur * usdRate;

                        etEur.setText(numberFormat.format(amountEur));
                        etUsd.setText(numberFormat.format(amountUsd));
                    }else {
                        Toast.makeText(view.getContext(),"Hãy nhập số tiền quy đổi",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception NumberFormatException) {
                    Toast.makeText(view.getContext(),"Hãy xoá dữ liệu cũ",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEur.setText("");
                etUsd.setText("");
                etVnd.setText("");
            }
        });
    }

}