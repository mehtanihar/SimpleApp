package edu.gatech.nihar.simpleapp.login;

/**
 * Created by Nihar .
 */

public class LoginPresenterImplementation implements LoginPresenterInterface,LoginInteractorInterface.OnLoginFinishedListener {

    private LoginInterface loginInterface;
    private LoginInteractorInterface loginInteractorInterface;

    public LoginPresenterImplementation(LoginInterface loginInterface, LoginInteractorInterface loginInteractorInterface) {
        this.loginInterface = loginInterface;
        this.loginInteractorInterface = loginInteractorInterface;
    }


    @Override
    public void onUsernameError(String error) {
        if(loginInterface!=null){
            loginInterface.setUsernameError(error);
        }
    }

    @Override
    public void onPasswordError(String error) {
        if(loginInterface!=null){
            loginInterface.setPasswordError(error);
        }
    }

    @Override
    public void onSuccess(String success_message) {
        if(loginInterface!=null){
            loginInterface.navigateToHome(success_message);
        }
    }

    @Override
    public void validateCredentials(String email, String password) {
        loginInteractorInterface.login(email, password, this);
    }

    @Override
    public void onDestroy() {
        loginInterface=null;
    }


    @Override
    public void onFailed() {
        if(loginInterface!=null){
            loginInterface.onFailed();
        }
    }
}
