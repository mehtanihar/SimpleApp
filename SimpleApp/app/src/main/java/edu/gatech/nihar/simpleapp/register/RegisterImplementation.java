package edu.gatech.nihar.simpleapp.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.nihar.simpleapp.login.LoginImplementation;
import edu.gatech.nihar.simpleapp.R;

/**
 * Created by Nihar .
 */

public class RegisterImplementation extends Activity implements edu.gatech.nihar.simpleapp.register.RegisterInterface {

    private EditText username;
    private EditText password1, password2;
    private  EditText email;
    private EditText first_name;
    private edu.gatech.nihar.simpleapp.register.RegisterPresenterInterface presenter;
    //private Button button,button2;
    private CardView button;
    String tag="debug view";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Log.e(tag,"Log working");
        username=(EditText)findViewById(R.id.username);
        password1=(EditText)findViewById(R.id.password);
        password2=(EditText)findViewById(R.id.confirm_password);
        email=(EditText)findViewById(R.id.email);
        button=(CardView) findViewById(R.id.signup_button);
        //button2=(Button)findViewById(R.id.signup_login_button);

        presenter = new edu.gatech.nihar.simpleapp.register.RegisterPresenterImplementation(this,new edu.gatech.nihar.simpleapp.register.RegisterInteractorImplementation());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(tag,"button clicked");
                presenter.validateCredentials(
                        username.getText().toString(),email.getText().toString(), password1.getText().toString(),password2.getText().toString());


            }
        });



    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void setUsernameError(String e) {

        username.setError("Invalid username");

        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }

    @Override public void setPasswordError(String e) {
        password1.setError("Invalid password");

        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }

    @Override public void setEmailError(String e) {

        email.setError("Invalid email");
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }

    @Override public void navigateToHome(String s) {

        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginImplementation.class));

    }

}
