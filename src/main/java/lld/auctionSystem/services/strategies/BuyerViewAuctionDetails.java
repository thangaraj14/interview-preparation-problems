package lld.auctionSystem.services.strategies;


import lld.auctionSystem.enums.AuctionProductState;
import lld.auctionSystem.enums.AuctionState;
import lld.auctionSystem.models.Auction;
import lld.auctionSystem.models.AuctionProduct;
import lld.auctionSystem.models.Product;
import lld.auctionSystem.repository.AuctionRepository;
import lld.auctionSystem.repository.ProductRepository;

public class BuyerViewAuctionDetails extends AuctionDetails{
    AuctionRepository auctionRepository = AuctionRepository.getInstance();
    ProductRepository productRepository = ProductRepository.getInstance();

    public String getAuctionDetails(String buyerId, String auctionId) {
        Auction auction = auctionRepository.getData(auctionId);
        if(auction.getAuctionState()== AuctionState.STARTED) {
            StringBuilder data = new StringBuilder();
            data.append("Auction start Date & Time" + auction.getStartDate().toString() + "\n");
            data.append("Auction end Date & Time" + auction.getEndDate().toString() + "\n");
            for (AuctionProduct auctionProduct : auction.getProducts()) {
                Product product = productRepository.getData(auctionProduct.getProductId());
                data.append("Auction Product : " + product.getName() + ", " +
                        product.getDescription() + ", ");
                if (auctionProduct.getAuctionProductState() == AuctionProductState.SOLD) {
                    data.append("State: SOLD");
                } else {
                    data.append("State: UNSOLD" + ", ");
                    if (auctionProduct.getAuctionProductState() == AuctionProductState.UNBID) {
                        data.append("Bid starts at: " + "No bid placed till now");
                    } else {
                        data.append("Current Bid: " + auctionProduct.getCurrentBid());
                    }
                }
                data.append("\n");
            }
            return data.toString();
        }
        else {
            System.out.println("Error! Auction should not be visible to the user.");
            return null;
        }
    }
}
