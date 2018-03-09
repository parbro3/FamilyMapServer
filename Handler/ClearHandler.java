package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import JSON.Encoder;
import Service.ClearService;
import Service.Request.ClearRequest;
import Service.Result.ClearResult;

/**
 * Represents the clear handler object instantiated when the /clear api is called
 */

public class ClearHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        //System.out.print("Register Handler!\n");

        Boolean success = false;
        ClearResult cResult = null;
        String respData = "";

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                //*************** GET DATA FROM EXCHANGE ****************

                //NO REQUEST BODY OR HEADERS

                //****************************************************


                //************** PERFORM SERVICE ****************
                Encoder encoder = new Encoder();

                ClearRequest cRequest = new ClearRequest();
                ClearService cService = new ClearService();

                cResult = cService.service(cRequest);
                respData = encoder.encode(cResult);

                //send response headers
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                //************************************************



                //*************** SEND DATA BACK *****************
                // Get the response body output stream.
                OutputStream respBody = exchange.getResponseBody();

                //WRITE DATA TO RESPBODY
                encoder.writeString(respData, respBody);

                //SEND DATA
                respBody.close();

                //************************************************

                success = true;
            }
        }
        catch(Exception e)
        {
            //System.out.print(e.getMessage());
        }

    }
}
