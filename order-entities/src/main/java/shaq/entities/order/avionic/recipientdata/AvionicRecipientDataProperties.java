package shaq.entities.order.avionic.recipientdata;

import shaq.entities.order.avionic.common.OrderTime;

public class AvionicRecipientDataProperties {

    private String recipientName;
    private String recipientNameFill;
    private OrderTime whenOrdered;

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientNameFill() {
        return recipientNameFill;
    }

    public void setRecipientNameFill(String recipientNameFill) {
        this.recipientNameFill = recipientNameFill;
    }

    public OrderTime getWhenOrdered() {
        return whenOrdered;
    }

    public void setWhenOrdered(OrderTime whenOrdered) {
        this.whenOrdered = whenOrdered;
    }
}
