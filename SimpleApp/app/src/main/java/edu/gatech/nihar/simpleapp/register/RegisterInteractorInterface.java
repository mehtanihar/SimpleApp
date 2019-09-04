package edu.gatech.nihar.simpleapp.register;

/**
 * Created by Nihar .
 */

public interface RegisterInteractorInterface {
    interface OnRegisterFinishedListener {
        void onUsernameError(String e);

        void onPasswordError(String e);
        void onEmailError(String e);

        void onSuccess(String s);
    }

    void register(String username, String email, String password1, String password2, OnRegisterFinishedListener listener);
}

