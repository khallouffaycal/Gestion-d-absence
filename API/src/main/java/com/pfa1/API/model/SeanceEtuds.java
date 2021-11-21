package com.pfa1.API.model;

//List of students
public class SeanceEtuds {
	long idEtudiants;
	String NomComplets;
	public SeanceEtuds() {
		
	}
	public SeanceEtuds(long idEtudiants, String nomComplets) {
		super();
		this.idEtudiants = idEtudiants;
		NomComplets = nomComplets;
	}
	public long getIdEtudiants() {
		return idEtudiants;
	}
	public void setIdEtudiants(long idEtudiants) {
		this.idEtudiants = idEtudiants;
	}
	public String getNomComplets() {
		return NomComplets;
	}
	public void setNomComplets(String nomComplets) {
		NomComplets = nomComplets;
	}
	@Override
	public String toString() {
		return "SeanceEtuds [idEtudiants=" + idEtudiants + ", NomComplets=" + NomComplets + "]";
	}
}
