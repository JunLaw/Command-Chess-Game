package com.chess.project;
/**
 * Classe herité de Piece
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */
public class Tour extends Piece {
/**
 * Constructeur de creation de Tour
 * @param couleur
 */
	public Tour( String couleur,Position pos) {
		super("Tour", couleur,pos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean estValide(Move move) {
		if((move.getStart().getColonne() == move.getStop().getColonne())||(move.getStart().getLigne() == move.getStop().getLigne()))
		{
			return true;
		}

		// TODO Auto-generated method stub
		return false;
	}

}
