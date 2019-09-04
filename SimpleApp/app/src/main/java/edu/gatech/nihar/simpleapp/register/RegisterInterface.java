package edu.gatech.nihar.simpleapp.register;

/**
 * Created by Nihar .
 */

public interface RegisterInterface {
    void setUsernameError(String e);

    void setPasswordError(String e);

    void setEmailError(String e);

    void navigateToHome(String s);
}
