package lld.auctionSystem.models;


import java.util.List;

public class Seller extends User {
	private List<String> auctions; //List of AuctionId

	public Seller( String sellerId,  String username,  String emailId,  String phoneNo) {
		super(sellerId, username,emailId, phoneNo);
	}

	public List<String> getAuctions() {
		return auctions;
	}
}
