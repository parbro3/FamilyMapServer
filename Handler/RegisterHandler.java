package Handler;

import java.io.*;
import java.net.*;
import com.google.gson.*;
import com.sun.net.httpserver.*;

import Service.RegisterService;
import Service.Request.RegisterRequest;
import Service.Result.RegisterResult;
import JSON.Encoder;

/**
 * Represents the register handler object instantiated when the /register api is called
 */

public class RegisterHandler implements HttpHandler{

    public RegisterHandler() {}

    /**
     * Reads in the http exchange from the server. checks for post.
     * gets data, builds a request, and sends the data over to the service class.
     * A result is returned and sent back to the client.
     * @param exchange
     */
    @Override
    public void handle(HttpExchange exchange)
    {
        //System.out.print("Register Handler!\n");

        Boolean success = false;
        RegisterResult rResult = null;
        String respData = "";

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("post"))
            {


                //just adding this for Code Guru test on aws
                List<Integer> listOfInts = new ArrayList<Integer>();
                listOfInts.add(10);
                listOfInts.add(20);
                listOfInts.add(30);
                listOfInts.add(40);
                listOfInts.add(50);
                for(int i = 0; i < 10; i++){
                    for(int j = 0; j < 20; j++){
                        for(int k = 0; k < 30; k++){
                            listOfInts.add(1);            
                        }
                    }
                }


                //*************** GET DATA FROM EXCHANGE ****************

                Headers reqHeaders = exchange.getRequestHeaders();
                //String body = exchange.getRequestBody();

                //check for authorization header... if it needs authorization
                //which registration doesn't need one... it returns one...

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();
                // Read JSON string from the input stream

                Encoder encoder = new Encoder();
                String reqData = encoder.readString(reqBody);

                //print json data
                //System.out.println("Data\n" + reqData + "\n");

                //****************************************************


                //************** PERFORM SERVICE ****************

                //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                RegisterRequest rRequest = (RegisterRequest)encoder.decode(reqData, RegisterRequest.class);
                RegisterService rService = new RegisterService();

                //if username doesn't exist in system... create it.

                rResult = rService.service(rRequest);
                respData = encoder.encode(rResult);
                //String respData = gson.toJson(rResult);
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



    /*

    RegisterRequest rRequest = new RegisterRequest();

    RegisterService rService = new RegisterService();


    RegisterResult rResult = rService.service(rRequest);

    Instantiate a RegisterService class.
    call RegisterService.register();

    something like..

    RegisterRequest rRequest = new RegisterRequest();
    rRequest.username = httphandler.bodyinfo[0];

    RegisterService rService = new RegisterService();

    RegisterResult rResult = rService.register( rRequest );



    maybe have a service interface that has a service method that every class has to implement

     */



/*
handle(HTTPExchange exchange)
decode the exchange...
then send the request when instantiating a service.
RegisterService.register(RRequest)
 */


    /*
        you need empty constructors for Gson to work... getters and setters.
        Gson.fromJson(String jsonString, RegisterRequest.class);
     */


    /*
    create a client, shared, and server module
     */
}
