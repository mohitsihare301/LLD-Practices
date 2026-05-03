package carRentalSystem.strategies.payment;

public class CreditCardPayment implements PaymentStrategy{
    @Override

    public boolean pay(double amount){
        System.out.println("Paid $ " + amount + "using credit card.");
        return true;
    }
}
