package shaq.entities.order.avionic.location;

import shaq.entities.order.avionic.common.AvionicMessageMetaData;

public class AvionicOrderLocation {

    private AvionicMessageMetaData metaData;
    private AvionicOrderLocationProperties properties;

    public AvionicMessageMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(AvionicMessageMetaData metaData) {
        this.metaData = metaData;
    }

    public AvionicOrderLocationProperties getProperties() {
        return properties;
    }

    public void setProperties(AvionicOrderLocationProperties properties) {
        this.properties = properties;
    }
}
