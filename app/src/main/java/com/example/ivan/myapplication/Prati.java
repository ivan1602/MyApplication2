package com.example.ivan.myapplication;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.provider.Settings;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;

import com.example.ivan.myapplication.model.Koordinate;
import com.example.ivan.myapplication.model.Ruta;
import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


/**
 * Created by Ivan on 23.6.2016..
 */
public class Prati extends Service implements LocationListener{

    public static final String INTENT_ACTION="com.extejljaflija";
    private ArrayList<LatLng> routePoints;
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    String idrute;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 0 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 2000 ; // 5 seconds

    // Declaring a Location Manager
    protected LocationManager locationManager;

    public Prati ()
    {

    }

    @Override
    public void onDestroy() {
        //stopUsingGPS();
        super.onDestroy();
    }

private Ruta ruta;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        idrute = intent.getExtras().getString("id_rute");
        routePoints = new ArrayList<LatLng>();
        ParseQuery<Ruta> rutaQuery=ParseQuery.getQuery(Ruta.class);
        rutaQuery.getInBackground(idrute, new GetCallback<Ruta>() {
            @Override
            public void done(Ruta object, ParseException e) {
                if(e==null){
                    ruta=object;
                    getLocation();
                }
            }
        });


        return Service.START_STICKY;
    }


    public Location getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled)
            {
                showSettingsAlert();
            }
            else
            {
                if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

                    stopSelf();
                }


                this.canGetLocation = true;
                if (isNetworkEnabled)
                {
                   locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled)
                {
                    if (location == null)
                    {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null)
                        {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null)
                            {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                sendLocation(latitude, longitude);
                            }
                        }
                    }
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return location;
    }

    public double getLatitude()
    {
        if(location != null)
        {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    public double getLongitude()
    {
        if(location != null)
        {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    public boolean canGetLocation()
    {
        return this.canGetLocation;
    }


    public void showSettingsAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int which)
            {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        sendLocation(location.getLatitude(), location.getLongitude());

    }

    private void sendLocation(double lat, double lng){
        routePoints.add(new LatLng(lat,lng));
        Koordinate koor= new Koordinate();
        koor.setLat(lat);
        koor.setLng(lng);
        koor.setId_rute(ruta);
        koor.saveEventually();
        Intent intent = new Intent();
        intent.setAction(INTENT_ACTION);
        intent.putParcelableArrayListExtra("lokacija",routePoints);
        sendBroadcast(intent);
        Log.d("nova lokacija prati", "nova lokacija");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
