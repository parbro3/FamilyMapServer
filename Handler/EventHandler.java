package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.net.HttpURLConnection;

import JSON.Encoder;
import Service.EventService;
import Service.Request.EventRequest;
import Service.Result.EventResult;

/**
 * Represents the event handler object instantiated when the /event api is called
 */

public class EventHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        System.out.print("Person Handler!\n");

        Boolean success = false;
        EventResult result = null;
        String respData = "";

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                //*************** GET DATA FROM EXCHANGE ****************

                //get auth token from header
                Headers reqHeaders = exchange.getRequestHeaders();
                String authID = reqHeaders.getFirst("Authorization");

                // NO REQUEST BODY

                //****************************************************


                //************** PERFORM SERVICE ****************

                Encoder encoder = new Encoder();

                //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                EventRequest request = new EventRequest();
                request.setAuthID(authID);
                EventService service = new EventService();

                result = service.service(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                //************************************************

                //*************** SEND DATA BACK *****************
                // Get the response body output stream.
                respData = encoder.encode(result);

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
            System.out.print(e.getMessage());
        }

    }
}
