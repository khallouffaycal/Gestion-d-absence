package com.pfa1.API.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.pfa1.API.model.Seance;
import com.pfa1.API.model.SeanceAbsence;
import com.pfa1.API.model.SeanceEtuds;
import com.pfa1.API.model.SeanceEtudsFac;
import com.pfa1.API.service.OpenCVFaceRecognizer;
import com.pfa1.API.service.OpenCVFaceRecognizerIm;
import com.pfa1.API.service.SeanceService;
import com.pfa1.API.service.VideoConverter;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/enseignant/")
public class EnseignantResource {
	@Context
	private ContainerRequestContext requestContext;
	//Ajout d'absence
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Seances/Absence/Add")
	public void putabscSeances(SeanceAbsence absence) throws ClassNotFoundException, SQLException{
		SeanceService.addAbsence(absence);
	}
	//Suppression d'absence
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/Seances/Absence/Remove")
	public void delabscSeances(SeanceAbsence absence) throws ClassNotFoundException, SQLException{
		SeanceService.RemoveAbsence(absence);
	}
	//Liste des seances restantes 
	@GET
	@Produces("application/json")
	@Path("/Seances")
	public List<Seance> getSeances() throws Exception, SQLException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
			return null;
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String token1 = token.substring(token.indexOf("<") + 1, token.indexOf(">"));
		return SeanceService.getSeancesEns(token1);
	}
	//Liste de tous les seances
	@GET
	@Produces("application/json")
	@Path("/Seances/All")
	public List<Seance> getSeancesAll() throws Exception, SQLException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
			return null;
		String token = authorizationHeader.substring("Bearer".length()).trim();
		String token1 = token.substring(token.indexOf("<") + 1, token.indexOf(">"));
		return SeanceService.getSeancesAllEns(token1);
	}
	//Liste des etudiants d'une seance
	@GET
	@Produces("application/json")
	@Path("/Seances/{idSeance}/Etudiants")
	public List<SeanceEtuds> etudSeance(@PathParam("idSeance") long idSeance) throws ClassNotFoundException, SQLException {
		return SeanceService.getseaEtud(idSeance);
		
	}
	//Liste des etudiants absents dans une seance
	@GET
	@Produces("application/json")
	@Path("/Seances/{idSeance}/Absence")
	public List<SeanceEtuds> absSeance(@PathParam("idSeance") long idSeance) throws ClassNotFoundException, SQLException {
		return SeanceService.getAbsence(idSeance);
		
	}
	//Liste des etudiants d'une seance en indiquant les absents
	@POST
	@Path("/upload")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public List<SeanceEtudsFac> uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData,
	                                @FormDataParam("idSeance") String idSeance) throws Exception
	{
	    String UPLOAD_PATH = "/root/eclipse/";
	    System.out.println(idSeance);
	    try
	    {
	    	//Partie upload file
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        File uploaded = new File(UPLOAD_PATH + fileMetaData.getFileName());
	        OutputStream out = new FileOutputStream(uploaded);
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        final String fileType = Files.probeContentType(uploaded.toPath());
	        //System.out.println(fileType);
	        out.flush();
	        out.close();
	        //Partie upload file
	        //Partie detection Image
	        String[] images= {"image/bmp","image/jpeg","image/tiff","image/png"};
	        if (fileType.equalsIgnoreCase("video/mp4")) {
	        	ArrayList<Integer> ids = OpenCVFaceRecognizer.recognize(VideoConverter.convert(uploaded));;
	        	System.out.println(ids);
	        	uploaded.delete();
	        	return SeanceService.getseaEtudFac(Long.parseLong(idSeance), ids);
	        }
	      //Partie detection Image
	      //Partie detection Video
	        else if (Arrays.stream(images).anyMatch(t -> t.equals(fileType))) {
	        	ArrayList<Integer> ids = OpenCVFaceRecognizerIm.recognize(uploaded);
	        	System.out.println(ids);
	        	uploaded.delete();
	        	return SeanceService.getseaEtudFac(Long.parseLong(idSeance), ids);
	        }
	      //Partie detection Video
	    } catch (IOException e) 
	    {
	    	System.out.println(e);
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	    return new ArrayList<SeanceEtudsFac>();
	}
}
