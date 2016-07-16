package com.example.ivan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ivan.myapplication.model.Koordinate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class PokaziNaKarti extends Activity {
    Button statistika;
    private GoogleMap map;
    int idRute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokazi_na_karti);
        statistika= (Button)findViewById(R.id.statistikaRute);
        statistika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PokaziNaKarti.this,StatistikaRute.class));

            }
        });


        idRute=getIntent().getIntExtra("idrute", 0);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMyLocationEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
        PokaziRutu();

    }

    private void PokaziRutu() {
//        DatabaseHandler baza= new DatabaseHandler(this);
//        //baza.openDB();
//        ArrayList<Koordinate> listRuta= baza.DohvatiKoordinate(idRute);
//        baza.closeDB();
//        ArrayList<LatLng> list= new ArrayList<LatLng>();
//        for (Koordinate k:listRuta){
//            list.add(new LatLng(k.getLat(), k.getLng()));
//        }
        PolylineOptions p = new PolylineOptions().width(3).color(0xFFEE8888);
//        p.addAll(list);
        map.addPolyline(p);
        map.setMyLocationEnabled(false);
//        map.moveCamera(CameraUpdateFactory.newLatLng(list.get(list.size()-1)));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
//        map.addMarker(new MarkerOptions().position(list.isEmpty()?null:list.get(0)).title("Start"));
//        map.addMarker(new MarkerOptions().position(list.isEmpty()?null:list.get(list.size()-1)).title("End"));

    }


}
