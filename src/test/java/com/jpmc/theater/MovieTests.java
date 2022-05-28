package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        assertEquals(spiderMan.getTicketPrice()*0.8, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void testTimed25PercentDiscount(){
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
