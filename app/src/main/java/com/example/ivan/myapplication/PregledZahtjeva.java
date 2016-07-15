package com.example.ivan.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class PregledZahtjeva extends AppCompatActivity {

    ListView listViewPregledZahtjeva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_zahtjeva);
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

        listViewPregledZahtjeva = (ListView) findViewById(R.id.listViewPregledZahtjeva);
        ParseQuery<Zahtjev> query = new ParseQuery<Zahtjev>(Zahtjev.class);
        query.findInBackground(new FindCallback<Zahtjev>() {
            @Override
            public void done(List<Zahtjev> objects, ParseException e) {

                ArrayAdapter<Zahtjev> adapter = new ArrayAdapter<Zahtjev>(PregledZahtjeva.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1,objects);
                listViewPregledZahtjeva.setAdapter(adapter);
            }
        });
    }

}
