package edu.gatech.nihar.simpleapp.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.nihar.simpleapp.MainActivity;
import edu.gatech.nihar.simpleapp.R;
import edu.gatech.nihar.simpleapp.register.RegisterImplementation;

/**
 * Created by Nihar .
 */

public class LoginImplementation extends Activity implements LoginInterface {
    private EditText email;
    private EditText password;
    private CardView button;
    private LoginPresenterInterface presenter;
    String tag="debug login";
    private TextView btn2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.e(tag,"Please login now.");
        email=(EditText)findViewById(R.id.login_username);
        password=(EditText)findViewById(R.id.login_password);
        button = (CardView) findViewById(R.id.login_button);
        btn2= (TextView)findViewById(R.id.register);

        presenter = new LoginPresenterImplementation(this,new LoginInteractorImplementation());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(tag,"button clicked");
                presenter.validateCredentials(
                        email.getText().toString(), password.getText().toString());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginImplementation.this,RegisterImplementation.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void setUsernameError(String e) {
        email.setError(e);
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }

    @Override public void setPasswordError(String e) {
        password.setError(e);
        Toast.makeText(this,e,Toast.LENGTH_SHORT).show();
    }

    @Override public void navigateToHome(String success_message) {
        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(this,success_message,Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailed() {
        Toast.makeText(this,"Cannot login. No internet connection!",Toast.LENGTH_SHORT).show();
    }
}
