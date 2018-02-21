package Handler;

import com.sun.net.httpserver.*;
import com.google.gson.*;
import Service.Request.RegisterRequest;

/**
 * Represents the register handler object instantiated when the /register api is called
 */

public class RegisterHandler implements HttpHandler{

    public RegisterHandler() {}

    @Override
    public void handle(HttpExchange exchange)
    {
        Boolean success = false;

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                Headers reqHeaders = exchange.getRequestHeaders();
                //String body = exchange.getRequestBody();

                //check for authorization header... if it needs authorization
                //which registration doesn't need one... it returns one...

                //probably first create a register request out of it
                RegisterRequest rRequest = new RegisterRequest();

                //using Gson... make the request.



            }
        }
        catch(Exception e)
        {

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
