package servercomms;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import encoder.Encoder;


/**
 * Created by tyler on 9/12/2017.
 * This class communicates with the server. It is called by the server proxy, given URLs and request objects and send those
 * off through http and returns the results.
 */
class ClientCommunicator {
    /**The instance of the CC*/
    private static ClientCommunicator ourInstance = new ClientCommunicator();
    /**THe tag for the log*/
    private final String TAG = "ClientCommunicator";

    static ClientCommunicator getInstance() {
        return ourInstance;
    }

    private ClientCommunicator() {
    }
    /**This method is used by the proxy to send URLs to the server
     * @param url The url being sent
     * @param request The type of request
     * @param typeOfRequest POST,GET,etc...
     * @return A result from the server*/
    InputStream send(URL url, Object request, String typeOfRequest) {
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod(typeOfRequest);
            http.setDoOutput(true);    // There is a request body
            http.addRequestProperty("Accept", "application/json");

            //Makes an encoder object to encode the request object into the output stream
            Encoder encoder = new Encoder();
            Log.d(TAG,"getting outputstream" + request.getClass());
            OutputStream respBody = http.getOutputStream();
            encoder.encode(request, respBody); //Sending it into object -> JSON
            respBody.close();
            http.connect();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return http.getInputStream();

            } else {
                Log.d(TAG,"ERROR: " + http.getResponseMessage());
                return http.getErrorStream();
            }
        } catch (IOException e) {
            Log.d(TAG,"ERROR: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }

}