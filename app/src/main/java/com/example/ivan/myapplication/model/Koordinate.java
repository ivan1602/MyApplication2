package com.example.ivan.myapplication.model;

import com.parse.ParseClassName;

/**
 * Created by Ivan on 23.6.2016..
 */
@ParseClassName("Koordinata")
public class Koordinate extends BaseModel{

    public static final String KEY_ID_RUTE = "id_rute";
    public static final String KEY_LONG = "longitude";
    public static final String KEY_LAT = "latitude";


    public double getLat() {
        return getDouble(KEY_LAT);
    }
    public void setLat(double lat) {
        put(KEY_LAT, lat);
    }
    public double getLng() {
        return getDouble(KEY_LONG);
    }

    public void setLng(double lng) {
        put(KEY_LONG, lng);
    }

    public Ruta getRuta() {
        return (Ruta)getParseObject(KEY_ID_RUTE);
    }

    public void setId_rute(Ruta ruta) {
        put(KEY_ID_RUTE,ruta);
    }

    public Koordinate() {
    }
}
