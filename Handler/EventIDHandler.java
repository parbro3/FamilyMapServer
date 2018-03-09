package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import JSON.Encoder;
import Service.EventIDService;
import Service.EventService;
import Service.Request.EventIDRequest;
import Service.Request.EventRequest;
import Service.Result.EventIDResult;
import Service.Result.EventResult;

/**
 * Represents the eventID handler object instantiated when the /event/eventID api is called
 */

public class EventIDHandler implements HttpHandler {

    public EventIDHandler() {}

    /**
     * Reads in the http exchange from the server. checks for post.
     * gets data, builds a request, and sends the data over to the service class.
     * A result is returned and sent back to the client.
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange)
    {
        //System.out.print("PersonID Handler!\n");

        Boolean success = false;
        EventIDResult eidResult = null;
        EventResult eResult = null;
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
                //System.out.print("URL: " + urlString);
                String[] urlParameters = urlString.split("/");

                //get auth token from header
                Headers reqHeaders = exchange.getRequestHeaders();
                String authID = reqHeaders.getFirst("Authorization");


                if(urlParameters.length < 3)
                {
                    //************** PERFORM SERVICE ****************

                    Encoder encoder = new Encoder();

                    //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                    EventRequest request = new EventRequest();
                    request.setAuthID(authID);
                    EventService service = new EventService();

                    eResult = service.service(request);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    //************************************************

                    //*************** SEND DATA BACK *****************
                    // Get the response body output stream.
                    respData = encoder.encode(eResult);

                    OutputStream respBody = exchange.getResponseBody();

                    //WRITE DATA TO RESPBODY
                    encoder.writeString(respData, respBody);

                    //SEND DATA
                    respBody.close();

                    //************************************************

                    success = true;
                }
                else
                {
                    //************** PERFORM SERVICE ****************
                    String eventID = urlParameters[2];

                    Encoder encoder = new Encoder();

                    //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                    EventIDRequest request = new EventIDRequest();
                    request.setAuthID(authID);
                    request.setEventID(eventID);
                    EventIDService service = new EventIDService();

                    eidResult = service.service(request);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    //************************************************

                    //*************** SEND DATA BACK *****************
                    // Get the response body output stream.
                    respData = encoder.encode(eidResult);

                    OutputStream respBody = exchange.getResponseBody();

                    //WRITE DATA TO RESPBODY
                    encoder.writeString(respData, respBody);

                    //SEND DATA
                    respBody.close();

                    //************************************************

                    success = true;
                }



                // NO REQUEST BODY

                //****************************************************



            }
        }
        catch(Exception e)
        {
            //System.out.print(e.getMessage());
        }

    }

}
