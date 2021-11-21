package com.pfa1.API.model;

//List of students and if they're absent or not
public class SeanceEtudsFac {
	long idEtudiants;
	String NomComplets;
	int Abs;
	public SeanceEtudsFac() {
	}
	public SeanceEtudsFac(long idEtudiants, String nomComplets, int abs) {
		super();
		this.idEtudiants = idEtudiants;
		NomComplets = nomComplets;
		Abs = abs;
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
	public int getAbs() {
		return Abs;
	}
	public void setAbs(int abs) {
		Abs = abs;
	}
	@Override
	public String toString() {
		return "SeanceEtudsFac [idEtudiants=" + idEtudiants + ", NomComplets=" + NomComplets + ", Abs=" + Abs + "]";
	}
	
	
}

