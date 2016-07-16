package com.example.ivan.myapplication;

import android.app.Application;

import com.example.ivan.myapplication.model.Koordinate;
import com.example.ivan.myapplication.model.Ruta;
import com.example.ivan.myapplication.model.Zahtjev;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseRole;

/**
 * Created by Jack on 22.6.2016..
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Zahtjev.class);
        ParseObject.registerSubclass(Ruta.class);
        ParseObject.registerSubclass(Koordinate.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("1rYGdTn2AMtXPOPrVbnooFVmpC0EE0CNllTTC2xC")
                .clientKey("o78lIY0qDURGlvmCPpO90G0sfR9KeRwSXsMaBIK1")
                .build());

    }
}
