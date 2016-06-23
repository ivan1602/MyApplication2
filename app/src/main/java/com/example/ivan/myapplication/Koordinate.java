package com.example.ivan.myapplication;

/**
 * Created by Ivan on 23.6.2016..
 */
public class Koordinate {

    public static final String  KEY_ROWID = "id";
    public static final String KEY_ID_RUTE = "id_rute";
    public static final String KEY_LONG = "longitude";
    public static final String KEY_LAT = "latitude";

    public static final String DATABASE_TABLE = "Koordinate";

    public static final String DATABASE_CREATE =
            "CREATE TABLE "  + DATABASE_TABLE + " ("+ KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_ID_RUTE + " INTEGER, "  + KEY_LONG + " REAL, " +KEY_LAT+ " REAL" +")";



    double lat,lng;
    int id, id_rute;
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId_rute() {
        return id_rute;
    }
    public void setId_rute(int id_rute) {
        this.id_rute = id_rute;
    }
    public Koordinate(double lat, double lng, int id, int id_rute) {
        super();
        this.lat = lat;
        this.lng = lng;
        this.id = id;
        this.id_rute = id_rute;
    }

}
