package com.example;

public class CabInvoice {

    public static double pricePerKm = 10.0, perPerMin = 1.0, minFare = 5.0;

    public double calculateFare(Ride ride) {
        double totalFare = ride.distance * pricePerKm + ride.time * perPerMin;
        return Math.max(totalFare, minFare);
    }

    public double total(Ride[] rides) {
        double sum = 0;
        for (Ride ride : rides) {
            sum += calculateFare(ride);
        }
        return sum;
    }
}
