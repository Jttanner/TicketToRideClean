package ui.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import MVP_coms_classes.MVP_TurnStartOption;
import clientModel.CModel;
import presenters.TurnStartOptionPresenter;
import teamjapannumbahone.tickettoride.R;

/**
 * Created by korea on 11/8/2017.
 */

public class TurnStartOptionFragment extends DialogFragment implements MVP_TurnStartOption.TurnStartOptionViewOps {
    private Button drawResourceCard;
    private Button drawDestinationCard;
    private Button claimRoute;
    private MVP_TurnStartOption.TurnStartOptionPresOps myPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myPresenter = new TurnStartOptionPresenter(this);
        View v = inflater.inflate(R.layout.fragment_turn_start_option, container, false);
        getDialog().show();
        getDialog().getWindow().setLayout(1500, 1500);
        setUp(v);
        onClickers();

        return v;
    }

    public void setUp(View v) {

        drawResourceCard = (Button) v.findViewById(R.id.turnStartOptionResourceCard);
        drawDestinationCard = (Button) v.findViewById(R.id.turnStartOptionDestinationCard);
        claimRoute = (Button) v.findViewById(R.id.turnStartOptionClaimRoute);
    }

    public void onClickers() {
        drawResourceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       //Have all buttons greyed out, and ungrey the chosen option
                ((MapActivity)getActivity()).ResourceCardOption();
                getDialog().dismiss();
            }
        });
        drawDestinationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MapActivity)getActivity()).DestinationCardOption();
                Toast.makeText(getActivity(), "Please select at least 1 destination card", Toast.LENGTH_LONG).show();
                getDialog().cancel();

            }
        });
        claimRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MapActivity)getActivity()).ClaimRouteOption();
                Toast.makeText(getActivity(), "Please select a route to claim", Toast.LENGTH_LONG).show();
                getDialog().cancel();

            }
        });
    }
}