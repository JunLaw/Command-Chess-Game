package com.chess.project;

/**
 * Classe Move : stocke un deplacement par son départ et son arrivé et par son deplacement sur colonne/ligne
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */

public class Move {
	/**
	 * movement en colonne
	 */
	private int moveC ; //numbers of square move in column

	/**
	 * movement en ligne
	 */
	private int moveL;	//NUBERS OF SQUARE MOVE on line

	/**
	 * position de départ
	 */
	private Position start; //beginning position

	/**
	 * position d'arrivé
	 */
	private Position stop;	//ending position
	
	/**
	 * Constructeur qui determine les mouvements en colonne et en ligne d'une piece
	 * @param start Position depart
	 * @param stop Position arrivé
	 */
	public Move(Position start,Position stop){
		
		this.start = start;
		this.stop = stop;
		this.moveC = (stop.getColonne() - start.getColonne());
		this.moveL = (stop.getColonne() - start.getColonne());
		
		
	}
	/**
	 * Getter
	 * @returnnombre de case déplacé en colonne
	 */
	public int getMoveC() {return this.moveC;}
	/**
	 * Getter
	 * @return nombre de case déplacé en Ligne
	 */
	public int getMoveL() {return this.moveL;}
	/**
	 * Getter
	 * @return Position de départ du mouvement 
	 */
	public Position getStart() {return this.start;}
	/**
	 * Getter
	 * @return Position d'arrivé du mouvement
	 */
	public Position getStop() {return this.stop;}
	/**
	 * Setter
	 *
	 * @param nombre de case déplacé en ligne
	 */
	public void setMoveC(int MoveC) { this.moveC = MoveC;}
	/**
	 * Setter
	 *
	 * @param nombre de case déplacé en colonne
	 */
	public void setMoveL(int MoveL) { this.moveL = MoveL;}
	
	/**
	 * Methode qui determine si un deplacement vaut 0 ou non
	 * @return vrai si le déplacement est nul
	 */
	
	public void setStart(Position start) {
		this.start = start;
	}
	
	public void setStop(Position stop) {
		this.stop = stop;
	}
	
	public boolean zeroMove() {
		if( (this.moveC == 0) && (this.moveL == 0) ) {return true;}
		else return false;
	}
	

	
}
