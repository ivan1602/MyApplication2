package com.example.ivan.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ivan.myapplication.model.Ruta;

import java.util.ArrayList;

public class PregledBazeActivity extends Activity {
    Button namapi;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_baze);
        listView= (ListView)findViewById(R.id.listView1);

        String imekorisnika = getSharedPreferences("postavke", Context.MODE_PRIVATE).getString("korisnik", "");
//        DatabaseHandler baza= new DatabaseHandler(this);
        //baza.openDB();
//        final ArrayList<Ruta> listRuta= baza.sveRute(imekorisnika);
//        baza.closeDB();
    /*    ArrayList<String> list= new ArrayList<String>();
        for(Ruta ruta:listRuta){
            list.add("Ime rute: "+ruta.getIme_rute());
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent i= new Intent(PregledBazeActivity.this,PokaziNaKarti.class);
                i.putExtra("idrute", listRuta.get(position).getId());
                startActivity(i);
            }
        });
*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
