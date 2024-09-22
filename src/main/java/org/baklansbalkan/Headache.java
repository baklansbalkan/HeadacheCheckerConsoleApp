package org.baklansbalkan;

import java.io.Serializable;
import java.time.LocalDate;

public class Headache implements Serializable {

    private LocalDate date;
    private boolean isHeadache;
    private String medicine;
    private int intensity;
    private String localisation;
    private boolean physicalActivity;
    private boolean nausea;
    private boolean photophobia;
    private boolean phonophobia;
    private String timesOfDay;
    private String comment;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getHeadache() {
        return isHeadache;
    }

    public void setHeadache(boolean headache) {
        isHeadache = headache;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public boolean isPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(boolean physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public boolean isNausea() {
        return nausea;
    }

    public void setNausea(boolean nausea) {
        this.nausea = nausea;
    }

    public boolean isPhotophobia() {
        return photophobia;
    }

    public void setPhotophobia(boolean photophobia) {
        this.photophobia = photophobia;
    }

    public boolean isPhonophobia() {
        return phonophobia;
    }

    public void setPhonophobia(boolean phonophobia) {
        this.phonophobia = phonophobia;
    }

    public String getTimesOfDay() {
        return timesOfDay;
    }

    public void setTimesOfDay(String timesOfDay) {
        this.timesOfDay = timesOfDay;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "DATE: " + date  +
                ": Headache: " + isHeadache +
                "; Medicine: " + medicine +
                "; Intensity from 1 to 5: " + intensity +
                "; Localisation: " + localisation +
                "; Physical activity: " + physicalActivity +
                "; Nausea: " + nausea +
                "; Photophobia: " + photophobia +
                "; Phonophobia: " + phonophobia +
                "; Started: " + timesOfDay +
                "; Extra info: " + comment + ";";
    }
}



