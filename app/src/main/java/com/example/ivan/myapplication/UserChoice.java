package com.example.ivan.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseUser;

public class UserChoice extends AppCompatActivity{

    Button noviZahtjev, pregledZahtjeva, pracenje,pregledPutnihNaloga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },0 );
        }

        noviZahtjev = (Button)findViewById(R.id.newZahtjev);
        noviZahtjev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nZahtjevi = new Intent(UserChoice.this, NoviZahtjevi.class);
                startActivity(nZahtjevi);
            }
        });

        pregledZahtjeva = (Button)findViewById(R.id.odlZahtjevi);
        pregledZahtjeva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oZahtjevi = new Intent(UserChoice.this, PregledZahtjeva.class);
                startActivity(oZahtjevi);
            }
        });

        pracenje = (Button)findViewById(R.id.pracenje);
        pracenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prati = new Intent(UserChoice.this, Mapa.class);
                startActivity(prati);
            }
        });

        pregledPutnihNaloga = (Button) findViewById(R.id.Putninalog);
        pregledPutnihNaloga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pokazi = new Intent(UserChoice.this, PokaziNaKarti.class);
                startActivity(pokazi);
            }
        });
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

