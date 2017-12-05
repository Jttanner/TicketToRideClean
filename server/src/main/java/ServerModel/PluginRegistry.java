package ServerModel;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tyler on 12/5/2017.
 */

public class PluginRegistry {

    public IPersistenceManager create(String name){
        return null;
    }
    public boolean loadConfig(){
        return false;
    }
    public boolean saveConfig(){
        return false;
    }
    public boolean unregisterPlugin(){
        return false;
    }
    public Iterator<IPlugin> iterator(){
        return null;
    }
    public Collection<IPlugin> getAvailablePlugins(){
        return null;
    }
    public IPlugin getCurrPlugin(){
        return null;
    }
}
