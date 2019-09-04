package edu.gatech.nihar.simpleapp.register;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import edu.gatech.nihar.simpleapp.Models.RegisterClient;
import edu.gatech.nihar.simpleapp.Models.ServiceGenerator;
import edu.gatech.nihar.simpleapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nihar.
 */

public class RegisterInteractorImplementation implements edu.gatech.nihar.simpleapp.register.RegisterInteractorInterface {

    String tag="debug_interactor";

    @Override
    public void register(final String username,final String email, final String password1, final String password2,final RegisterInteractorInterface.OnRegisterFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {




                if (TextUtils.isEmpty(email)) {
                    listener.onUsernameError("Email is empty");
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    listener.onPasswordError("Password is empty");
                    return;
                }
                else{


                    RegisterClient client = ServiceGenerator.createService(RegisterClient.class);


                    User user=new User(username,email,password1,password2);

                    // Fetch a list of the Github repositories.
                    Call<Void> call =
                            client.createUser(user);

                    // Execute the call asynchronously. Get a positive or negative callback.
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            // The network call was a success and we got a response
                            // TODO: use the repository list and display it

                            Log.e(tag,response.message());
                            if(response.message().equals("OK")) {
                                listener.onSuccess("New User Created");
                            }
                            else{
                                listener.onPasswordError(response.message());
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // the network call was a failure
                            // TODO: handle error
                            Log.e(tag,"Network call failed");
                            Log.e(tag,t.getMessage());
                            listener.onPasswordError(t.getMessage());

                        }
                    });





                }




            }
        }, 1000);
    }

}
