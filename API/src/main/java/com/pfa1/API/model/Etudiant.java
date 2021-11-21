package com.pfa1.API.model;

public class Etudiant {
	long idEtudiant;
	String Nom;
	String Prenom;
	String dateNaissance;
	String Lieu_naissance;
	String Genre;
	String CIN;
	String Telephone;
	String Photo;
	String Email;
	String CNE;
	String Classe;
	public Etudiant() {
		}
	public Etudiant(long idEtudiant, String nom, String prenom, String dateNaissance, String lieu_naissance,
			String genre, String cIN, String telephone, String photo, String email, String cNE, String classe) {
		super();
		this.idEtudiant = idEtudiant;
		Nom = nom;
		Prenom = prenom;
		this.dateNaissance = dateNaissance;
		Lieu_naissance = lieu_naissance;
		Genre = genre;
		CIN = cIN;
		Telephone = telephone;
		Photo = photo;
		Email = email;
		CNE = cNE;
		Classe = classe;
	}
	public long getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieu_naissance() {
		return Lieu_naissance;
	}
	public void setLieu_naissance(String lieu_naissance) {
		Lieu_naissance = lieu_naissance;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCNE() {
		return CNE;
	}
	public void setCNE(String cNE) {
		CNE = cNE;
	}
	public String getClasse() {
		return Classe;
	}
	public void setClasse(String classe) {
		Classe = classe;
	}
	@Override
	public String toString() {
		return "Etudiant [idEtudiant=" + idEtudiant + ", Nom=" + Nom + ", Prenom=" + Prenom + ", dateNaissance="
				+ dateNaissance + ", Lieu_naissance=" + Lieu_naissance + ", Genre=" + Genre + ", CIN=" + CIN
				+ ", Telephone=" + Telephone + ", Photo=" + Photo + ", Email=" + Email + ", CNE=" + CNE + ", Classe="
				+ Classe + "]";
	}
	
	
}
