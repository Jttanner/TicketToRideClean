package ui.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Adapters.ClaimRouteAdapter;
import Adapters.GameHistoryAdapter;
import MVP_coms_classes.MVP_ClaimRoute;
import clientModel.CModel;
import presenters.MapPresenter;
import teamjapannumbahone.tickettoride.R;

public class ClaimRouteFragment extends android.support.v4.app.DialogFragment implements MVP_ClaimRoute.ClaimRouteMapViewOps{
    private final String TAG = "claim_route_fragment";

    private RecyclerView recyclerView;
    private ClaimRouteAdapter adapter;


    private String selectedCityName;

    public String getSelectedCityName() {
        return selectedCityName;
    }

    public void setSelectedCityName(String selectedCityName) {
        this.selectedCityName = selectedCityName;
    }

    private OnFragmentInteractionListener mListener;

    public ClaimRouteFragment() {
        // Required empty public constructor
    }

    MapPresenter mapPresenter;

    public void setMapPresenter(MapPresenter presenter){
        this.mapPresenter = mapPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_claim_route, container, false);

        getDialog().show();
        getDialog().getWindow().setLayout(1000,1000);

        recyclerView = (RecyclerView) v.findViewById(R.id.choose_claim_route_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ClaimRouteAdapter(CModel.getInstance().getUnclaimedRouteList().getRoutesFromCity(selectedCityName), CModel.getInstance().getUnclaimedRouteList().getCityRouteInfoStrings(selectedCityName), getContext());
        recyclerView.setAdapter(adapter);

        return v;
    }


    @Override
    public void UpdateClaimRouteView() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
