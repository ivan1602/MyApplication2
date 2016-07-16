package com.example.ivan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ivan.myapplication.model.Koordinate;
import com.example.ivan.myapplication.model.Ruta;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PokaziNaKarti extends Activity {
    Button statistika;
    private GoogleMap map;
    String idRute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokazi_na_karti);
        statistika = (Button) findViewById(R.id.statistikaRute);
        statistika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PokaziNaKarti.this, StatistikaRute.class));

            }
        });


        idRute = "RhliVIGiUe";
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
        PokaziRutu();

    }

    private void PokaziRutu() {
        Ruta ruta = Ruta.createWithoutData(Ruta.class, idRute);
        ParseQuery<Koordinate> query = ParseQuery.getQuery(Koordinate.class);
        query.whereEqualTo(Koordinate.KEY_ID_RUTE, ruta);
        query.findInBackground(new FindCallback<Koordinate>() {
            @Override
            public void done(List<Koordinate> objects, ParseException e) {
                ArrayList<LatLng> list = new ArrayList<LatLng>();
                for (Koordinate k : objects) {
                    list.add(new LatLng(k.getLat(), k.getLng()));
                }
                PolylineOptions p = new PolylineOptions().width(3).color(0xFFEE8888);
                p.addAll(list);
                map.addPolyline(p);
                map.setMyLocationEnabled(false);
                map.moveCamera(CameraUpdateFactory.newLatLng(list.get(list.size() - 1)));
                map.animateCamera(CameraUpdateFactory.zoomTo(15));
                map.addMarker(new MarkerOptions().position(list.isEmpty() ? null : list.get(0)).title("Start"));
                map.addMarker(new MarkerOptions().position(list.isEmpty() ? null : list.get(list.size() - 1)).title("End"));
            }
        });


    }


}
