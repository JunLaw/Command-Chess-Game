package com.chess.project;

import java.util.Random;
import java.util.Scanner;

/**
 * Classe IA
 * @author Andrianina Maeva Nikola
 * @version 1
 *
 */
public class IA {
	private Random r = new Random();
	private Integer r1,r2,r3,r4;
	private Echiquier e;
	 private Data undo;
	private Scanner sc = new Scanner(System.in);
	private String move,start,stop;
	private Case cStart,cStop;
	private Move m;
	private int cpt=0;
	private Piece tStop;
	private String combine1,combine2;
	
	private Position pStart,pStop;
	private int echec = 0; 
	
	
	/**
	 * Constructeur courant
	 * @param e echiquier courant
	 * @param undo data pour le retour arriere
	 */
	public IA(Echiquier e,Data undo) {
		this.e = e;
		this.undo = undo;
	}
	/**
	 * Conversion d'un vers un string courant a l'echiquier
	 * @param i
	 * @return
	 */
	public String conv(int i) {
		String n ;
		switch(i) {
		case 1:
			n = "a";
			break;
		case 2:
			n = "b";
			break;
		case 3:
			n = "c";
			break;
		case 4:
			n = "d";
			break;
		case 5:
			n = "e";
			break;
		case 6:
			n = "f";
			break;
		case 7:
			n = "g";
			break;
		case 8:
			n = "h";
			break;
		default:
			n = "a";
			break;
		}
		
		return n;
		
		
	}
	/**
	 * Methode de jeu pour JvsIA
	 * @param tour choix du joueur
	 */
	public void Jouer(int tour) {
		while(e.echecEtMats() == false ) 
	{//tant qu'il n'y a pas d'échec et mats
		if(tour == 1)
		{//tour des blanc
			e.affiche();
			do 
			{
				System.out.println("tour des blanc Joueur");
				if(e.echec() == 1) //les blancs sont en échec
				{
					echec = 1;
				}
				System.out.println("faites votre move");//exemple e2e3 = pion se deplace de e2 a e3 (tout attacher)
			
				move = sc.nextLine();
			}while(move.length() != 4);
			if(move.contains("undo")) {
				if(undo.retourArrierre(e,cpt,tStop)) {
					tour = 1;
				}else {
					tour = 0;
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
								tStop = cStop.getPiece();
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
								undo.sauvegarde(action);
								e.promote(m, cStop.getPiece());
							}
							
							else if(cStop.estOccupe() )
							{//manger une piece ennemi
								e.changeCurrPos(cStart.getPiece(), 1,pStop);
								Action action = new Action(pStart,pStop,cStart.getPiece());
								tStop = cStop.getPiece();
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
				
				if(echec == 1) //si on était en échec au début du tour
				{
					if(e.echec() == 1) //et qu'on est toujours en échec
					{
						undo.retourArrierre(e,cpt,tStop); //on va devoir faire un autre move
						tour = 1;
					}
				}
				
				echec = 0;
		
				
			}
		}
		
		else if(tour == 0)
		{//tour des noirs
			e.affiche();
			System.out.println("Tour des noirs IA");
			
			if(e.echec() == 2) //les noirs sont en échec
			{
				echec = 1;
			}
			
			//System.out.println("faites votre move");
			do {
			r1 = r.nextInt(8)+1;
			combine1 = this.conv(r1);
			r2 = r.nextInt(8)+1;
			combine1 += r2.toString();
			r3 = r.nextInt(8)+1;
			combine2 = this.conv(r2);
			r4 = r.nextInt(8)+1;
			combine2 += r3.toString();
			}while(combine1.equals(combine2));
			
			cStart = e.getCase(combine1);
			cStop = e.getCase(combine2);
			pStart = new Position(combine1);
			pStop = new Position(combine2);
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
							e.changeCurrPos(cStart.getPiece(), 1,pStop);
							Action action = new Action(pStart,pStop,cStart.getPiece());
							e.removeToken(cStop.getPiece(), tour);
							System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + combine1 + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + combine2 );
							cStop.setPiece(cStart.getPiece());
							cStart.removePiece();
							//undo.sauvegarde(new Echiquier(e));
							e.promote(m, cStop.getPiece());
							tour = 1;
							
						
						}
						else 
						{
							e.changeCurrPos(cStart.getPiece(), 1,pStop);
							Action action = new Action(pStart,pStop,cStart.getPiece());
							cStop.setPiece(cStart.getPiece());
							System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + combine1 + " avance en " + combine2 );
							cStart.removePiece();
							//undo.sauvegarde(new Echiquier(e));
							e.promote(m, cStop.getPiece());
							tour = 1;
						}
					
					}
					else
					{
						if(cStop.estOccupe() == false) 
						{
							e.changeCurrPos(cStart.getPiece(), 1,pStop);
							Action action = new Action(pStart,pStop,cStart.getPiece());
							
							cStop.setPiece(cStart.getPiece());
							System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + combine1 + " avance en " + combine2 );
							cStart.removePiece();
							//undo.sauvegarde(new Echiquier(e));
							e.promote(m, cStop.getPiece());
							tour = 1;
						}
						else if(cStop.estOccupe())
						{
							e.changeCurrPos(cStart.getPiece(), 1,pStop);
							Action action = new Action(pStart,pStop,cStart.getPiece());
							e.removeToken(cStop.getPiece(), tour);
							System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + combine1 + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + combine2 );
							cStop.setPiece(cStart.getPiece());
							cStart.removePiece();
							//undo.sauvegarde(new Echiquier(e));
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
		
			
			
