package MVP_coms_classes;

import modeling.ResourceCard;

/**
 * Created by korea on 11/8/2017.
 */

public interface MVP_DrawResourceCard {

    //Allow presenter to Update the View
     interface DrawResourceCardViewOps {
        void upDateFaceUp();
    }

    //Processes User Input
     interface DrawResourceCardPresOps {
        void drawCard(ResourceCard card);
        void shuffleDiscardPile();
    }
}
