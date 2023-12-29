package com.example;

public class Ride {
    public double distance, time;
    public static double pricePerKm = 10.0, perPerMin = 1.0, minFare = 5.0;

    public Ride(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }

    public double totalFare() {
        return Math.max(minFare, (distance * 10 + time));
    }
}
