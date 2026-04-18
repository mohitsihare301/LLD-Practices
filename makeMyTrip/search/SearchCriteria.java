package makeMyTrip.search;

import makeMyTrip.models.City;
import java.time.LocalDate;
import makeMyTrip.enums.SeatClass;

public class SearchCriteria {
    private City sourceCity;
    private City destinationCity;
    private LocalDate travelDate;
    private int passengers;
    private SeatClass preferredSeatClass;

    public SearchCriteria(Builder b){
        this.sourceCity=b.sourceCity;
        this.destinationCity=b.destinationCity;
        this.travelDate=b.travelDate;
        this.passengers=b.passengers;
        this.preferredSeatClass=b.preferredSeatClass;
    }

    public City getSourceCity(){
        return sourceCity;
    }

    public City getDestinationCity(){
        return destinationCity;
    }

    public LocalDate getTravelDate(){
        return travelDate;
    }

    public int getPassengers(){
        return passengers;
    }

    public SeatClass getPreferredClass(){
        return preferredSeatClass;
    }

    public static class Builder{
        private City sourceCity;
        private City destinationCity;
        private LocalDate travelDate;
        private int passengers=1;
        private SeatClass preferredSeatClass = SeatClass.ECONOMY;

        public Builder from(City sourceCity){
            this.sourceCity=sourceCity;
            return this;
        }

        public Builder to(City destinationCity){
            this.destinationCity=destinationCity;
            return this;
        }

        public Builder on(LocalDate travelDate){
            this.travelDate=travelDate;
            return this;
        }

        public Builder passengers(int passengers){
            this.passengers=passengers;
            return this;
        }

        public Builder seatClass(SeatClass seatClass){
            this.preferredSeatClass=seatClass;
            return this;
        }

        public SearchCriteria build(){
            if(sourceCity==null || destinationCity==null || travelDate==null){
                throw new IllegalArgumentException("source, destination, and travelDate are required.");
            }
            return new SearchCriteria(this);
        }


    }
}
