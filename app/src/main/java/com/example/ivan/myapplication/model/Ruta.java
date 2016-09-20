package com.example.ivan.myapplication.model;

import com.parse.ParseClassName;

import java.util.Date;


/**
 * Created by Ivan on 23.6.2016..
 */
@ParseClassName("Ruta")
public class Ruta extends BaseModel {
    public static final String KEY_START_RUTE = "startRuteTime";
    public static final String KEY_STOP_RUTE = "stopRuteTime";
    public static final String KEY_ZAHTJEV = "zahtjev";


    public Date getStartRute() {
        return getDate(KEY_START_RUTE);
    }
    public void setStartRute(Date date){
        put(KEY_START_RUTE,date);
    }
    public void setStopRute(Date date){
        put(KEY_STOP_RUTE,date);
    }
    public Date getStopRute() {
        return getDate(KEY_STOP_RUTE);
    }


    public void setZahtjev(Zahtjev zahtjev){put(KEY_ZAHTJEV,zahtjev);}

    public Zahtjev getZahtjev(){return (Zahtjev) getParseObject(KEY_ZAHTJEV);}

    public Ruta() {

    }
}
