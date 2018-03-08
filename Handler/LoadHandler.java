package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import JSON.Encoder;
import Service.LoadService;
import Service.Request.LoadRequest;
import Service.Result.LoadResult;

/**
 * Represents the load handler object instantiated when the /load api is called
 */


public class LoadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        System.out.print("Load Handler!\n");

        Boolean success = false;
        LoadResult result = null;
        String respData;

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                //*************** GET DATA FROM EXCHANGE ****************

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();
                // Read JSON string from the input stream

                Encoder encoder = new Encoder();
                String reqData = encoder.readString(reqBody);

                //print json data
                System.out.println("Data\n" + reqData + "\n");

                //****************************************************


                //************** PERFORM SERVICE ****************

                try
                {
                    LoadRequest request = (LoadRequest)encoder.decode(reqData, LoadRequest.class);
                    LoadService service = new LoadService();

                    result = service.service(request);
                }
                catch(Exception e)
                {
                    result.setMessage("Invalid request data (missing values, invalid values, etc.)");
                    System.out.print(e.getMessage());
                }

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
            System.out.print(e.getMessage());
        }

    }

}
