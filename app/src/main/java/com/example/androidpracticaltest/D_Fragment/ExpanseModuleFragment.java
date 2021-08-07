package com.example.androidpracticaltest.D_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidpracticaltest.ExpenseDetails.ExpenseAPI.ExpenseMyArray;
import com.example.androidpracticaltest.ExpenseDetails.ExpenseDetailsScreen;
import com.example.androidpracticaltest.R;
import com.example.androidpracticaltest.RegistationAPI.ApiClient;
import com.example.androidpracticaltest.RegistationAPI.RegistrationServise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpanseModuleFragment extends Fragment {
    Gson gson = new Gson();
    ListView lvExpenseList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_expanse_module,container,false);

        lvExpenseList = view.findViewById(R.id.lvExpenseList);

        getExpenseType();
        return view;
    }

    private void getExpenseType() {
        RegistrationServise registrationServise = ApiClient.getExpenseInterceptor();
        Call<String> call = registrationServise.getExpansListing(0, 1, 10,10);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != "") {
                    Type listType =
                            new TypeToken<ArrayList<ExpenseMyArray>>() {
                            }.getType();
                    String root = response.body().replaceAll("\\s", "\\");
                    List<ExpenseMyArray> responseData = gson.fromJson(root, listType);
                    List<String> expenseTypes = new ArrayList<>();
                    for (int i = 0; i < responseData.size(); i++) {
                        expenseTypes.add(responseData.get(i).getExpenseTypeName());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, expenseTypes);
                    lvExpenseList.setAdapter(arrayAdapter);
                    lvExpenseList.setOnItemClickListener((adapterView, view, i, l) -> {
                        if (i==0){
                            startActivity(new Intent(getContext(), ExpenseDetailsScreen.class));
                        }
                    });

                } else {
                    Toast.makeText(getContext(), "Expense Types Null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

  
}