
//
// Maxime Chanu L1 G5 - B 
// 
//   Optionnel : vous pouvez aussi joindre un document PDF donnant des explications supplÃ©mentaires
//

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

				/* Main */
public class projet_demineur {
	// variables globales tableaux 2D
	static int[][] T;
	static int[][] Tadj;

	public static void main(String[] args) {
		System.out.print("/>*** Jeu du Démineur en Java *** <\\ ");
		System.out.println();
		// Choix des configs 
		System.out.print("Hauteur de la grille : ");
		Scanner in0 = new Scanner(System.in);
	    String hauteurS = in0.nextLine();
	    System.out.println();
		System.out.print("Largeur de la grille : ");
	    Scanner in1 = new Scanner(System.in);
	    String largeurS = in1.nextLine();
	    System.out.println();
		System.out.print("Nombre de mines : ");
	    Scanner in2 = new Scanner(System.in);
	    String nbMineS = in2.nextLine();
	    // conversion des config
	    int hauteur = Integer.parseInt(hauteurS);
	    int largeur = Integer.parseInt(largeurS);
	    int nbMine = Integer.parseInt(nbMineS);
	    // Initialisation de la partie
	    if(0 <= hauteur && 99 >= hauteur && 0 <= largeur && 52 >= largeur && nbMine > 0 && nbMine < (largeur * hauteur)) {
	    	init(hauteur,largeur,nbMine);
			calculerAdjacent();
			System.out.println();
			// afficherTableau2D(Tadj);  // décommenter cette ligne pour tricher 
			afficherGrille(false);
			// Bonne chance ! 
			jeu();
	    }else {
	    	System.out.println(" Taille de grille ou nombre de mines invalide");
			System.out.println(" Erreur ! ");
	    }

	}
	
	// fonction nombre aléatoire
	public static int entierAleatoire(int a, int b){
		return ThreadLocalRandom.current().nextInt(a, b + 1);
	}
	
	// Fonction affichage d'un tableau a 2 dimensions 
		public static void afficherTableau2D(int[][] tab) {
			for(int i = 0; i < tab.length; i++) {
				for (int j = 0; j < tab[i].length; j++) {
					System.out.print(tab[i][j]);
					System.out.print("  ");
				}
				System.out.println();
			}
		}
	
	// Question 1.b]
		//Fonction init pour T et Tadj 
	public static void init(int h, int l, int n) {
			T 		= new int [h][l];
			Tadj 	= new int [h][l];
			int i 	= 0;
			while(i < n){
				// remplir de mines 
				int a = entierAleatoire(0,h-1);
				int b = entierAleatoire(0,l-1);
				if( Tadj[a][b] != -1 ) {
					Tadj[a][b] = -1;
					i++;
				}
			}
	}
	
		
	// Question 1.c] Fonction caseCorrecte
		// vérifie si les coordonnées sont valides 
		public static boolean caseCorrecte(int i, int j ) { 
	        int largeur = T[0].length;
	        int hauteur = T.length; 
	        if(j >= largeur || i >= hauteur){
	            return false; // invalides 
	        }else{
	            return true; // valides
	        }
	        
			
		}

		// Question 1.d] Fonction calculerAdjacent
			// remplit le Tadj avec les nombres de mines adjacentes
		public static void calculerAdjacent() {
	        int nbAdj; // nb de mines adjacentes 
	        /* Variable de détection des cases autour */
	        int a;      // ligne    
	        int b;      // colonne 
	        int i =0;
	        int j =0;
	        /* 2 'for' imbriqués pour visiter toutes les cases du tableau */ 
	        for(i= 0; i < Tadj.length  ; i++ ){
	            for(j = 0; j < Tadj[i].length  ; j++){
	                if( Tadj[i][j] != -1 ){ // Si ce n'est pas une mine
	                    nbAdj = 0;
	                    /* 2 'for' imbriques pour tester les cases adjacentes */
	                    for(a = i-1; a <= i+1; a++){
	                        for(b = j-1; b <= j+1; b++){
	                        	if(a >= 0 & a < Tadj.length & b >= 0 & b < Tadj[i].length ) {
	                        		 if(Tadj[a][b] == -1){ // si présence mine
	 	                                nbAdj++; // on incrémente 
	 	                            }
	                        	}
	                        }
	                    }
	                    Tadj[i][j] = nbAdj; // resultat placé dans la case concernée 
	                }
	            }
	        }
			
		}
	
		//
		// Exercice 2 : Affichage de la grille
		//

