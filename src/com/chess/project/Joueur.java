package com.chess.project;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe joueur
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */

public class Joueur {
	private Echiquier e;
	 private Data undo;
	private Scanner sc = new Scanner(System.in);
	private String move,start,stop;
	private Case cStart,cStop;
	private Move m;
	private Position pStart,pStop;
	private List<Piece> piecePrise = new LinkedList<Piece>();
	private int cpt=0;
	private int echec = 0; //nous permet de régler les différents move si échec
		public Joueur(Echiquier e,Data undo) {
			this.e = e;
			this.undo = undo;
		}
		
		/**
		 * Methode de lancement du JCJ
		 * @param tour
		 */
		public void jouer(int tour) {
			
			
			while(e.echecEtMats() == false ) 
			//while(true)
			{//tant qu'il n'y a pas d'échec et mats
				if(tour == 1)
				{//tour des blanc
					e.affiche();
					do 
					{
						System.out.println("tour des blanc");
						if(e.echec() == 1) //les blancs sont en échec
						{	undo.retourArrierre(e,cpt, piecePrise.get(piecePrise.size()-1));
							echec = 2;
							
						}
						System.out.println("faites votre move");//exemple e2e3 = pion se deplace de e2 a e3 (tout attacher)
						
						
						move = sc.nextLine();
					}while(move.length() != 4);
						if(move.contains("undo")) {
							if(!piecePrise.isEmpty()) {
								cpt =1;
								undo.retourArrierre(e, cpt, piecePrise.get(piecePrise.size()-1));
								piecePrise.remove(piecePrise.size()-1);
								
							}
							else if(piecePrise.isEmpty() ) {
								cpt =0;
								undo.retourArrierre(e, cpt, null);
								tour = 0;
							}else {
								System.out.println("dans le else");
								tour = 1;
							}
							}
							
						
						else {
						start = move.substring(0, 2);
						stop = move.substring(2,4);
						cStart = e.getCase(start);
						cStop = e.getCase(stop);
						pStart = new Position(start);
						pStop = new Position(stop);
						m = new Move(pStart,pStop);
						m.setMoveC(pStop.getColonne() - pStart.getColonne());
						m.setMoveL(pStop.getLigne() - pStart.getLigne());
		
						if(cStart.estOccupe() && (e.isMovePossible(m) || e.pionDeplacementDiagonale(m)) && cStart.getPiece().getCouleur().equals("Blanc")) 
						{//verif si le mouv est possible pour n'importe quel piece ou si le mouvement est une diagonale pour un pion
							if(e.pionDeplacementDiagonale(m) || cStart.getPiece().estValide(m) ) 
							{//verif de validité d'un move pour la piece donné
								if(cStart.getPiece() instanceof Pion) 
								{//verif de conditions pour un pion
									if(e.pionDeplacementDiagonale(m)) 
									{//quand un pion mange une piece
										
										e.changeCurrPos(cStart.getPiece(), 1,pStop);
										Action action = new Action(pStart,pStop,cStart.getPiece());
										piecePrise.add(cStop.getPiece());
										cpt = 1;
										e.removeToken(cStop.getPiece(), tour);
										System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop );
										cStop.setPiece(cStart.getPiece());
										cStart.removePiece();
										undo.sauvegarde(action);
										e.promote(m, cStop.getPiece());
										tour = 0;
										
									}else 
									{//si cela n'est pas un move pour manger un piece et que c'est un pion
										e.changeCurrPos(cStart.getPiece(), 1,pStop);
										Action action = new Action(pStart,pStop,cStart.getPiece());
										cStop.setPiece(cStart.getPiece());
										cpt=0;
										System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop );
										cStart.removePiece();
										tour = 0;
										undo.sauvegarde(action);
										e.promote(m, cStop.getPiece());
							
									}
								}else
								{//verif pur toute les piece autre que le pion
									if(cStop.estOccupe() == false)
									{//deplacement simple
										e.changeCurrPos(cStart.getPiece(), 1,pStop);
										Action action = new Action(pStart,pStop,cStart.getPiece());
										cStop.setPiece(cStart.getPiece());
										System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop + " tour suivant " );
										cStart.removePiece();
										tour = 0;
										cpt=0;
										undo.sauvegarde(action);
										e.promote(m, cStop.getPiece());
									}
									
									else if(cStop.estOccupe())
									{//manger une piece ennemi
										e.changeCurrPos(cStart.getPiece(), 1,pStop);
										Action action = new Action(pStart,pStop,cStart.getPiece());
										piecePrise.add(cStop.getPiece());
										cpt = 1;
										e.removeToken(cStop.getPiece(), tour);
										System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop +" tour suivant ");
										cStop.setPiece(cStart.getPiece());
										cStart.removePiece();
										tour = 0;
										undo.sauvegarde(action);
										e.promote(m, cStop.getPiece());
									}
							
								}
							}else
							{//si le mouv n'est pas valide pour une piece specifier
								System.out.println("move impossible recommencez");
								tour = 1;
							}
					
						}else 
						{//si le mouv n'est pas valide pour un mouve general
							System.out.println("move impossible recommencez ");
							tour = 1;
						}
						
