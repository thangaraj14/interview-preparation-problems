package practiceproblems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-underground-system/
 *
 * tricky
 */
public class UndergroundSystem {

    Map<Integer, Passenger> passengerDetails;
    Map<String, Route> routeDetails;

    public UndergroundSystem() {
        this.passengerDetails = new HashMap<>();
        this.routeDetails = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        passengerDetails.putIfAbsent(id, new Passenger(id));
        passengerDetails.get(id).checkin(stationName, t);
    }

    public void checkOut(int id, String stationName, int t) {
        if (!passengerDetails.containsKey(id)) return;

        Passenger pass = passengerDetails.get(id);
        pass.checkout(stationName, t);

        String routeKey = pass.startStation + " , " + pass.endStation;
        routeDetails.putIfAbsent(routeKey, new Route(pass.startStation, pass.endStation));

        routeDetails.get(routeKey).addTotalTiming(pass.startTime, pass.endTime);
        passengerDetails.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        return routeDetails.get(startStation + " , " + endStation).avgTimeTaken();
    }


    static class Passenger {

        int passengerId;
        String startStation;
        String endStation;
        int startTime;
        int endTime;

        public Passenger(int passengerId) {
            this.passengerId = passengerId;
        }

        public void checkin(String startStation, int startTime) {
            this.startStation = startStation;
            this.startTime = startTime;
        }

        public void checkout(String endStation, int endTime) {
            this.endStation = endStation;
            this.endTime = endTime;
        }
    }

    static class Route {
        String startStation;
        String endStation;
        long totalTravelTime;
        int numberOfTravels;

        public Route(String startStation, String endStation) {
            this.startStation = startStation;
            this.endStation = endStation;
            this.totalTravelTime = 0;
            this.numberOfTravels = 0;
        }

        public void addTotalTiming(int startTime, int endTime) {
            this.totalTravelTime += (endTime - startTime);
            numberOfTravels++;
        }

        public double avgTimeTaken() {
            return (double) totalTravelTime / numberOfTravels;
        }

    }
}

