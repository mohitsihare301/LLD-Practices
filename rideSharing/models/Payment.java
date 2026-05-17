package rideSharing.models;

import rideSharing.enums.PaymentStatus;
import rideSharing.enums.PaymentMode;

public class Payment {
    private String id;
    private PaymentMode mode;
    private PaymentStatus status;
    private double amount;

    public Payment(String id,PaymentMode mode, PaymentStatus status, double amount){
        this.id=id;
        this.mode=mode;
        this.status=status;
        this.amount=amount;
    }

    public String getId(){
        return id;
    }

    public PaymentMode getMode(){
        return mode;
    }

    public PaymentStatus getStatus(){
        return status;
    }

    public void setStatus(PaymentStatus status){
        this.status=status;
    }

    public double getAmount(){
        return amount;
    }
}
