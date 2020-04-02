package shaq.example;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import shaq.entities.order.avionic.location.AvionicOrderLocation;
import shaq.entities.order.ground.Location;
import shaq.entities.order.ground.Order;
import shaq.entities.order.ground.SpecificLocation;
import shaq.intefaces.InternetOrderToAvionicOrderLocationMapper;

import java.util.ArrayList;
import java.util.List;

public class InternetOrderToAvionicOrderLocationMapperTest {

    private InternetOrderToAvionicOrderLocationMapper mapper = Mappers.getMapper(InternetOrderToAvionicOrderLocationMapper.class);

    @Test
    public void InternetOrderToAvionicOrderLocationMapperTest() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        List<Location> locations = new ArrayList<>();
        SpecificLocation specificLocation = new SpecificLocation();
        specificLocation.setEncodedLocation((long)123456);
        SpecificLocation specificLocation2 = new SpecificLocation();
        specificLocation2.setEncodedLocation((long)000000);
        Location location = new Location();
        locations.add(location);
        locations.add(specificLocation);
        locations.add(specificLocation2);
        order.setLocations(locations);
        orders.add(order);
        for (Order currOrder: orders) {
            for (Location currLocation: locations) {
                AvionicOrderLocation avionicOrderLocation = mapper.internetOrderToAvionicOrderLocation(currLocation, orders.indexOf(currOrder));
                System.out.println(avionicOrderLocation);

            }
        }
    }
}
