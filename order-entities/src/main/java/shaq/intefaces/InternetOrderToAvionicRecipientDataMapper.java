package shaq.intefaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import shaq.entities.order.avionic.recipientdata.AvionicRecipientData;
import shaq.entities.order.ground.InternetOrder;

@Mapper(uses = { ZonedDateTimeToOrderTimeMapper.class })
public interface InternetOrderToAvionicRecipientDataMapper {

    @Mapping(target = "metaData.messageSerialNumber", constant = "1")
    @Mapping(source = "clientDetails.firstName", target = "properties.recipientName")
    @Mapping(target = "properties.recipientNameFill", constant = "    ")
    @Mapping(source = "orderDetails.timeOrdered", target = "properties.whenOrdered")
    AvionicRecipientData internetOrderToAvionicRecipientData(InternetOrder internetOrder);
}