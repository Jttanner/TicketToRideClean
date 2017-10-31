package presenters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

import MVP_coms_classes.MVP_DestCard;
import MVP_coms_classes.MVP_TrainCard;
import clientModel.CModel;
import modeling.Player;
import modeling.ResourceCard;
import modeling.ResourceCardList;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by Hwang on 10/29/2017.
 */

public class TrainCardPresenter implements MVP_TrainCard.MapPresOps {
    private WeakReference<MVP_TrainCard.MapViewOps> myView;
    Player player = CModel.getInstance().getUserPlayer();
    List<ResourceCard> availableCards = CModel.getInstance().getCurrGame().getResourceCardList().getAvailableCards();
    Map<ImageButton, ResourceCard> buttonToCardType = new HashMap<>();
    int counter = 5;
    Map<String, Bitmap> bitmapMap;

    public TrainCardPresenter(MVP_TrainCard.MapViewOps view, List<ImageButton> imageButtonList){
        myView = new WeakReference<>(view);
        Resources resources = myView.get().getActivityContext().getResources();

        Bitmap redTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.redtrain);
        Bitmap blueTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.bluetrain);
        Bitmap blackTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.blacktrain);
        Bitmap greenTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.greentrain);
        Bitmap orangeTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.orangetrain);
        Bitmap purpleTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.purpletrain);
        Bitmap whiteTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.whitetrain);
        Bitmap yellowTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.yellowtrain);
        Bitmap wildTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.wildtrain);

        bitmapMap.put("Red", redTrainBitmap);
        bitmapMap.put("Blue", blueTrainBitmap);
        bitmapMap.put("Black", blackTrainBitmap);
        bitmapMap.put("Green", greenTrainBitmap);
        bitmapMap.put("Orange", orangeTrainBitmap);
        bitmapMap.put("Purple", purpleTrainBitmap);
        bitmapMap.put("White", whiteTrainBitmap);
        bitmapMap.put("Yellow", yellowTrainBitmap);
        bitmapMap.put("Wild", wildTrainBitmap);

        for (int i = 0; i < 5; i++) {
            buttonToCardType.put(imageButtonList.get(i), availableCards.get(i));
        }
    }

    public void deckClicked() {
        player.addResourceCard(availableCards.get(counter));
        counter++;
    }

    public void cardClicked(ImageButton card) {
        //Determine which card was clicked based on the ImageButton, then
        player.addResourceCard(buttonToCardType.get(card));
        buttonToCardType.put(card, availableCards.get(counter));
        Bitmap chosenTrainCard = bitmapMap.get(availableCards.get(counter).getMyColor());
        card.setImageBitmap(chosenTrainCard);
        counter++;
    }



    /*
    public void initialize(){
        ImageButton[] buttons = (ImageButton[]) buttonToCardType.keySet().toArray(); //Array of image buttons
        for (int i = 0; i < 5; i++) {
            buttonToCardType.put(buttons[i], availableCards.get(i));
        }

        //Take the top 5 cards in the available cards list and place them in each ImageButton
    }*/



        /*

        @Override
        public void getDestinationCards(Game game, Player player) {

            //if we didnt find the user, add him in the server
            DrawDestinationCardCommandData data = new DrawDestinationCardCommandData(game.getGameID(), player);
            ServerProxy.getInstance().sendCommand(data);
        }


        @Override
        public void update(Observable o, Object arg) {
            //For updating the game list we will have a gamelist sent
            if(arg instanceof DestinationCard){
                List<DestinationCard> destinationCards = (List<DestinationCard>) arg;
                myView.get().giveChosenCards(destinationCards);
            }
        }*/
}
