package result;

import java.util.List;

import modeling.DestinationCard;

/**
 * Created by ahwang13 on 11/1/17.
 */

public class ClaimDestinationCardCommandResult extends CommandResult{

    private List<DestinationCard> claimedDestinationCards;
    private int currentSizeOfDeck;

    public ClaimDestinationCardCommandResult(boolean success, List<DestinationCard> claimedDestinationCards, String errorInfo) {
        super(success,errorInfo);
        this.claimedDestinationCards = claimedDestinationCards;
    }
    public ClaimDestinationCardCommandResult(boolean success, String errorInfo) {
        super(success,errorInfo);
    }

    public List<DestinationCard> getClaimedDestinationCards() {
        return claimedDestinationCards;
    }
}
