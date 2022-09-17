package lld.auctionSystem.models;


import lld.auctionSystem.enums.ObserverType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Publisher {
	private Map<ObserverType, List<String>> observers;
	
	public Publisher() {
		this.observers = new HashMap<>();
		this.observers.put(ObserverType.SELLER, new LinkedList<>());
		this.observers.put(ObserverType.BUYER, new LinkedList<>());
	}
	
	public Map<ObserverType, List<String>> getObservers() {
		return this.observers;
	}
}
