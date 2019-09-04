package edu.gatech.nihar.simpleapp.Models;

import android.text.TextUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String BASE_URL = "https://simpleappdjango.herokuapp.com/api/v1/";
    private static String auth_token=null;
    private static User user=null;


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static String getAuth_token() {
        return auth_token;
    }

    public static void setAuth_token(String auth_token) {
        ServiceGenerator.auth_token = auth_token;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ServiceGenerator.user = user;
    }

    public static <S> S createService(
            Class<S> serviceClass) {

        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);

        }

        if(auth_token!=null){

            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(auth_token);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }



        }

        builder.client(httpClient.build());
        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }


}
