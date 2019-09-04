package edu.gatech.nihar.simpleapp.login;

/**
 * Created by Nihar .
 */

public interface LoginPresenterInterface {


    void validateCredentials(String email, String password);

    void onDestroy();

}
