package edu.gatech.nihar.simpleapp.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import edu.gatech.nihar.simpleapp.Models.KeyClass;
import edu.gatech.nihar.simpleapp.Models.RegisterClient;
import edu.gatech.nihar.simpleapp.Models.ServiceGenerator;
import edu.gatech.nihar.simpleapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nihar .
 */

public class LoginInteractorImplementation implements LoginInteractorInterface {

    private String tag="debug login interactor";


    @Override
    public void login(final String email, final String password, final OnLoginFinishedListener listener) {

        if (TextUtils.isEmpty(email)) {
            listener.onUsernameError("Email is empty");
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError("Password is empty");
            return;
        }
        else{

            final RegisterClient client = ServiceGenerator.createService(RegisterClient.class);

            final User user=new User(email,password);

            Call<KeyClass> call =
                    client.loginUser(user);

            // Execute the call asynchronously. Get a positive or negative callback.
            call.enqueue(new Callback<KeyClass>() {
                @Override
                public void onResponse(Call<KeyClass> call, Response<KeyClass> response) {

                    Log.e(tag,response.message());
                    if(response.message().equals("OK")) {
                        listener.onSuccess("Successfully Logged in!");
                    }
                    else{

                        listener.onPasswordError(response.message());

                    }

                }

                @Override
                public void onFailure(Call<KeyClass> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    Log.e(tag,"Network call failed");
                    Log.e(tag,t.getMessage());
                    listener.onFailed();

                }
            });

        }

    }

}
