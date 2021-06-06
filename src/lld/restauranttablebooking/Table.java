package lld.restauranttablebooking;

import java.util.Date;
import java.util.List;

public class Table {
    private int tableID;
    private TableStatus status;
    private int maxCapacity;
    private int locationIdentifier;

    private List<TableSeat> seats;

    public boolean isTableFree() {
        return false;
    }

    public boolean addReservation() {
        return false;
    }

    public static List<Table> search(int capacity, Date startTime) {
        return null;
        // return all tables with the given capacity and availability
    }
}

