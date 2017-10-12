package result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CommandResult {

    /*
    Packaged result for a Result. Able to handle any type of data since its contained within an object.
    Just make sure to cast it back to it's original data form when receiving it
    */

    private boolean success;
    private Object data;
    private String type;
    private String errorInfo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CommandResult() {
    }

    public CommandResult(boolean success, Object data, String errorInfo) {
        this.success = success;
        this.data = data;
        this.errorInfo = errorInfo;
    }

    CommandResult(boolean success, String errorInfo){
        this.success = success;
        this.errorInfo = errorInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
