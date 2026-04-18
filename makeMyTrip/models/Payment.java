package makeMyTrip.models;

import makeMyTrip.enums.PaymentMode;
import makeMyTrip.enums.PaymentStatus;

public class Payment {
    private String id;
    private PaymentMode mode;
    private PaymentStatus status;
    private double amount;

    public Payment(String id,PaymentMode mode, double amount){
        this.id=id;
        this.mode=mode;
        this.status=PaymentStatus.PENDING;
        this.amount=amount;
    }

    public double getAmount(){
        return amount;
    }

    public PaymentMode getPaymentMode(){
        return mode;
    }

    public PaymentStatus getStatus(){
        return status;
    }

    public void setStatus(PaymentStatus status){
        this.status=status;
    }
}
