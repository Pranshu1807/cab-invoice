package com.example;

public class Ride {
    public double distance, time;
    public boolean premimum = false;

    public Ride(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }

    public Ride(double distance, double time, boolean premimum) {
        this.distance = distance;
        this.time = time;
        this.premimum = premimum;
    }

}
