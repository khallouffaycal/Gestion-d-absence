package com.pfa1.API.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pfa1.API.model.Etudiant;

public class EtudiantService {
	private static String url = "jdbc:mysql://localhost:3306/mcd1";
	private static String username = "root";
	private static String password = "root";
	//Liste des etudiants d'une classe
	public static List<Etudiant> getEtudiants() throws ClassNotFoundException, SQLException{
		String sql = "select * from Etudiant inner join Classe on Etudiant.id_classe=Classe.id_classe;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		try {
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			   Etudiant etudiant = new Etudiant();      
			   etudiant.setIdEtudiant(rs.getLong("Id_etudiant"));
			   etudiant.setNom(rs.getString("Nom"));
			   etudiant.setPrenom(rs.getString("Prenom"));
			   etudiant.setDateNaissance(rs.getString("Date_naissance"));
			   etudiant.setLieu_naissance(rs.getString("Lieu_naissance"));
			   etudiant.setCIN(rs.getString("CIN"));
			   etudiant.setCNE(rs.getString("CNE"));
			   etudiant.setEmail(rs.getString("E_mail"));
			   etudiant.setGenre(rs.getInt("Genre") ==1 ? "Feminin":"Masculin");
			   etudiant.setClasse(rs.getString("Attribut"));
			   etudiant.setTelephone(rs.getString("Telephone"));
			   etudiant.setPhoto(rs.getString("Photo"));		   
			  etudiants.add(etudiant);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return etudiants;		
	}
	//Information de l'etudiant
	public static List<Etudiant> getEtudiant(String session) throws ClassNotFoundException, SQLException{
		String sql = "select * from Etudiant inner join Classe on Etudiant.id_classe=Classe.id_classe inner join Users on Etudiant.Id_user=Users.Id_user where session=?;";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,password);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, session);
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		Etudiant etudiant = new Etudiant() ;
		try {
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			   etudiant.setIdEtudiant(rs.getLong("Id_etudiant"));
			   etudiant.setNom(rs.getString("Nom"));
			   etudiant.setPrenom(rs.getString("Prenom"));
			   etudiant.setDateNaissance(rs.getString("Date_naissance"));
			   etudiant.setLieu_naissance(rs.getString("Lieu_naissance"));
			   etudiant.setCIN(rs.getString("CIN"));
			   etudiant.setCNE(rs.getString("CNE"));
			   etudiant.setEmail(rs.getString("E_mail"));
			   etudiant.setGenre(rs.getInt("Genre") ==1 ? "Feminin":"Masculin");
			   etudiant.setClasse(rs.getString("Attribut"));
			   etudiant.setTelephone(rs.getString("Telephone"));
			   etudiant.setPhoto(rs.getString("Photo"));
			   etudiants.add(etudiant);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		return etudiants;		
	}
	
		}
