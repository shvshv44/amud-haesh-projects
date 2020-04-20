package shaq.entities.order.ground;

import java.time.ZonedDateTime;

public class TimedOrder extends Order {

    private ZonedDateTime timeOrdered;

    public ZonedDateTime getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(ZonedDateTime timeOrdered) {
        this.timeOrdered = timeOrdered;
    }
}
