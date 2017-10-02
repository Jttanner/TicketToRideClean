package result;

/**
 * Created by tyler on 9/26/2017.
 */

public class ResultObject  {

    ResultObject(){

    }

    private String errorMessage;
    private boolean success;
    /**I made a constructor and setter depending on how yall want to do this*/
    public ResultObject(boolean success) {
        this.success = success;
    }

    public ResultObject(boolean success,String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
