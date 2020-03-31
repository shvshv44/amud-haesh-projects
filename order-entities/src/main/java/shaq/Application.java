package shaq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
/**
 * The AvionicMajorOrder Contains alot of Orders to many places on earth from single recipient
 * AvionicMajorOrder is built from many Avionic Messages
 * SPECIAL NOTE - every String in the avionic message should be encoded with this logic: if there is a character which is not in hebrew it will encoded to '*'
 * Every Avionic Message has a meta data:
 *
 * AvionicMessageMetaData:
 *  messageSerialNumber - number of message in the major order, AvionicMajorOrder has serialNumber too which starts from 0
 *  messageType - the type of the AvionicMessage
 *  doubleSpareBits - for future use - need to initialize it to 0
 *  planeType - the type of the plane which deliver the order - always initialized to Hercules
 *  messageSizeInBits - the size in bits of every message:
 *      AvionicMajorOrder - 0
 *      AvionicRecipientData - 20003412
 *      AvionicChefRemark - 12345
 *      AvionicOrderData - 293845732
 *      AvionicOrderLocation - 32463532423363
 *
 * AvionicMajorOrder:
 *  metaData - the meta data of the main message - always serial number 0
 *  recipientData - the recipient data
 *  chefRemark - the chef remark
 *  orders - list of AvionicOrders to deliver (AvionicOrders contains AvionicMessages but not one itself!)
 *
 * AvionicOrder:
 *  orderNo - the index of the orders on the list
 *  orderData - the order data of this order
 *  locations - list of AvionicOrderLocation which contains several places to deliver this order
 *
 * AvionicRecipientData:
 *  metaData: serial no must be 1
 *  properties:
 *      recipientName - we take it from InternetOrder -> clientDetails -> firstName
 *      recipientNameFill - string which contains 4 spaces
 *      whenOrdered - we take it from InternetOrder -> orderDetails -> timeOrdered (should be parsed by the OrderTime fields)
 *
 * AvionicChefRemark:
 *  metaData: serial no must be 2
 *  properties:
 *      isRemarkValid - true if the remark is bigger then 30 characters, otherwise false
 *      remarkLength - the actual length of the remark
 *      remark - InternetOrder -> chefRemark
 *      remarkFill - string which contains 2 spaces
 *
 * AvionicOrderData:
 *  metaData: from now on the serial continues to increment by 1 every time a new message is mapped
 *  properties:
 *      orderNo - the index of the order which this data is part of
 *      timeToArrive - we take it from InternetOrder -> orders -> requestedTime [order must be of type TimedOrder]
 *      specialRequestLength - length of specialRequest String
 *      specialRequest -  we take it from InternetOrder -> orders -> requests [we will take the first one]
 *      specialRequestFill - string which contains 2 spaces
 *      souceTypeLength - the length of souceType String
 *      souceType -  we take it from InternetOrder -> orders -> souceName, if null we will take InternetOrder -> chefDailySouce, if also null we will put 'no souce'
 *      souceTypeSource -
 *          if we take the souce from souceName -> FROM_ORIGINAL_ORDER
 *          if we take the souce from chefDailySouce -> FROM_CHEF_DAY_CHOOSING
 *          otherwise -> INVALID
 *
 * AvionicOrderLocation:
 *  metaData: continues to increment by 1 every time a new message is mapped
 *  properties:
 *      orderNo - the index of the order which this data is part of
 *      locationNo - the index of the location in the list
 *      spareByte - initialize to 0
 *      location - we take it from InternetOrder -> orders -> locations [must take only SpecificLocation type] -> encodedLocation , we need to calculate the encoding when KEY = 24.57
 *          lon: round(((encodedLocation * KEY)/ 40) + 5)
 *          lat: round(((encodedLocation * KEY)/ 20) + 7)
 *          alt: round(((encodedLocation * KEY)/ 30) + 20)
 *      spareBit - initialize to 0
 *      activeLocation - always true
 *
 *
 *
 *
 *
 *
 *
 *
 */
}
