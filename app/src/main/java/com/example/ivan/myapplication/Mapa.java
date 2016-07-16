package com.example.ivan.myapplication;

import java.sql.Date;
import java.util.ArrayList;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ivan.myapplication.model.Ruta;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.ParseException;
import com.parse.SaveCallback;

public class Mapa extends Activity {
    private GoogleMap map;
    ArrayList<LatLng> routePoints;
    BroadcastReceiver rcv;
    Button prati, stop;
    Location location; // location
    double latitude; // latitude
    double longitude;

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

        routePoints = new ArrayList<LatLng>();
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        if (map != null) {
            setUpMap();
        }
        prati = (Button)findViewById(R.id.kreni);
        prati.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog ();
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

        stop = ( Button)findViewById(R.id.stop);
        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService( new Intent(Mapa.this, Prati.class));
                map.clear();
            }
        });

        rcv = new ReciveKoord();
        IntentFilter inf = new IntentFilter();

        inf.addAction(Prati.INTENT_ACTION);
        this.registerReceiver(rcv, inf);
        }
    private void setUpMap() {
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },0 );
        }
        else {

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

        alert.setTitle("Ime ruta");
        alert.setMessage("Molim Vas unesite ime rute");

        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();

                if (value != null && value.trim().length() == 0)
                {
                    Context context = getApplicationContext();
                    CharSequence error = "Niste unijeli ime rute, molim Vas unesite ime rute" + value;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, error, duration);
                    toast.show();
                    showInputDialog();
                }
                else
                {
                    dodajRutuUBazu(value);
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();

    }
    public void dodajRutuUBazu (String imerute){
        final Ruta ruta = new Ruta();
        ruta.setImeRute(imerute);
        ruta.spremi(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Intent i = new Intent(Mapa.this, Prati.class);
                i.putExtra("id_rute", ruta.getObjectId());
                startService(i);
            }
        });

    }
}
