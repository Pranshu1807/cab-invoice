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
            "5.0, 10.0,false, 60.0",
            "10.2, 25.0,false, 127.0",
            "2, 4.0, true,38.0",
            "0.2, 2.0,true, 20.0",
            "5.6,15 ,false,71.0"
    })
    public void fareTest(double distance, double time, boolean premimum, double expected) {
        Ride ride = new Ride(distance, time, premimum);
        double fare = cabInvoice.calculateFare(ride);
        assertEquals(expected, fare, 0);
    }

    @Test
    public void aggTotalTest() {
        CabInvoice cabInvoice = new CabInvoice();
        Ride[] rides = new Ride[] { new Ride(5.0, 10.0), new Ride(2.0, 6.0), new Ride(0.2, 1.0, true) };
        double fare = cabInvoice.total(rides);
        assertEquals(106.0, fare, 0);
    }

    @Test
    public void enhancedInvoice() {
        CabInvoice cabInvoice = new CabInvoice();
        Ride[] rides = new Ride[] { new Ride(5.0, 10.0), new Ride(2.0, 5.0, true), new Ride(0.2, 1.0),
                new Ride(3, 10, true) };
        InvoiceReport Calculated = cabInvoice.getReport(rides);
        InvoiceReport Expected = new InvoiceReport(170.0, 4, 42.5);

        assertEquals(Expected.totalFare, Calculated.totalFare, 0);
        assertEquals(Expected.noOfRides, Calculated.noOfRides);
        assertEquals(Expected.avgFare, Calculated.avgFare, 0);
    }

    @Test
    public void userInvoiceTest() {
        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.addRide("User1", 5.0, 10.0);
        cabInvoice.addRide("User1", 4.0, 8.0);
        cabInvoice.addRide("User2", 8.0, 20.0);
        cabInvoice.addRide("User2", 0.3, 1.0);
        cabInvoice.addRide("User3", 3.0, 10.0, true);
        cabInvoice.addRide("User3", 0.3, 1.0, true);

        InvoiceReport CalculatedUser1 = cabInvoice.getReportByUser("User1");
        InvoiceReport ExpectedUser1 = new InvoiceReport(108.0, 2, 54.0);
        assertEquals(ExpectedUser1.totalFare, CalculatedUser1.totalFare, 0);
        assertEquals(ExpectedUser1.noOfRides, CalculatedUser1.noOfRides);
        assertEquals(ExpectedUser1.avgFare, CalculatedUser1.avgFare, 0);

        InvoiceReport CalculatedUser2 = cabInvoice.getReportByUser("User2");
        InvoiceReport ExpectedUser2 = new InvoiceReport(105.0, 2, 52.5);
        assertEquals(ExpectedUser2.totalFare, CalculatedUser2.totalFare, 0);
        assertEquals(ExpectedUser2.noOfRides, CalculatedUser2.noOfRides);
        assertEquals(ExpectedUser2.avgFare, CalculatedUser2.avgFare, 0);

        InvoiceReport CalculatedUser3 = cabInvoice.getReportByUser("User3");
        InvoiceReport ExpectedUser3 = new InvoiceReport(85.0, 2, 42.5);
        assertEquals(ExpectedUser3.totalFare, CalculatedUser3.totalFare, 0);
        assertEquals(ExpectedUser3.noOfRides, CalculatedUser3.noOfRides);
        assertEquals(ExpectedUser3.avgFare, CalculatedUser3.avgFare, 0);
    }

}
