package com.example.hostlerapp;

public class Hostel {
    String hostel_no;
    String infected;
    String symptomatic;
    double lat;
    double log;

    public Hostel(String hostel_no, String infected, String symptomatic, double lat, double log) {
        this.hostel_no = hostel_no;
        this.infected = infected;
        this.symptomatic = symptomatic;
        this.lat = lat;
        this.log = log;
    }

    public String getHostel_no() {
        return hostel_no;
    }

    public void setHostel_no(String hostel_no) {
        this.hostel_no = hostel_no;
    }

    public String getInfected() {
        return infected;
    }

    public void setInfected(String infected) {
        this.infected = infected;
    }

    public String getSymptomatic() {
        return symptomatic;
    }

    public void setSymptomatic(String symptomatic) {
        this.symptomatic = symptomatic;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }
}
