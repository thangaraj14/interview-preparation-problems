package structuralpattern.facade;

public class TravelFacade {
    FlightBooking flightBooking;
    TrainBooking trainBooking;
    HotelBooking hotelBooking;

    enum BookingType {
        Flight, Train, Hotel, Flight_And_Hotel, Train_And_Hotel;
    }

    ;

    public TravelFacade() {
        flightBooking = new FlightBooking();
        trainBooking = new TrainBooking();
        hotelBooking = new HotelBooking();
    }

    public void book(BookingType type, BookingInfo info) {
        switch (type) {
            case Flight:
                flightBooking.bookFlight(info);
                return;
            case Hotel:
                hotelBooking.bookHotel(info);
                return;
            case Train:
                trainBooking.bookTrain(info);
                return;
            case Flight_And_Hotel:
                flightBooking.bookFlight(info);
                hotelBooking.bookHotel(info);
                return;
            case Train_And_Hotel:
                trainBooking.bookTrain(info);
                hotelBooking.bookHotel(info);
                return;
        }
    }
}