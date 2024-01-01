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

    @Test
    public void userInvoiceTest() {
        CabInvoice cabInvoice = new CabInvoice();
        cabInvoice.addRide("User1", 5.0, 10.0);
        cabInvoice.addRide("User1", 4.0, 8.0);
        cabInvoice.addRide("User2", 8.0, 20.0);
        cabInvoice.addRide("User2", 0.3, 1.0);

        InvoiceReport CalculatedUser1 = cabInvoice.getReportByUser("User1");
        InvoiceReport ExpectedUser1 = new InvoiceReport(108.0, 2, 54.0);
        assertEquals(true,
                CalculatedUser1.totalFare == ExpectedUser1.totalFare
                        && CalculatedUser1.noOfRides == ExpectedUser1.noOfRides
                        && CalculatedUser1.avgFare == ExpectedUser1.avgFare);

        InvoiceReport CalculatedUser2 = cabInvoice.getReportByUser("User2");
        InvoiceReport ExpectedUser2 = new InvoiceReport(105.0, 2, 52.5);
        assertEquals(true,
                CalculatedUser2.totalFare == ExpectedUser2.totalFare
                        && CalculatedUser2.noOfRides == ExpectedUser2.noOfRides
                        && CalculatedUser2.avgFare == ExpectedUser2.avgFare);
        InvoiceReport CalculatedUser3 = cabInvoice.getReportByUser("User3");
        InvoiceReport ExpectedUser3 = new InvoiceReport(0.0, 0, 0.0);
        assertEquals(true,
                CalculatedUser3.totalFare == ExpectedUser3.totalFare
                        && CalculatedUser3.noOfRides == ExpectedUser3.noOfRides
                        && CalculatedUser3.avgFare == ExpectedUser3.avgFare);
    }

}
