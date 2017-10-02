package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.SQLException;

import ServerModel.ServerFacade;
import request.*;
import result.*;

/**
 * Created by Hwang on 9/28/2017.
 */

public class LoginHandler extends BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {

            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                InputStream reqBody = exchange.getRequestBody();

                String reqData = readString(reqBody);

                Gson gson = new Gson();

                LoginRequest loginRequest = gson.fromJson(reqData, LoginRequest.class);
                ServerFacade loginService = new ServerFacade();
                LoginResult lr = null;

                try
                {
                    lr = loginService.login(loginRequest);
                }
                //catch (SQLException e)
                //{
                //    e.printStackTrace();
                //}
                //Database.DatabaseException e
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                String jsonStr = gson.toJson(lr);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonStr, respBody);
                respBody.close();

                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
