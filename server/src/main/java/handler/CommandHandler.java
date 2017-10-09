package handler;

import com.encoder.Encoder;
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
                    realCommand.setGameObject(command.getGameObject());
                    result = realCommand.execute();
                    break;
                case "joinGame":
                    JoinGameCommandData joinGameCommandData = gson.fromJson(reqData, JoinGameCommandData.class);
                    JoinGameCommand joinGameCommand = new JoinGameCommand(Integer.parseInt(joinGameCommandData.getUser().getUserID()), joinGameCommandData.getUser());
                    result = joinGameCommand.execute();
                    break;
                case "getGameList":
                    //we don't really need these objects.
                    GetGameListCommandData getGameListCommandData = gson.fromJson(reqData, GetGameListCommandData.class);
                    GetGameListCommand getGameListCommand = new GetGameListCommand();
                    getGameListCommand.setType("getGameList");
                    result = getGameListCommand.execute();
                    break;
                case "startGame":
                    StartGameCommandData startGameCommandData = gson.fromJson(reqData, StartGameCommandData.class);
                    StartGameCommand startGameCommand = new StartGameCommand((Game)startGameCommandData.getGame());
                    result = startGameCommand.execute();
                default:
                    break;
            }
//             result = word.execute();
            //String jsonStr = gson.toJson(result);
            OutputStream respBody = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            new Encoder().encode(result,exchange.getResponseBody());
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
