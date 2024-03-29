package org.sid.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
public class Produit implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // by default it takes GenerationType.AUTO and create hibernate_sequence table in the DB
	private Long id;
	@NotNull
	@Size(min=5, max=70)
	private String designation;
	@DecimalMin("100")
	private double prix;
	private int quantite;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public Produit() {
		super();
	}
	public Produit( String designation, double prix, int quantite) {
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}
}
