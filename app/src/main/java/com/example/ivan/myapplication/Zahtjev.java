package com.example.ivan.myapplication;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ivan on 15.7.2016..
 */
@ParseClassName("Zahtjev")
public class Zahtjev extends ParseObject{
    String aktivnost="aktivnost";
    String Ime;
    String Prezime;
    String Rmjesto;
    String MjPutovanja;
    String VProjekta;
    String DPolaska;
    String VPolaska;
    String DPovratka;
    String VPovratka;
    String Akontacija;
    String OAktivnosti;
    String VPrijevoza;
    String Troskovi;
    String Obrazlozenje;
    String Podnositelj;

    public Zahtjev() {
    }

    public Zahtjev(String aktivnost, String ime, String prezime, String rmjesto, String mjPutovanja, String VProjekta, String DPolaska, String VPolaska, String DPovratka, String VPovratka, String akontacija, String OAktivnosti, String VPrijevoza, String troskovi, String obrazlozenje, String podnositelj) {
        this.aktivnost = aktivnost;
        Ime = ime;
        Prezime = prezime;
        Rmjesto = rmjesto;
        MjPutovanja = mjPutovanja;
        this.VProjekta = VProjekta;
        this.DPolaska = DPolaska;
        this.VPolaska = VPolaska;
        this.DPovratka = DPovratka;
        this.VPovratka = VPovratka;
        Akontacija = akontacija;
        this.OAktivnosti = OAktivnosti;
        this.VPrijevoza = VPrijevoza;
        Troskovi = troskovi;
        Obrazlozenje = obrazlozenje;
        Podnositelj = podnositelj;
    }

    public String getAktivnost() {
        return getString(aktivnost);
    }

    public void setAktivnost(String aktivnost) {
        put(this.aktivnost,aktivnost);
    }

    public String getIme() {
        return getString("ime");
    }

    public void setIme(String ime) {
        put("ime",ime);
    }

    public String getPrezime() {
        return getString("prezime");
    }

    public void setPrezime(String prezime) {
        put("prezime",prezime);
    }

    public String getRmjesto() {
        return Rmjesto;
    }

    public void setRmjesto(String rmjesto) {
        Rmjesto = rmjesto;
    }

    public String getMjPutovanja() {
        return MjPutovanja;
    }

    public void setMjPutovanja(String mjPutovanja) {
        MjPutovanja = mjPutovanja;
    }

    public String getVProjekta() {
        return VProjekta;
    }

    public void setVProjekta(String VProjekta) {
        this.VProjekta = VProjekta;
    }

    public String getDPolaska() {
        return DPolaska;
    }

    public void setDPolaska(String DPolaska) {
        this.DPolaska = DPolaska;
    }

    public String getVPolaska() {
        return VPolaska;
    }

    public void setVPolaska(String VPolaska) {
        this.VPolaska = VPolaska;
    }

    public String getDPovratka() {
        return DPovratka;
    }

    public void setDPovratka(String DPovratka) {
        this.DPovratka = DPovratka;
    }

    public String getVPovratka() {
        return VPovratka;
    }

    public void setVPovratka(String VPovratka) {
        this.VPovratka = VPovratka;
    }

    public String getAkontacija() {
        return Akontacija;
    }

    public void setAkontacija(String akontacija) {
        Akontacija = akontacija;
    }

    public String getOAktivnosti() {
        return OAktivnosti;
    }

    public void setOAktivnosti(String OAktivnosti) {
        this.OAktivnosti = OAktivnosti;
    }

    public String getVPrijevoza() {
        return VPrijevoza;
    }

    public void setVPrijevoza(String VPrijevoza) {
        this.VPrijevoza = VPrijevoza;
    }

    public String getTroskovi() {
        return Troskovi;
    }

    public void setTroskovi(String troskovi) {
        Troskovi = troskovi;
    }

    public String getObrazlozenje() {
        return Obrazlozenje;
    }

    public void setObrazlozenje(String obrazlozenje) {
        Obrazlozenje = obrazlozenje;
    }

    public String getPodnositelj() {
        return Podnositelj;
    }

    public void setPodnositelj(String podnositelj) {
        Podnositelj = podnositelj;
    }

    @Override
    public String toString() {
        return "Zahtjev{" +
                "Ime='" + getIme() + '\'' +
                ", Prezime='" + getPrezime() + '\'' +
                '}';
    }
}
