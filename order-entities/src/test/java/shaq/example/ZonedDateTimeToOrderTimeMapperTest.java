package shaq.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import shaq.entities.order.avionic.common.OrderTime;
import shaq.intefaces.ZonedDateTimeToOrderTimeMapper;

import java.time.Duration;
import java.time.ZonedDateTime;

public class ZonedDateTimeToOrderTimeMapperTest {
    private ZonedDateTimeToOrderTimeMapper mapper = Mappers.getMapper(ZonedDateTimeToOrderTimeMapper.class);

    @Test
    public void ZonedDateTimeToOrderTimeTest() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        OrderTime orderTime = mapper.zonedDateTimeToOrderTime(zonedDateTime);
        Long expectedMillisSinceMidnight =
                Duration.between(zonedDateTime.toLocalDate().atStartOfDay(zonedDateTime.getZone()), zonedDateTime).getSeconds();
        Assert.assertEquals((Integer)zonedDateTime.getDayOfMonth(), orderTime.getDay());
        Assert.assertEquals((Integer)zonedDateTime.getMonthValue(), orderTime.getMonth());
        Assert.assertEquals((Integer)zonedDateTime.getYear(), orderTime.getYear());
        Assert.assertEquals(expectedMillisSinceMidnight, orderTime.getMillisSinceMidnight());
    }
}
