package ServerModel;

/**
 * Created by Hwang on 9/28/2017.
 */

public class DeleteGameCommandData extends Command {
    private String str;

    public DeleteGameCommandData() {
        setType("deleteGame");
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
