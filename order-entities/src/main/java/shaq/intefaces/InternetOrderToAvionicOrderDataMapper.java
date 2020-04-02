package shaq.intefaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import shaq.entities.order.avionic.common.OrderTime;
import shaq.entities.order.avionic.orderdata.AvionicOrderData;
import shaq.entities.order.avionic.orderdata.SouceTypeSource;
import shaq.entities.order.ground.InternetOrder;
import shaq.entities.order.ground.TimedOrder;

@Mapper(uses = { ZonedDateTimeToOrderTimeMapper.class })
public abstract class InternetOrderToAvionicOrderDataMapper {

    protected Integer messageSerialNumber = 1;
    protected Integer orderIndex = 0;
    protected SouceTypeSource souceTypeSource;
    protected ZonedDateTimeToOrderTimeMapper zonedDateTimeToOrderTimeMapper =
            Mappers.getMapper(ZonedDateTimeToOrderTimeMapper.class);;

    @Mapping(target = "messageMetaData.messageSerialNumber", expression = "java( messageSerialNumber() )")
    @Mapping(target = "properties.orderNo", expression = "java( orderIndex() )")
    @Mapping(target = "properties.timeToArrive", expression = "java( timeToArrive(internetOrder) )")
    @Mapping(target = "properties.specialRequestLength",
            expression = "java( String.valueOf(internetOrder.getOrders().get(orderIndex).getRequests().get(0).length()) )")
    @Mapping(target = "properties.specialRequest",
            expression = "java( internetOrder.getOrders().get(orderIndex).getRequests().get(0) )")
    @Mapping(target = "properties.specialRequestFill", constant = "  ")
    @Mapping(target = "properties.souceTypeLength", expression = "java( souceTypeLength(internetOrder) )")
    @Mapping(target = "properties.souceType", expression = "java( souceType(internetOrder) )")
    @Mapping(target = "properties.souceTypeSource", expression = "java( souceTypeSource() )")
    public abstract AvionicOrderData internetOrderToAvionicOrderData(InternetOrder internetOrder);

    @Named("messageSerialNumber")
    protected Integer messageSerialNumber() {
        return messageSerialNumber++;
    }

    @Named("orderIndex")
    protected Integer orderIndex() {
        return orderIndex;
    }

    @Named("timeToArrive")
    protected OrderTime timeToArrive(InternetOrder internetOrder){
        if(internetOrder.getOrders().get(orderIndex).getClass() == TimedOrder.class){
            TimedOrder timedOrder = (TimedOrder)(internetOrder.getOrders().get(orderIndex));
            return zonedDateTimeToOrderTimeMapper.zonedDateTimeToOrderTime(timedOrder.getTimeOrdered());
        } else {
            return null;
        }
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
        orderIndex++;
        return souceTypeSource;
    }
}
