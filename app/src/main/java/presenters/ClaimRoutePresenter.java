package presenters;

import java.lang.ref.WeakReference;

import MVP_coms_classes.MVP_ClaimRoute;
import clientModel.CModel;

/**
 * Created by jontt on 11/10/2017.
 */

public class ClaimRoutePresenter implements MVP_ClaimRoute.ClaimRoutePresenterOps {

    private WeakReference<MVP_ClaimRoute.ClaimRouteViewOps> myView;

    public ClaimRoutePresenter(MVP_ClaimRoute.ClaimRouteViewOps view){
        myView = new WeakReference<>(view);
        //CModel.getInstance().addObserver(this);
    }

    @Override
    public void UpdateClaimRouteToServer(String s) {

    }
}
