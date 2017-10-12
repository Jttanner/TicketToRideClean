package result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class CommandResult {

    private boolean success;
    private Object data;
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

    public CommandResult(boolean success, Object data, String errorInfo) {
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

    public CommandResult(String type, boolean success){
        this.success = success;
        this.type = type;
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

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
