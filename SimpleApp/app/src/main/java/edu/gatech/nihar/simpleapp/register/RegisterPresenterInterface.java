package edu.gatech.nihar.simpleapp.register;

/**
 * Created by Nihar .
 */

public interface RegisterPresenterInterface {

    void validateCredentials(String username, String email, String password1, String password2);

    void onDestroy();
}
