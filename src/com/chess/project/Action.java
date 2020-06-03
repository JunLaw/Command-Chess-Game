package com.chess.project;

/**
 * 
 * Class action qui stocke les actions précédentes
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */


public class Action {
	private Position previousPos;
	private Position currentPos;
	private Piece currentToken;
	/**
	 * Constructeur normal
	 * @param prev position precedente
	 * @param cur position courante
	 * @param token piece qui effectue l'action
	 */
	public Action(Position prev,Position cur, Piece token) {
		this.previousPos = prev;
		this.currentPos = cur;
		this.currentToken = token;
	}
	/**
	 * Constructeur Vide
	 * 
	 * 
	 */
	
	public Action() {
		
	}
	/**
	 * Getters and setters
	 */
	public void setPrevPos(Position curr) {
		this.previousPos = curr;
	}
	/**
	 * Getters and setters
	 */
	public void setCurrPos(Position curr) {
		this.currentPos = curr;
		
	}
	/**
	 * Getters and setters
	 */
	public void setToken(Piece p) {
		this.currentToken = p;
	}
	/**
	 * Getters and setters
	 * 
	 */
	public Position getPrevPos() {
		return this.previousPos;
		
	}
	/**
	 * Getters and setters
	 * @param
	 * @return POsition courante
	 */
	public Position getCurrPos() {
		return this.currentPos;
	}
	/**
	 * Getters and setters
	 */
	public Piece getToken() {
		return this.currentToken;
	}

}
