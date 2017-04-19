package com.example.kshitija.medicalfacility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static android.R.attr.data;

public class LoginApp extends AppCompatActivity {
    TextView status;
    LoginButton login_button;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_app);
        initialiseControls();
        loginwithFB();

    }
    private void initialiseControls(){
        callbackManager = CallbackManager.Factory.create();
        status=(TextView)findViewById(R.id.status);
        login_button=(LoginButton)findViewById(R.id.login_button);
    }

    private void loginwithFB(){

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                status.setText("Login Successful \n"+loginResult.getAccessToken());
                Intent i = new Intent( LoginApp.this, MapActivity.class);
                startActivity(i);

            }

            @Override
            public void onCancel() {
                status.setText("Login Canceled");
            }

            @Override
            public void onError(FacebookException error) {
                status.setText("Login Error");
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
