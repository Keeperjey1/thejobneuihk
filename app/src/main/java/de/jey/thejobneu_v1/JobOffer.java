package de.jey.thejobneu_v1;

import java.util.SplittableRandom;

public class JobOffer {
    private final String beruf;
    private final String betriebsart;
    private final String ort;
    private final String verfuegbarkeit;
    public JobOffer(String beruf, String betriebsart, String ort, String verfuegbarkeit) {
        this.beruf = beruf;
        this.betriebsart = betriebsart;
        this.ort =ort;
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public String getBeruf() {
        return beruf;
    }

    public String getBetriebsart() {
        return betriebsart;
    }

    public String getOrt() {
        return ort;
    }
    public String getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    @Override
    public String toString() {
        return "JobOffer{" +
                "beruf='" + beruf + '\'' +
                ", betriebsart='" + betriebsart + '\'' +
                ", ort='" + ort + '\'' +
                ", verfuegbarkeit='" + verfuegbarkeit + '\'' +
                '}';
    }

    /*public String toString() {
        String output = beruf + " - " + betriebsart + " - " + ort + " - " + verfuegbarkeit;
        return output;
    }*/
    /*public String toString() {
        String output = beruf + " - " + betriebsart + " - " + ort + " - " + verfuegbarkeit;
        return output;
    }*/
}
