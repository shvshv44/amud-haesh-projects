package shaq.entities.order.avionic;

import shaq.entities.order.avionic.chefremark.AvionicChefRemark;
import shaq.entities.order.avionic.common.AvionicMessageMetaData;
import shaq.entities.order.avionic.recipientdata.AvionicRecipientData;

import java.util.List;

public class AvionicMajorOrder {

    private AvionicMessageMetaData metaData;
    private AvionicRecipientData recipientData;
    private AvionicChefRemark chefRemark;
    private List<AvionicOrder> orders;

    public AvionicMessageMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(AvionicMessageMetaData metaData) {
        this.metaData = metaData;
    }

    public AvionicRecipientData getRecipientData() {
        return recipientData;
    }

    public void setRecipientData(AvionicRecipientData recipientData) {
        this.recipientData = recipientData;
    }

    public AvionicChefRemark getChefRemark() {
        return chefRemark;
    }

    public void setChefRemark(AvionicChefRemark chefRemark) {
        this.chefRemark = chefRemark;
    }

    public List<AvionicOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<AvionicOrder> orders) {
        this.orders = orders;
    }
}
