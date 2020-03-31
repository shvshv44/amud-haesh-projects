package shaq.intefaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import shaq.entities.order.avionic.chefremark.AvionicChefRemark;
import shaq.entities.order.ground.InternetOrder;

@Mapper
public interface InternetOrderToAvionicChefRemarkMapper {

    int remarkValidLength = 30;

    @Mapping(target = "metaData.messageSerialNumber", constant = "2")
    @Mapping(target = "properties.isRemarkValid", expression = "java(  internetOrder.getChefRemark().length() > remarkValidLength )")
    @Mapping(target = "properties.remarkLength", expression="java( String.valueOf(internetOrder.getChefRemark().length()) )")
    @Mapping(source = "chefRemark", target = "properties.remark")
    @Mapping(target = "properties.remarkFill", constant = "  ")
    AvionicChefRemark internetOrderToAvionicChefRemark(InternetOrder internetOrder);

}
