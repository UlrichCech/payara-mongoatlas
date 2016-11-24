package de.sofiworx.users.boundary;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author Ulrich Cech
 */
@Path("users")
public class UsersResource {

    @GET
    public Response get() {
        return Response.ok("OK").build();
    }

}
