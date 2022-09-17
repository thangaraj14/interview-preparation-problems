package lld.auctionSystem.services.strategies;

import lld.auctionSystem.enums.AuctionProductState;
import lld.auctionSystem.models.Auction;
import lld.auctionSystem.models.AuctionProduct;
import lld.auctionSystem.models.Product;
import lld.auctionSystem.repository.AuctionRepository;
import lld.auctionSystem.repository.ProductRepository;

public class SellerViewAuctionDetails extends AuctionDetails {
    AuctionRepository auctionRepository = AuctionRepository.getInstance();
    ProductRepository productRepository = ProductRepository.getInstance();

    public String getAuctionDetails(String sellerId, String auctionId) {
        Auction auction = auctionRepository.getData(auctionId);
        if (!auction.getSellerId().equals(sellerId)) {
            // TODO - Throw Exception
            System.out.println("This auction does not belong to the seller who requested auction data.");
            return null;
        }
        StringBuilder data = new StringBuilder("");
        data.append("Auction State : " + auction.getAuctionState().toString() + "\n");
        data.append("Auction start Date & Time" + auction.getStartDate().toString() + "\n");
        data.append("Auction end Date & Time" + auction.getEndDate().toString() + "\n");
        if (auction.getProducts() != null) {
            for (AuctionProduct auctionProduct : auction.getProducts()) {
                Product product = productRepository.getData(auctionProduct.getProductId());
                data.append("Auction Product : " + product.getName() + ", " +
                        product.getDescription() + ", " +
                        auctionProduct.getBasePrice() + ", " +
                        auctionProduct.getAuctionProductState().toString() + ", ");
                if(auctionProduct.getAuctionProductState() != AuctionProductState.UNBID) {
                    data.append(auctionProduct.getCurrentBid());
                }
                else {
                    data.append("No bid on product yet");
                }
                data.append("\n");
            }
        }
        else {
            data.append("Auction Product : No product added\n");
        }
        return data.toString();
    }
}
