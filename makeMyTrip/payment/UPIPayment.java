package makeMyTrip.payment;

public class UPIPayment implements PaymentStrategy{
    @Override
    public boolean pay(double amount){
        System.out.println(" UPI payment of "+ amount);
        return Math.random() > 0.75;
    }
}
