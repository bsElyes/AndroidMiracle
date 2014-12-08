package com.esprit.entities;

public class Produit {
	int id;
	String libelle;
	double prix;
	int quantite;
	boolean dispo;
	String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	String imagePath;
	int idC;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public boolean isDispo() {
		return dispo;
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getIdC() {
		return idC;
	}
	public void setIdC(int idC) {
		this.idC = idC;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return super.equals(o);
	}

	
	@Override
	public String toString() {
		return "Produit [id=" + id + ", libelle=" + libelle + ", prix=" + prix
				+ ", quantite=" + quantite + ", dispo=" + dispo
				+ ", description=" + description + ", imagePath=" + imagePath
				+ ", idC=" + idC + "]";
	}
	public Produit(int id, String libelle, double prix, int quantite,
			boolean dispo, String description, String imagePath, int idC) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.quantite = quantite;
		this.dispo = dispo;
		this.description = description;
		this.imagePath = imagePath;
		this.idC = idC;
	}
	public Produit(){
		
	}
	
}