		// Question 2.a]
			//	Affichage de la grille
		public static void afficherGrille(boolean affMines) { 
	        /* Affichage 1er ligne (celle avec les lettres ) */
	        int k;
	        char c = 'A';
	        System.out.print("  |");
	        for( k = 0 ; k < T[0].length; k++){
	        	if(c == '[')
	        	{
	        		c = 'a'; // entre 24 et 30 c'est des caractères spéciaux
	        	}
	        	System.out.print(c);
	        	System.out.print("|");
	            c++;
	        }
	               	/* le numéro de ligne  */ 
	           for(int i = 0; i < T.length; i++ ){
	        	   System.out.println();
	        	   if(i<10) {
	        		   System.out.print("0" + i + "|");
	        	   }else{
	        		   System.out.print( i + "|");
	        	   }
	         /*  affichage de la grille de jeu    */ 
	               for(int j = 0; j < T[i].length; j++){
	                   /* reveler toutes les mines */
	                   if(affMines && Tadj[i][j] == -1){
	                       T[i][j] = 1;
	                   }
	                   /* affichage du contenu en fonction de la case */ 
	                   if(T[i][j] == 0){
	                	   System.out.print(" ");
	                   }else if(T[i][j] == 1 & Tadj[i][j] == -1){
	                	   System.out.print("!");
	                   }else if(T[i][j] == 1 & Tadj[i][j] >= 0) {
	                	   System.out.print(Tadj[i][j]);
	                   }else if(T[i][j] == 2){
	                	   System.out.print("X");
	                   }
	                   System.out.print("|");
	               }
	 	      }
		}
		
	//
	// Exercice 3 : Révéler une case
	//

	// Question 3.a]
		// Détéction des cases adjacentes '0'
	public static boolean caseAdjacenteZero(int i, int j) { 
		int a = 0;
		int b = 0;
		// parcourt des cases adjacentes
		 for(a = i-1; a <= i+1; a++){
             for(b = j-1; b <= j+1; b++){
             	if(a >= 0 & a < Tadj.length & b >= 0 & b < Tadj[i].length ) {
             		 if(T[a][b] == 1 & (Tadj[i][j] == 0 || Tadj[i][j] == 1 || Tadj[i][j] == 2 )){ // revelée ?
                        return true;
                     }
             	}
             }
         } return false; 
	}

	// Question 3.b]
		// révéler les cases vides
	public static void revelation(int i, int j) { 
		T[i][j] = 1; // révéler la case 
		int a = 0;
		int b = 0;
		if(Tadj[i][j] == 0) { // si aucune mines adjacentes
			for(int k = 0; k < 10; k++) { // parcourt la grille max 10 fois
				for(a = 0; a < Tadj.length  ; a++ ){
		            for(b = 0; b < Tadj[i].length  ; b++){
		            	if(caseCorrecte(a,b) && caseAdjacenteZero(a,b)) {
		            		// si elle a un '0' adjacent on la revèle 
		            		T[a][b] = 1; 
		            	}
		            }
				}
			}
			 
		}
	}

	// Question 3.c] Optionnel
	public static void relevation2(int i, int j) {
		
		
	}

	// Question 3.d]
		// placer un drapeau
	public static void actionDrapeau(int i, int j) { 
		if(caseCorrecte(i,j)) {	
			if(T[i][j] == 0) {
				T[i][j] = 2; //add drapeau
			}else if ( T[i][j] == 2) {
				T[i][j] = 0; //del drapeau
			}
		}
	}
	
	// Question 3.e]
		//révéler la case
	public static boolean revelerCase(int i, int j) { 
			if(Tadj[i][j] == -1) { //mine
				return false;
			}else {
				revelation(i,j); // pas mine
				return true;
			}
	
	}

	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
		//Permet de savoir si la partie est gagnée
	public static boolean aGagne() { // true si gagné 
		int i = 0;
		int j = 0;
			/* Parcourt de la grille */
		  for(i= 0; i < Tadj.length  ; i++ ){
	            for(j = 0; j < Tadj[i].length  ; j++){
	            	if(Tadj[i][j] != -1 & T[i][j] == 0) { 
	            		return false; //  mine révélée
	            	}
	            }
		  }
		  return true; 
	}

	// Question 4.b]
		//vérifier le format de la chaine de caractères
	public static boolean verifierFormat(String chaine) { 
		if( chaine.length() == 4 ) { // la chaine doit etre longue de 4 caractères
			char lettre1 = chaine.charAt(0);//
			char lettre2 = chaine.charAt(1);// on sépare chaque caractère
			char lettre3 = chaine.charAt(2);// dans un char
			char lettre4 = chaine.charAt(3);//
			if((lettre2 >= '0' && lettre2 <= '9') && (lettre2 >= '0' && lettre2 <= '9')) {
			// vérifie si les deux caractères du milieu sont bien des nombres
			}else {
				return false; // mauvais format 
			}
			// transforme les deux nombres (char) en un int a deux digits
			String ligne = "" + lettre2 + lettre3;
			int ligneInt = Integer.parseInt(ligne);
							
			if(lettre1 != 'd' & lettre1 != 'r') { // check le premier caractère
				return false;
			}
			if(ligneInt < 0 || ligneInt > 99) { // check la ligne
				return false;
			}
			if(lettre4 < 'A' || lettre4 > 'z') {// check la colonne
				return false;
			}
			return true;
		}else {
			return false;
		}
		
	}

