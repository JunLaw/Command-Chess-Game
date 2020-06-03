package com.chess.project;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Reine hérité de Piece
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */

public class Reine extends Piece {
/**
 * Constructeur Reine hérité piece
 * @param couleur
 */
	public Reine( String couleur,Position pos) {
		super("Reine", couleur,pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * Methode reecrite pour la classe Reine
	 * @param move 
	 * @return true si valide false sinon
	 */
	public boolean estValide(Move move) {
		// TODO Auto-generated method stub
		return (((Math.abs(move.getMoveC()) - Math.abs(move.getMoveL())  == 0) || Math.abs(move.getMoveC())*Math.abs(move.getMoveL())==0 ) && !move.zeroMove()) ;
		
		
		
		
	}

	

}
