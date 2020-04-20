package shaq.example;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import shaq.entities.order.avionic.orderdata.AvionicOrderData;
import shaq.entities.order.ground.InternetOrder;
import shaq.entities.order.ground.Order;
import shaq.entities.order.ground.TimedOrder;
import shaq.intefaces.InternetOrderToAvionicOrderDataMapper;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InternetOrderToAvionicOrderDataMapperTest {
    private InternetOrderToAvionicOrderDataMapper mapper = Mappers.getMapper(InternetOrderToAvionicOrderDataMapper.class);

    @Test
    public void InternetOrderToAvionicOrderDataTest() {
        Order order = new Order();
        List <Order> orders = new ArrayList<>();
        List <String> requests = new ArrayList<>();
        requests.add("AAa");
        order.setRequests(requests);
        order.setLocations(Collections.emptyList());
        order.setSouceName(null);
        orders.add(order);
        List <String> requests2 = new ArrayList<>();
        TimedOrder order2 = new TimedOrder();
        requests2.add("BBBB");
        order2.setRequests(requests2);
        order2.setLocations(Collections.emptyList());
        order2.setSouceName(null);
        order2.setTimeOrdered(ZonedDateTime.now());
        orders.add(order2);
        InternetOrder internetOrder = new InternetOrder();
        internetOrder.setChefDailySouce("secret");
        internetOrder.setOrders(orders);
        AvionicOrderData avionicOrderData = mapper.internetOrderToAvionicOrderData(internetOrder);
        System.out.println(avionicOrderData);
        AvionicOrderData avionicOrderData2 = mapper.internetOrderToAvionicOrderData(internetOrder);
        System.out.println(avionicOrderData2);
    }
}
