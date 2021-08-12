package com.example.metrocard2020.login;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.9:8080/api/v1.0/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static  ApiPlaceHolder getApiPlaceHolder(){
        ApiPlaceHolder apiPlaceHolder = getRetrofit().create(ApiPlaceHolder.class);
        return apiPlaceHolder;
    }


}
