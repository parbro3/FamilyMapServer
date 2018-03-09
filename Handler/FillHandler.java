package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;


import Service.FillService;
import Service.Request.FillRequest;
import Service.Result.FillResult;
import JSON.Encoder;

/**
 * Represents the fill handler object instantiated when the /fill api is called
 */

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        //System.out.print("Register Handler!\n");

        Boolean success = false;
        FillResult result = null;
        String respData = "";

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                //*************** GET DATA FROM EXCHANGE ****************

                //get id from url
                URI url = exchange.getRequestURI();
                String urlString = url.toString();
                //System.out.print("URL: " + urlString);
                String[] urlParameters = urlString.split("/");
                String userName = urlParameters[2];

                int generations;
                if(urlParameters.length < 4)
                {
                    generations = 4;
                }
                else
                {
                    generations = Integer.valueOf(urlParameters[3]);
                }

                //****************************************************


                //************** PERFORM SERVICE ****************
                Encoder encoder = new Encoder();

                FillRequest request = new FillRequest();
                request.setGenerations(generations);
                request.setUserName(userName);
                FillService service = new FillService();

                result = service.service(request);
                respData = encoder.encode(result);

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
