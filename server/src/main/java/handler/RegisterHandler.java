package handler;

import ServerModel.ServerFacade;
import request.*;
import result.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.sql.SQLException;

/**
 * Created by Hwang on 9/28/2017.
 */

public class RegisterHandler extends BaseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Headers reqHeaders = exchange.getRequestHeaders();

                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                Gson gson = new Gson();

                RegisterRequest registerRequest = gson.fromJson(reqData, RegisterRequest.class);
                ServerFacade registerService = new ServerFacade();
                RegisterResult rs = null;
                try {
                    rs = registerService.register(registerRequest);
                } /*catch (SQLException e) {
                    e.printStackTrace();
                } catch (Database.DatabaseException e) {
                    e.printStackTrace();
                }*/
                catch (Exception e){
                    e.printStackTrace();
                }

                String jsonStr = gson.toJson(rs);
                System.out.println(reqData);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonStr, respBody);
                respBody.close();
                success = true;

                if (!success) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    exchange.getResponseBody().close();
                }
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
