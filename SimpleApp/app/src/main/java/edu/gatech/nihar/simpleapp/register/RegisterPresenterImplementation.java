package edu.gatech.nihar.simpleapp.register;

/**
 * Created by Nihar .
 */

public class RegisterPresenterImplementation implements edu.gatech.nihar.simpleapp.register.RegisterPresenterInterface,RegisterInteractorInterface.OnRegisterFinishedListener {
    private RegisterInterface registerInterface;
    private RegisterInteractorInterface registerInteractorInterface;

    public RegisterPresenterImplementation(RegisterInterface registerInterface, RegisterInteractorInterface registerInteractorInterface) {
        this.registerInterface = registerInterface;
        this.registerInteractorInterface = registerInteractorInterface;
    }



    @Override
    public void onUsernameError(String error) {
        if(registerInterface!=null){
            registerInterface.setUsernameError(error);
        }
    }

    @Override
    public void onPasswordError(String error) {
        if(registerInterface!=null){
            registerInterface.setPasswordError(error);
        }
    }

    @Override
    public void onEmailError(String error) {
        if(registerInterface!=null){
            registerInterface.setEmailError(error);
        }
    }

    @Override
    public void onSuccess(String success_message) {
        if(registerInterface!=null){
            registerInterface.navigateToHome(success_message);
        }
    }

    @Override
    public void validateCredentials(String username,String email, String password1,String password2) {
        registerInteractorInterface.register(username,email,password1,password2,this);
    }

    @Override
    public void onDestroy() {
        registerInterface=null;
    }
}
