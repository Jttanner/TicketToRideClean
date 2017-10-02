package result;

/**
 * Created by Hwang on 9/29/2017.
 */

public class Result {

    private boolean success;
    private Object data;
    private String errorInfo;

    public Result() {
    }

    public Result(boolean success, Object data, String errorInfo) {
        this.success = success;
        this.data = data;
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
