package lld.auctionSystem.models;


import java.util.HashMap;
import java.util.Map;

public class Buyer extends User {
    private Map<String, Map<String, Double>> subscribedAuctions;

    public Buyer( String BuyerId,  String username,  String emailId,  String phoneNo) {
        super(BuyerId, username,emailId, phoneNo);
        this.subscribedAuctions = new HashMap<>();
    }

    public Map<String, Map<String, Double>> getSubscribedAuctions() {
        return this.subscribedAuctions;
    }
}
