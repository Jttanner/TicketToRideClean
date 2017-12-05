package ServerModel;

/**
 * Created by tyler on 12/5/2017.
 */

class NeedTransactionException extends Exception {
    @Override
    public String getMessage() {
        return "You need to open a transaction or close the last one before you do this operation";
    }
}
