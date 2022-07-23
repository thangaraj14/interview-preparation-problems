package multithreading.com.safecabs.cab;


import multithreading.com.safecabs.Constants;

import java.util.Date;

public class Cab {

    int id;

    int CAPACITY = Constants.CAB_CAPACITY;

    Date availableFrom;

    /*
    Not required for current requirement

    Vehicle cab; // Vehicle made, model, year

    Driver owner;
    */

    public Cab(int id) {
        this.id = id;
        availableFrom = new Date();
    }

    public Cab(int id, Date availableFrom) {
        this.id = id;
        this.availableFrom = availableFrom;
    }

    public int getId() {
        return id;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

}
