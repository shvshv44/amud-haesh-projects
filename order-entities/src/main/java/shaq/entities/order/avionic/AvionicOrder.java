package shaq.entities.order.avionic;

import shaq.entities.order.avionic.location.AvionicOrderLocation;
import shaq.entities.order.avionic.orderdata.AvionicOrderData;

import java.util.List;

public class AvionicOrder {

    private Integer orderNo;
    private AvionicOrderData orderData;
    private List<AvionicOrderLocation> locations;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public AvionicOrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(AvionicOrderData orderData) {
        this.orderData = orderData;
    }

    public List<AvionicOrderLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<AvionicOrderLocation> locations) {
        this.locations = locations;
    }
}
