package com.example.androidpracticaltest.RegistationAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
 public  static RegistrationServise registrationServise=null;
    public  static RegistrationServise getRegistrationServise()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
                registrationServise=new Retrofit.Builder()
                        .baseUrl("http://122.170.108.107:89/Login/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build().create(RegistrationServise.class);

        return registrationServise;
    }

    public  static RegistrationServise getExpenseTypes()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        registrationServise=new Retrofit.Builder()
                .baseUrl("http://122.170.108.107:89/Expense/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(RegistrationServise.class);

        return registrationServise;
    }
    public  static RegistrationServise saveData() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        registrationServise = new Retrofit.Builder()
                .baseUrl("http://122.170.108.107:89/Expense/2")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(RegistrationServise.class);

        return registrationServise;
    }
    public  static RegistrationServise getExpansListing() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        registrationServise = new Retrofit.Builder()
                .baseUrl("http://122.170.108.107:89/Expense/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(RegistrationServise.class);

        return registrationServise;
    }
}
