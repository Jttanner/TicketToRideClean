package ServerModel;

/**
 * Created by Hwang on 9/28/2017.
 */

public class Command {

    /*
    Every command object needs a type (what command it is) and the data associated with it to successfully execute the command.
    Basically a model which stores data for the command.
     */

    private String type;
    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
