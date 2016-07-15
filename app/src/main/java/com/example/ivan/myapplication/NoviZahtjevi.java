package com.example.ivan.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.SaveCallback;

public class NoviZahtjevi extends AppCompatActivity {

    Zahtjev noviZahtjev;

    EditText ime, prezime, rmjesto, mputovanja, vprojekta, datumPol, vrijemePol, datumPovr, vrijemePovr;
    EditText akontacija, vprijevoza, troskovi, obrazlozenje,podnositelj ;
    Button predaj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_zahtjevi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ime = (EditText)findViewById(R.id.Ime);
        String imeK = ime.getText().toString();

        prezime = (EditText)findViewById(R.id.Prezime);
        rmjesto = (EditText)findViewById(R.id.Radno_mjesto);
        mputovanja = (EditText)findViewById(R.id.Mjesto_putovanja);
        vprojekta   = (EditText)findViewById(R.id.Voditelj_projekta);
        datumPol = (EditText)findViewById(R.id.Datum_polaska);
        vrijemePol = (EditText)findViewById(R.id.Vrijeme_polaska);
        datumPovr = (EditText)findViewById(R.id.Datum_povratka);
        vrijemePovr = (EditText)findViewById(R.id.Vrijeme_povratka);
        akontacija =  (EditText)findViewById(R.id.Akontacija);
        vprijevoza = (EditText)findViewById(R.id.Vrsta_prijevoza);
        troskovi = (EditText)findViewById(R.id.Troskovi);
        obrazlozenje = (EditText)findViewById(R.id.Obrazlozenje);
        podnositelj = (EditText)findViewById(R.id.Podnositelj);






        predaj = (Button)findViewById(R.id.Predaj);
        predaj.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(final View v) {
            noviZahtjev = new Zahtjev();
                noviZahtjev.setIme(ime.getText().toString());
                noviZahtjev.setPrezime(prezime.getText().toString());
                //nastaviti za ostale atribute objekta
                noviZahtjev.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Snackbar.make(v,"Uspješno spremljeno",Snackbar.LENGTH_SHORT).show();
                        }else{
                            Snackbar.make(v,e.getMessage(),Snackbar.LENGTH_LONG).show();
                        }
                    }
                });




            }
        });







    }

}
