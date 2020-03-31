package shaq.entities.order.avionic.common;

public class OrderTime {

    private Integer day;
    private Integer month;
    private Integer year;
    private Long millisSinceMidnight;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getMillisSinceMidnight() {
        return millisSinceMidnight;
    }

    public void setMillisSinceMidnight(Long millisSinceMidnight) {
        this.millisSinceMidnight = millisSinceMidnight;
    }
}
