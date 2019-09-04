package edu.gatech.nihar.simpleapp.login;

/**
 * Created by Nihar .
 */

public interface LoginInterface {
    void setUsernameError(String error);

    void setPasswordError(String error);

    void navigateToHome(String sucess_message);

    void onFailed();
}
