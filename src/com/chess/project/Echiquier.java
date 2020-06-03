package com.chess.project;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe Echiquier qui stocke les cases et les methodes de validation de déplacement des pieces ainsi que les methodes d'arret (echec/echec et mat) du jeux
 * @author Andrianina Maeva Nikola
 * @version 1
 * Classe Echiquier qui stocke les cases et les methodes de validation de déplacement des pieces ainsi que les methodes d'arret (echec/echec et mat) du jeux
 * 
 *
 */
public class Echiquier {
	private Case[][] plateau;
	private List<Piece> tokenB = new LinkedList<Piece>();
	private List<Piece> tokenW = new LinkedList<Piece>();
	private List<Integer> spaceEmptyB = new LinkedList<Integer>();//0 case libre 1 non disponible 2 en echec
	private List<Integer> spaceEmptyW = new LinkedList<Integer>();
	
	/**
	 * Constructeur echiquier créant les cases sans les remplir en leur assignant un nom
	 * init without occuped squares
	 * @param
	 * 
	 */
	 
	public Echiquier(Echiquier e)
	{
		this.plateau = e.plateau;
	}
	public Echiquier() {
		String a= null;
			plateau = new Case[8][8];
			for(int i = 0; i< 8; ++i)
			{
				for(int j = 0; j<8; ++j)
				{
					
					plateau[i][j] = new Case();
					switch (i) {
					case 0:
						a="a";
						break;
					case 1:
						a="b";
						break;
					case 2:
						a="c";
						break;
					case 3 :
						a="d";
						break;
					case 4 :
						a="e";
						break;
					case 5 :
						a="f";
					case 6 :
						a="g";
						break;
					case 7 :
						a="h";
						break;
					}
					plateau[i][j].SetNom(a + (j+1));
				}
			}
	}
	/**
	 * A getter to obtain particular info in a square
	 * @param numero 
	 * colonne n 
	 * @param numero 
	 * ligne m
	 * @return la case a la nieme colonne et la m-ieme ligne
	 */
	
	public Case getCase(int colonne, int ligne)

	{
		return plateau[colonne][ligne];
	}
	/**
	 * A getter to obtain particular info in a square
	 * @param name 
	 * 
	 * @return la case au nom recherchée
	 */
	
	public Case[][] getPlateau()
	{
		return this.plateau;
	}
	
	
	/**
	 * Change la position courante d'une piece dans la liste des piece
	 * @param p piece
	 * @param i noir ou blanc
	 * @param pStop nouvelle position
	 */
	public void changeCurrPos(Piece p, int i,Position pStop) {
		if(i == 1) {
			for(int j = 0; j<this.getListW().size();j++) {
				if(this.tokenW.get(j).equals(p)) {
					this.tokenW.get(j).setPos(pStop);
					
					
				}
			}
			
		}else if(i == 0){
			for(int j = 0; j<this.getListB().size();j++ ) {
				if(this.tokenB.get(j).equals(p)) {
					this.tokenB.get(j).setPos(pStop);
					
				}
			}
		}
		
	}
	/**
	 * enleve un Pion lorsqu'il est mangé
	 * @param p pion mangé
	 * @param i couleur
	 */
	public void removeToken(Piece p,int i) {
		if(i == 1) {
		for(int j = 0; j<this.tokenB.size();j++) {
			if(this.tokenB.get(j).equals(p)) {
				this.tokenB.remove(j);
				
			}
		}
		
		}
		else {
			for(int j = 0; j<this.tokenW.size();j++) {
				if(this.tokenW.get(j).equals(p)) {
					this.tokenW.remove(j);
					
				}
			}
		}
		
	}
	
	/**
	 * Retourne la list des pion NOir
	 * @return liste pion NOir
	 */
	public List<Piece> getListB(){
		return this.tokenB;
		
		
	}
	
	
	/**
	 * Retourne la liste des pions blanc
	 * @return liste Pion blanc
	 */
	public List<Piece> getListW(){
		return this.tokenW;
	}
	/**
	 * Methode pour avoir une case par son nom
	 * @param name nom de la case
	 * @return la case courante
	 */
	public Case getCase(String name) {
		char s =name.charAt(0);
		
		int z=name.charAt(1)-48;
		if(z<0 || z>8) {
			z = 0;
		}
		int i=0;
		switch (s) {
		case 'a' | 'A':
			i = 0;
			break;
		case 'b' | 'B':
			i = 1;
			break;
		case 'c' | 'C':
			i = 2;
			break;
		case 'd' | 'D':
			i = 3;
			break;
		case 'e' | 'E':
			i = 4;
			break;
		case 'f' | 'F':
			i = 5;
			break;
		case 'g' | 'G':
			i = 6;
			break;
		case 'h' | 'H':
			i = 7;
			break;
			default:
				i =0;
				break;
		
		}
		//System.out.println("i = " + i + "z = " + z);
		return plateau[i][z-1];
		
	}
	
