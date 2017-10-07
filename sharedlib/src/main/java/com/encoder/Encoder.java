package com.encoder;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

import commandData.GetGameListCommandData;
import result.LoginResult;
import result.RegisterResult;
import result.ResultObject;

/**
 * This class handles all encoding(and will have methods for decoding) to and from JSON
 * */
public class Encoder {

    private Gson gson = new Gson();
    public Encoder() {
    }
    /**
     * This encodes java objects into JSON
     * @param obj The object to encode
     * @param respBody The output stream
     * */
    public void encode(Object obj,OutputStream respBody) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(respBody);
        writer.write(gson.toJson(obj));
        writer.flush();
    }
    /**Handles the decoding of register result objects,
     * @param inputStream The input from the server
     * @return RegisterResult A decoded RegisterResult
     * */
    public RegisterResult decodeRegisterResult(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, RegisterResult.class);
    }

    /**Handles the decoding of login  result objects,
     * @param inputStream The input from the server
     * @return LoginResult A LoginResult result
     * */
    public LoginResult decodeLoginResult(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, LoginResult.class);
    }

    public GetGameListCommandData decodeGetGameListCommandData(InputStream inputStream){
        Reader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, GetGameListCommandData.class);
    }

    //TODO make decoding methods

}
