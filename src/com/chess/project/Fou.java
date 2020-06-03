package com.chess.project;
/**
 * Classe Fou
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */

public class Fou extends Piece {
/**
 * 
 * constructeur pour le fou
 * @param couleur
 */
	public Fou( String couleur,Position pos) {
		super("Fou", couleur, pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean estValide(Move move) {
		// TODO Auto-generated method stub
		return (Math.abs(move.getMoveC()) - Math.abs(move.getMoveL())  == 0) && (!move.zeroMove());
	}

}
