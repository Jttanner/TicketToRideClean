package ServerModel;

import modeling.User;

/**
 * Created by tyler on 12/5/2017.
 */

interface IUserDao {

    boolean registerUser(String userName, String password) throws NeedTransactionException;;

    User verifyUser(String name) throws NeedTransactionException;;

    boolean clear() throws NeedTransactionException;;

}
