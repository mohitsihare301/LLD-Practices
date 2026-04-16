package bookMyShow.payment;

public class UPIPayment implements PaymentStrategy{
    private String upiId;

    public UPIPayment(String upiId){
        this.upiId=upiId;
    }

    @Override
    public boolean pay(double amount){
        System.out.println("Charging Rs "+ amount+ " to "+ upiId);
        return true;
    }

}
