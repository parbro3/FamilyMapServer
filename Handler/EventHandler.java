package Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Represents the event handler object instantiated when the /event api is called
 */

public class EventHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange)
    {
        System.out.print("Event Handler!\n");
    }
}
