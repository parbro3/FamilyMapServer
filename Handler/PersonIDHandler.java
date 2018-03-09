package Handler;

import com.sun.net.httpserver.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.*;

import JSON.Encoder;
import Service.PersonIDService;
import Service.PersonService;
import Service.Request.PersonIDRequest;
import Service.Request.PersonRequest;
import Service.Result.PersonIDResult;
import Service.Result.PersonResult;

/**
 * Represents the personID handler object instantiated when the /person/personID api is called
 */


public class PersonIDHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        //System.out.print("PersonID Handler!\n");

        Boolean success = false;
        PersonIDResult pidResult = null;
        PersonResult pResult = null;
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

                //call the person service
                if(urlParameters.length < 3)
                {
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
                //call personid service
                else
                {
                    String personID = urlParameters[2];

                    //************** PERFORM SERVICE ****************

                    Encoder encoder = new Encoder();

                    //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                    PersonIDRequest request = new PersonIDRequest();
                    request.setAuthID(authID);
                    request.setPersonID(personID);
                    PersonIDService pService = new PersonIDService();


                    pidResult = pService.service(request);
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    //************************************************

                    //*************** SEND DATA BACK *****************
                    // Get the response body output stream.
                    respData = encoder.encode(pidResult);

                    OutputStream respBody = exchange.getResponseBody();

                    //WRITE DATA TO RESPBODY
                    encoder.writeString(respData, respBody);

                    //SEND DATA
                    respBody.close();

                    //************************************************

                    success = true;
                }



            }
        }
        catch(Exception e)
        {
            //System.out.print(e.getMessage());
        }

    }

}


