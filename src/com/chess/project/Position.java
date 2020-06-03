package com.chess.project;
/**
 * Classe Position qui détermine la position d'une case par sa ligne/colonne ou par son nom
 * @author Andrianina Maeva Nikola
 *
 */

public class Position {
	private int ligne; 
	private int colonne; //switch to letters later
	/**
	 * Constructeur Position avec les lignes/colonne
	 * @param ligne
	 * @param colonne
	 */
	public Position(int ligne,int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	/**
	 * Constructeur Postion avec le noms des cases
	 * @param name
	 */
	public Position(String name) {
		char s =name.charAt(0);
		int z=name.charAt(1)-48;
		int i=0;
		switch (s) {
		case 'a':
			i = 0;
			break;
		case 'b':
			i = 1;
			break;
		case 'c':
			i = 2;
			break;
		case 'd':
			i = 3;
			break;
		case 'e':
			i = 4;
			break;
		case 'f':
			i = 5;
			break;
		case 'g':
			i = 6;
			break;
		case 'h':
			i = 7;
			break;
			default:
				break;
		
		}
		this.ligne= z-1;
		this.colonne=i;
		
	}
	/**
	 * Getter et Setters
	 * @return ligne
	 */
	public int getLigne() {return ligne; }
	/**
	 * Getter et Setters
	 * @return colonne
	 */
	public int getColonne() {return colonne;}
	/**
	 * Getter et Setters
	 * @param ligne
	 * @return
	 */
	public void SetLigne(int ligne) {this.ligne = ligne;}
	/**
	 * Getter et Setters
	 * @param colonne
	 * @return 
	 */
	public void SetColonne(int colonne) {this.colonne = colonne;}
	

}
