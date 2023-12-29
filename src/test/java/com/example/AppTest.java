package com.example;

import static org.junit.Assert.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AppTest {

    @ParameterizedTest
    @CsvSource({
            "5.0, 10.0, 60.0",
            "10.2, 25.0, 127.0",
            "1.5, 4.0, 19.0",
            "0.2, 2.0, 5.0"
    })
    public void fareTest(double distance, double time, double expected) {
        Ride ride = new Ride(distance, time);
        double fare = ride.totalFare();
        assertEquals(expected, fare, 0);
    }

}
