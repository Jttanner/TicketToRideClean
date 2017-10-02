package com.encoder;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * This class handles all encoding(and will have methods for decoding) to and from JSON
 * */
public class Encoder {

    private Gson gson = new Gson();
    public Encoder() {
    }
    /**
     * This encodes java objects into JSON
     * */
    public void encode(Object obj,OutputStream respBody) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(respBody);
        writer.write(gson.toJson(obj));
        writer.flush();
    }

}
