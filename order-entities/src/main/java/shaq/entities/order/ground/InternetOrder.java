package shaq.entities.order.ground;

import java.util.List;

public class InternetOrder {

    private ClientDetails clientDetails;
    private OrderDetails orderDetails;
    private String chefDailySource;
    private String chefRemark;
    private List<Order> orders;

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getChefDailySource() {
        return chefDailySource;
    }

    public void setChefDailySource(String chefDailySource) {
        this.chefDailySource = chefDailySource;
    }

    public String getChefRemark() {
        return chefRemark;
    }

    public void setChefRemark(String chefRemark) {
        this.chefRemark = chefRemark;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