	// Question 4.c]
		// Convertit la chaine en un tableau d'int
	public static int[] conversionCoordonnees(String chaine) {
		int[] InputJoueur = new int[3]; // on créé le tableau
		char lettre1 = chaine.charAt(0); // r  d
		char lettre2 = chaine.charAt(1); // 0  9
		char lettre3 = chaine.charAt(2); // 0  9 
		char lettre4 = chaine.charAt(3); // A  Z  a z 
		//on transforme les deux nombres (char) en un int a deux digits
		String chiffresString = "" + lettre2 + lettre3;
		int chiffresInt = Integer.parseInt(chiffresString);
		
		/* révéler ou drapeau */
		if(lettre1 == 'r') {
			InputJoueur[2] = 1;
		}else if (lettre1 == 'd') {
			InputJoueur[2] = 0;
		}
		/* Numéro de ligne */
		if(0 <= chiffresInt || chiffresInt <= 99) {
			InputJoueur[0] = chiffresInt;
		}
		/* Lettre colonne en numéro de colonne */ 
		String lettreS = "" + lettre4; 		// convertir char en String
		if(lettreS.toUpperCase() == lettreS) { // CAPS ?
			char lettreChar = lettreS.charAt(0); // convert String en char
			InputJoueur[1] = Character.getNumericValue(lettreChar) - 10 ; // insert la colonne
		}else if (lettreS.toUpperCase() != lettreS) { // minuscule ? 
			char lettreChar = lettreS.charAt(0); // convert String en char
			InputJoueur[1] = Character.getNumericValue(lettreChar) - 10 + 26 ; // -10 pour l'offset et +26 car lettre minuscule 
		}
		/* 		NB :    A -> Z = 0  -> 25 
		 * 				a -> z = 26 -> 52  	  */	 		
		return InputJoueur;
		
	}

	// Question 4.d]
		// Boucle de jeu 
	public static void jeu() {
		boolean etatPartie = aGagne();
		boolean boom = true; // si on fait exploser une mine BOOM = false   :(
		while(boom && !etatPartie) {
			/* Joueuse entre une action et coordonnée de case */
			System.out.println();
			System.out.print("Entrez une case valide : ");
			Scanner in = new Scanner(System.in);
		    String inputPlayer = in.nextLine();
		     
		    if(verifierFormat(inputPlayer)) { // vérifie le format 
		    	System.out.print("coordonnées valides : " + inputPlayer );
		    	System.out.println();
		    	int[] InputJoueur = conversionCoordonnees(inputPlayer); //trasfert la conversion dans le tableau
		    	// Quelle action : 
		    	if(InputJoueur[2] == 0 && caseCorrecte(InputJoueur[0],InputJoueur[1])) { 
		    		actionDrapeau(InputJoueur[0],InputJoueur[1]); 							// drapeau
		    		afficherGrille(false);
		    	}else if(InputJoueur[2] == 1 && caseCorrecte(InputJoueur[0],InputJoueur[1])) {
		    		boom = revelerCase(InputJoueur[0],InputJoueur[1]); 						// reveler une case
		    		if(!boom) { // mine découverte
		    			System.out.println();
		    			System.out.println("	PERDU !	  "); 
		    			afficherGrille(true);
		    		}else { // ce n'était pas une mine 
		    			afficherGrille(false); // affiche la grille actualisée et on recommence la boucle
		    		}
		    	}
		    	etatPartie = aGagne(); // la partie est-elle gagnée ? 
		    	if(etatPartie) { // oui
		    		System.out.println();
	    			System.out.println("	GAGNE !	  ");
		    	}
		    }else { // si l'input de la joueuse n'est pas bon 
		    	System.out.println("coordonnées invalides format : " );
		    	System.out.println("rXXX  > reveler une case" );
		    	System.out.println("dXXX  > marquer d'un drapeau la case");
		    	System.out.println("X01X  > ligne du tableau");
		    	System.out.println("XXXB  > colonne du tableau");
		    }
		}
	}

	
	//
	// Exercice 5 bonus challenge : Pour aller plus loin
	//

	// Question 5.a] Optionnel
	public static void aide() {
		
	}

	// Question 5.b] Optionnel
	public static void intelligenceArtificielle() {
		
	}

	// Question 5.c] Optionnel
	public static void statistiquesVictoiresIA() {
		
	}

}
