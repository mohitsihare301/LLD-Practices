package makeMyTrip;
import java.time.LocalDate;
import java.util.*;

import makeMyTrip.enums.PaymentMode;
import makeMyTrip.models.*;
import makeMyTrip.services.BookingService;
import makeMyTrip.search.SearchCriteria;
import makeMyTrip.services.FlightService;;

public class MakeMyTripMain {
    public static void main(String[] args){

        City Delhi = new City("C1", "Delhi");
        City Mumbai = new City("C2", "Mumbai");

        User Mohit = new User("U1", "Mohit", "mohit@gmail.com");
        User Himanshu = new User("U2", "Himanshu", "himanshu@gmail.com");

        FlightService flightService = FlightService.getInstance();
        BookingService bookingService = BookingService.getInstance();

        SearchCriteria criteria = new SearchCriteria.Builder().from(Delhi).to(Mumbai).on(LocalDate.now().plusDays(1)).passengers(2).build();

        System.out.println("Available flights from Delhi to Mumbai tomorrow");
        List<Flight> flights = flightService.search(criteria);

        for(Flight flight: flights){
            flightService.displayFlightDetails(flight.getId());
        }

        Booking b1 = bookingService.createBooking(Mohit, flights.get(0), List.of(new Passenger("Yash", 34, "Male"), new Passenger("Shailesh", 45, "Male")), List.of("1A, 2B"));
        bookingService.confirmBooking(b1, PaymentMode.CREDIT_CARD);

        Booking b2 = bookingService.createBooking(Himanshu, flights.get(0), List.of(new Passenger("Yash", 34, "Male"), new Passenger("Shailesh", 45, "Male")), List.of("1A, 2B"));
        bookingService.confirmBooking(b2, PaymentMode.CREDIT_CARD);

        for(Flight flight: flights){
            flightService.displayFlightDetails(flight.getId());
        }

    }
}
