package JSON;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import Data.LocationData;
import Service.Request.RegisterRequest;


/**
 * GSON Encoder class that converts files and json to strings and back.
 */
//should every handler class share the same encoder?? We only want one instance of gson...
    //so how can we do that??
public class Encoder {

    /**
     * gson instance
     */
    Gson gson = new Gson();

    public Encoder(){}

    /**
     * encode takes in an object and converts it to a json string.
     * @param objectToEncode
     * @return the json string
     */
    public String encode(Object objectToEncode)
    {
        return gson.toJson(objectToEncode);
    }

    /**
     * Decodes the json string into the desired object and returns the object
     * @param json
     * @param toJsonClass
     * @return the desired object
     */
    public Object decode(String json, Class toJsonClass)
    {
        return gson.fromJson(json, toJsonClass);
    }

    /**
     * Takes in the file and returns the object.. same as decode but from file
     * rather than string.
     * @param fileName
     * @param toJsonClass
     * @return
     */
    public Object decodeFile(String fileName, Class toJsonClass)
    {
        try
        {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            return gson.fromJson(fileReader, toJsonClass);
        }
        catch(FileNotFoundException e)
        {
            //System.out.print(e.getMessage());
        }
        catch(Exception e)
        {
            //System.out.print(e.getMessage());
        }
        return null;
    }

    /**
     * writes a string to the http client.
     * @param str
     * @param os
     * @throws IOException
     */
    public void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    /**
     * reads a string from the input stream and returns the string.
     * for request bodies.
     * @param is
     * @return
     * @throws IOException
     */
    public String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

}