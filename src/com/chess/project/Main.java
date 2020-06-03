package com.chess.project;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//variable
		Echiquier e = new Echiquier();
		Data undo = new Data();
		Scanner sc2 = new Scanner(System.in);
		
	
		e.Remplir();
		
		int i =0;
		
		IA ia = new IA(e,undo);
		Joueur j= new Joueur(e,undo);
		e.affiche();
		System.out.println();
		
		System.out.println("debut de la partie");
		do 
		{
		System.out.println("1/JcJ \n2/JcIA?\n3/IAvIA? ");
		 i = sc2.nextInt();
		 
		}while(i >3);
		if(i == 1) {
		j.jouer(1);
		}
		else if(i == 2) {
			ia.Jouer(1);
		}
		else if(i == 3){
			ia.jouerIa(1,e);
		}
		
		
		
		
	}
	
	
	}


