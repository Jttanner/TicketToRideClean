package com.encoder;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

import result.CommandResult;
import result.GetGameCommandResult;
import result.LoginResult;
import result.RegisterResult;

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
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, RegisterResult.class);
        }catch (Exception e){
            return new RegisterResult(false,e.getMessage());
        }
    }

    /**Handles the decoding of login  result objects,
     * @param inputStream The input from the server
     * @return LoginResult A LoginResult result
     * */
    public LoginResult decodeLoginResult(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, LoginResult.class);
        }
        catch (Exception e){
            return new LoginResult(false, e.getMessage());
        }
    }
    /**Handles the decoding of command results coming from the server
    * @param inputStream The input given back
    * @return CommandResult**/
    public CommandResult decodeCommandResult(InputStream inputStream) {
        try {
            Reader reader = new InputStreamReader(inputStream);
            return gson.fromJson(reader, CommandResult.class);
        }catch (Exception e){
            return new CommandResult(false,e.getMessage());
        }
    }

    public GetGameCommandResult decodeGetGameCommandResult(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        return gson.fromJson(reader, GetGameCommandResult.class);
    }


}
