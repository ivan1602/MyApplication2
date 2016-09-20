package com.example.ivan.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ivan.myapplication.model.Zahtjev;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoviZahtjevi extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Zahtjev noviZahtjev;

    RadioGroup rg;
    RadioButton btn1;

    TextView ime, prezime, rmjesto, mputovanja, vprojekta;
    TextView akontacija, vprijevoza, oaktivnosti, troskovi, obrazlozenje, podnositelj;

    Button predaj, datumPola, vrijemePola, datumPovr, vrijemePovr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_zahtjevi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rg = (RadioGroup) findViewById(R.id.vrsta_aktivnosti);


        ime = (TextView) findViewById(R.id.Ime);
        prezime = (TextView) findViewById(R.id.Prezime);
        rmjesto = (TextView) findViewById(R.id.Radno_mjesto);
        mputovanja = (TextView) findViewById(R.id.Mjesto_putovanja);
        vprojekta = (TextView) findViewById(R.id.Voditelj_projekta);
        datumPovr = (Button) findViewById(R.id.Datum_povratka);
        vrijemePovr = (Button) findViewById(R.id.Vrijeme_povratka);
        oaktivnosti = (TextView) findViewById(R.id.Opis_aktivnosti);
        akontacija = (TextView) findViewById(R.id.Akontacija);
        vprijevoza = (TextView) findViewById(R.id.Vrsta_prijevoza);
        troskovi = (TextView) findViewById(R.id.Troskovi);
        obrazlozenje = (TextView) findViewById(R.id.Obrazlozenje);
        podnositelj = (TextView) findViewById(R.id.Podnositelj);
        datumPola = (Button) findViewById(R.id.datumPolaska);
        vrijemePola = (Button) findViewById(R.id.vrijemePolaska);


        predaj = (Button) findViewById(R.id.Predaj);
        predaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                noviZahtjev = new Zahtjev();
                int selectedId = rg.getCheckedRadioButtonId();
                btn1 = (RadioButton) findViewById(selectedId);
                btn1.getText();
                noviZahtjev.setVAktiv(btn1.getText().toString());
                noviZahtjev.setIme(ime.getText().toString());
                noviZahtjev.setPrezime(prezime.getText().toString());
                noviZahtjev.setMjPutovanja(mputovanja.getText().toString());
                noviZahtjev.setDPolaska(datumPola.getText().toString());
                noviZahtjev.setVPolaska(vrijemePola.getText().toString());
                noviZahtjev.setVProjekta(vprojekta.getText().toString());
                noviZahtjev.setDPovratka(datumPovr.getText().toString());
                noviZahtjev.setVPovratka(vrijemePovr.getText().toString());
                noviZahtjev.setOAktivnosti(oaktivnosti.getText().toString());
                noviZahtjev.setVPrijevoza(vprijevoza.getText().toString());
                noviZahtjev.setTroskovi(troskovi.getText().toString());
                noviZahtjev.setObrazlozenje(obrazlozenje.getText().toString());
                noviZahtjev.setPodnositelj(podnositelj.getText().toString());

                noviZahtjev.spremi(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Snackbar.make(v, "Uspješno spremljeno", Snackbar.LENGTH_SHORT).show();
                        } else {
                            Snackbar.make(v, e.getMessage(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });


    }

    public void showDatePickerDialog(View v) {
        DialogFragment newDFragment = new DatePickerFragment();

        newDFragment.show(getSupportFragmentManager(), "datumPolaska");


    }

    public void showTimePickerDialog(View v) {

        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "vrijemePolaska");


    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Date date = new Date();
        ((Button) findViewById(R.id.datumPolaska)).setText("");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        String tag;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            tag = this.getTag();
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.clear();
            c.set(year,monthOfYear,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
            if (tag.equals("datumPolaska")) {
                ((Button) getActivity().findViewById(R.id.datumPolaska)).setText(sdf.format(c.getTime()));
            }
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        String tag;
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            tag = getTag();
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar c = Calendar.getInstance();
            c.clear();
            c.set(0,0,0,hourOfDay,minute);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            if (tag.equals("vrijemePolaska")) {
                ((Button) getActivity().findViewById(R.id.vrijemePolaska)).setText(sdf.format(c.getTime()));
            }
        }
    }

}
