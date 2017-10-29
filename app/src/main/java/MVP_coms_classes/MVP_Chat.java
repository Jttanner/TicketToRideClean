package MVP_coms_classes;

/**
 * Created by ACL1 on 10/28/2017.
 */

public interface MVP_Chat {

    interface ChatViewOps{
        void UpdateChatView();
    }

    interface ChatPresenterOps{
       void UpdateChatServer(String s);
    }
}
