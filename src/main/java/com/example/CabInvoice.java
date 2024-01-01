package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class CabInvoice {

    public static double pricePerKm = 10.0, perPerMin = 1.0, minFare = 5.0, perPerMinPrem = 2.0, pricePerKmPrem = 15.0,
            minFarePrem = 20.0;
    public static HashMap<String, ArrayList<Ride>> rideRepo = new HashMap<>();

    public double calculateFare(Ride ride) {
        double totalFare;
        if (ride.premimum == false)
            totalFare = Math.max(ride.distance * pricePerKm + ride.time * perPerMin, minFare);
        else
            totalFare = Math.max(ride.distance * pricePerKmPrem + ride.time * perPerMinPrem, minFarePrem);
        return totalFare;
    }

    public void addRide(String user, double distance, double time) {
        if (!rideRepo.containsKey(user)) {
            rideRepo.put(user, new ArrayList<Ride>());
        }
        Ride ride = new Ride(distance, time);
        rideRepo.get(user).add(ride);
    }

    public void addRide(String user, double distance, double time, boolean premimum) {
        if (!rideRepo.containsKey(user)) {
            rideRepo.put(user, new ArrayList<Ride>());
        }
        Ride ride = new Ride(distance, time, premimum);
        rideRepo.get(user).add(ride);
    }

    public double total(Ride[] rides) {
        double sum = 0;
        for (Ride ride : rides) {
            sum += calculateFare(ride);
        }
        return sum;
    }

    public int noOfRides(Ride[] rides) {
        return rides.length;
    }

    public double avgFare(Ride[] ride) {
        return total(ride) / (double) noOfRides(ride);
    }

    public InvoiceReport getReport(Ride[] rides) {
        return new InvoiceReport(total(rides), noOfRides(rides), avgFare(rides));
    }

    public InvoiceReport getReportByUser(String name) {
        if (!rideRepo.containsKey(name)) {
            return new InvoiceReport(0.0, 0, 0.0);
        }
        ArrayList<Ride> temp = rideRepo.get(name);
        Ride[] rides = new Ride[temp.size()];
        rides = temp.toArray(rides);
        return new InvoiceReport(total(rides), noOfRides(rides), avgFare(rides));
    }
}
