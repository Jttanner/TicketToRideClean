package handler;

import encoder.Encoder;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import ServerModel.ServerFacade;
import request.RegisterRequest;
import result.RegisterResult;

/**
 * Created by Hwang on 9/28/2017.
 */

public class RegisterHandler extends BaseHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream respBody = exchange.getResponseBody();
        Encoder encoder = new Encoder();
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                Gson gson = new Gson();

                RegisterRequest registerRequest = gson.fromJson(reqData, RegisterRequest.class);
                RegisterResult rs;
                try {
                    rs = ServerFacade.getInstance().register(registerRequest);
                    encoder.encode(rs,respBody);
                    respBody.close();

                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                encoder.encode(new RegisterResult(false,"HTTP_BAD_REQUEST"), respBody);
                respBody.close();
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            encoder.encode(new RegisterResult(false,"HTTP_SERVER_ERROR"), respBody);
            respBody.close();
            // e.printStackTrace();
        }
    }
}
