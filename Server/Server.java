package Server;

import Handler.FillHandler;
import Handler.PersonHandler;
import Handler.RegisterHandler;
import Handler.UserHandler;

/**
 * Created by Parker on 2/10/18.
 */

public class Server {

    public static void main(String[] args)
    {
        System.out.print("Entering Main Function " );
    }

    RegisterHandler registerHandler = null;
    UserHandler userHandler = null;
    FillHandler fillHandler = null;
    PersonHandler personHandler = null;

    public Server()
    {
        registerHandler = new RegisterHandler();
        fillHandler= new FillHandler();
        userHandler = new UserHandler();
        personHandler = new PersonHandler();
    }





}
