package com.pfa1.API.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Seance {
	long idSeance;
	String Debut;
	String Fin;
	String Element;
	String Salle;
	String Type;
	public Seance() {
	}
	public Seance(long idSeance, String debut, String fin, String element, String salle,String type) {
		super();
		this.idSeance = idSeance;
		Debut = debut;
		Fin = fin;
		Element = element;
		Salle = salle;
		Type = type;
	}
	public long getIdSeance() {
		return idSeance;
	}
	public void setIdSeance(long idSeance) {
		this.idSeance = idSeance;
	}
	public String getDebut() {
		return Debut;
	}
	public void setDebut(String debut) {
		Debut = debut;
	}
	public String getFin() {
		return Fin;
	}
	public void setFin(String fin) {
		Fin = fin;
	}
	public String getElement() {
		return Element;
	}
	public void setElement(String element) {
		Element = element;
	}
	public String getSalle() {
		return Salle;
	}
	public void setSalle(String salle) {
		Salle = salle;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	@Override
	public String toString() {
		return "Seance [idSeance=" + idSeance + ", Debut=" + Debut + ", Fin=" + Fin + ", Element=" + Element
				+ ", Salle=" + Salle + ", Type=" + Type + "]";
	}
}
