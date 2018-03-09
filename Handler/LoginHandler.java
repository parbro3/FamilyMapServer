package Handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import JSON.Encoder;
import Service.LoginService;
import Service.Request.LoginRequest;
import Service.Result.LoginResult;

/**
 * Represents the login handler object instantiated when the /login api is called
 */

public class LoginHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) {
        //System.out.print("Register Handler!\n");

        Boolean success = false;
        LoginResult lResult = null;
        String respData = "";

        try {
            //check for post... should be post
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {
                //*************** GET DATA FROM EXCHANGE ****************

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
                LoginRequest lRequest = (LoginRequest)encoder.decode(reqData, LoginRequest.class);
                LoginService lService = new LoginService();

                //if username doesn't exist in system... create it.
                lResult = lService.service(lRequest);
                respData = encoder.encode(lResult);
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
        } catch (Exception e) {
            //System.out.print(e.getMessage());
        }

    }

}