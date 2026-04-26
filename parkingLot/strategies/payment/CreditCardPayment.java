package parkingLot.strategies.payment;

public class CreditCardPayment implements PaymentStrategy{
    @Override
    public boolean pay(double amount){
        System.out.println("Credit card payment of $" + amount + " has been made.");
        return true;
    }
}
