package edu.gatech.nihar.simpleapp.Models;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Nihar .
 */

public interface RegisterClient {

    @POST("users/register")
    Call<Void> createUser(@Body User user);

    @POST("users/login")
    Call<KeyClass> loginUser(@Body User user);


    @GET("users/{user}")
    Call<User> getUserData(
            @Path("user") String user
    );

    @GET("users/self")
    Call<User> getCurrentUser();


    @Multipart
    @PATCH("users/self")
    Call<User> uploadProfilePic(
            @Part MultipartBody.Part avatar
    );


}


