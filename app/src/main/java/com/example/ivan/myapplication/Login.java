package com.example.ivan.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends Activity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLogin;
    protected Button mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialization

        mUsername = (EditText)findViewById(R.id.UserName);
        mPassword = (EditText)findViewById(R.id.UserPass);
        mLogin = (Button)findViewById(R.id.LoginBtn);
        mCreateAccount = (Button)findViewById(R.id.RegisterBtn);

        //listen when the mLogin button is clicked
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the user inputs from edittexts and convert to string
                String user = mUsername.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();
                //login the user using parse sdk
                //
                ParseUser.logInInBackground(user, pass, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if (e == null) {
                                //sucess, user logged in
                                Toast.makeText(Login.this, "Uspjesno logiran", Toast.LENGTH_LONG).show();
                                //take user to the homepage
                                Intent takeUserHome = new Intent(Login.this, UserChoice.class);
                                startActivity(takeUserHome);

                        } else {
                            //there was a problem during logging
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle("Sorry");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                        }
                    }
                });
            }
        });

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerUser = new Intent(Login.this, RegisterPage.class);
                startActivity(registerUser);
            }
        });
    }
}
