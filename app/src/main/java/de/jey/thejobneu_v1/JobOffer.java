package de.jey.thejobneu_v1;

import java.util.SplittableRandom;

public class JobOffer {
    private String beruf;
    private String betriebsart;
    private String ort;
    private String verfuegbarkeit;
    public JobOffer(String beruf, String betriebsart, String ort, String verfuegbarkeit) {
        this.beruf = beruf;
        this.betriebsart = betriebsart;
        this.ort =ort;
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public String getBeruf() {
        return beruf;
    }

    public void setBeruf(String beruf) {
        this.beruf = beruf;
    }

    public String getBetriebsart() {
        return betriebsart;
    }

    public void setBetriebsart(String betriebsart) {
        this.betriebsart = betriebsart;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    public void setVerfuegbarkeit(String verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }



}
