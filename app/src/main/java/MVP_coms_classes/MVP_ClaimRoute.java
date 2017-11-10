package MVP_coms_classes;

/**
 * Created by jontt on 11/10/2017.
 */

public interface MVP_ClaimRoute {
    interface ClaimRouteViewOps {
        void UpdateClaimRouteView();
    }

    interface ClaimRoutePresenterOps {
        void UpdateClaimRouteToServer(String s);

    }
}
