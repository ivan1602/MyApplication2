package com.example.ivan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseUser;

public class UserChoice extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null)
        {

        }else
        {
            Intent getUserToLoginPage = new Intent(UserChoice.this, Login.class);
            startActivity(getUserToLoginPage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.logout:

                //Logout ne radi kako treba, treba popraviti!!!
               ParseUser.logOut();
               Intent getUserToLogin = new Intent(this, Login.class);
               startActivity(getUserToLogin);
               break;

            case R.id.action_about:
                Intent i = new Intent(this, AboutThisApp.class);
                startActivity(i);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}

