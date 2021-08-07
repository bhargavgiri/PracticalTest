package com.example.androidpracticaltest.ExpenseDetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidpracticaltest.ExpenseDetails.ExpenseAPI.ExpenseMyArray;
import com.example.androidpracticaltest.R;
import com.example.androidpracticaltest.RegistationAPI.ApiClient;
import com.example.androidpracticaltest.RegistationAPI.MyArray;
import com.example.androidpracticaltest.RegistationAPI.RegistrationServise;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseDetailsScreen extends AppCompatActivity implements View.OnClickListener {

    TextView tvSelectDate, tvSelectImage, tvSave;
    Spinner spExpenseType;
    EditText etVoucherAmount, etRemarks;
    ImageView ivSelectedImage;
    Gson gson = new Gson();
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details_screen);

        tvSelectDate = findViewById(R.id.tvSelectDate);
        tvSelectImage = findViewById(R.id.tvSelectImage);
        tvSave = findViewById(R.id.tvSave);
        spExpenseType = findViewById(R.id.spExpenseType);
        etVoucherAmount = findViewById(R.id.etVoucherAmount);
        etRemarks = findViewById(R.id.etRemarks);
        ivSelectedImage = findViewById(R.id.ivSelectedImage);

        tvSelectDate.setOnClickListener(this);
        tvSelectImage.setOnClickListener(this);
        tvSave.setOnClickListener(this);


        getExpenseType();

    }

    private void getExpenseType() {
        RegistrationServise registrationServise = ApiClient.getExpenseTypes();
        Call<String> call = registrationServise.getExpenseTypesCall(0, "L", 10);
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
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ExpenseDetailsScreen.this, android.R.layout.simple_dropdown_item_1line, expenseTypes);
                    spExpenseType.setAdapter(arrayAdapter);

                } else {
                    Toast.makeText(ExpenseDetailsScreen.this, "Expense Types Null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ExpenseDetailsScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSelectDate:
                getDatePicker();
                break;
            case R.id.tvSelectImage:
                selectImage();
                break;
            case R.id.tvSave:
                saveExpense();
                break;
        }
    }

    private void saveExpense() {

        RegistrationServise registrationServise = ApiClient.getExpenseTypes();
        Call<String> call = registrationServise.saveData(0, "2022-08-31",10,100,"remarks","","","Admin",10,"logo");
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
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(ExpenseDetailsScreen.this, android.R.layout.simple_dropdown_item_1line, expenseTypes);
                    spExpenseType.setAdapter(arrayAdapter);

                } else {
                    Toast.makeText(ExpenseDetailsScreen.this, "Expense Types Null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ExpenseDetailsScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

    }

    private void selectImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);

    }

    private void getDatePicker() {

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "dd/MM/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
            tvSelectDate.setText(sdf.format(calendar.getTime()));
        };

        new DatePickerDialog(ExpenseDetailsScreen.this, date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                String path=resultUri.getPath();
                Glide.with(this).load(resultUri).into(ivSelectedImage);
               /* File abc=new File(resultUri.toString());
                uploadImage(abc);*/
                //uploadImage(new File(path));

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage(File file) {

        RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("filename", file.getName(), mFile);

        RegistrationServise registrationServise = ApiClient.getExpenseTypes();
        Call<String> call = registrationServise.uploadImage(10, "admin", 0, fileToUpload);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                    String root = response.body();
                    Toast.makeText(ExpenseDetailsScreen.this, root, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ExpenseDetailsScreen.this, "Image Upload Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(ExpenseDetailsScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }
}