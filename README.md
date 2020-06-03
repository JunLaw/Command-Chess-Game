{\rtf1\ansi\ansicpg1252\cocoartf1561\cocoasubrtf600
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww28600\viewh18000\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Manuel Utilisateur:\
\
Pour lancer le jeu Utiliser un IDE ou avec javac /src/com/chess/project/Main.java *.java\
puis java /src/com/chess/project/Main.class\
\
\
Mode Joueur vs Joueur : Taper 1\
Mode Joueur vs IA : Taper 2\
Mode IA vs IA : Taper 3\
\
\
Pour taper une commande/action joueur\
exemple e2e4 -> la piece en e2 ira en e4\
\
\
Manuel Technique \
\
16 Classes : Piece et ses Fils(Roi,Reine,Pion) etc classe abstraite pour utiliser le polymorphisme de ces methodes, determin\'e9 par son nom, sa position courante, et sa couleur\
Echiquier: Contient un tableau de Case et les methode pour determiner l\'92echec/echec et mat promotion si un deplacement est possible pour un move donn\'e9e, affichage\
Case: classe contenant une piece et le noms de la case en question (exemple e2 nom d\'92une case)\
Data: classe contenant les methode pour retourner une action en arri\'e8re,Stocke la liste des Action faites pr\'e9c\'e9demment.\
Action : classe contenant les don\'e9es d\'92une action jou\'e9 : La piece jou\'e9, sa position precedente et sa position courante\
Position: classe stockant les position sur l\'92echiquier, numero de colonne et de ligne.\
Joueur: Classe qui lance le jeu en mode JcJ\
IA : Classe qui lance le jeu en mode JcIA ou IAvsIA\
\
\
\
\
\
\
}