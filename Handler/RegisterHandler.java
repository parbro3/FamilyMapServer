package Handler;

import java.io.*;
import java.net.*;
import com.google.gson.*;
import com.sun.net.httpserver.*;

import Service.RegisterService;
import Service.Request.RegisterRequest;
import Service.Result.RegisterResult;

/**
 * Represents the register handler object instantiated when the /register api is called
 */

public class RegisterHandler implements HttpHandler{

    public RegisterHandler() {}

    @Override
    public void handle(HttpExchange exchange)
    {
        System.out.print("Register Handler!\n");
        Gson gson = new Gson();
        Boolean success = false;
        RegisterResult rResult = null;

        try
        {
            //check for post... should be post
            if(exchange.getRequestMethod().toLowerCase().equals("post"))
            {

                //should we check if the username is already used??
                //or do we leave that up to the DAO?

                Headers reqHeaders = exchange.getRequestHeaders();
                //String body = exchange.getRequestBody();

                //check for authorization header... if it needs authorization
                //which registration doesn't need one... it returns one...

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();
                // Read JSON string from the input stream
                String reqData = readString(reqBody);

                //print json data
                System.out.println("Data\n" + reqData + "\n");

                //CREATE REQUEST, SERVICE, AND RESULT, AND ENTER SERVICE CLASS
                RegisterRequest rRequest = gson.fromJson(reqData, RegisterRequest.class);


                RegisterService rService = new RegisterService();

                //if username doesn't exist in system... create it.
                if (!rService.checkUsername(rRequest.getUserName())) {
                    rResult = rService.service(rRequest);
                    String respData = gson.toJson(rResult);
                    //send response headers
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    System.out.print("Handler: User Created\n");
                }
                else
                {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    System.out.print("Handler: Username already exists!\n");
                }

                // Get the response body output stream.
                OutputStream respBody = exchange.getResponseBody();
                // Write the JSON string to the output stream.

                //respData is going to be the result stuff...

                String respData = gson.toJson(rResult);
                writeString(respData, respBody);


                // Close the output stream.  This is how Java knows we are done
                // sending data and the response is complete/
                respBody.close();

                success = true;
            }
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }

    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
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
