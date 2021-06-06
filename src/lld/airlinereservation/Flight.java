package lld.airlinereservation;

import java.util.List;

public class Flight {
    private String flightNumber;
    private Airport departure;
    private Airport arrival;
    private int durationInMinutes;
    private Aircraft aircraft;

    private List<WeeklySchedule> weeklySchedules;
    private List<CustomSchedule> customSchedules;
    private List<FlightInstance> flightInstances;
}
