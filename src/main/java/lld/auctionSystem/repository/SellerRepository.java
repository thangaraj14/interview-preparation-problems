package lld.auctionSystem.repository;


import lld.auctionSystem.models.Seller;

import java.util.HashMap;
import java.util.Map;

public class SellerRepository {

    public Map<String, Seller> sellers;

    // TODO - Implement thread safe lazy initialization singleton pattern.
    private SellerRepository() {
        this.sellers = new HashMap<>();
    }

    private static SellerRepository single_instance = null;

    public static SellerRepository getInstance() {
        if (single_instance == null) {
            single_instance = new SellerRepository();
        }
        return single_instance;
    }

    public void addDataToRepository(String key, Seller value) {
        this.sellers.put(key, value);
    }

    public Seller getData(String key) {
        if (key == null) {
            return null;
        }
        return this.sellers.get(key);
    }

}
