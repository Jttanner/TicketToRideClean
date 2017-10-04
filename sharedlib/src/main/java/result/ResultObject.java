package result;

/**
 * Created by tyler on 9/26/2017.
 */

public class ResultObject  {
    private String userName;
    private boolean success;
    private String message;

    public ResultObject(){

    }
    /**I made a constructor and setter depending on how yall want to do this*/
    public ResultObject(boolean success, String userName, String errorMessage) {
        this.success = success;
    }

    public ResultObject(boolean success,String errorMessage) {
        this.success = success;
        this.message = errorMessage;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
