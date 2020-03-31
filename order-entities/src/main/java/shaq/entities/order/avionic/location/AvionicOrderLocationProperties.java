package shaq.entities.order.avionic.location;

public class AvionicOrderLocationProperties {

    private Integer orderNo;
    private Integer locationNo;
    private Integer spareByte;
    private OrderSpecificLocation location;
    private Boolean spareBit;
    private Boolean activeLocation;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(Integer locationNo) {
        this.locationNo = locationNo;
    }

    public Integer getSpareByte() {
        return spareByte;
    }

    public void setSpareByte(Integer spareByte) {
        this.spareByte = spareByte;
    }

    public OrderSpecificLocation getLocation() {
        return location;
    }

    public void setLocation(OrderSpecificLocation location) {
        this.location = location;
    }

    public Boolean getSpareBit() {
        return spareBit;
    }

    public void setSpareBit(Boolean spareBit) {
        this.spareBit = spareBit;
    }

    public Boolean getActiveLocation() {
        return activeLocation;
    }

    public void setActiveLocation(Boolean activeLocation) {
        this.activeLocation = activeLocation;
    }
}
