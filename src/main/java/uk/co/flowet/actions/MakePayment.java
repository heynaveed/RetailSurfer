package uk.co.flowet.actions;

import uk.co.flowet.domains.Brand;
import uk.co.flowet.domains.Actor;
import uk.co.flowet.payments.Payment;
import uk.co.flowet.payments.SupremePayment;

public class MakePayment implements Action {

    private Actor actor;
    private Payment payment;

    private MakePayment(Actor actor){
        this.actor = actor;
    }

    public static MakePayment with(Actor actor){
        return new MakePayment(actor);
}

    public MakePayment to(Brand brand){
        switch(brand){
            case SUPREME:
                payment = new SupremePayment();
                break;
        }

        return this;
    }

    @Override
    public void now() {
        payment.fillForm(actor);
    }
}
