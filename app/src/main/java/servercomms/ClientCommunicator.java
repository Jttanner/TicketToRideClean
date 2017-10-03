package servercomms;

import com.encoder.Encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import result.RegisterResult;
import result.ResultObject;


/**
 * Created by tyler on 9/12/2017.
 * This class communicates with the server. It is called by the server proxy, given URLs and request objects and send those
 * off through http and returns the results.
 */
class ClientCommunicator {
    private static ClientCommunicator ourInstance = new ClientCommunicator();

    static ClientCommunicator getInstance() {
        return ourInstance;
    }

    private ClientCommunicator() {
    }

    ResultObject send(URL url, Object request) {
        try {

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);    // There is a request body

            http.addRequestProperty("Accept", "application/json");
            //Makes an encoder object to encode the request object into the output stream
            Encoder encoder = new Encoder();
            OutputStream respBody = http.getOutputStream();
            encoder.encode(request, respBody);
            respBody.close();
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //http.getInputStream();
                //return encoder.decodeResultObject(http.getInputStream());
                return new RegisterResult(false,"error");
                //TODO handle decoding

            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                //TODO handle decoding
                //return encoder.decodeResultObject(http.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}