package makeMyTrip.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import makeMyTrip.enums.SeatStatus;
import makeMyTrip.models.City;
import makeMyTrip.enums.SeatClass;
import makeMyTrip.models.Aircraft;
import makeMyTrip.models.Airport;
import makeMyTrip.models.Airline;
import makeMyTrip.models.Seat;
import makeMyTrip.search.SearchCriteria;
import makeMyTrip.models.Flight;

public class FlightService {
    private static FlightService instance;
    private Map<String, Flight> flights = new ConcurrentHashMap<>();
    private FlightService(){
        initializeData();
    }

    public static synchronized FlightService getInstance(){
        if(instance==null){
            instance= new FlightService();
        }
        return instance;
    } 

    public void addFlight(Flight flight){
        flights.put(flight.getId(), flight);
    }

    public List<Flight> search( SearchCriteria criteria){
        return flights.values().stream()
               .filter(f -> f.getSourceAirport().getCity() == criteria.getSourceCity())
               .filter(f -> f.getDestinationAirport().getCity() == criteria.getDestinationCity())
               .filter(f -> f.getDepartureTime().toLocalDate().equals(criteria.getTravelDate()))
               .filter(f -> f.getAvailableSeatsCount(criteria.getPreferredClass())>=criteria.getPassengers())
               .toList();

    }

    public void displayFlightDetails(String flightId){
        Flight flight = flights.get(flightId);
        System.out.println("Flight: "+ flight.getId());
        System.out.println("Route: "+ flight.getSourceAirport().getCity() + " -> "+ flight.getDestinationAirport().getCity());
        System.out.println("Arrival: "+ flight.getArrivalTime());
        System.out.println("Departure: "+ flight.getDepartureTime());
        System.out.println("Economy Seats: "+ flight.getAvailableSeatsCount(SeatClass.ECONOMY));
        System.out.println("Business Seats: "+ flight.getAvailableSeatsCount(SeatClass.BUSINESS));
    }

    private void initializeData(){
        City Delhi = new City("C1", "Delhi");
        City Mumbai = new City("C2", "Mumbai");

        Airport IGI = new Airport("A1", "IGI", Delhi);
        Airport CSMT = new Airport("A2", "CMST", Mumbai);

        Airline Indigo = new Airline("AL1", "Indigo");

        Aircraft Boeing787 = new Aircraft("AC1", Indigo, List.of());

        addSeats(Boeing787, 6, 30);

        LocalDateTime tomorrow  = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0);
        Flight F1 = new Flight("F1", Boeing787, IGI, CSMT, tomorrow.plusHours(4), tomorrow.plusHours(4).plusMinutes(30));
        Flight F2 = new Flight("F2", Boeing787, IGI, CSMT, tomorrow.plusHours(8), tomorrow.plusHours(10).plusMinutes(30));

        flights.put(F1.getId(), F1);
        flights.put(F2.getId(), F2);
    }

    private void addSeats(Aircraft aircraft, int businessSeats, int economySeats){
        for(int row=1;row<=5 && businessSeats>0;row++){
            for(char col='A';col<='F' && businessSeats>0;col++){
                String seatId = row + "" + col;
                aircraft.addSeat(new Seat(seatId, SeatClass.BUSINESS,SeatStatus.AVAILABLE, 1000));
            }
        }
        for(int row=6;row<=10 && economySeats>0;row++){
            for(char col='A';col<='F' && economySeats>0;col++){
                String seatId= row+ ""+ col;
                aircraft.addSeat(new Seat(seatId, SeatClass.ECONOMY, SeatStatus.AVAILABLE, 400));
            }
        }
        
    }

}
