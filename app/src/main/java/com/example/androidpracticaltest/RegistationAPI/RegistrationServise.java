package com.example.androidpracticaltest.RegistationAPI;

import android.app.DatePickerDialog;

import java.util.Date;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RegistrationServise {
    @FormUrlEncoded
    @POST("SerialKey")
    Call<String> registrationCall(@Field("SerialKey") String no);

    @FormUrlEncoded
    @POST("ExpenseType")
    Call<String> getExpenseTypesCall(@Field("pkID") int pkId, @Field("ListMode") String mode, @Field("CompanyId") int cmpId);

    @FormUrlEncoded
    @POST("UploadImage")
    Call<String> uploadImage(@Field("CompanyId") int cmpId, @Field("LoginUserId") String userId, @Field("pkID") int pkId,@Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST("{pkID}/Save")
    Call<String> saveData(@Path ("pkID")int pkID,@Field("PkID") int pkdid,
                          @Field("ExpenseDate") String date,
                          @Field("ExpenseTypeId") int expensetypeid,
                          @Field("Amount") double amount,
                          @Field("ExpenseNotes") String remarks,
                          @Field("FromLocation") String fromlocation,
                          @Field("ToLocation") String tolocation,
                          @Field("LoginUserID") String loginuserid,
                          @Field("CompanyId") int companyid,
                          @Field("ExpenseImage") String epenseImagename);

    @FormUrlEncoded
    @POST("10")
    Call<String> getExpansListing (@Field("pkID") int pkdid,
                          @Field("PageNo") int pageNo,
                          @Field("PageSize") int pagesize,
                          @Field("CompanyId") int companyid
                          );

    @FormUrlEncoded
    @POST("0/ImageSave")
    Call<String> saveImage (@Field("pkID") int pkdid,
                          @Field("ExpenseID") int expenseId,
                          @Field("LoginUserID") String loginUserId,
                          @Field("CompanyId") int companyid,
                          @Field("Name") String name,
                          @Field("Type") String type
                          );
}
