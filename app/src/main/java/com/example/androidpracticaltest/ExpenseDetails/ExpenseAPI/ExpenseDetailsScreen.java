package com.example.androidpracticaltest.ExpenseDetails.ExpenseAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidpracticaltest.R;
import com.example.androidpracticaltest.RegistationAPI.ApiClient;
import com.example.androidpracticaltest.RegistationAPI.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseDetailsScreen extends AppCompatActivity {
TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details_screen2);
        date=findViewById(R.id.date1);

      /*  ExpenseServise expenseServise=ExpenseApiClient.getApiClient();
        Call<ExpenseRoot> expenseRootCall=expenseServise.getExpenseResponse("0","L","10");
        expenseRootCall.enqueue(new Callback<ExpenseRoot>() {
            @Override
            public void onResponse(Call<ExpenseRoot> call, Response<ExpenseRoot> response) {
                if (response.isSuccessful())
                {
                    ExpenseRoot expenseRoot=response.body();
                    List<ExpenseMyArray> data=expenseRoot.getMyArray();
                    for(int i=0;i<data.size();i++)
                    {
                        String expenseTypeName=data.get(i).getExpenseTypeName();
                        Toast.makeText(ExpenseDetailsScreen.this, "hello"+expenseTypeName, Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ExpenseRoot> call, Throwable t) {

            }
        });
*/
    }
}