	/**
	 * Check if the move of any token is possible
	 * @param Move m le mouvement a effectué 
	 * @return vrai si le move est possible faux sinon
	 * 
	 * 
	 */
	
	public boolean isMovePossible(Move M) {
		if(M.getStop().getColonne() < 0 || M.getStop().getLigne()<0 || M.getStop().getColonne() >= 8 || M.getStop().getLigne() >= 8 || M.getStart().getColonne() < 0 || M.getStart().getColonne() >= 8 || M.getStart().getLigne()<0 || M.getStart().getLigne() >= 8) {
			return false;
		}
		else {
		Piece pBegin = null;
		if(plateau[(int) M.getStart().getColonne()][(int)M.getStart().getLigne()].estOccupe()) {
		 pBegin = plateau[(int)M.getStart().getColonne()][(int)M.getStart().getLigne()].getPiece();
		}else {
			return false;
		}
		Case CEnd = null;
		
		 CEnd = plateau[(int)M.getStop().getColonne()][(int) M.getStop().getLigne()];
		
		int CStart = M.getStart().getColonne();
		int LStart = M.getStart().getLigne();
		int CStop = M.getStop().getColonne();
		int LStop = M.getStop().getLigne();
		int MC= M.getMoveC();
		int ML= M.getMoveL();
		
		
	
			
			
			
			
			
			if((pBegin != null)  &&  !(CEnd.estOccupe(pBegin.getCouleur() == "Blanc" ? "Blanc" : "Noir")) || M.zeroMove() ) {//If the token who's present in the end square got a different color 
				Piece pEnd = CEnd.getPiece();
				if( pBegin.getNom() != "Cavalier") {//if token is not a knight
					
					if ( pBegin.getNom() != "Pion"){//if token is not a pawn
						
						if(! ( (Math.abs(MC-ML)<=1) && (Math.abs(MC + ML)<= 1) ) ) {
							int jumpX=0,jumpY=0;
							//implements type of incrementation to check all the square between the move
							if(MC < 0) {
								jumpX = -1;
							}else if(MC > 0) {
								jumpX = 1;
							}else {
								jumpX = 0;
							}
							if(ML < 0) {
								jumpY = -1;
							}else if(ML > 0) {
								jumpY = 1;
							}else {
								jumpY = 0;
							}
							//System.out.println(jumpX + " " + jumpY + " " + MC +" " + ML+" " + CStart + " "+ CStop + " " + LStart + " " + LStop + " " );
							for(int i = CStart+jumpX  , j = LStart+jumpY   ; i != CStop || j != LStop ; i+=jumpX, j+= jumpY) {//checking all the square
								if(i >= 8 || j >= 8 || i <= -1 || j<= -1) {
									return false;
								}
								
								if(this.plateau[i][j].estOccupe()) {
									return false;
								}
							}
							return true;
						}//move is 1 no need checking off square
						else
							return true;
						
					}else//check if end square is empty for a pawn move
						
					return !plateau[CStop][LStop].estOccupe();
					
				}else//knight always true
					return true;
					
				}else// same color or move zero
					return false;
			
		}
		}
		
		
		
