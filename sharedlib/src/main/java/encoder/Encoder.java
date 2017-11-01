package encoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import commandData.ChatCommandData;
import commandData.Command;
import commandData.DrawDestinationCardCommandData;
import commandData.GetCmndListDataToClient;
import commandData.StartGameCommandData;
import modeling.CommandList;
import result.CommandResult;
import result.CreateGameCommandResult;
import result.DrawDestinationCardCommandResult;
import result.GetGameCommandResult;
import result.JoinGameCommandResult;
import result.LoginResult;
import result.RegisterResult;

/**
 * This class handles all encoding(and will have methods for decoding) to and from JSON
 */
public class Encoder {

    private Gson gson = new Gson();

    public Encoder() {
    }

    /**
     * This encodes java objects into JSON
     *
     * @param obj      The object to encode
     * @param respBody The output stream
     */
    public void encode(Object obj, OutputStream respBody) throws IOException {
        //System.out.println(obj.getClass());
        OutputStreamWriter writer = new OutputStreamWriter(respBody);
        writer.write(gson.toJson(obj));
        writer.flush();
    }

    /**
     * Handles the decoding of register result objects,
     *
     * @param inputStream The input from the server
     * @return RegisterResult A decoded RegisterResult
     */
    public RegisterResult decodeRegisterResult(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, RegisterResult.class);
        } catch (Exception e) {
            return new RegisterResult(false, e.getMessage());
        }
    }

    /**
     * Handles the decoding of login  result objects,
     *
     * @param inputStream The input from the server
     * @return LoginResult A LoginResult result
     */
    public LoginResult decodeLoginResult(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, LoginResult.class);
        } catch (Exception e) {
            return new LoginResult(false, e.getMessage());
        }
    }

    /**
     * Handles the decoding of command results coming from the server
     *
     * @param inputStream The input given back
     * @return CommandResult
     **/
    public CommandResult decodeCommandResult(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, CommandResult.class);
        } catch (Exception e) {
            return new CommandResult(false, e.getMessage());
        }
    }

    public DrawDestinationCardCommandResult decodeDestinationCardResult(InputStream stream) {
        try {
            Reader reader = new InputStreamReader(stream);
            return gson.fromJson(reader, DrawDestinationCardCommandResult.class);
        } catch (Exception e) {
            return new DrawDestinationCardCommandResult(false, e.getMessage());
        }
    }

    /**
     * Handles the decoding of GetGameCommandResult coming from the server
     *
     * @param inputStream The input given back
     * @return CommandResult
     **/
    public CommandResult decodeGetGameResult(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, GetGameCommandResult.class);
        } catch (Exception e) {
            return new CommandResult(false, e.getMessage());
        }
    }

    public CreateGameCommandResult decodeCreateResult(InputStream stream) {
        try {
            Reader reader = new InputStreamReader(stream);
            return gson.fromJson(reader, CreateGameCommandResult.class);
        } catch (Exception e) {
            return new CreateGameCommandResult(false, e.getMessage());
        }
    }

    public JoinGameCommandResult decodeJoinCommandResult(InputStream stream) {
        try {
            Reader reader = new InputStreamReader(stream);
            return gson.fromJson(reader, JoinGameCommandResult.class);
        } catch (Exception e) {
            return new JoinGameCommandResult(false, e.getMessage());
        }
    }


    public GetCmndListDataToClient decodeGetCommandListToClient(InputStream stream) {
        List<Command> list = new ArrayList<>();
        String gameID = "";
        Command command = null;
        try {


            Reader reader = new InputStreamReader(stream);
            String string = reader.toString();

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(
                    new InputStreamReader(stream, "UTF-8"));

            JsonElement element = (JsonElement) jsonObject;

            if (element.isJsonObject()) {
                JsonObject wrapper = element.getAsJsonObject();
                gameID = wrapper.get("gameId").getAsString();
                JsonObject cl = null;
                try {
                    cl = wrapper.get("returnCommandList").getAsJsonObject();
                }
                catch (NullPointerException e){
                    //if we get a null pointer it means there is nothing to get
                    return new GetCmndListDataToClient(new CommandList(), gameID);
                }
                JsonArray array = cl.get("commandList").getAsJsonArray();
                for (int i = 0; i < array.size(); i++) {
                    JsonObject object = array.get(i).getAsJsonObject();
                    switch (object.get("type").getAsString()) {
                        case "addChat":
                            command = gson.fromJson(object, ChatCommandData.class);
                            break;
                        case "startGame":
                            command = gson.fromJson(object, StartGameCommandData.class);
                            break;
                        case "drawDestinationCards":
                            command = gson.fromJson(object, DrawDestinationCardCommandData.class);
                            break;
                        //TODO: PLEASE ADD CASES HERE
                    }
                    if (command != null)
                        list.add(command);
                }
            }
            CommandList commandList = new CommandList();
            commandList.setCommandList(list);
            GetCmndListDataToClient dataToClient = new GetCmndListDataToClient(commandList, gameID);
            return dataToClient;
        } catch (Exception e) {
            // return new JoinGameCommandResult(false,e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
