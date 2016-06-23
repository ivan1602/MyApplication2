package com.example.ivan.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by Ivan on 23.6.2016..
 */
public class DatabaseHandler {

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "Baza ruta";
    private static final int DATABASE_VERSION = 3;
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DatabaseHandler(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);    }



    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper (Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Ruta.DATABASE_CREATE);
            db.execSQL(Koordinate.DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS "+ Ruta.DATABASE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "+ Koordinate.DATABASE_TABLE);
            onCreate(db);
        }
    }

    public DatabaseHandler openDB () throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void closeDB () { DBHelper.close();}

    public int DodajRutu ( String imekorisnika, String imerute )
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Ruta.KEY_IME_KORISNIKA, imekorisnika);
        initialValues.put(Ruta.KEY_IME_RUTE, imerute);
        int id = (int ) db.insert(Ruta.DATABASE_TABLE, null, initialValues);
        return id;
    }


    public ArrayList<Ruta> sveRute(String imekorisnika)
    {
        Cursor c=db.query(Ruta.DATABASE_TABLE, new String[] {
                        Ruta.KEY_ROWID,
                        Ruta.KEY_IME_KORISNIKA,
                        Ruta.KEY_IME_RUTE
                },
                Ruta.KEY_IME_KORISNIKA + "='" + imekorisnika+"'",
                null,
                null,
                null, null);
        ArrayList<Ruta> sveRute = new ArrayList<Ruta>();
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex(Ruta.KEY_ROWID));
            String imerute= c.getString(c.getColumnIndex(Ruta.KEY_IME_RUTE));
            sveRute.add(new Ruta(id ,imerute, imekorisnika, null, null));
        }
        return sveRute;
    }

    public void BrisiRutu (int id){

        db.delete(Ruta.DATABASE_TABLE,  Ruta.KEY_ROWID + "=" + id, null);
        db.delete(Koordinate.DATABASE_TABLE, Koordinate.KEY_ID_RUTE + " =" + id, null);
    }
    public void DodajKoordinate ( int idrute , double latitude , double longitude )
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(Koordinate.KEY_ID_RUTE, idrute);
        initialValues.put(Koordinate.KEY_LAT, latitude);
        initialValues.put(Koordinate.KEY_LONG, longitude);
        db.insert(Koordinate.DATABASE_TABLE, null, initialValues);
    }
    public ArrayList<Koordinate> DohvatiKoordinate(int idrute)
    {
        Cursor c=db.query(Koordinate.DATABASE_TABLE, new String[] {
                        Koordinate.KEY_ROWID,
                        Koordinate.KEY_ID_RUTE,
                        Koordinate.KEY_LAT,
                        Koordinate.KEY_LONG
                },
                Koordinate.KEY_ID_RUTE + "=" + idrute,
                null,
                null,
                null, null);
        ArrayList<Koordinate> DohvatiKoordinate = new ArrayList<Koordinate>();
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex(Koordinate.KEY_ROWID));
            double latitude = c.getDouble(c.getColumnIndex(Koordinate.KEY_LAT));
            double longitude = c.getDouble(c.getColumnIndex(Koordinate.KEY_LONG));
            DohvatiKoordinate.add(new Koordinate(latitude, longitude, id, idrute));
        }
        return DohvatiKoordinate;
    }

}
