package shaq.intefaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import shaq.entities.order.avionic.orderdata.AvionicOrderData;
import shaq.entities.order.avionic.orderdata.SouceTypeSource;
import shaq.entities.order.ground.InternetOrder;

@Mapper
public abstract class InternetOrderToAvionicOrderDataMapper {

    protected Integer messageSerialNumber = 1;
    protected Integer orderIndex = 0;
    protected SouceTypeSource souceTypeSource;

    @Mapping(target = "messageMetaData.messageSerialNumber", qualifiedByName = "messageSerialNumber")
    @Mapping(target = "properties.orderNo", qualifiedByName = "orderIndex")
    @Mapping(target = "properties.specialRequestLength",
            expression = "java( String.valueOf(internetOrder.getOrders().get(orderIndex).getRequests().get(0).length()) )")
    @Mapping(target = "properties.specialRequest",
            expression = "java( internetOrder.getOrders().get(orderIndex).getRequests().get(0) )")
    @Mapping(target = "properties.specialRequestFill", constant = "  ")
    @Mapping(target = "properties.souceTypeLength", qualifiedByName = "souceTypeLength")
    @Mapping(target = "properties.souceType", qualifiedByName = "souceType")
    @Mapping(target = "properties.souceTypeSource", qualifiedByName = "souceTypeSource")
    public abstract AvionicOrderData internetOrderToAvionicOrderData(InternetOrder internetOrder);

    @Named("messageSerialNumber")
    protected Integer messageSerialNumber() {
        return messageSerialNumber++;
    }

    @Named("orderIndex")
    protected Integer orderIndex() {
        return orderIndex++;
    }

    @Named("souceType")
    protected String souceType(InternetOrder internetOrder) {
        if(internetOrder.getOrders().get(orderIndex).getSouceName() != null) {
            souceTypeSource = SouceTypeSource.FROM_ORIGINAL_ORDER;
            return internetOrder.getOrders().get(orderIndex).getSouceName();
        } else if(internetOrder.getChefDailySouce() != null) {
            souceTypeSource = SouceTypeSource.FROM_CHEF_DAY_CHOOSING;
            return internetOrder.getChefDailySouce();
        } else {
            souceTypeSource = SouceTypeSource.INVALID;
            return "no souce";
        }
    }

    @Named("souceTypeLength")
    protected String souceTypeLength(InternetOrder internetOrder) {
        return String.valueOf(souceType(internetOrder).length());
    }

    @Named("souceTypeSource")
    protected SouceTypeSource souceTypeSource() {
        return souceTypeSource;
    }
}
