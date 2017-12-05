package ServerModel;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tyler on 12/5/2017.
 */

public class PluginRegistry {
    private static PluginRegistry ourInstance = new PluginRegistry();

    static PluginRegistry getInstance() {
        return ourInstance;
    }

    private IPlugin currPlugin;

    private PluginRegistry(){}
    /**Creates the current plugin from the name and then returns the appropriate Persistence Manager
     * @param pluginName The class name of the plugin you are going to use
     * @return The IPersistenceManager*/
    IPersistenceManager create(String pluginName){

        Class<?> c = null;
        try {
            c = Class.forName(pluginName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            currPlugin = (IPlugin) c.newInstance();
            //get the correct Persistence manager
            c = Class.forName(currPlugin.getPManagerClassName());
            //return it
            return (IPersistenceManager) c.newInstance();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        return this.currPlugin;
    }

}
