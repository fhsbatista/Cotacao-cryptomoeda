package com.devandroid.fbatista.androidchallange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.devandroid.fbatista.androidchallange.model.CryptoCurrency;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CoinCapService coinCapService = CoinCapService.retrofit.create(CoinCapService.class);
        Call<List<CryptoCurrency>> call = coinCapService.getCryptoCurrency(10);
        call.enqueue(new Callback<List<CryptoCurrency>>() {
            @Override
            public void onResponse(Call<List<CryptoCurrency>> call, Response<List<CryptoCurrency>> response) {
                int code = response.code();
                if(code == 200){
                    List<CryptoCurrency> currencies = response.body();
                    for(CryptoCurrency currency : currencies){
                        Log.i(TAG, currency.getName() + " " + currency.getSymbol());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CryptoCurrency>> call, Throwable t) {

            }
        });


    }
}
