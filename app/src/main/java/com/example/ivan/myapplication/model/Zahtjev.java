package com.example.ivan.myapplication.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ivan on 15.7.2016..
 */
@ParseClassName("Zahtjev")
public class Zahtjev extends BaseModel{

    String Ime="ime";
    String Prezime="prezime";
    String Rmjesto="rmjesto";
    String MjPutovanja="mjputovanja";
    String VProjekta="vprojekta";
    String DPolaska="dpolaska";
    String VPolaska="vpolaska";
    String DPovratka="dpolaska";
    String VPovratka="vpovratka";
    String Akontacija="akontacija";
    String OAktivnosti="oaktivnosti";
    String VPrijevoza="vprijevoza";
    String Troskovi="troskovi";
    String Obrazlozenje="obrazlozenje";
    String Podnositelj="podnositelj";

    public Zahtjev() {


       }



    public String getIme()
    {
        return getString(Ime);
    }

    public void setIme(String ime) {
        put(Ime,ime);
    }

    public String getPrezime()
    {
        return getString(Prezime);
    }

    public void setPrezime(String prezime)
    {
        put(Prezime,prezime);
    }

    public String getRmjesto()
    {  return getString(Rmjesto);
    }

    public void setRmjesto(String rmjesto) {
       put( Rmjesto, rmjesto);
    }

    public String getMjPutovanja() {
        return getString(MjPutovanja);
    }

    public void setMjPutovanja(String mjputovanja) {
        put (MjPutovanja, mjputovanja);
    }

    public String getVProjekta() {
        return getString( VProjekta);
    }

    public void setVProjekta(String vProjekta)
    {
       put(this.VProjekta,vProjekta);
    }

    public String getDPolaska() {
        return getString (DPolaska);
    }

    public void setDPolaska(String DPolaska) {
       put (this.DPolaska, DPolaska);
    }

    public String getVPolaska() {
       return getString (VPolaska);
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
