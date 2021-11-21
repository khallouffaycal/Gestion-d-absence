package com.pfa1.API.resource;
import java.sql.SQLException;

import com.pfa1.API.model.Tokenlog;
import com.pfa1.API.service.LoginService;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;

@Path("/login")
public class LoginResource {
	@Context
	private ContainerRequestContext requestContext;
	//Partie login
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(){
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
		return Response.status(Response.Status.UNAUTHORIZED).entity("Please log in").build();
		return Response.status(Response.Status.OK).entity("You're already logged in").build();
	}
	//Partie login 
	@POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Tokenlog login(@FormParam("username") String username,@FormParam("password") String password) throws ClassNotFoundException, SQLException {
		return LoginService.getSession(username, password);
	}
}
