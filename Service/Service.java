package Service;

import Service.Result.Result;
import Service.Request.Request;

/**
 * Service interface implemented by all service classes
 */
public interface Service {

    /**
     * Web API service. Implemented by ClearService, EventService, etc.
     * @param request of class type
     * @return returns a login, fill, clear, register etc result
     */
    Result service( Request request);

}