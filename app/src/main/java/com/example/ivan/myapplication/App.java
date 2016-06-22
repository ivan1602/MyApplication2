package com.example.ivan.myapplication;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Jack on 22.6.2016..
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("1rYGdTn2AMtXPOPrVbnooFVmpC0EE0CNllTTC2xC").clientKey("o78lIY0qDURGlvmCPpO90G0sfR9KeRwSXsMaBIK1").build());

    }
}
