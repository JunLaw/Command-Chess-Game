package com.chess.project;

/**
 * 
 * Class who determines the presence of a token
 * @author Andrianina Maeva Nikola
 * @version 1
 * 
 * 
 */

public class Case {
	private Piece piece;
	public String nom;
	
	/**
	 * Default constructor w/o piece
	 * @param 
	 * 
	 */
	
	public Case() {
		
	}
	/*
	 * Generic constructor w/ piece
	 * 
	 */
	/**
	 * 
	 * Constructeur De Case
	 * @param piece
	 */
	public Case(Piece piece) {
		this.piece = piece;
	}
	/**
	 * Getters and Setters
	 * @param String
	 * @return void, NomPiece,Piece
	 */
	public String getNom()
	{
		return this.nom ;
		
	}
	/**
	 * Getters and Setters
	 * @param String
	 * @return void, NomPiece,Piece
	 */
	public void SetNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Getters and Setters
	 * @param String
	 * @return void, NomPiece,Piece
	 */
	public Piece getPiece() {
		return this.piece;
		
	}
	/**
	 * Getters and Setters
	 * @param String
	 * @return void, NomPiece,Piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	/**
	 * method asking if space is busy or not false->notBusy true->Busy
	 * @param 
	 * return true si la piece contient une piece sinon false
	 */
	
	public boolean estOccupe() {
		return piece != null;
	}
	/**
	 * method asking if space is busy or if there's a token with another color or not false->notBusy true->Busy
	 * @param couleur 
	 * return true si la piece contient une piece sinon false
	 */
	public boolean estOccupe(String couleur) {//check if there 's a token from the opposite color
		if(this.piece == null) return false;
		else
			return piece.getCouleur().equals(couleur);
		
	}
	
	public void removePiece() {
		if(this.estOccupe()) {
			this.piece = null;
			
		}
	}

}