		/**
		 * 
		 * Methode qui détermine si il y a une piece sur la diagonale du pion
		 * @param m mouvement du pion
		 * @return true si le pion peux manger en diagonale faux sinon
		 */
	public boolean pionDeplacementDiagonale(Move m) //vérifie s'il a une pièce ennemie sur la diagonale du pion, donc s'il peut y aller
	{
		if(this.plateau[m.getStart().getColonne()][m.getStart().getLigne()].getPiece() instanceof Pion)
		{
		
			if( (m.getStart().getColonne() -1 == m.getStop().getColonne()) || (m.getStart().getColonne()+1== m.getStop().getColonne()))
			{
			
				if( m.getStart().getLigne() == m.getStop().getLigne() + 1 || m.getStart().getLigne()  == m.getStop().getLigne() -1 )
				{
					
					if(this.plateau[m.getStart().getColonne()][m.getStart().getLigne()].getPiece().getCouleur().equals("Blanc"))
					{
						if(this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].estOccupe("Noir"))
						{
							return true;
						}
					}
				
					else if(this.plateau[m.getStart().getColonne()][m.getStart().getLigne()].getPiece().getCouleur().equals("Noir"))
					{
						if(this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].estOccupe("Blanc"))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * Methode qui permet au pion d'avance de deux case lorsqu'il n'a fait aucun mouvement
	 * @param m mouvement du pion
	 * @return vrai si le mouvement est possible faux sinon
	 */
	
/**
 * Methode pour determiner l'echec
 * @return vrai si le joueur est mis en echec
 * 
 */
	public int echec() //retourne un entier: 0 si pas d'echec, 1 si Roi blanc en échec, 2 si c'est le Roi noir
	{
		int i;
		int j;
		int k;
		int l;

		for(i=0;i<8;i++) //on va parcourir toutes les cases de l'échiquier(départ)
		{
			for(j=0;j<8;j++)
			{
				for(k=0;k<8;k++) //on va parcourir toutes les cases de l'échiquier(arrivée)
				{
					for(l=0;l<8;l++)
					{
						Position start = new Position(j,i);
						Position stop = new Position(l,k);
						Move m = new Move(start,stop);
						m.setMoveC(stop.getColonne()-start.getColonne());
						m.setMoveL(stop.getLigne()-start.getLigne());
						if( this.plateau[i][j].estOccupe() &&(this.plateau[i][j].getPiece().estValide(m)) && (this.isMovePossible(m) || this.pionDeplacementDiagonale(m)) ) //si on peut aller à cette case
						{
							if(this.plateau[i][j].getPiece().getNom().contains("Piece") && (this.plateau[i][j].getPiece().getCouleur() != this.plateau[k][l].getPiece().getCouleur())) {
								if(this.pionDeplacementDiagonale(m) && this.plateau[k][l].getPiece() instanceof Roi) {
									if(this.plateau[i][j].getPiece().getCouleur() == "Blanc")
									{
										System.out.println("Le Roi Noir est en échec (pion)");
										return 1;
									}
									if(this.plateau[i][j].getPiece().getCouleur() == "Noir")
									{
										System.out.println("Le Roi Blanc est en échec (pion)");
										return 2;
									}
									
								}
							}
							else if(this.plateau[i][j].estOccupe() && this.plateau[k][l].estOccupe() && this.plateau[i][j].getPiece().estValide(m) && this.isMovePossible(m) && (this.plateau[i][j].getPiece().getCouleur() != this.plateau[k][l].getPiece().getCouleur()) ) //que sur cette case il y a une pièce ennemie
							{
								if(this.plateau[k][l].getPiece()instanceof Roi) //que cette case est un roi
								{
									if(this.plateau[i][j].getPiece().getCouleur() == "Blanc")
									{
										System.out.println("Le Roi Noir est en échec");
										return 1;
									}
									if(this.plateau[i][j].getPiece().getCouleur() == "Noir")
									{
										System.out.println("Le Roi Blanc est en échec");
										return 2;
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}
	/**
	 * Methode pour determiner l'echec et mat
	 * @return vrai si echec et mat (+ arret jeu) faux sinon
	 */
	
	public boolean echecEtMats()
	{
		int s1=0,s=0,s2=0,i;
		int j;
		int k;
		int l;
		int m;
		int n;
		int c1 = 0; //compteur du nombre de cases "condamnées" du Roi 1;
		int c2 = 0;
		
		for(i=0;i<8;i++) //on va parcourir l'échiquier
		{
			for(j=0;j<8;j++)
			{
				if(this.plateau[i][j].estOccupe() && this.plateau[i][j].getPiece() instanceof Roi) //on cherche les Rois 
				{
				s=0;
				s2=0;
					for(m=i-1;m<=i+1;m++) //on va parcourir toutes les cases sur lesquelles le Roi peut aller
					{
						for(n=j-1;n<=j+1;n++)
						{
							
							if(this.plateau[i][j].estOccupe() && this.plateau[i][j].getPiece().getCouleur().equals("Blanc")) 
							{
								if(this.echec() ==1) {
									this.spaceEmptyB.set(8, 2);
									
								}
								
								Position start = new Position(j,i);
								Position stop = new Position(n,m);
								Move m1 = new Move(start,stop);
								m1.setMoveC(stop.getColonne()-start.getColonne());
								m1.setMoveL(stop.getLigne()-start.getLigne());
								
								if(this.isMovePossible(m1) && !this.plateau[m][n].estOccupe() ) 
								{//le roi peut aller a la case qui est vide
									
									this.plateau[m][n].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 1) {
									this.spaceEmptyB.set(s, 2);
									s++;
									}else if(this.echec()==0){
										this.spaceEmptyB.set(s, 0);
										s++;
									}
									this.plateau[i][j].setPiece(this.plateau[m][n].getPiece());
									this.plateau[m][n].removePiece();
								}
								else if(this.kingCantMove(this.plateau[i][j].getPiece(), m1) == 1) {//roi ne peut pas bouger
									this.spaceEmptyB.set(s, 1);
									s++;
								}
								else if(this.isMovePossible(m1) && this.plateau[m][n].estOccupe() && this.plateau[m][n].getPiece().getCouleur().equals("Noir") )
								{//si le move est possible et qu'une piece adverse est presente
									Piece token = this.plateau[m][n].getPiece();
									this.plateau[m][n].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 1) {
										this.spaceEmptyB.set(s, 2);
										s++;
									}else if(this.echec()==0){
										this.spaceEmptyB.set(s, 0);
										s++;
									}
									this.plateau[i][j].setPiece(this.plateau[m][n].getPiece());
									this.plateau[m][n].removePiece();
									this.plateau[m][n].setPiece(token);
								}
								
								if(this.echec()==0)
								{//test position courante
									this.spaceEmptyB.set(8, 3);
									
								}
								
								
							}
						
							else if(this.plateau[i][j].estOccupe() && this.plateau[i][j].getPiece().getCouleur().equals("Noir"))
							{
								if(this.echec() ==2) {
									this.spaceEmptyW.set(8, 2);
									
								}
								
								Position start = new Position(j,i);
								Position stop = new Position(n,m);
								Move m1 = new Move(start,stop);
								m1.setMoveC(stop.getColonne()-start.getColonne());
								m1.setMoveL(stop.getLigne()-start.getLigne());
								
								if(this.isMovePossible(m1) && !this.plateau[m][n].estOccupe() ) 
								{//le roi peut aller a la case qui est vide
									
									this.plateau[m][n].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 2) {
									this.spaceEmptyW.set(s2, 2);
									s2++;
									}else if(this.echec()==0){
										this.spaceEmptyW.set(s2, 0);
										s2++;
									}
									this.plateau[i][j].setPiece(this.plateau[m][n].getPiece());
									this.plateau[m][n].removePiece();
								}
								else if(this.kingCantMove(this.plateau[i][j].getPiece(), m1) == 1) {//roi ne peut pas bouger
									this.spaceEmptyW.set(s2, 1);
									s2++;
								}
								else if(this.isMovePossible(m1) && this.plateau[m][n].estOccupe() && this.plateau[m][n].getPiece().getCouleur().equals("Blanc") )
								{//si le move est possible et qu'une piece adverse est presente
									Piece token = this.plateau[m][n].getPiece();
									this.plateau[m][n].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 2) {
										this.spaceEmptyW.set(s2, 2);
										s2++;
									}else if(this.echec()==0){
										this.spaceEmptyW.set(s2, 0);
										s2++;
									}
									this.plateau[i][j].setPiece(this.plateau[m][n].getPiece());
									this.plateau[m][n].removePiece();
									this.plateau[m][n].setPiece(token);
								}
								
								if(this.echec()==0)
								{//test position courante
									this.spaceEmptyW.set(8, 3);
									
								}
								
							
							}
						
						}
				}
			}else if(this.plateau[i][j].estOccupe() && !(this.plateau[i][j].getPiece() instanceof Roi) ) {
				s=0;
				s2=0;
				for(int x =0;x<8;x++)
				{
					for(int y=0;y<8;y++)
					{
						
						if(this.plateau[i][j].estOccupe() && this.plateau[i][j].getPiece().getCouleur().equals("Blanc"))
						{
							Position start = new Position(j,i);
							Position stop = new Position(y,x);
							Move m1 = new Move(start,stop);
							m1.setMoveC(stop.getColonne()-start.getColonne());
							m1.setMoveL(stop.getLigne()-start.getLigne());
						
							if(this.plateau[i][j].getPiece().getNom().equals("Pion"))  //si c'est une piece
							{
								if(this.plateau[i][j].getPiece().estValide(m1) && this.isMovePossible(m1) && !this.pionDeplacementDiagonale(m1) && !this.plateau[x][y].estOccupe()) 
								{//move tout droit pour une piece
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 1) {
										c1++;
										}else if(this.echec()==0){
											c1--;
										}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
								
								}
								else if(this.isMovePossible(m1) && this.pionDeplacementDiagonale(m1) && this.plateau[x][y].estOccupe()  && this.plateau[x][y].getPiece().getCouleur().equals("Noir")) 
								{
									Piece token = this.plateau[x][y].getPiece();
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 1) {
										c1++;
									}else if(this.echec()==0){
										c1--;
									}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
									this.plateau[x][y].setPiece(token);
								}
							}
							else if(!this.plateau[i][j].getPiece().getNom().equals("Pion") )
							{
								if(this.plateau[i][j].getPiece().estValide(m1) && this.isMovePossible(m1) && !this.plateau[x][y].estOccupe())
								{
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 1) {
										c1++;
										}else if(this.echec()==0){
											c1--;
										}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
								}
								else if(this.plateau[i][j].getPiece().estValide(m1) && this.isMovePossible(m1) && this.plateau[x][y].estOccupe() && this.plateau[x][y].getPiece().getCouleur().equals("Noir"))
								{
									Piece token = this.plateau[x][y].getPiece();
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 1) {
										c1++;
									}else if(this.echec()==0){
										c1--;
									}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
									this.plateau[x][y].setPiece(token);
								}
								
							}
						} 
						else if(this.plateau[i][j].estOccupe() && this.plateau[i][j].getPiece().getCouleur().equals("Noir"))
						{
							Position start = new Position(j,i);
							Position stop = new Position(y,x);
							Move m1 = new Move(start,stop);
							m1.setMoveC(stop.getColonne()-start.getColonne());
							m1.setMoveL(stop.getLigne()-start.getLigne());
							
							if(this.plateau[i][j].getPiece().getNom().equals("Pion"))  //si c'est une piece
							{
								if(this.plateau[i][j].getPiece().estValide(m1) && this.isMovePossible(m1) && !this.pionDeplacementDiagonale(m1) && !this.plateau[x][y].estOccupe()) 
								{//move tout droit pour une piece
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 2) {
										c2++;
										}else if(this.echec()==0){
											c2--;
										}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
								
								}
								else if(this.isMovePossible(m1) && this.pionDeplacementDiagonale(m1) && this.plateau[x][y].estOccupe()  && this.plateau[x][y].getPiece().getCouleur().equals("Blanc")) 
								{
									Piece token = this.plateau[x][y].getPiece();
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 2) {
										c2++;
									}else if(this.echec()==0){
										c2--;
									}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
									this.plateau[x][y].setPiece(token);
								}
							}
							else if(!this.plateau[i][j].getPiece().getNom().equals("Pion") )
							{
								if(this.plateau[i][j].getPiece().estValide(m1) && this.isMovePossible(m1) && !this.plateau[x][y].estOccupe())
								{
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 2) {
										c2++;
										}else if(this.echec()==0){
											c2--;
										}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
								}
								else if(this.plateau[i][j].getPiece().estValide(m1) && this.isMovePossible(m1) && this.plateau[x][y].estOccupe() && this.plateau[x][y].getPiece().getCouleur().equals("Blanc"))
								{
									Piece token = this.plateau[x][y].getPiece();
									this.plateau[x][y].setPiece(this.plateau[i][j].getPiece());
									this.plateau[i][j].removePiece();
									if( this.echec() == 2) {
										c2++;
									}else if(this.echec()==0){
										c2--;
									}
									this.plateau[i][j].setPiece(this.plateau[x][y].getPiece());
									this.plateau[x][y].removePiece();
									this.plateau[x][y].setPiece(token);
								}
								
							}
							
						}
						
					}
				}
				
			}
		}
		}
			
			/*System.out.println("s = " +s + " " + this.spaceEmptyB);
			System.out.println("s2 = " + s2 + " " + this.spaceEmptyW);
			System.out.println(c1 + " " + c2);*/
		
			if(c2 ==0) {
				System.out.println("Echec et Mat les Blanc ont gagné");
				this.affiche();
				return true;
			}
			if(c1 == 0) {
				System.out.println("Echec et Mat les Noir ont gagné");
				this.affiche();
				return true;
			}
			
			if(this.spaceEmptyB.contains(1) && this.spaceEmptyB.contains(2) && !this.spaceEmptyB.contains(0) && this.spaceEmptyB.get(8).equals(2) && c2 ==0) {
				
				System.out.println("Echec et Mat les Blanc ont gagné");
				this.affiche();
				return true;
			}
			if(this.spaceEmptyW.contains(1) && this.spaceEmptyW.contains(2) && !this.spaceEmptyW.contains(0) && this.spaceEmptyW.get(8).equals(2) && c1 == 0) {
				System.out.println("Echec et Mat les Noir ont gagné");
				this.affiche();
				return true;
			}
		
	
		
		return false;
	}	
	
	
	/**
	 * Fill up the game board
	 * 
	 */
	
