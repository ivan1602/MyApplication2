package com.example.ivan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ivan.myapplication.model.Zahtjev;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PregledZahtjeva extends AppCompatActivity {

    ListView listViewPregledZahtjeva;
    List<Zahtjev> listZahtjeva;
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
                listZahtjeva=objects;
                ArrayAdapter<Zahtjev> adapter = new ArrayAdapter<Zahtjev>(PregledZahtjeva.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1,objects);
                listViewPregledZahtjeva.setAdapter(adapter);
            }
        });

        listViewPregledZahtjeva.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listZahtjeva!=null){
                    Zahtjev zahtjev=listZahtjeva.get(position);
                    Intent i = new Intent(PregledZahtjeva.this,ZahtjevIKarta.class);
                    i.putExtra("idZahtjeva",zahtjev.getObjectId());
                    startActivity(i);
                }
            }
        });
    }

}
