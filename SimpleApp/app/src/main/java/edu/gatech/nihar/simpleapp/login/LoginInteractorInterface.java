package edu.gatech.nihar.simpleapp.login;

/**
 * Created by Nihar .
 */

public interface LoginInteractorInterface {
    interface OnLoginFinishedListener {
        void onUsernameError(String e);

        void onPasswordError(String e);

        void onSuccess(String s);
        void onFailed();
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
