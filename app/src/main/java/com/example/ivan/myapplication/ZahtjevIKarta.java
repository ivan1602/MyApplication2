package com.example.ivan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.ivan.myapplication.model.Zahtjev;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;


public class ZahtjevIKarta extends AppCompatActivity {

    TextView Aktivnost, Ime, Prezime,RMjesto, MPutovanja, Vprojekta, DPolaska, VPolaska, DPovratka, VPovratka;
    TextView Akontacija, OAktivnosti, VPrijevoza, TTerete, Obrazlozenje, PZahtjeva, Odobrio;

    Button pregledKarte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pregled_zahtjeva_rute);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // Aktivnost = (TextView)findViewById(R.id.Aktivnost);
        Ime = (TextView)findViewById(R.id.Name);
        Prezime = (TextView)findViewById(R.id.Lastname);
        RMjesto = (TextView)findViewById(R.id.WorkPlace);
        MPutovanja = (TextView)findViewById(R.id.TravelPlace);
        Vprojekta =(TextView)findViewById(R.id.ProjectManager);
        DPolaska =(TextView)findViewById(R.id.StartDate);
        VPolaska =(TextView)findViewById(R.id.StartTime);
        DPovratka =(TextView)findViewById(R.id.ReturnDate);
        VPovratka =(TextView)findViewById(R.id.ReturnTime);
        Akontacija =(TextView)findViewById(R.id.Funds);
        OAktivnosti =(TextView)findViewById(R.id.ActivityD);
        VPrijevoza=(TextView)findViewById(R.id.Transportation);
        TTerete =(TextView)findViewById(R.id.Expenses);
        Obrazlozenje =(TextView)findViewById(R.id.Description);
        PZahtjeva = (TextView)findViewById(R.id.Person);
       // Odobrio = (TextView)findViewById(R.id.Approved);
        final String idZahtjeva=getIntent().getStringExtra("idZahtjeva");
        pregledKarte = (Button)findViewById(R.id.PregledKarte);
        pregledKarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PokaziKartu = new Intent(ZahtjevIKarta.this, PokaziNaKarti.class);
                PokaziKartu.putExtra("idZahtjeva",idZahtjeva);
                startActivity(PokaziKartu);
            }
        });


        ParseQuery<Zahtjev> query = ParseQuery.getQuery(Zahtjev.class);
        query.getInBackground(idZahtjeva, new GetCallback<Zahtjev>() {
            @Override
            public void done(Zahtjev object, ParseException e) {
                setValues(object);
            }
        });
    }

    private void setValues(Zahtjev z) {
        //Aktivnost.setText(z.getVAktiv());
        Ime.setText(z.getIme());
        Prezime.setText(z.getPrezime());
        RMjesto.setText(z.getRmjesto());
        MPutovanja.setText(z.getMjPutovanja());
        Vprojekta.setText(z.getVProjekta());
        DPolaska.setText(z.getDPolaska());
        VPolaska.setText(z.getVPolaska());
        DPovratka.setText(z.getDPovratka());
        VPovratka.setText(z.getVPovratka());
        Akontacija.setText(z.getAkontacija());
        OAktivnosti.setText(z.getOAktivnosti());
        VPrijevoza.setText(z.getVPrijevoza());
        TTerete.setText(z.getTroskovi());
        Obrazlozenje.setText(z.getObrazlozenje());
        PZahtjeva.setText(z.getPodnositelj());
       // Odobrio.setText(z.getOdobrio());


    }

}
