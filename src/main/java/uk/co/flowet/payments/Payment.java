package uk.co.flowet.payments;

import uk.co.flowet.domains.Actor;

public interface Payment {
    void fillForm(Actor actor);
}
