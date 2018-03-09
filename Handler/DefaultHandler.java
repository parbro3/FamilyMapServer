package Handler;

import com.sun.net.httpserver.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Represents the clear handler object instantiated when the /clear api is called
 */

public class DefaultHandler implements HttpHandler{

    public DefaultHandler(){}

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        System.out.print("Default Handler!\n");

        String path = exchange.getRequestURI().getPath();
        if (path.equals("/") || path.length()==0)
        {
            path = "/index.html";
        }
        File file = new File("web" + path);

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream output = exchange.getResponseBody();
        FileInputStream fs = new FileInputStream(file);


        final byte[] buffer = new byte[0x10000];
        int count = 0;
        while((count = fs.read(buffer)) >=0)
        {
            output.write(buffer, 0, count);
        }

        output.flush();
        output.close();
        fs.close();
    }



}
