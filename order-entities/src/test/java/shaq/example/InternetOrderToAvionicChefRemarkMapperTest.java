package shaq.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import shaq.entities.order.avionic.chefremark.AvionicChefRemark;
import shaq.entities.order.ground.InternetOrder;
import shaq.intefaces.InternetOrderToAvionicChefRemarkMapper;

public class InternetOrderToAvionicChefRemarkMapperTest {
    private InternetOrderToAvionicChefRemarkMapper mapper = Mappers.getMapper(InternetOrderToAvionicChefRemarkMapper.class);

    @Test
    public void ZonedDateTimeToOrderTimeTest() {
        InternetOrder internetOrder = new InternetOrder();
        internetOrder.setChefRemark("I wanna eat pizza and hamburger and burekas baba");
        AvionicChefRemark avionicChefRemark = mapper.internetOrderToAvionicChefRemark(internetOrder);
        String expectedNameFill = "  ";
        Integer expectedMessageSerialNumber = 2;
        Boolean expectedRemarkValid = internetOrder.getChefRemark().length() > 30;
        Assert.assertEquals(expectedMessageSerialNumber, avionicChefRemark.getMetaData().getMessageSerialNumber());
        Assert.assertEquals(internetOrder.getChefRemark(), avionicChefRemark.getProperties().getRemark());
        Assert.assertEquals(expectedNameFill, avionicChefRemark.getProperties().getRemarkFill());
        Assert.assertEquals(String.valueOf(internetOrder.getChefRemark().length()), avionicChefRemark.getProperties().getRemarkLength());
        Assert.assertEquals(expectedRemarkValid, avionicChefRemark.getProperties().getIsRemarkValid());
    }
}
