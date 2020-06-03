package com.chess.project;
/**
 * class extended by Piece
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */

public class Cavalier extends Piece {
	
	/**
	 * 
	 * Constructeur pour cavalier hérité de Piece
	 * @param couleur
	 */
	public Cavalier(String couleur,Position pos) {
		super("Cavalier", couleur,pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Une methode estValide réecrite pour la classe cavalier 
	 * 
	 * 
	 * 
	 */
	public boolean estValide(Move move) {
		
		// TODO Auto-generated method stub
		
		if((move.getStart().getColonne()+1 == move.getStop().getColonne())||(move.getStart().getColonne()-1 == move.getStop().getColonne()))
{
	if((move.getStart().getLigne()+2 == move.getStop().getLigne())||(move.getStart().getLigne()-2 == move.getStop().getLigne()))
	{
		return true;
	}
}
if((move.getStart().getColonne()+2 == move.getStop().getColonne())||(move.getStart().getColonne()-2 == move.getStop().getColonne()))
{
	if((move.getStart().getLigne()+1 == move.getStop().getLigne())||(move.getStart().getLigne()-1 == move.getStop().getLigne()))
	{
		return true;
	}
}
return false;
}

	}
	


