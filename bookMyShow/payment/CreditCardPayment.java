package bookMyShow.payment;

public class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;

    public CreditCardPayment(String cardNumber){
        this.cardNumber=cardNumber;
    }

    @Override
    public boolean pay(double amount){
        System.out.println("Charging Rs " + amount + " to Card **** **** **** "+ cardNumber.substring(cardNumber.length()-4));
        return true;
    }
}
