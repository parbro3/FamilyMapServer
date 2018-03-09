package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.net.HttpURLConnection;

import JSON.Encoder;
import Service.PersonService;
import Service.Request.PersonRequest;
import Service.Result.PersonResult;

/**
 * Represents the person handler object instantiated when the /person api is called
 */


public class PersonHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        //System.out.print("Person Handler!\n");

        Boolean success = false;
        PersonResult pResult = null;
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
                PersonRequest pRequest = new PersonRequest();
                pRequest.setAuthID(authID);
                PersonService pService = new PersonService();

                pResult = pService.service(pRequest);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                //************************************************

                //*************** SEND DATA BACK *****************
                // Get the response body output stream.
                respData = encoder.encode(pResult);

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
