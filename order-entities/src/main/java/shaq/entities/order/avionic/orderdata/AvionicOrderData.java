package shaq.entities.order.avionic.orderdata;

import shaq.entities.order.avionic.common.AvionicMessageMetaData;

public class AvionicOrderData {

    private AvionicMessageMetaData messageMetaData;
    private AvionicOrderDataProperties properties;

    public AvionicMessageMetaData getMessageMetaData() {
        return messageMetaData;
    }

    public void setMessageMetaData(AvionicMessageMetaData messageMetaData) {
        this.messageMetaData = messageMetaData;
    }

    public AvionicOrderDataProperties getProperties() {
        return properties;
    }

    public void setProperties(AvionicOrderDataProperties properties) {
        this.properties = properties;
    }
}