	public void Remplir() {
		int line = 7;
		String couleur = "Noir";
		//Black token
		plateau[0][line].setPiece(new Tour(couleur,new Position(line,0)));
		plateau[1][line].setPiece(new Cavalier(couleur,new Position(line,1)));
		plateau[2][line].setPiece(new Fou(couleur,new Position(line,2)));
		plateau[4][line].setPiece(new Roi(couleur,new Position(line,4)));
		plateau[3][line].setPiece(new Reine(couleur,new Position(line,3)));
		plateau[5][line].setPiece(new Fou(couleur,new Position(line,5)));
		plateau[6][line].setPiece(new Cavalier(couleur,new Position(line,6)));
		plateau[7][line].setPiece(new Tour(couleur,new Position(line,7)));
		this.tokenB.add(this.plateau[0][line].getPiece());
		this.tokenB.add(this.plateau[1][line].getPiece());	
		this.tokenB.add(this.plateau[2][line].getPiece());	
		this.tokenB.add(this.plateau[3][line].getPiece());	
		this.tokenB.add(this.plateau[4][line].getPiece());	
		this.tokenB.add(this.plateau[5][line].getPiece());	
		this.tokenB.add(this.plateau[6][line].getPiece());	
		this.tokenB.add(this.plateau[7][line].getPiece());	
		
		line = 6;
		for(int i = 0; i<8; ++i) {
			plateau[i][line].setPiece(new Pion(couleur,new Position(line,i)));
			this.tokenB.add(this.plateau[i][line].getPiece());	
		}
		
		//White Token
		line = 0;
		couleur = "Blanc";
		plateau[0][line].setPiece(new Tour(couleur,new Position(line,0)));
		plateau[1][line].setPiece(new Cavalier(couleur,new Position(line,1)));
		plateau[2][line].setPiece(new Fou(couleur,new Position(line,2)));
		plateau[4][line].setPiece(new Roi(couleur,new Position(line,4)));
		plateau[3][line].setPiece(new Reine(couleur,new Position(line,3)));
		plateau[5][line].setPiece(new Fou(couleur,new Position(line,5)));
		plateau[6][line].setPiece(new Cavalier(couleur,new Position(line,6)));
		plateau[7][line].setPiece(new Tour(couleur,new Position(line,7)));
		this.tokenW.add(this.plateau[0][line].getPiece());	
		this.tokenW.add(this.plateau[1][line].getPiece());	
		this.tokenW.add(this.plateau[2][line].getPiece());	
		this.tokenW.add(this.plateau[3][line].getPiece());	
		this.tokenW.add(this.plateau[4][line].getPiece());	
		this.tokenW.add(this.plateau[5][line].getPiece());	
		this.tokenW.add(this.plateau[6][line].getPiece());	
		this.tokenW.add(this.plateau[7][line].getPiece());	
		
		line = 1;
		for(int i = 0; i<8; ++i) {
			plateau[i][line].setPiece(new Pion(couleur,new Position(line,i)));
			this.tokenW.add(this.plateau[i][line].getPiece());	
		}
		for(int i = 0;i<8;i++) {
		this.spaceEmptyB.add(0);
		this.spaceEmptyW.add(0);
		}
		this.spaceEmptyB.add(3);//position courante
		this.spaceEmptyW.add(3);
		
	}
	
