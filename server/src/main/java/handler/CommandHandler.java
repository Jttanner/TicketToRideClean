package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import command.AddChatCommand;
import command.CreateGameCommand;
import command.GetCommandListDataClient;
import command.GetCommandListServer;
import command.GetGameListCommand;
import command.JoinGameCommand;
import command.StartGameCommand;
import commandData.ChatCommandData;
import commandData.Command;
import commandData.CreateGameCommandData;
import commandData.GetCommandListData;
import commandData.JoinGameCommandData;
import commandData.StartGameCommandData;
import encoder.Encoder;
import result.CommandResult;

/**
 * Created by Hwang on 9/28/2017.
 */

public class CommandHandler extends BaseHandler implements HttpHandler {

    /*
    All commands will come through here and be sorted out. Results will
     */

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            InputStream reqBody = exchange.getRequestBody();
            String reqData = readString(reqBody);

            Gson gson = new Gson();
            Command cmd = gson.fromJson(reqData, Command.class);
            CommandResult result = null;
            Command commandData = null;
            switch (cmd.getType()) {
                case "createGame":
                    CreateGameCommandData command = gson.fromJson(reqData,CreateGameCommandData.class);
                    CreateGameCommand realCommand = new CreateGameCommand(command.getGame());
                    result = realCommand.execute();
                    break;
                case "joinGame":
                    JoinGameCommandData joinGameCommandData = gson.fromJson(reqData, JoinGameCommandData.class);
                    JoinGameCommand joinGameCommand = new JoinGameCommand(joinGameCommandData.getGameID(), joinGameCommandData.getUser());
                    result = joinGameCommand.execute();
                    break;
                case "getGameList":

                    GetGameListCommand getGameListCommand = new GetGameListCommand();
                    result = getGameListCommand.execute();
                    break;
                case "startGame":
                    StartGameCommandData startGameCommandData = gson.fromJson(reqData, StartGameCommandData.class);
                    StartGameCommand startGameCommand = new StartGameCommand(startGameCommandData);
                    result = startGameCommand.execute();
                    break;
                case "getCommandList":
                    GetCommandListData commandListData = gson.fromJson(reqData,GetCommandListData.class);
                    GetCommandListServer commandListServer = new GetCommandListServer(commandListData.getGameID());
                    commandData = new GetCommandListDataClient(commandListServer.execute());
                case "addChat":
                    ChatCommandData chatCommandData = gson.fromJson(reqData,ChatCommandData.class);
                    AddChatCommand addChatCommand = new AddChatCommand(chatCommandData);
                    addChatCommand.execute();
                default:
                    break;
            }
            OutputStream respBody = exchange.getResponseBody();
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            //Whether we are sending over a command result or a commandData
            if(result != null) {
                new Encoder().encode(result,exchange.getResponseBody());
            }
            else if(commandData != null){
                new Encoder().encode(commandData,exchange.getResponseBody());
            }
            respBody.close();
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
