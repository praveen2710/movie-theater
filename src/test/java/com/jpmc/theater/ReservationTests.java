package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        int audienceCount = 3;
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now().withHour(9).withMinute(30)
        );
        assertEquals( (12.5-3)*audienceCount,new Reservation(customer, showing, audienceCount).totalFee());
    }

    @Test
    void testTimedDiscount(){
        var customer = new Customer("John Doe", "unused-id");
        int audienceCount = 3;
        Movie movie =  new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        var showing = new Showing(
                movie,
                4,
                LocalDateTime.now().withHour(11).withMinute(0)
        );
        assertEquals( (movie.getTicketPrice()*0.75)*audienceCount,new Reservation(customer, showing, audienceCount).totalFee());
    }

    @Test
    void testOverlappingDiscount(){
        var customer = new Customer("John Doe", "unused-id");
        int audienceCount = 3;
        Movie movie =  new Movie("Turning Red", Duration.ofMinutes(85), 2, 1);
        var showing = new Showing(
                movie,
                7,
                LocalDateTime.now().withHour(11).withMinute(0)
        );
        double expectedTicketPrice = Math.min(movie.getTicketPrice()-1,movie.getTicketPrice()*0.75);
        assertEquals( expectedTicketPrice*audienceCount,new Reservation(customer, showing, audienceCount).totalFee());
    }
}
