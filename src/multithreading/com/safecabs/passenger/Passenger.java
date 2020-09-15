package multithreading.com.safecabs.passenger;


import multithreading.com.safecabs.Gender;

public class Passenger {

    private int id;
    private Gender gender;

    public Passenger(int id, Gender gender) {
        this.id = id;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }

}
