package presenters;

import java.lang.ref.WeakReference;

import MVP_coms_classes.MVP_ClaimRoute;

/**
 * Created by jontt on 11/10/2017.
 */

public class ClaimRoutePresenter implements MVP_ClaimRoute.ClaimRoutePresenterOps {

    private WeakReference<MVP_ClaimRoute.ClaimRouteMapViewOps> myView;

    public ClaimRoutePresenter(MVP_ClaimRoute.ClaimRouteMapViewOps view){
        myView = new WeakReference<>(view);
        //CModel.getInstance().addObserver(this);
    }

    @Override
    public void UpdateClaimRouteToServer(String s) {

    }
}
