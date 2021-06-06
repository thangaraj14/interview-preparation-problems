package structuralpattern.facade;

import java.util.Date;
import java.util.List;

class BookingInfo {
    String source;
    String destination;
    Date fromDate;
    Date toDate;
    List<PersonInfo> list;
}

class PersonInfo {
    String name;
    int age;
    Address address;
}

class Address {

}