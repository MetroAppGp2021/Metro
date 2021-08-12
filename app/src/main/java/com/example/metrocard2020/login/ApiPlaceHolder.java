package com.example.metrocard2020.login;

import com.example.metrocard2020.model.ScanModel;
import com.example.metrocard2020.model.TripModel;
import com.example.metrocard2020.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPlaceHolder {
    @GET("student/")
    Call<List<UserModel>> userLogin(@Query("id") Long id);
    @POST("student")
    Call<UserModel> singUp(@Body UserModel userModel);
    @POST("student")
    Call<UserModel> addUser(@Body UserModel userModel);
    @PUT("student/{phone}")
    Call<UserModel> update(@Path("phone") String phone, @Body UserModel userModel);
    @DELETE("student/{id}")
    Call<Void> delete (@Path("id") Long id);

    @GET("trip/")
    Call<List<TripModel>> getTrips(@Query("id") Long id);

    @POST("trip")
    Call<TripModel> addTrip(@Body TripModel tripModel);

    @DELETE("trip/{id}")
    Call<Void> deleteTrip (@Path("id") Long id);

    @GET("scan/")
    Call<List<ScanModel>> getScans(@Query("id") Long id);

    @POST("scan")
    Call<ScanModel> addScan(@Body ScanModel scanModel);

    @DELETE("scan/{id}")
    Call<Void> deleteScan (@Path("id") Long id);


}
