package shaq.entities.order.avionic.chefremark;

public class AvionicChefRemarkProperties {

    private Boolean isRemarkValid;
    private String remarkLength;
    private String remark;
    private String remarkFill;

    public Boolean getRemarkValid() {
        return isRemarkValid;
    }

    public void setRemarkValid(Boolean remarkValid) {
        isRemarkValid = remarkValid;
    }

    public String getRemarkLength() {
        return remarkLength;
    }

    public void setRemarkLength(String remarkLength) {
        this.remarkLength = remarkLength;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkFill() {
        return remarkFill;
    }

    public void setRemarkFill(String remarkFill) {
        this.remarkFill = remarkFill;
    }
}
