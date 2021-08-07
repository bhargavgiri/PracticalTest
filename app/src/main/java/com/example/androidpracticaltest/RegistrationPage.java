package com.example.androidpracticaltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidpracticaltest.D_Fragment.DashboardFragment;
import com.example.androidpracticaltest.RegistationAPI.ApiClient;
import com.example.androidpracticaltest.RegistationAPI.MyArray;
import com.example.androidpracticaltest.RegistationAPI.RegistrationServise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationPage extends AppCompatActivity {

    EditText serialkey;
    Button checkey;
    Gson gson = new Gson();

    FragmentManager fragmentManager = getSupportFragmentManager();
    DashboardFragment dashboardFragment=new DashboardFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        serialkey=findViewById(R.id.serialkey);
        checkey=findViewById(R.id.validkey);

        checkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationServise registrationServise= ApiClient.getRegistrationServise();
                Call<String> call=registrationServise.registrationCall(serialkey.getText().toString());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful() && response.body()!="")
                                {
                                    Type listType =
                                            new TypeToken<ArrayList<MyArray>>(){}.getType();
                                    String root=response.body().replaceAll("\\s","\\");
                                    List<MyArray> responseData= gson.fromJson(root, listType);
//                                    Toast.makeText(RegistrationPage.this, root, Toast.LENGTH_SHORT).show();
                                  //  Toast.makeText(RegistrationPage.this, "hey", Toast.LENGTH_SHORT).show();
                                    if(responseData!=null&&responseData.size()>0) {

                                        Intent ii = new Intent(RegistrationPage.this, MainActivity.class);
                                        startActivity(ii);
                                    }
                                       /* for(int i=0;i<details.size();i++)
                                        {
                                            String companyName=details.get(i).getCompanyName();
                                            System.out.println(companyName);
                                           *//* Intent ii=new Intent(RegistrationPage.this,MainActivity.class);
                                            startActivity(ii);*//*
                                        }*/
                                   /**/

                                   // Toast.makeText(RegistrationPage.this, "Response "+details, Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }


                    });
            }
        });

    }
}