package shaq.entities.order.avionic.orderdata;

import shaq.entities.order.avionic.common.OrderTime;

public class AvionicOrderDataProperties {

    private Integer orderNo;
    private OrderTime timeToArrive;
    private String specialRequestLength;
    private String specialRequest;
    private String specialRequestFill;
    private String souceTypeLength;
    private String souceType;
    private SouceTypeSource souceTypeSource;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public OrderTime getTimeToArrive() {
        return timeToArrive;
    }

    public void setTimeToArrive(OrderTime timeToArrive) {
        this.timeToArrive = timeToArrive;
    }

    public String getSpecialRequestLength() {
        return specialRequestLength;
    }

    public void setSpecialRequestLength(String specialRequestLength) {
        this.specialRequestLength = specialRequestLength;
    }

    public String getSpecialRequest() {
        return specialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }

    public String getSpecialRequestFill() {
        return specialRequestFill;
    }

    public void setSpecialRequestFill(String specialRequestFill) {
        this.specialRequestFill = specialRequestFill;
    }

    public String getSouceTypeLength() {
        return souceTypeLength;
    }

    public void setSouceTypeLength(String souceTypeLength) {
        this.souceTypeLength = souceTypeLength;
    }

    public String getSouceType() {
        return souceType;
    }

    public void setSouceType(String souceType) {
        this.souceType = souceType;
    }

    public SouceTypeSource getSouceTypeSource() {
        return souceTypeSource;
    }

    public void setSouceTypeSource(SouceTypeSource souceTypeSource) {
        this.souceTypeSource = souceTypeSource;
    }
}
