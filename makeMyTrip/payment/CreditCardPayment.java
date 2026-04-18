package makeMyTrip.payment;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public boolean pay(double amount){
        System.out.println(" Credit Card payment of "+ amount);
        return Math.random() > 0.8;
    }
}
