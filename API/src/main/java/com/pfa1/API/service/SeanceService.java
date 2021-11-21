package com.pfa1.API.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pfa1.API.model.SeanceEtuds;
import com.pfa1.API.model.SeanceEtudsFac;
import com.pfa1.API.model.Seance;
import com.pfa1.API.model.SeanceAbsence;

public class SeanceService {
	private static String url = "jdbc:mysql://localhost:3306/mcd1";
	private static String username = "root";
	private static String password = "root";
	//get list of seances of a student
	public static List<Seance> getSeancesAll(String session) throws ClassNotFoundException, SQLException{
		String sql = "select * from Seance inner join Emploi on Seance.id_emploi=Emploi.id_emploi inner join Etudiant on Etudiant.id_classe=Emploi.id_classe inner join Element on Seance.id_element=Element.id_element inner join Users on Users.Id_user=Etudiant.Id_user where session=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		List<Seance> seances = new ArrayList<Seance>();
		try {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Seance seance=new Seance();
				seance.setIdSeance(rs.getLong("id_seance"));
				seance.setDebut(rs.getString("debut"));
				seance.setFin(rs.getString("fin"));
				seance.setSalle(rs.getString("salle"));
				seance.setType(rs.getString("type_seance"));
				seance.setElement(rs.getString("Nom_Element"));
				seances.add(seance);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return seances;
	}
	//Get remaining seances student
	public static List<Seance> getSeances(String session) throws ClassNotFoundException, SQLException{
		String sql = "select * from Seance inner join Emploi on Seance.id_emploi=Emploi.id_emploi inner join Etudiant on Etudiant.id_classe=Emploi.id_classe inner join Element on Seance.id_element=Element.id_element inner join Users on Users.Id_user=Etudiant.Id_user where session=? and Emploi.debut>=curdate();";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		List<Seance> seances = new ArrayList<Seance>();
		try {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Seance seance=new Seance();
				seance.setIdSeance(rs.getLong("id_seance"));
				seance.setDebut(rs.getString("debut"));
				seance.setFin(rs.getString("fin"));
				seance.setSalle(rs.getString("salle"));
				seance.setType(rs.getString("type_seance"));
				seance.setElement(rs.getString("Nom_Element"));
				seances.add(seance);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return seances;
	}
	//Get all seance teacher 
	public static List<Seance> getSeancesAllEns(String session) throws ClassNotFoundException, SQLException{
		String sql = "select * from Seance inner join Element on Element.id_element=Seance.id_element inner join Enseignant on Seance.Id_enseignant=Enseignant.Id_enseignant inner join Users on Users.Id_user=Enseignant.Id_user where session=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		List<Seance> seances = new ArrayList<Seance>();
		try {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Seance seance=new Seance();
				seance.setIdSeance(rs.getLong("id_seance"));
				seance.setDebut(rs.getString("debut"));
				seance.setFin(rs.getString("fin"));
				seance.setSalle(rs.getString("salle"));
				seance.setType(rs.getString("type_seance"));
				seance.setElement(rs.getString("Nom_Element"));
				seances.add(seance);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return seances;
	}
	//Get remaining seances teacher
	public static List<Seance> getSeancesEns(String session) throws ClassNotFoundException, SQLException{
		String sql = "select * from Seance inner join Element on Element.id_element=Seance.id_element inner join Enseignant on Seance.Id_enseignant=Enseignant.Id_enseignant inner join Users on Users.Id_user=Enseignant.Id_user where session=? and Seance.debut>=now();";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		List<Seance> seances = new ArrayList<Seance>();
		try {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Seance seance=new Seance();
				seance.setIdSeance(rs.getLong("id_seance"));
				seance.setDebut(rs.getString("debut"));
				seance.setFin(rs.getString("fin"));
				seance.setSalle(rs.getString("salle"));
				seance.setType(rs.getString("type_seance"));
				seance.setElement(rs.getString("Nom_Element"));
				seances.add(seance);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return seances;
	}
	//Get absent seances student
	public static List<Seance> getAbsSeances(String session) throws ClassNotFoundException, SQLException{
		String sql = "select * from Absent inner join Seance on Absent.id_seance=Seance.id_seance inner join Etudiant on Etudiant.Id_etudiant=Absent.Id_Etudiant inner join Element on Seance.id_element=Element.id_element inner join Users on Users.Id_user=Etudiant.Id_user where session=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		List<Seance> seances = new ArrayList<Seance>();
		try {
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Seance seance=new Seance();
				seance.setIdSeance(rs.getLong("id_seance"));
				seance.setDebut(rs.getString("debut"));
				seance.setFin(rs.getString("fin"));
				seance.setSalle(rs.getString("salle"));
				seance.setType(rs.getString("type_seance"));
				seance.setElement(rs.getString("Nom_Element"));
				seances.add(seance);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return seances;
	}
	//Add absence 
	public static SeanceAbsence addAbsence(SeanceAbsence absence) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		for(long ids:absence.getEtudiants()) {
			String sql = "select * from Absent where id_seance=? and Id_etudiant=?;";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, absence.getIdSeance());
			st.setLong(2, ids);
			ResultSet rs = st.executeQuery();
			String sql1 = "insert into Absent (id_seance,Id_etudiant,Justifie) value (?,?,1);";
			PreparedStatement st1 = con.prepareStatement(sql1);
			st1.setLong(1, absence.getIdSeance());
			if(!rs.next()) {
					st1.setLong(2, ids);
					st1.executeUpdate();
			}
		}
		return absence;
	}
	//Remove absence
	public static SeanceAbsence RemoveAbsence(SeanceAbsence absence) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		for(long ids:absence.getEtudiants()) {
			String sql1 = "delete from Absent where id_seance=? and Id_etudiant=?";
			PreparedStatement st1 = con.prepareStatement(sql1);
			st1.setLong(1, absence.getIdSeance());
			st1.setLong(2, ids);
			st1.executeUpdate();
		}
		return absence;
	}
	//Get student absent in certain seance
	public static List<SeanceEtuds> getAbsence(long idSeance) throws SQLException, ClassNotFoundException {
		String sql = "select * from Absent inner join Etudiant on Absent.Id_etudiant=Etudiant.Id_etudiant where id_seance=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1,idSeance);
		List<SeanceEtuds> etudiants = new ArrayList<SeanceEtuds>();
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			SeanceEtuds etuds = new SeanceEtuds();
			etuds.setIdEtudiants(rs.getLong("Id_etudiant"));
			etuds.setNomComplets(rs.getString("Nom")+" "+rs.getString("Prenom"));
			etudiants.add(etuds);	
		}
		return etudiants;
	}
	//Get a list of student indicating which is absent based on a list of detected students
	public static List<SeanceEtudsFac> getseaEtudFac(long idSeance,ArrayList<Integer> IdEtudiants) throws SQLException, ClassNotFoundException {
		String sql = "select * from Etudiant inner join Classe on Etudiant.id_classe=Classe.id_classe inner join Emploi on Emploi.id_classe=Classe.id_classe inner join Seance on Emploi.id_emploi=Seance.id_emploi where id_seance=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1,idSeance);
		List<SeanceEtudsFac> etudiants = new ArrayList<SeanceEtudsFac>();
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			SeanceEtudsFac etuds = new SeanceEtudsFac();
			etuds.setIdEtudiants(rs.getLong("Id_etudiant"));
			Long i = rs.getLong("Id_etudiant");
			int y= i.intValue();
			if(IdEtudiants.contains(y))	etuds.setAbs(0);
			else etuds.setAbs(1);
			etuds.setNomComplets(rs.getString("Nom")+" "+rs.getString("Prenom"));
			etudiants.add(etuds);	
		}
		return etudiants;
	}
	//Get student in seance
	public static List<SeanceEtuds> getseaEtud(long idSeance) throws SQLException, ClassNotFoundException {
		String sql = "select * from Etudiant inner join Classe on Etudiant.id_classe=Classe.id_classe inner join Emploi on Emploi.id_classe=Classe.id_classe inner join Seance on Emploi.id_emploi=Seance.id_emploi where id_seance=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1,idSeance);
		List<SeanceEtuds> etudiants = new ArrayList<SeanceEtuds>();
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			SeanceEtuds etuds = new SeanceEtuds();
			etuds.setIdEtudiants(rs.getLong("Id_etudiant"));
			etuds.setNomComplets(rs.getString("Nom")+" "+rs.getString("Prenom"));
			etudiants.add(etuds);	
		}
		return etudiants;
	}
}
