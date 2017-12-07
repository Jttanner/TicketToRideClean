package ServerModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import commandData.ChatCommandData;
import commandData.Command;
import modeling.Game;
import modeling.GameList;
import modeling.User;
import modeling.UserInfoList;

/**
 * Serves as the root of the server where information and algorithms are stored. Accesses important data such as the list of Destination Cards,
 * list of commands, list of games, list of user info, and chat history.
 * Created by jontt on 9/27/2017.
 */

public class ServerModel {

    /*
    This holds all the information and calculations needed for the server.
     */
    private int delta_n = 0;
    private static ServerModel instance = new ServerModel();
    private Map<String, User> users = new HashMap<>(); //Key=UserName
    //private Map<String, Game> games = new HashMap<>(); //Key=gameID
    private Map<String, List<Command>> commandListMap = new HashMap<>();
    //private DestinationCardList destinationCardList = new DestinationCardList();
    private GameList gameList = new GameList();
    private UserInfoList userInfoList = new UserInfoList();
    private List<String> chatHistory = new ArrayList<>();

    /**Our Persistence manager object
    private IPersistenceManager pManager;*/
    /**Our current plugin object*/
    private IPlugin currPlugin;

    public List<String> getChatHistory() {
        return chatHistory;
    }

    public List<Command> returnCommand = new ArrayList<>();

    public List<Command> getReturnCommand() {
        return returnCommand;
    }

    public void setReturnCommand(List<Command> returnCommand) {
        this.returnCommand = returnCommand;
    }

    public void setChatHistory(List<String> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public ServerModel() {
    }

    public static ServerModel getInstance()
    {
        if(instance == null){
            instance = new ServerModel();
        }
        return instance;
    }

    User register(String userName, String password){ //If register succeeds, it'll give us back a new user object
        return userInfoList.register(userName, password);
    }

    User login(String userName, String password){
        //If the account exists and matches with one in the database...
        return new User(userInfoList.login(userName, password));
    }

    boolean createGame(Game newGame){
        //if the game has not already been made
        return gameList.addGame(newGame);
    }

    Game joinGame(User user, String gameID){
        return gameList.joinGame(user, gameID);
    }

    /*boolean startGame(String gameID){;
        commandListMap.put(gameID,new ArrayList<Command>());
        return gameList.startGame(gameID);
    }*/

    boolean deleteGame(Game game){
        return gameList.deleteGame(game);
    }

    /*
    public boolean leaveGame(Game game, Player player){
        return gameList.leaveGame(game);
    }
    */

    //(userName, User)
    public Map<String, User> getUsers() {
        return users;
    }

    //(gameID, Game)

    void addChatHistory(String s,String gameId){/*public Map<String, Game> getGamesAsMap(){
        return games;
    }*/

        gameList.findGame(gameId).getChatHistory().add(s);

        ChatCommandData chatCommandData = new ChatCommandData(gameList.findGame(gameId).getChatHistory(),gameId);
        ServerModel.getInstance().getReturnCommand().add(chatCommandData);
        ServerFacade.getInstance().addCommandToList(gameId, chatCommandData);
    }

    //Takes in the current list of games and returns a list of games that haven't started yet
    GameList getGames() {
                return this.gameList;
    }

    public Map<String, List<Command>> getCommandListMap() {
        return commandListMap;
    }

    public IPlugin getPlugin(){
        return currPlugin;
    }

    /**
     * @param fileName The PluginName
     * @param n "n" save integer*/
    public void saveArgs(String fileName, String n) throws FileNotFoundException {
        ArrayList<String> fileArgs = readFile(fileName);

        //save the persistence manager and the plugin
        currPlugin = (IPlugin) loadClass(fileName,fileArgs != null ? fileArgs.get(0) : null);
        IPersistenceManager persistenceManager = PluginRegistry.getInstance().create(currPlugin.getPManagerClassName());
        currPlugin.setPManager(persistenceManager);
        //TODO save n, the "n"
        delta_n = Integer.parseInt(n);
    }

    private ArrayList<String> readFile(String fileName) {
        // This will reference one line at a time
        String line = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                arrayList.add(line);
            }

            // Always close files.
            bufferedReader.close();
            return arrayList;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
        return  null;
    }

    private Object loadClass(String fileName,String className) {
        String jarPath = null;
        if(fileName.equals("sql.txt")){
            jarPath = "sqljarPathHere";
        }
        else if(fileName.equals("file.txt")){
            jarPath = "filejarPathHere";
        }
        // Getting the jar URL which contains target class
        try {
            URL[] classLoaderUrls = new URL[]{new URL(jarPath)};

            // Create a new URLClassLoader
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            // Load the target class
            Class<?> beanClass = urlClassLoader.loadClass(className);

            // Create a new instance from the loaded class
            return beanClass.getConstructor().newInstance();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}