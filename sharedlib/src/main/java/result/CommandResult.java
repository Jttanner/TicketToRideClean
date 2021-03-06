package result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CommandResult extends Result {

    /*
    Packaged result for a Result. Able to handle any type of data since its contained within an object.
    Just make sure to cast it back to it's original data form when receiving it
    */

    private boolean success;
    private Result data;
    private String message;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CommandResult() {
    }

    public CommandResult(boolean success, Result data, String errorInfo) {
        this.success = success;
        this.data = data;
        this.message = errorInfo;
    }

    public CommandResult(boolean success, String errorInfo){
        this.success = success;
        this.message = errorInfo;
    }

    public CommandResult(boolean success){
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Result data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