						if(echec == 2) //si on était en échec au début du tour
						{
							if(e.echec() == 2) //et qu'on est toujours en échec
							{
								undo.retourArrierre(e,cpt,piecePrise.get(piecePrise.size()-1)); //on va devoir faire un autre move
								tour = 1;
							}
						}
						
						echec = 0;
				
						}
			
				}
				
				else if(tour == 0)
				{//tour des noirs
					e.affiche();
					do 
					{
						System.out.println("tour des noir");
						if(e.echec() == 2) //les blancs sont en échec
						{	
							undo.retourArrierre(e,0,piecePrise.get(piecePrise.size()-1));
							echec = 1;
							tour = 1;
						}
						System.out.println("faites votre move");//exemple e2e3 = pion se deplace de e2 a e3 (tout attacher)
					
						move = sc.nextLine();
					}while(move.length() != 4);
					if(move.contains("undo")) {
						if(!piecePrise.isEmpty()) {
							cpt =1;
							undo.retourArrierre(e, cpt, piecePrise.get(piecePrise.size()-1));
							piecePrise.remove(piecePrise.size()-1);
							tour = 1;
						}
						else if(piecePrise.isEmpty() ) {
							cpt=0;
							undo.retourArrierre(e, cpt, null);
							tour = 1;
						}else {
							tour = 0;
						}
						}
					else {
					
					
					start = move.substring(0, 2);
					stop = move.substring(2,4);
					start = move.substring(0, 2);
					stop = move.substring(2,4);
					cStart = e.getCase(start);
					cStop = e.getCase(stop);
					pStart = new Position(start);
					pStop = new Position(stop);
					m = new Move(pStart,pStop);
					m.setMoveC(pStop.getColonne() - pStart.getColonne());
					m.setMoveL(pStop.getLigne() - pStart.getLigne());
				
					if(cStart.estOccupe() && (e.isMovePossible(m) || e.pionDeplacementDiagonale(m)) && cStart.getPiece().getCouleur().equals("Noir"))
						{
						if(e.pionDeplacementDiagonale(m) || cStart.getPiece().estValide(m))
						{
							if(cStart.getPiece() instanceof Pion)
							{
								if(e.pionDeplacementDiagonale(m))
								{
									e.changeCurrPos(cStart.getPiece(), 0,pStop);
									Action action = new Action(pStart,pStop,cStart.getPiece());
									piecePrise.add(cStop.getPiece());
									cpt = 1;
									e.removeToken(cStop.getPiece(), tour);
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop );
									cStop.setPiece(cStart.getPiece());
									cStart.removePiece();
								
									undo.sauvegarde(action);
									e.promote(m, cStop.getPiece());
									tour = 1;
									
								
								}
								else 
								{
									e.changeCurrPos(cStart.getPiece(), 0,pStop);
									Action action = new Action(pStart,pStop,cStart.getPiece());
									cStop.setPiece(cStart.getPiece());
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop );
									cStart.removePiece();
									undo.sauvegarde(action);
									cpt=0;
									e.promote(m, cStop.getPiece());
									tour = 1;
								}
							
							}
							else
							{
								if(cStop.estOccupe() == false) 
								{
									e.changeCurrPos(cStart.getPiece(), 0,pStop);
									Action action = new Action(pStart,pStop,cStart.getPiece());
									cStop.setPiece(cStart.getPiece());
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop );
									cStart.removePiece();
									undo.sauvegarde(action);
									e.promote(m, cStop.getPiece());
									cpt=0;
									tour = 1;
								}
								else if(cStop.estOccupe())
								{
									e.changeCurrPos(cStart.getPiece(), 0,pStop);
									Action action = new Action(pStart,pStop,cStart.getPiece());
									piecePrise.add(cStop.getPiece());
									cpt = 1;
									e.removeToken(cStop.getPiece(), tour);
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop );
									cStop.setPiece(cStart.getPiece());
									cStart.removePiece();
									undo.sauvegarde(action);
									e.promote(m, cStop.getPiece());
									tour = 1;
								}
							
							}
					}
					else
					{
						System.out.println("move impossible recommencez");
						tour = 0;
					}
					
					
					
					}
					else
					{
						System.out.println("move impossible recommencez ");
						tour = 0;
					}
				
					if(echec == 1) //si on était en échec au début du tour
					{
						if(e.echec() == 1) //et qu'on est toujours en échec
						{
							undo.retourArrierre(e,cpt,piecePrise.get(piecePrise.size()-1)); //on va devoir faire un autre move
							tour = 0;
						}	
					}
					
					echec = 0;
				
				
				
			}
				}
			
		}
			
			
		}
}


