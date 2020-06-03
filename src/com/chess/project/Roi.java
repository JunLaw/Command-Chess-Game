package com.chess.project;
/**
 * Classe Roi hérité de piece
 * @author Andrianina Maeva Nikola
 *@version 1
 *
 */



public class Roi extends Piece {
/**
 * Constructeur Roi création de la piece Roi
 * @param couleur
 */
	public Roi( String couleur,Position pos) {
		super("Roi", couleur,pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Methode reecrite
	 * @param move
	 */
	public boolean estValide(Move move) {
		// TODO Auto-generated method stub
		//cas cases même colonne
				if( move.getStart().getColonne() == move.getStop().getColonne()) 
				{
					if((move.getStart().getLigne()+1 == move.getStop().getLigne())||(move.getStart().getLigne()-1 == move.getStop().getLigne()))
					{
						return true;
					}
				}
				//cas même ligne
				if( move.getStart().getLigne() == move.getStop().getLigne()) 
				{
					if((move.getStart().getColonne()+1 == move.getStop().getColonne())||(move.getStart().getColonne()-1 == move.getStop().getColonne()))
					{
							return true;
					}
				}
				//diagonales
				if((move.getStart().getColonne()+1 == move.getStop().getColonne())||(move.getStart().getColonne()-1 == move.getStop().getColonne()))
				{
					if((move.getStart().getLigne()+1 == move.getStop().getLigne())||(move.getStart().getLigne()-1 == move.getStop().getLigne()))
					{
						return true;
					}
				}
				
		
		
		return false;
	}

}
