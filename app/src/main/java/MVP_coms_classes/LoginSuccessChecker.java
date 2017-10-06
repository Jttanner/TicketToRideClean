package MVP_coms_classes;

/**
 * Created by tyler on 10/5/2017.
 * This class is implemented by the LoginPresenter and allows the HttpTask class to talk to the LoginPresenter
 */

public interface LoginSuccessChecker {
    /**This method is given the Presenter object that created it to allow it to return results
     * @param r THe presenter*/
    void checkLogSuccess(Object r);

}
