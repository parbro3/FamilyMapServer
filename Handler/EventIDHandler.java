package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import JSON.Encoder;
import Service.EventIDService;
import Service.Request.EventIDRequest;
import Service.Result.EventIDResult;

/**
 * Represents the eventID handler object instantiated when the /event/eventID api is called
 */

public class EventIDHandler implements HttpHandler {

    public EventIDHandler() {}

    @Override
    public void handle(HttpExchange exchange)
    {
        System.out.print("PersonID Handler!\n");

        Boolean success = false;
        EventIDResult result = null;
        String respData = "";

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("get"))
            {
                //*************** GET DATA FROM EXCHANGE ****************

                //get id from url
                URI url = exchange.getRequestURI();
                String urlString = url.toString();
                System.out.print("URL: " + urlString);
                String[] urlParameters = urlString.split("/");
                String eventID = urlParameters[2];

                //get auth token from header
                Headers reqHeaders = exchange.getRequestHeaders();
                String authID = reqHeaders.getFirst("Authorization");

                // NO REQUEST BODY

                //****************************************************


                //************** PERFORM SERVICE ****************

                Encoder encoder = new Encoder();

                //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                EventIDRequest request = new EventIDRequest();
                request.setAuthID(authID);
                request.setEventID(eventID);
                EventIDService service = new EventIDService();

                result = service.service(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                //************************************************

                //*************** SEND DATA BACK *****************
                // Get the response body output stream.
                respData = encoder.encode(respData);

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
