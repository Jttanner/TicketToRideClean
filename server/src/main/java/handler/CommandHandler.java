package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import ServerModel.ServerModel;
import command.AddChatCommand;
import command.ClaimDestinationCardCommand;
import command.ClaimInitialDestinationCardCommand;
import command.ClaimRouteCommand;
import command.CreateGameCommand;
import command.DrawDestinationCardCommand;
import command.DrawTrainCardCommand;
import command.EndTurnCommandServer;
import command.GetCmndListServer;
import command.GetGameListCommand;
import command.IncrementCommandIndexCommand;
import command.JoinGameCommand;
import command.ResetCommandIndex;
import command.StartGameCommand;
import commandData.ChatCommandData;
import commandData.ClaimDestinationCardCommandData;
import commandData.ClaimInitialDestinationCardCommandData;
import commandData.ClaimRouteCommandData;
import commandData.Command;
import commandData.CreateGameCommandData;
import commandData.DrawDestinationCardCommandData;
import commandData.DrawTrainCardCommandData;
import commandData.EndTurnCommandData;
import commandData.GetCmndDataFromServer;
import commandData.GetCmndListDataToClient;
import commandData.IncrementCommandIndexCommandData;
import commandData.JoinGameCommandData;
import commandData.ResetCommandIndexData;
import commandData.StartGameCommandData;
import encoder.Encoder;
import result.CommandResult;
import ServerModel.*;

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
            System.out.println("Command handler: " + cmd.getType());
            switch (cmd.getType()) {
                case "setZero":
                    //Command ZeroCommand = gson.fromJson(reqData,Command.class);
                    //ServerModel.getInstance().zeroOut((String)ZeroCommand.getData());
                    break;
                case "createGame":
                    CreateGameCommandData command = gson.fromJson(reqData,CreateGameCommandData.class);
                    CreateGameCommand realCommand = new CreateGameCommand(command.getGame());
                    result = realCommand.execute();
                    break;
                case "joinGame":
                    JoinGameCommandData joinGameCommandData = gson.fromJson(reqData, JoinGameCommandData.class);
                    JoinGameCommand joinGameCommand = new JoinGameCommand(joinGameCommandData.getGameID(), joinGameCommandData.getUser());
                    result = joinGameCommand.execute();
                    if (result.isSuccess()){
                        ServerModel.getInstance().getPlugin().saveGame(ServerModel.getInstance().getGames().findGame(joinGameCommandData.getGameID()));

                    }
                    break;
                case "getGameList":

                    GetGameListCommand getGameListCommand = new GetGameListCommand();
                    result = getGameListCommand.execute();
                    break;
                case "startGame":
                    StartGameCommandData startGameCommandData = gson.fromJson(reqData, StartGameCommandData.class);
                    StartGameCommand startGameCommand = new StartGameCommand(startGameCommandData);
                    result = startGameCommand.execute();
                    if (result.isSuccess()){
                        ServerFacade.getInstance().saveCommands(startGameCommandData.getGame(), startGameCommandData);
                    }
                    break;
                case "getCommandList":
                    //System.out.println("Command handler: getCommandList 2");
                    GetCmndDataFromServer getCmndDataFromServer = gson.fromJson(reqData,GetCmndDataFromServer.class);
                    String gameId = getCmndDataFromServer.getGameId();
                    GetCmndListServer commandListServer = new GetCmndListServer(gameId);
                    commandData = new GetCmndListDataToClient(commandListServer.execute(),gameId);
                    //System.out.println("Command handler: getCommandList 3");
                    break;
                case "addChat":
                    ChatCommandData chatCommandData = gson.fromJson(reqData, ChatCommandData.class);
                    AddChatCommand addChatCommand = new AddChatCommand(chatCommandData);
                    addChatCommand.execute();
                    break;
                /*case "drawTrainCardDeck":
                    DrawTrainCardDeckCommandData drawTrainCardDeckCommandData = gson.fromJson(reqData, DrawTrainCardDeckCommandData.class);
                    DrawTrainCardDeckCommand drawTrainCardDeckCommand = new DrawTrainCardDeckCommand(drawTrainCardDeckCommandData);
                    result = drawTrainCardDeckCommand.execute();
                    break;*/
                case "drawTrainCard":
                    DrawTrainCardCommandData drawTrainCardCommandData = gson.fromJson(reqData, DrawTrainCardCommandData.class);
                    DrawTrainCardCommand drawTrainCardCommand = new DrawTrainCardCommand(drawTrainCardCommandData);
                    result = drawTrainCardCommand.execute();
                    if (result.isSuccess()){
                        ServerFacade.getInstance().saveCommands(drawTrainCardCommandData.getGameID(), drawTrainCardCommandData);
                    }
                    break;
                case "drawDestinationCards":
                    DrawDestinationCardCommandData drawDestinationCardCommandData = gson.fromJson(reqData,DrawDestinationCardCommandData.class);
                    DrawDestinationCardCommand drawDestinationCardCommand = new DrawDestinationCardCommand(drawDestinationCardCommandData);
                    result = drawDestinationCardCommand.execute();
                    if (result.isSuccess()){
                        ServerFacade.getInstance().saveCommands(drawDestinationCardCommandData.getGameID(), drawDestinationCardCommandData);
                    }
                    break;
                case "claimDestinationCards":
                    ClaimDestinationCardCommandData claimDestinationCardCommandData = gson.fromJson(reqData,ClaimDestinationCardCommandData.class);
                    ClaimDestinationCardCommand claimDestinationCardCommand = new ClaimDestinationCardCommand(claimDestinationCardCommandData);
                    result = claimDestinationCardCommand.execute();
                    if (result.isSuccess()){
                        ServerFacade.getInstance().saveCommands(claimDestinationCardCommandData.getGameID(), claimDestinationCardCommandData);
                    }
                    break;
                case "claimInitialDestinationCards":
                    ClaimInitialDestinationCardCommandData claimInitialDestinationCardCommandData = gson.fromJson(reqData, ClaimInitialDestinationCardCommandData.class);
                    ClaimInitialDestinationCardCommand claimInitialDestinationCardCommand = new ClaimInitialDestinationCardCommand(claimInitialDestinationCardCommandData);
                    result = claimInitialDestinationCardCommand.execute();
                    if (result.isSuccess()){
                        ServerFacade.getInstance().saveCommands(claimInitialDestinationCardCommandData.getGameID(), claimInitialDestinationCardCommandData);
                    }
                    break;
                case "claimRoute":
                    ClaimRouteCommandData claimRouteCommandData = gson.fromJson(reqData,ClaimRouteCommandData.class);
                    ClaimRouteCommand claimRouteCommand = new ClaimRouteCommand(claimRouteCommandData);
                    result = claimRouteCommand.execute();
                    if (result.isSuccess()){
                        ServerFacade.getInstance().saveCommands(claimRouteCommandData.getGameID(), claimRouteCommandData);
                    }
                    break;
                case"endTurn":
                    EndTurnCommandData data = gson.fromJson(reqData,EndTurnCommandData.class);
                    EndTurnCommandServer endTurnCommandServer = new EndTurnCommandServer(data);
                    endTurnCommandServer.execute();
                    ServerFacade.getInstance().saveCommands(data.getGameID(), data);
                    break;
                case "incrementCommandIndex":
                    IncrementCommandIndexCommandData incrementCommandIndexCommandData = gson.fromJson(reqData, IncrementCommandIndexCommandData.class);
                    IncrementCommandIndexCommand incrementCommandIndexCommand = new IncrementCommandIndexCommand(incrementCommandIndexCommandData.getGameID(), incrementCommandIndexCommandData.getPlayerName());
                    incrementCommandIndexCommand.execute();
                    break;
                case "ResetCommandIndex":
                    //ResetCommandIndexData resetCommandIndexData = gson.fromJson(reqData, ResetCommandIndexData.class);
                   // ResetCommandIndex reset = new ResetCommandIndex(resetCommandIndexData.getGameId());
                    //reset.execute();
                    break;
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
                if (commandData.getType().equals("getCommandList")){
                    new Encoder().encode(commandData,exchange.getResponseBody());
                } else{
                    new Encoder().encode(commandData,exchange.getResponseBody());
                }

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
