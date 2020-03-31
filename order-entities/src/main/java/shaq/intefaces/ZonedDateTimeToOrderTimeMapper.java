package shaq.intefaces;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import shaq.entities.order.avionic.common.OrderTime;

import java.time.Duration;
import java.time.ZonedDateTime;

@Mapper
public interface ZonedDateTimeToOrderTimeMapper {

    @Mapping(source = "zonedDateTime", target = "day", qualifiedByName = "day")
    @Mapping(source = "zonedDateTime", target = "month", qualifiedByName = "month")
    @Mapping(source = "zonedDateTime", target = "year", qualifiedByName = "year")
    @Mapping(source = "zonedDateTime", target = "millisSinceMidnight", qualifiedByName = "millisSinceMidnight")
    OrderTime zonedDateTimeToOrderTime(ZonedDateTime zonedDateTime);

    @Named("day")
    default Integer zonedDateTimeToDay(ZonedDateTime zonedDateTime) {
        return zonedDateTime.getDayOfMonth();
    }

    @Named("month")
    default Integer zonedDateTimeToMonth(ZonedDateTime zonedDateTime) {
        return zonedDateTime.getMonthValue();
    }

    @Named("year")
    default Integer zonedDateTimeToYear(ZonedDateTime zonedDateTime) {
        return zonedDateTime.getYear();
    }

    @Named("millisSinceMidnight")
    default Long zonedDateTimeToMillisSinceMidnight(ZonedDateTime zonedDateTime) {
        return Duration.between(zonedDateTime.toLocalDate().atStartOfDay(zonedDateTime.getZone()), zonedDateTime).getSeconds();
    }
}
