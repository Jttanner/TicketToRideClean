package MVP_coms_classes;

import result.CommandResult;

/**
 * Created by tyler on 10/5/2017.
 * Any presenter class dealing with Command objects implements this class to allow it to communicate with the HttpTash class
 */

public interface CommandSuccessChecker {
    /**This command checks for the success of a given command
     * @param r The command result object*/
    void checkCommandSuccess(CommandResult r);
}
