package handler;

import encoder.Encoder;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import command.CreateGameCommand;
import command.GetGameListCommand;
import command.JoinGameCommand;
import command.StartGameCommand;
import commandData.Command;
import commandData.CreateGameCommandData;
import commandData.GetGameListCommandData;
import commandData.JoinGameCommandData;
import commandData.StartGameCommandData;
import modeling.Game;
import result.CommandResult;

/**
 * Created by Hwang on 9/28/2017.
 */

public class CommandHandler extends BaseHandler implements HttpHandler {

    /*
    It's coming from ServerCommunicator. Receives the command from the inputStream, finds what type the command is, and calls execute on that command.
    Goes to it's corresponding command in the command package.
     */

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream reqBody = exchange.getRequestBody();
            String reqData = readString(reqBody);

            Gson gson = new Gson();
            Command cmd = gson.fromJson(reqData, Command.class);
            CommandResult result = null;
            switch (cmd.getType()) {
                case "createGame":
                    CreateGameCommandData command = gson.fromJson(reqData,CreateGameCommandData.class);
                    CreateGameCommand realCommand = new CreateGameCommand();
                    realCommand.setGame(command.getGame());
                    result = realCommand.execute(); //Sending it to the command
                    break;
                case "joinGame":
                    //result.setType("joinGame");
                    JoinGameCommandData joinGameCommandData = gson.fromJson(reqData, JoinGameCommandData.class);
                    JoinGameCommand joinGameCommand = new JoinGameCommand(joinGameCommandData.getGameID(), joinGameCommandData.getUser());
                    result = joinGameCommand.execute(); //Sending it to the command
                    break;
                case "getGameList":
                    //we don't really need these objects.
                    GetGameListCommandData getGameListCommandData = gson.fromJson(reqData, GetGameListCommandData.class);
                    GetGameListCommand getGameListCommand = new GetGameListCommand();
                    getGameListCommand.setType("getGameList");
                    result = getGameListCommand.execute(); //Sending it to the command
                    break;
                case "startGame":
                    StartGameCommandData startGameCommandData = gson.fromJson(reqData, StartGameCommandData.class);
                    StartGameCommand startGameCommand = new StartGameCommand((Game)startGameCommandData.getGame());
                    result = startGameCommand.execute(); //Sending it to the command
                    result.setType("startGame");
                default:
                    break;
            }
//             result = word.execute();
            //String jsonStr = gson.toJson(result);
            OutputStream respBody = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            new Encoder().encode(result,exchange.getResponseBody()); //Back to JSON format
            //writeString(jsonStr, respBody);
            respBody.close();
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
