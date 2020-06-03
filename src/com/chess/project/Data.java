package com.chess.project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/**
 * Classe pour le retour arriere
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */
public class Data {

private List<Action> act;
	/**
	 * Constructeur pour initialiser la liste des action precedentes
	 */
	public Data()
	{
		this.act = new LinkedList<Action>();
	}
	/**
	 * Sauvegarde une action
	 * @param action
	 */
	public void sauvegarde(Action action)
	{
		this.act.add(action);
		
	}
	/**
	 * Revient a la position precedente
	 * @param e echiquier courant
	 * @param cpt 1 si une piece a été mangé 2 si non
	 * @param tStop la piece mangé en question
	 * @return vrai si effectué faux sinon
	 */
	public boolean retourArrierre(Echiquier e,int cpt,Piece tStop)
	{
		if(!this.act.isEmpty() && cpt == 0) {
		
		
		Action action = this.act.get(this.act.size()-1);
		e.getCase(action.getPrevPos().getColonne(),action.getPrevPos().getLigne()).setPiece(action.getToken());
		e.getPlateau()[action.getCurrPos().getColonne()][action.getCurrPos().getLigne()].removePiece();
		act.remove(this.act.size()-1);
		return true;
		}
		else if(!this.act.isEmpty() && cpt == 1 ) {
			Action action = this.act.get(this.act.size()-1);
			e.getCase(action.getPrevPos().getColonne(),action.getPrevPos().getLigne()).setPiece(action.getToken());
			e.getPlateau()[action.getCurrPos().getColonne()][action.getCurrPos().getLigne()].removePiece();
			e.getCase(tStop.getPos().getColonne(), tStop.getPos().getLigne()).setPiece(tStop);
			act.remove(this.act.size()-1);
			if(tStop.getCouleur().contains("Blanc")) {
				e.getListW().add(tStop);
				
			}else {
				e.getListB().add(tStop);
				
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
}
