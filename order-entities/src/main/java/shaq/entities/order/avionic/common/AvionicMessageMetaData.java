package shaq.entities.order.avionic.common;

public class AvionicMessageMetaData {

    private Integer messageSerialNumber;
    private AvionicOrderMessageType messageType;
    private Integer doubleSpareBits;
    private String planeType;
    private Integer messageSizeInBits;

    public Integer getMessageSerialNumber() {
        return messageSerialNumber;
    }

    public void setMessageSerialNumber(Integer messageSerialNumber) {
        this.messageSerialNumber = messageSerialNumber;
    }

    public AvionicOrderMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(AvionicOrderMessageType messageType) {
        this.messageType = messageType;
    }

    public Integer getDoubleSpareBits() {
        return doubleSpareBits;
    }

    public void setDoubleSpareBits(Integer doubleSpareBits) {
        this.doubleSpareBits = doubleSpareBits;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public Integer getMessageSizeInBits() {
        return messageSizeInBits;
    }

    public void setMessageSizeInBits(Integer messageSizeInBits) {
        this.messageSizeInBits = messageSizeInBits;
    }
}
