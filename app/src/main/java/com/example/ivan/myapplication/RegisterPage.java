package com.example.ivan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterPage extends Activity {

    protected  Button register;
    protected EditText musername;
    protected EditText mpassword;
    protected EditText memail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        //Parse.initialize(new Parse.Configuration.Builder(RegisterPage.this).applicationId("1rYGdTn2AMtXPOPrVbnooFVmpC0EE0CNllTTC2xC").clientKey("o78lIY0qDURGlvmCPpO90G0sfR9KeRwSXsMaBIK1").build());
        //initialize
        musername = (EditText)findViewById(R.id.User);
        mpassword = (EditText)findViewById(R.id.Pass);
        memail = (EditText)findViewById(R.id.Email);

        register = (Button)findViewById(R.id.sign);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toast

                //get the username, pass and email and convert them to string
                String username = musername.getText().toString().trim();
                String pass= mpassword.getText().toString().trim();
                String email= memail.getText().toString().trim();

                //Store user in parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(pass);
                user.setEmail(email);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            //user signed up successfully
                            Toast.makeText(RegisterPage.this, "Uspjesno registriran", Toast.LENGTH_LONG).show();
                            //take user homepage
                            musername.setText(null);
                            mpassword.setText(null);
                            memail.setText(null);

                            Intent goToHomepage = new Intent(RegisterPage.this, Login.class);
                            startActivity(goToHomepage);
                        }
                        else
                        {
                            //there was error signing up user, advice user
                            Toast.makeText(RegisterPage.this, "Vec postojeci ili neispravni podaci", Toast.LENGTH_LONG).show();
                            musername.setText(null);
                            mpassword.setText(null);
                            memail.setText(null);

                        }
                    }
                });
            }
        });

    }

}
