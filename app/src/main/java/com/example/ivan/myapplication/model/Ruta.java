package com.example.ivan.myapplication.model;

import com.parse.ParseClassName;

import java.util.Date;


/**
 * Created by Ivan on 23.6.2016..
 */
@ParseClassName("Ruta")
public class Ruta extends BaseModel {
    public static final String KEY_IME_RUTE = "ime_rute";
    public static final String KEY_START_RUTE = "startRuteTime";
    public static final String KEY_STOP_RUTE = "stopRuteTime";


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

    public String getImeRute() {
        return getString(KEY_IME_RUTE);
    }
    public void setImeRute(String ime_rute) {
        put(this.KEY_IME_RUTE ,ime_rute);
    }

    public Ruta() {

    }
}
