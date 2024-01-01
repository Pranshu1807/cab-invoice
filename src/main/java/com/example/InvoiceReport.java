package com.example;

public class InvoiceReport {
    public double avgFare, totalFare;
    public int noOfRides;

    public InvoiceReport(double totalFare, int noOfRides, double avgFare) {
        this.totalFare = totalFare;
        this.noOfRides = noOfRides;
        this.avgFare = avgFare;
    }
}
