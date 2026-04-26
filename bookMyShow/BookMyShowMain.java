package bookMyShow;

import bookMyShow.enums.BookingStatus;
import bookMyShow.enums.SeatType;
import bookMyShow.models.*;
import bookMyShow.payment.CreditCardPayment;
import bookMyShow.pricing.BasePricing;
import bookMyShow.services.BookingService;
import bookMyShow.services.ShowService;

import java.time.LocalDateTime;
import java.util.List;

public class BookMyShowMain {
    public static void main(String[] args){
        User user = new User("U1", "Mohit","mohit@gmail.com");

        List<Seat> seats = List.of(
            new Seat("A","1",SeatType.GOLD,500),
            new Seat("A","2",SeatType.GOLD,500),
            new Seat("A","3",SeatType.GOLD,500),
            new Seat("B","1",SeatType.SILVER,400),
            new Seat("B","2",SeatType.SILVER,400)
        );

        Screen screen = new Screen(1,"Screen-1",seats);
        Theatre theatre = new Theatre(1, "PVR Cinemas", "Gwalior", List.of(screen));
        Movie movie = new Movie(1,"Dhurandar", 200, "Hindi",  "Thriller");
        Show show = new Show (1, movie, screen, LocalDateTime.of(2026, 4,16, 18,0) );


        // Available seats for show 
        System.out.println("Available Seats for Show "+ show.getId() + " :");
        for(ShowSeat showSeat: show.getAvailableShowSeats()){
            System.out.println("  "+showSeat.getId());
        }

        BookingService bookingService = BookingService.getInstance();
        ShowService showService = ShowService.getInstance();
        showService.addShow(show);

        //Get shows for movie
        System.out.print("Shows for movie "+ movie.getId()+ " :");
        for( Show s: showService.getShows(movie.getId())){
            System.out.println(" -Show ID: "+ s.getId()+" Time: "+ s.getStartTime());
        }

        Booking booking = bookingService.bookSeats(user, show, List.of("A1","A2"), new BasePricing(), new CreditCardPayment("3434 4324 4324 2342"));
        if(booking.getBookingStatus()==BookingStatus.CONFIRMED){
            System.out.println("["+ booking.getId()+"]"+" Booking confirmed!");
        }else{
            System.out.println("["+ booking.getId()+"]"+" Booking failed!");
        }


        System.out.println("All Bookings: ");
        bookingService.getAllBookings().forEach(b -> System.err.println("  "+ b));


        // Available seats for show 
        System.out.println("Available Seats for Show "+ show.getId() + " :");
        for(ShowSeat showSeat: show.getAvailableShowSeats()){
            System.out.println("  "+showSeat.getId());
        }




    }
}
