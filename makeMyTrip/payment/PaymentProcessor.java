package makeMyTrip.payment;

import makeMyTrip.enums.PaymentMode;
import makeMyTrip.enums.PaymentStatus;
import makeMyTrip.models.Payment;
import java.util.HashMap;
import java.util.Map;


public class PaymentProcessor {
    private Map<PaymentMode, PaymentStrategy> strategies = new HashMap<>();

    public PaymentProcessor(){
        strategies.put(PaymentMode.CREDIT_CARD, new CreditCardPayment());
        strategies.put(PaymentMode.UPI, new UPIPayment());
    }

    public boolean process(Payment payment){
        PaymentStrategy strategy = strategies.get(payment.getPaymentMode());
        if(strategy == null){
            payment.setStatus(PaymentStatus.FAILED);
            return false;
        }

        boolean success = strategy.pay(payment.getAmount());
        payment.setStatus(success ? PaymentStatus.SUCCESS: PaymentStatus.FAILED);
        return success;
    }
}
