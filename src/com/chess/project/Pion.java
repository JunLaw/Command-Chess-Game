package com.chess.project;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Pion extended by Piece
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */
public class Pion extends Piece {
	/**
	 * Constructeur Pion hérité de piece
	 * @param couleur
	 */
	public Pion( String couleur,Position pos) {
		super("Pion",couleur,pos);
		
	}
	
	

	@Override
	public boolean estValide(Move move) {
		//départ, et donc peut avancer de deux cases
		//case ou on veut avancer le pion sur la case de devant
		if( move.getStart().getColonne() == move.getStop().getColonne()) 
		{	
			//System.out.println("bonjour");
			if( (move.getStart().getLigne() +1 == move.getStop().getLigne()) && (this.getCouleur().equals("Blanc"))) 
			{
			//	System.out.println("ici");
				return true;
				
			}
			if( (move.getStart().getLigne() -1 == move.getStop().getLigne()) && (this.getCouleur().equals("Noir"))) 
			{
			//	System.out.println("laa");
				return true;
			
			}
			if(( move.getStart().getLigne() + 2 == move.getStop().getLigne() && this.getCouleur().equals("Blanc")) && move.getStart().getLigne() == 1) {
				return true;
			}
			if(( move.getStart().getLigne() - 2 == move.getStop().getLigne() && this.getCouleur().equals("Noir")) && move.getStart().getLigne() == 6) {
				return true;
			}
			
			
			
		}
		
			/*if(move.getStart().getLigne() + 1 == move.getStop().getLigne() && move.getStart().getColonne() + 1 == move.getStop().getColonne() && this.getCouleur().equals("Blanc")) {
				return true;
			}
			if(move.getStart().getLigne() + 1 == move.getStop().getLigne() && move.getStart().getColonne() -1 == move.getStop().getColonne() && this.getCouleur().equals("Blanc")) {
				return true;
			}
			
			if(move.getStart().getLigne() -1 == move.getStop().getLigne() && move.getStart().getColonne() -1 == move.getStop().getColonne() && this.getCouleur().equals("Noir")) {
				return true;
			}
			if(move.getStart().getLigne() - 1 == move.getStop().getLigne() && move.getStart().getColonne() + 1 == move.getStop().getColonne() && this.getCouleur().equals("Noir")) {
				return true;
			}*/
		
		//System.out.println("end");
			return false;
		
			
		
		
		
	}



	
	
	
}