			echec = 0;
		
		
	}
	}
	
	
	
}
	/**
	 * Methode de jeu pour IAvsIA
	 * @param tour
	 * @param e
	 */
	public void jouerIa(int tour,Echiquier e) {
		while(e.echecEtMats() == false && (e.getListB().get(4).getNom() == "Roi" && e.getListW().get(4).getNom() == "Roi")) 
		{//tant qu'il n'y a pas d'échec et mats
			if(tour == 1)
			{//tour des blanc
				//e.affiche();
					System.out.println("tour des blanc IA");
					
					//System.out.println("faites votre move");//exemple e2e3 = pion se deplace de e2 a e3 (tout attacher)
				
					do {
						r1 = r.nextInt(8)+1;
						combine1 = this.conv(r1);
						r2 = r.nextInt(8)+1;
						combine1 += r2.toString();
						r3 = r.nextInt(8)+1;
						combine2 = this.conv(r2);
						r4 = r.nextInt(8)+1;
						combine2 += r3.toString();
						}while(combine1.equals(combine2));
						
						cStart = e.getCase(combine1);
						cStop = e.getCase(combine2);
						pStart = new Position(combine1);
						pStop = new Position(combine2);
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
									e.removeToken(cStop.getPiece(), tour);
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop );
									cStop.setPiece(cStart.getPiece());
									cStart.removePiece();
									//undo.sauvegarde(new Echiquier(e));
									e.affiche();
									e.promote(m, cStop.getPiece());
									tour = 0;
									
								}else 
								{//si cela n'est pas un move pour manger un piece et que c'est un pion
									e.changeCurrPos(cStart.getPiece(), 1,pStop);
									Action action = new Action(pStart,pStop,cStart.getPiece());
									
									cStop.setPiece(cStart.getPiece());
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop );
									cStart.removePiece();
									tour = 0;
									e.affiche();
									//undo.sauvegarde(new Echiquier(e));
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
									e.affiche();
									//undo.sauvegarde(new Echiquier(e));
									e.promote(m, cStop.getPiece());
								}
								
								else if(cStop.estOccupe())
								{//manger une piece ennemi
									e.changeCurrPos(cStart.getPiece(), 1,pStop);
									Action action = new Action(pStart,pStop,cStart.getPiece());
									e.removeToken(cStop.getPiece(), tour);
									System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop +" tour suivant ");
									cStop.setPiece(cStart.getPiece());
									cStart.removePiece();
									tour = 0;
									e.affiche();
									//undo.sauvegarde(new Echiquier(e));
									e.promote(m, cStop.getPiece());
								}
						
							}
						}else
						{//si le mouv n'est pas valide pour une piece specifier
							//System.out.println("move impossible recommencez");
							tour = 1;
						}
				
					}else 
					{//si le mouv n'est pas valide pour un mouve general
						//System.out.println("move impossible recommencez ");
						tour = 1;
					}
					
					
			
					
		
			}
			
			else if(tour == 0)
			{//tour des noirs
				//e.affiche();
				System.out.println("Tour des noirs IA");
				
				
				
				//System.out.println("faites votre move");
				do {
					r1 = r.nextInt(8)+1;
					combine1 = this.conv(r1);
					r2 = r.nextInt(8)+1;
					combine1 += r2.toString();
					r3 = r.nextInt(8)+1;
					combine2 = this.conv(r2);
					r4 = r.nextInt(8)+1;
					combine2 += r3.toString();
					}while(combine1.equals(combine2));
					
					cStart = e.getCase(combine1);
					cStop = e.getCase(combine2);
					pStart = new Position(combine1);
					pStop = new Position(combine2);
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
								e.changeCurrPos(cStart.getPiece(), 1,pStop);
								Action action = new Action(pStart,pStop,cStart.getPiece());
								e.removeToken(cStop.getPiece(), tour);
								System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop );
								cStop.setPiece(cStart.getPiece());
								cStart.removePiece();
								e.affiche();
								//undo.sauvegarde(new Echiquier(e));
								e.promote(m, cStop.getPiece());
								tour = 1;
								
							
							}
							else 
							{
								e.changeCurrPos(cStart.getPiece(), 1,pStop);
								Action action = new Action(pStart,pStop,cStart.getPiece());
								
								cStop.setPiece(cStart.getPiece());
								System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop );
								cStart.removePiece();
								e.affiche();
								//undo.sauvegarde(new Echiquier(e));
								e.promote(m, cStop.getPiece());
								tour = 1;
							}
						
						}
						else
						{
							if(cStop.estOccupe() == false) 
							{
								e.changeCurrPos(cStart.getPiece(), 1,pStop);
								Action action = new Action(pStart,pStop,cStart.getPiece());
								cStop.setPiece(cStart.getPiece());
								System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur()  + " " + start + " avance en " + stop );
								cStart.removePiece();
								e.affiche();
								//undo.sauvegarde(new Echiquier(e));
								e.promote(m, cStop.getPiece());
								tour = 1;
							}
							else if(cStop.estOccupe())
							{
								e.changeCurrPos(cStart.getPiece(), 1,pStop);
								Action action = new Action(pStart,pStop,cStart.getPiece());
								e.removeToken(cStop.getPiece(), tour);
								System.out.println(cStart.getPiece().getNom() + " " + cStart.getPiece().getCouleur() + " " + start + " mange " + cStop.getPiece().getNom() + " "+ cStop.getPiece().getCouleur()+ " en " + stop );
								cStop.setPiece(cStart.getPiece());
								cStart.removePiece();
								e.affiche();
								//undo.sauvegarde(new Echiquier(e));
								e.promote(m, cStop.getPiece());
								tour = 1;
							}
						
						}
				}
				else
				{
					//System.out.println("move impossible recommencez");
					tour = 0;
				}
				
				
				
				}
				else
				{
					//System.out.println("move impossible recommencez ");
					tour = 0;
				}
			
				
				
			
			
		}
		}
	}
	
	
	

}
