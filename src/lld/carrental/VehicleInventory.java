package lld.carrental;

import java.util.HashMap;
import java.util.List;

public class VehicleInventory implements Search {
    private HashMap<String, List<Vehicle>> vehicleTypes;
    private HashMap<String, List<Vehicle>> vehicleModels;

    public List<Vehicle> searchByType(String query) {
        // return all vehicles of the given type.
        return vehicleTypes.get(query);
    }

    public List<Vehicle> searchByModel(String query) {
        // return all vehicles of the given model.
        return vehicleModels.get(query);
    }
}