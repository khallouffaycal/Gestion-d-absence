package com.pfa1.API.resource;


import java.sql.SQLException;
import java.util.List;
import com.pfa1.API.model.Etudiant;
import com.pfa1.API.model.Seance;
import com.pfa1.API.service.EtudiantService;
import com.pfa1.API.service.SeanceService;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.GET;

@Path("/etudiant/")
public class EtudiantResource {
	@Context
	private ContainerRequestContext requestContext;
	//Information d'etudiant
	@GET
	@Produces("application/json")
	public Etudiant getEtudiant() throws Exception, SQLException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) return null;
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String token1 = token.substring(token.indexOf("<") + 1, token.indexOf(">"));
		return EtudiantService.getEtudiant(token1).get(0);
	}
	//Liste des seances restantes etudiant
	@GET
	@Produces("application/json")
	@Path("/Seances")
	public List<Seance> getSeances() throws Exception, SQLException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
			return null;
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String token1 = token.substring(token.indexOf("<") + 1, token.indexOf(">"));
		return SeanceService.getSeances(token1);
	}
	//liste de toutes les seances etudiant
	@GET
	@Produces("application/json")
	@Path("/Seances/All")
	public List<Seance> getSeancesAll() throws Exception, SQLException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
			return null;
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String token1 = token.substring(token.indexOf("<") + 1, token.indexOf(">"));
		return SeanceService.getSeancesAll(token1);
	}
	//Liste des seances absent etudiant
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/Seances/Absence")
	public List<Seance> getabscSeances() throws ClassNotFoundException, SQLException{
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
			return null;
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String token1 = token.substring(token.indexOf("<") + 1, token.indexOf(">"));
		return SeanceService.getAbsSeances(token1);
	}
}
