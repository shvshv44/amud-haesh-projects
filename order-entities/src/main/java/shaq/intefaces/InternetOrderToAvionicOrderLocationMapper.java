package shaq.intefaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import shaq.entities.order.avionic.location.AvionicOrderLocation;
import shaq.entities.order.avionic.location.OrderSpecificLocation;
import shaq.entities.order.ground.Location;
import shaq.entities.order.ground.SpecificLocation;

@Mapper
public abstract class InternetOrderToAvionicOrderLocationMapper {
    protected final Double KEY = 24.57;
    protected Integer messageSerialNumber = 1;
    protected Integer locationIndex = 0;
    protected Integer orderIndex = 0;


    @Mapping(target = "metaData.messageSerialNumber", expression = "java( messageSerialNumber() )")
    @Mapping(target = "properties.orderNo", expression = "java( orderIndex(orderIndex) )")
    @Mapping(target = "properties.locationNo", expression = "java( locationIndex() )")
    @Mapping(target = "properties.spareByte", constant = "0")
    @Mapping(target = "properties.location", expression = "java( encodedLocation(location) )")
    @Mapping(target = "properties.spareBit", constant = "false")
    @Mapping(target = "properties.activeLocation", constant = "true")
    public abstract AvionicOrderLocation internetOrderToAvionicOrderLocation(Location location, Integer orderIndex);

    @Named("messageSerialNumber")
    protected Integer messageSerialNumber() {
        return messageSerialNumber++;
    }

    @Named("locationIndex")
    protected Integer locationIndex() {
        return locationIndex++;
    }

    @Named("orderIndex")
    protected Integer orderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
        return this.orderIndex ;
    }

    @Named("encodedLocation")
    protected OrderSpecificLocation encodedLocation(Location location) {
            if(location.getClass() == SpecificLocation.class) {
                SpecificLocation specificLocation = (SpecificLocation) location;
                Long encodedLocation = specificLocation.getEncodedLocation();
                OrderSpecificLocation orderSpecificLocation = new OrderSpecificLocation();
                orderSpecificLocation.setAlt(Math.round(((encodedLocation * KEY) / 30) + 20));
                orderSpecificLocation.setLat(Math.round(((encodedLocation * KEY) / 20) + 7));
                orderSpecificLocation.setLon(Math.round(((encodedLocation * KEY) / 40) + 5));
                return orderSpecificLocation;
            }
        return null;
    }
}
