package Handler;

import Service.*;
import Service.Result.*;
import Service.Request.*;

/**
 * Represents the register handler object instantiated when the /register api is called
 */

public class RegisterHandler {

    public RegisterHandler(String[] inputTestArray) {

        RegisterRequest rRequest = new RegisterRequest();
        rRequest.setUserName(inputTestArray[0]);

        RegisterService rService = new RegisterService();


        RegisterResult rResult = rService.service(rRequest);



    }

    /*
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