	/**
	 * Affiche l'echiquier
	 */
	public void affiche() {
		System.out.println("  A    B   C   D   E   F   G   H ");
		for(int i = 0; i< 8; i++) {
			System.out.println();
			System.out.print(i+1);
			
			
			for(int j = 0; j<8; j++) {
				if(this.plateau[j][i].estOccupe()) {
					String p = this.plateau[j][i].getPiece().getNom();
					String c = this.plateau[j][i].getPiece().getCouleur();
					if(c.equals("Blanc"))
					{
						switch(p) 
						{
							case "Cavalier":
								System.out.print(" CW ");
								break;
							case "Reine":
								System.out.print(" QW ");
								break;
							case "Fou":
								System.out.print(" FW ");
								break;
							case "Roi": 
								System.out.print(" KW ");
								break;
							case "Tour":
								System.out.print(" TW ");
								break;
							case "Pion":
								System.out.print(" PW ");
								break;
								default:
									System.out.print(" - ");
									break;
					
					
					
						}
						//System.out.println();
						
					}else {
						switch(p) 
						{
							case "Cavalier":
								System.out.print(" CB ");
								break;
							case "Reine":
								System.out.print(" QB ");
								break;
							case "Fou":
								System.out.print(" FB ");
								break;
							case "Roi": 
								System.out.print(" KB ");
								break;
							case "Tour":
								System.out.print(" TB ");
								break;
							case "Pion":
								System.out.print(" PB ");
								break;
								default:
									System.out.print(" - ");
									break;
					
					
					
						}
					}
				}else {
					System.out.print(" -  ");
				}
				//System.out.println();
				
			}
		}
		
		System.out.println();
	}
	/**
	 * effectue une promotion pour l piece donnée
	 * @param m si le move effectué correspond a une promotion
	 * @param p piece
	 */
	public void promote(Move m,Piece p) {
		Scanner sc = new Scanner(System.in);
		if(p != null &&m.getStop().getLigne() == 7 && p.getNom().equals("Pion") && p.getCouleur().equals("Blanc") ) {//pour les blanc
			System.out.println("Choisir une piece de promotion (reine | tour | fou | cavalier) :");
			String rep = sc.nextLine();
			int test = 0;
			while(test == 0)
			{
				if (rep.equals("Reine") || rep.equals("Cavalier") || rep.equals("Tour") || rep.equals("Fou"))
				{
					//Cas ou l'utilisateur a bien renseignee l'une des pieces. On sort du while de gestion d'erreur
					test = 1;
				}
				else 
				{
					// Erreur input
					System.out.println("Erreur de selection.\nChoisir une piece de promotion (reine | tour | fou | cavalier) :");
					rep = sc.nextLine();
				}
			}
			switch(rep) {
				case "Reine":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Reine("Blanc",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
				case "Cavalier":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Cavalier("Blanc",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
				case "Tour":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Tour("Blanc",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
				case "Fou":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Fou("Blanc",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
					default:
						break;
			}
			
		}
		else if(p!= null && m.getStop().getLigne() == 0 && p.getNom().equals("Pion") && p.getCouleur().equals("Noir") ) {//pour les blanc
			System.out.println("Choisir une piece de promotion (reine | tour | fou | cavalier) :");
			String rep = sc.nextLine();
			int test = 0;
			while(test == 0)
			{
				if (rep.equals("Reine") || rep.equals("Cavalier") || rep.equals("Tour") || rep.equals("Fou"))
				{
					//Cas ou l'utilisateur a bien renseignee l'une des pieces. On sort du while de gestion d'erreur
					test = 1;
				}
				else 
				{
					// Erreur input
					System.out.println("Erreur de selection.\nChoisir une piece de promotion (reine | tour | fou | cavalier) :");
					rep = sc.nextLine();
				}
			}
			switch(rep) {
				case "Reine":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Reine("Noir",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
				case "Cavalier":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Cavalier("Noir",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
				case "Tour":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Tour("Noir",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
				case "Fou":
					this.plateau[m.getStop().getColonne()][m.getStop().getLigne()].setPiece(new Fou("Noir",new Position(m.getStop().getLigne(),m.getStop().getColonne())));
					break;
					default:
						break;
			}
			
		}
		
		
	}
	
	/**
	 * Si le roi ne peut pas bouger via le move donnée
	 * @param p le roi
	 * @param m le deplacement courant
	 * @return 1 faux 2 vrai
	 */
	public int kingCantMove(Piece p,Move m) {
		if(!this.isMovePossible(m)) {
			return 1;
		}
		else {
			return 2;
		}
		
		
	}
	
}
