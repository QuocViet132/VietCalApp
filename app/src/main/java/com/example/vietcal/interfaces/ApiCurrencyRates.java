package com.example.vietcal.interfaces;

import com.example.vietcal.model.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCurrencyRates {
    // http://data.fixer.io/api/latest?access_key=fe3ffe539d9de90fdf6f29fe449ec00f&symbols=VND,USD&format=1
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiCurrencyRates apiCurrencyRates = new Retrofit.Builder()
            .baseUrl("http://data.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiCurrencyRates.class);

    @GET("api/latest")
    Call<Currency> getCurrencyRate(@Query("access_key") String accessKey,
                                   @Query("symbols") String symbols,
                                   @Query("format") int format);
}
