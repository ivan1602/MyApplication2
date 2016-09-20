package com.example.ivan.myapplication.model;


import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Ivan on 15.7.2016..
 */
@ParseClassName("Zahtjev")
public class Zahtjev extends BaseModel{


    String VAktiv = "vaktivnosti";
    String Ime="ime";
    String Prezime="prezime";
    String Rmjesto="rmjesto";
    String MjPutovanja="mjputovanja";
    String VProjekta="vprojekta";
    String DPolaska="dpolaska";
    String VPolaska="vpolaska";
    String DPovratka="dpovratka";
    String VPovratka="vpovratka";
    String Akontacija="akontacija";
    String OAktivnosti="oaktivnosti";
    String VPrijevoza="vprijevoza";
    String Troskovi="troskovi";
    String Obrazlozenje="obrazlozenje";
    String Podnositelj="podnositelj";
    String Odobrio = "odobrio";



    public Zahtjev() {


       }

    public String getVAktiv() {
        return  getString (VAktiv);
    }

    public void setVAktiv(String vaktivnosti) {
        put (VAktiv, vaktivnosti);
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

    public void setVProjekta(String vprojekta)
    {
       put(VProjekta,vprojekta);
    }

    public String getDPolaska() {
        return getString (DPolaska);
    }

    public void setDPolaska(String dpolaska) {
       put (DPolaska, dpolaska);
            }

    public String getVPolaska() {
       return getString (VPolaska);
    }

    public void setVPolaska(String vpolaska)
    {
        put(VPolaska, vpolaska);
    }

    public String getDPovratka() {
        return getString(DPovratka);
    }

    public void setDPovratka(String dpovratka)
    {
        put (DPovratka, dpovratka);
    }

    public String getVPovratka() {
        return getString( VPovratka) ;
    }

    public void setVPovratka(String vpovratka)
    {
        put (VPovratka , vpovratka);
    }

    public String getAkontacija() {
        return getString (Akontacija);
    }

    public void setAkontacija(String akontacija) {
       put  (Akontacija, akontacija);
    }

    public String getOAktivnosti() {
        return getString (OAktivnosti);
    }

    public void setOAktivnosti(String oaktivnosti) {
        put (OAktivnosti, oaktivnosti);
    }

    public String getVPrijevoza() {
        return getString( VPrijevoza);
    }

    public void setVPrijevoza(String vprijevoza) {
        put (VPrijevoza, vprijevoza);
    }

    public String getTroskovi() {
        return getString(Troskovi);
    }

    public void setTroskovi(String troskovi) {
       put (Troskovi ,troskovi);
    }

    public String getObrazlozenje() {
        return getString (Obrazlozenje);
    }

    public void setObrazlozenje(String obrazlozenje) {
       put (Obrazlozenje , obrazlozenje);
    }

    public String getPodnositelj() {
        return getString(Podnositelj);
    }

    public void setPodnositelj(String podnositelj) {
       put (Podnositelj, podnositelj);
    }

    public String getOdobrio () {return getString (Odobrio);    }

    public void setOdobrio(String odobrio) {
       put (Odobrio, odobrio);
    }

    @Override
    public String toString() {
        return "Mjesto putovanja:" + getMjPutovanja() + '\'' +
                ", Datum:" + getDPolaska() + '\'';
    }
}
