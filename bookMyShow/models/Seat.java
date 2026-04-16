package bookMyShow.models;

import bookMyShow.enums.SeatType;

public class Seat {
    private String id;
    private String row;
    private String number;
    private SeatType type;
    private double basePrice;

    public Seat(String row,String number,SeatType type,double basePrice){
        this.id=row+number;
        this.row=row;
        this.number=number;
        this.type=type;
        this.basePrice=basePrice;
    }

    public String getId(){
        return id;
    }

    public SeatType getSeatType(){
        return type;
    }

    public double getBasePrice(){
        return basePrice;
    }
}
