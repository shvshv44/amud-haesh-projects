package shaq.entities.order.avionic.recipientdata;

import shaq.entities.order.avionic.common.AvionicMessageMetaData;

public class AvionicRecipientData {

    private AvionicMessageMetaData metaData;
    private AvionicRecipientDataProperties properties;

    public AvionicMessageMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(AvionicMessageMetaData metaData) {
        this.metaData = metaData;
    }

    public AvionicRecipientDataProperties getProperties() {
        return properties;
    }

    public void setProperties(AvionicRecipientDataProperties properties) {
        this.properties = properties;
    }
}
