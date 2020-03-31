package shaq.entities.order.avionic.chefremark;

import shaq.entities.order.avionic.common.AvionicMessageMetaData;

public class AvionicChefRemark {

    private AvionicMessageMetaData metaData;
    private AvionicChefRemarkProperties properties;

    public AvionicMessageMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(AvionicMessageMetaData metaData) {
        this.metaData = metaData;
    }

    public AvionicChefRemarkProperties getProperties() {
        return properties;
    }

    public void setProperties(AvionicChefRemarkProperties properties) {
        this.properties = properties;
    }
}
