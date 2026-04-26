package parkingLot;

import java.util.Optional;
import parkingLot.strategies.payment.CreditCardPayment;
import parkingLot.models.*;
import parkingLot.strategies.pricing.HourlyPricingStrategy;
import parkingLot.strategies.spotAssignment.NearestSpotAssignmentStrategy;
import parkingLot.services.ParkingService;

public class ParkingLotMain {
    public static void main(String[] args){
        ParkingLot lot = ParkingLot.getInstance();

        Level L1 = new Level("L1",5);
        Level L2 = new Level("L2",5);

        lot.addLevel(L1);
        lot.addLevel(L2);

        MotorCycle motorCycle = new MotorCycle("M1");
        Car car = new Car("C1");


        ParkingService parkingService = new ParkingService(lot, new NearestSpotAssignmentStrategy(), new HourlyPricingStrategy(), new CreditCardPayment());

        Optional<ParkingTicket> ticket = parkingService.parkVehicle(car);
        if(ticket.isPresent()){
            try{
                Thread.sleep(10000);
            } catch( InterruptedException e){
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            parkingService.unparkVehicle(ticket.get().getId());
        }
    }
}
