package com.example.ivan.myapplication;

import java.sql.Date;

/**
 * Created by Ivan on 23.6.2016..
 */
public class Ruta {
    public static final String  KEY_ROWID = "id";
    public static final String KEY_IME_KORISNIKA = "ime_korisnika";
    public static final String KEY_IME_RUTE = "ime_rute";
    public static final String DATABASE_TABLE = "Ruta";
    public static final String KEY_START_RUTE = "startRuteTime";
    public static final String KEY_STOP_RUTE = "stopRuteTime";

    public static final String DATABASE_CREATE =
            "CREATE TABLE "  + DATABASE_TABLE + " ("+KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_IME_KORISNIKA + " TEXT, "  + KEY_IME_RUTE + " TEXT " +KEY_START_RUTE+ " DATETIME, " +KEY_STOP_RUTE+ " DATETIME "  +")";

    int id;
    String ime_rute, ime_korisnika;
    Date startTime, stopTime;

    public static String getKeyStartRute() {
        return KEY_START_RUTE;
    }
    public static String getKeyStopRute() {
        return KEY_STOP_RUTE;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIme_rute() {
        return ime_rute;
    }
    public void setIme_rute(String ime_rute) {
        this.ime_rute = ime_rute;
    }
    public String getIme_korisnika() {
        return ime_korisnika;
    }
    public void setIme_korisnika(String ime_korisnika) {
        this.ime_korisnika = ime_korisnika;
    }
    public Ruta(int id, String ime_rute, String ime_korisnika, Date startTime,
                Date stopTime) {
        super();
        this.id = id;
        this.ime_rute = ime_rute;
        this.ime_korisnika = ime_korisnika;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
}
