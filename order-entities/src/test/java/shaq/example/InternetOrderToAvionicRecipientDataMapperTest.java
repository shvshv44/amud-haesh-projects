package shaq.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import shaq.entities.order.avionic.recipientdata.AvionicRecipientData;
import shaq.entities.order.ground.ClientDetails;
import shaq.entities.order.ground.InternetOrder;
import shaq.entities.order.ground.OrderDetails;
import shaq.intefaces.InternetOrderToAvionicRecipientDataMapper;

import java.time.Duration;
import java.time.ZonedDateTime;

public class InternetOrderToAvionicRecipientDataMapperTest {
    private InternetOrderToAvionicRecipientDataMapper mapper = Mappers.getMapper(InternetOrderToAvionicRecipientDataMapper.class);

    @Test
    public void ZonedDateTimeToOrderTimeTest() {
        InternetOrder internetOrder = new InternetOrder();
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setFirstName("Amit");
        internetOrder.setClientDetails(clientDetails);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setTimeOrdered(zonedDateTime);
        internetOrder.setOrderDetails(orderDetails);
        AvionicRecipientData avionicRecipientData = mapper.internetOrderToAvionicRecipientData(internetOrder);
        Long expectedMillisSinceMidnight =
                Duration.between(zonedDateTime.toLocalDate().atStartOfDay(zonedDateTime.getZone()), zonedDateTime).getSeconds();
        String expectedNameFill = "    ";
        Assert.assertEquals(clientDetails.getFirstName(), avionicRecipientData.getProperties().getRecipientName());
        Assert.assertEquals(expectedNameFill, avionicRecipientData.getProperties().getRecipientNameFill());
        Assert.assertEquals((Integer)orderDetails.getTimeOrdered().getYear(), avionicRecipientData.getProperties().getWhenOrdered().getYear());
        Assert.assertEquals((Integer)orderDetails.getTimeOrdered().getMonthValue(), avionicRecipientData.getProperties().getWhenOrdered().getMonth());
        Assert.assertEquals((Integer)orderDetails.getTimeOrdered().getDayOfMonth(), avionicRecipientData.getProperties().getWhenOrdered().getDay());
        Assert.assertEquals(expectedMillisSinceMidnight, avionicRecipientData.getProperties().getWhenOrdered().getMillisSinceMidnight());
    }
}
