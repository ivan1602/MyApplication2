package com.example.ivan.myapplication;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ivan.myapplication.model.Ruta;
import com.example.ivan.myapplication.model.Zahtjev;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.SaveCallback;

public class Mapa extends AppCompatActivity {
    private GoogleMap map;
    ArrayList<LatLng> routePoints;
    BroadcastReceiver rcv;
    Button prati, stop;
    Location location; // location
    double latitude; // latitude
    double longitude;
    Intent serviceIntent;

    @Override
    protected void onPause() {
        super.onPause();
        if (rcv != null) {

            this.unregisterReceiver(rcv);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (rcv != null) {
            IntentFilter inf = new IntentFilter();
            inf.addAction(Prati.INTENT_ACTION);
            this.registerReceiver(rcv, inf);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        routePoints = new ArrayList<LatLng>();
        SupportMapFragment mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                setUpMap();
            }
        });


        prati = (Button) findViewById(R.id.kreni);
        prati.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        final OnMyLocationButtonClickListener pozicija = new OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                map.moveCamera(CameraUpdateFactory.newLatLng(routePoints
                        .get(routePoints.size() - 1)));

                return true;
            }
        };

        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceIntent != null)
                    stopService(serviceIntent);
                map.clear();
            }
        });

        rcv = new ReciveKoord();
        IntentFilter inf = new IntentFilter();

        inf.addAction(Prati.INTENT_ACTION);
        this.registerReceiver(rcv, inf);
    }

    private void setUpMap() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        } else {

            map.setMyLocationEnabled(true);
            map.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }

    private void updateRoute() {
        PolylineOptions p = new PolylineOptions().width(3).color(0xFFEE8888);
        p.addAll(routePoints);
        Log.d("update route", "nova lokacija");
        map.addMarker(new MarkerOptions().position(routePoints.isEmpty() ? null : routePoints.get(0)).title("Start"));
        map.addPolyline(p);
    }

    public class ReciveKoord extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent intent) {
            if (intent.getAction().equals(Prati.INTENT_ACTION)) {

                routePoints = intent.getExtras().getParcelableArrayList("lokacija");
                updateRoute();
            }
        }
    }

    private void showInputDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Ruta");
        alert.setMessage("Odaberite Zahtjev");

        final ListView listView = new ListView(this);
        ParseQuery<Zahtjev> query = ParseQuery.getQuery(Zahtjev.class);


        alert.setView(listView);
        final AlertDialog dialog = alert.show();
        query.findInBackground(new FindCallback<Zahtjev>() {
            @Override
            public void done(final List<Zahtjev> objects, ParseException e) {
                listView.setAdapter(new ArrayAdapter<Zahtjev>(Mapa.this, android.R.layout.simple_list_item_1, objects));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                        dodajRutuUBazu(objects.get(position));
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    public void dodajRutuUBazu(final Zahtjev zahtjev) {
        final Ruta ruta = new Ruta();
        ruta.setZahtjev(zahtjev);
        ruta.spremi(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                serviceIntent = new Intent(Mapa.this, Prati.class);
                serviceIntent.putExtra("id_rute", ruta.getObjectId());
                startService(serviceIntent);
            }
        });

    }
}
