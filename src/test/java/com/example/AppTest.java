package com.example;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AppTest {

    public static CabInvoice cabInvoice;

    @BeforeAll
    public static void initialization() {
        cabInvoice = new CabInvoice();
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 10.0, 60.0",
            "10.2, 25.0, 127.0",
            "1.5, 4.0, 19.0",
            "0.2, 2.0, 5.0",
            "5.6,15 ,71.0"
    })
    public void fareTest(double distance, double time, double expected) {
        Ride ride = new Ride(distance, time);
        double fare = cabInvoice.calculateFare(ride);
        assertEquals(expected, fare, 0);
    }

    @Test
    public void aggTotalTest() {
        CabInvoice cabInvoice = new CabInvoice();
        Ride[] rides = new Ride[] { new Ride(5.0, 10.0), new Ride(2.0, 6.0), new Ride(0.2, 1.0) };
        double fare = cabInvoice.total(rides);
        assertEquals(91.0, fare, 0);
    }

    @Test
    public void enhancedInvoice() {
        CabInvoice cabInvoice = new CabInvoice();
        Ride[] rides = new Ride[] { new Ride(5.0, 10.0), new Ride(2.0, 5.0), new Ride(0.2, 1.0), new Ride(3, 10) };
        InvoiceReport Calculated = cabInvoice.getReport(rides);
        InvoiceReport Expected = new InvoiceReport(130.0, 4, 32.5);

        assertEquals(true, Calculated.totalFare == Expected.totalFare && Calculated.noOfRides == Expected.noOfRides
                && Calculated.avgFare == Expected.avgFare);
    }

}
