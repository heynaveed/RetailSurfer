package uk.co.flowet.actions;

import uk.co.flowet.browser.Page;

public interface Actions {
    Actions attemptsTo(Action action);
    Page enact();
}
