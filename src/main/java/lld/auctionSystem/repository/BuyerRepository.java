package lld.auctionSystem.repository;


import lld.auctionSystem.models.Buyer;

import java.util.HashMap;
import java.util.Map;

public class BuyerRepository {
	
	public static Map<String, Buyer> Buyers = new HashMap<>();

	// TODO - Implement thread safe lazy initialization singleton pattern.
	private BuyerRepository() {
		this.Buyers = new HashMap<>();
	}
	private static BuyerRepository single_instance = null;
	public static BuyerRepository getInstance() {
		if(single_instance==null) {
			single_instance = new BuyerRepository();
		}
		return single_instance;
	}

	public void addDataToRepository(String key, Buyer value) {
		this.Buyers.put(key, value);
	}

	public Buyer getData(String key) {
		if(key==null) {
			return null;
		}
		return this.Buyers.get(key);
	}
	
}
