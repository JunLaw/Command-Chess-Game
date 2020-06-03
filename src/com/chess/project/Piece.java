package com.chess.project;

import java.util.List;

/**
 * Class Piece super classe de Cavalier Fou, Pion, Reine, Tour, Roi
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */

public abstract class Piece {
	/**
	 * Attribute & Constructor
	 * 
	 */
	private String nom;
	private String couleur;
	private Position pos;
	
	/**
	 * Constructeur de piece initialise son nom et sa couleur
	 * @param nom
	 * @param couleur
	 */
	public Piece(String nom, String couleur,Position pos)
	{
		this.nom=nom;
		this.couleur=couleur;
		this.pos = pos;
	}
	
	public Piece(Piece p) {
		this.nom = p.getNom();
		this.couleur = p.getCouleur();
		this.pos = p.getPos();
	}
	
	
	/**
	 * Getter and Setter
	 * @param
	 * recupere le nom
	 */
	
	public String getNom() {return this.nom;}
	/**
	 * Getter and Setter
	 * @param 
	 * recupere la couleur
	 */
	
	public String getCouleur() {return this.couleur;}
	/**
	 * Getter and Setter
	 * @param nom 
	 * @return String
	 * implemente le nom donné en argument
	 */
	
	public void SetNom(String nom) {this.nom = nom;}
	/**
	 * Getter and Setter
	 * @param couleur
	 * implemente la couleur donné en argument
	 */
	
	public void SetCouleur(String couleur) {
		if(couleur == "noir" || couleur == "blanc") {
		this.couleur = couleur;}
		}
	
	
	public Position getPos() {
		return this.pos;
		
	}
	
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	/**
	 * abstract method to override in inherited class 
	 * estValide
	 * allow to check if the move is possible for all types of token
	 * @param move 
	 * @return true or false
	 */
	
	public abstract boolean estValide(Move move);
		
	
		
	
	
	
	
	
}
