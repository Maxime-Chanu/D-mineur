
//
// Maxime Chanu L1 G5 - B 
// 
//   Optionnel : vous pouvez aussi joindre un document PDF donnant des explications supplÃ©mentaires
//
// Pour utiliser des scanners pour lire des entrees depuis le clavier
// utilises en questions 4.d] pour la fonction jeu()
import java.util.Scanner;
// Pour la fonction entierAleatoire(a, b) 
import java.util.concurrent.ThreadLocalRandom;

				/* Main */
public class projet_demineur {

	public static int entierAleatoire(int a, int b){
		// Renvoie un entier aleatoire uniforme entre a (inclus) et b (inclus).
		return ThreadLocalRandom.current().nextInt(a, b + 1);
	}

	static int[][] T; //Voici les variables globale
	static int[][] Tadj;

	public static void main(String[] args) {
		
		init(3,3,1);
		boolean go = caseCorrecte(3,2); // verif de la fonction OK 
		/*
		 System.out.println(go);
		afficherTableau2D(T);
		calculerAdjacent();
		System.out.println();
		afficherTableau2D(Tadj);
		afficherGrille(false);
		System.out.println();
		revelation(0,0);
		System.out.println();
		afficherGrille(false);
		System.out.println(aGagne());
		 */
		/*
		String str ="12";
		char ch1 = str.charAt(0);
		char ch2 = str.charAt(1);
		String wola = "" + ch1 + ch2;
		int resultat = Integer.parseInt(wola);
		System.out.println("le résultat est : " + (resultat ) );
		int entier = Character.getNumericValue(lettre) ;
		*/
		/*
		String joueur = "r12z";
		char lettre = joueur.charAt(3); // 'A'
		String lettreS = "" + lettre; // string : "A"
		if(lettreS.toUpperCase() == lettreS) { // CAPS
			char lettreChar = lettreS.charAt(0);
			int j = Character.getNumericValue(lettreChar) ;
			j -= 10;
			System.out.println(j);
		}else if (lettreS.toUpperCase() != lettreS) { //min
			char lettreChar = lettreS.charAt(0);
			int j = Character.getNumericValue(lettreChar) ;
			j -= 10;
			j += 26;
			System.out.println(j);
			System.out.println("min");
		}
		char colonne = 'b';
		if(colonne < 'b') {
			System.out.println("inf à b");
		}else { 
			System.out.println("sup à b");
		}
		*/
	//	System.out.println("le résultat est : " + (entier  ) );
		String chaine = "d02A";
		boolean res = verifierFormat(chaine);
		System.out.println(res);
		int[] InputJoueur = conversionCoordonnees(chaine);
		System.out.println(InputJoueur[0] + "," + InputJoueur[1] + "," +InputJoueur[2] );
	}
	
	// Question 1.b] Fonction init pour T et Tadj 
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
	// affichage d'un tableau a 2 dimensions 
	public static void afficherTableau2D(int[][] tab) {
		for(int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				System.out.print(tab[i][j]);
				System.out.print("  ");
			}
			System.out.println();
		}
	}
	// Question 1.c] Fonction caseCorrecte
		public static boolean caseCorrecte(int i, int j ) { 
	        int largeur = T[0].length;
	        int hauteur = T.length; 
	        if(j >= largeur || i >= hauteur){
	            return false;
	        }else{
	            return true;
	        }
	        
			
		}


		// Question 1.d] Fonction calculerAdjacent
		public static void calculerAdjacent() {
	        int nbAdj; // nb de mines adjacentes 
	        /* Variable de détection des cases autour */
	        int a;      // ligne    
	        int b;      // colonne 
	        int i =0;
	        int j =0;
	        /* 2 for imbrique pour visiter toute les cases du tableau */ 
	        for(i= 0; i < Tadj.length  ; i++ ){
	            for(j = 0; j < Tadj[i].length  ; j++){
	                if( Tadj[i][j] != -1 ){ // Si ce n'est pas une mine
	                    nbAdj = 0;
	                    /* 2 for imbrique pour tester les cases adjacentes */
	                    for(a = i-1; a <= i+1; a++){
	                        for(b = j-1; b <= j+1; b++){
	                        	if(a >= 0 & a < Tadj.length & b >= 0 & b < Tadj[i].length ) {
	                        		 if(Tadj[a][b] == -1){
	 	                                nbAdj++;
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
		public static void afficherGrille(boolean affMines) { 
	        /* Affichage 1er ligne (celle avec les lettres ) */
	        int k;
	        char c = 'A';
	        System.out.print("  |");
	        for( k = 0 ; k < T[0].length; k++){
	        	System.out.print(c);
	        	System.out.print("|");
	            c++;
	        }
	        /*  affichage de la grille de jeu    */ 
	           for(int i = 0; i < T.length; i++ ){
	        	   System.out.println();
	        	   if(i<10) {
	        		   System.out.print("0" + i + "|");
	        	   }else{
	        		   System.out.print( i + "|");
	        	   }
	        	   
	               for(int j = 0; j < T[i].length; j++){
	                   /* revelé toutes les mines */
	                   if(affMines && Tadj[i][j] == -1){
	                       T[i][j] = 1;
	                   }
	                   /* affichage du contenu de chaque case */ 
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
	// Exercice 3 : RÃ©vÃ©ler une case
	//

	// Question 3.a]
	public static boolean caseAdjacenteZero(int i, int j) { 
		//boolean zero = false;
		int a = 0;
		int b = 0;
		// parcours des cases adjacentes
		 for(a = i-1; a <= i+1; a++){
             for(b = j-1; b <= j+1; b++){
             	if(a >= 0 & a < Tadj.length & b >= 0 & b < Tadj[i].length ) {
             		 if(T[a][b] == 1 & Tadj[i][j] == 0){ // revelée ?
                        return true;
                      }
             	}
             }
         } return false;
	//	if(zero & Tadj[i][j] == 0) {
	//		return true;
	//	}else {
	//		return false;
	//	}
		
	}

	// Question 3.b]
	public static void revelation(int i, int j) { 
		T[i][j] = 1;
		int a = 0;
		int b = 0;
		//boolean auMoinsUneNouvelleCase = true;
		if(Tadj[i][j] == 0) {
			for(int ko =0; ko < 10; ko++) {
				//auMoinsUneNouvelleCase = false;
				for(a = 0; a < Tadj.length  ; a++ ){
		            for(b = 0; b < Tadj[i].length  ; b++){
		            	if(caseAdjacenteZero(a,b)) {
		            		T[a][b] = 1;
		            	//	auMoinsUneNouvelleCase = true;
		            	}
		            }
				}
			}
			 
		}
	}


	// Question 3.c] Optionnel
	public static void relevation2() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	
	}

	// Question 3.d]
	public static void actionDrapeau(int i, int j) { 
		if(T[i][j] == 0) {
			T[i][j] = 2;
		}else if ( T[i][j] == 2) {
			T[i][j] = 0;
		}
		
	}
	
	
	// Question 3.e]
	public static boolean revelerCase(int i, int j) { 
		if(Tadj[i][j] == -1) {
			return false;
		}else {
			revelation(i,j);
			return true;
		}
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static boolean aGagne() {
		int i = 0;
		int j = 0;
		  for(i= 0; i < Tadj.length  ; i++ ){
	            for(j = 0; j < Tadj[i].length  ; j++){
	            	if(Tadj[i][j] != -1 & T[i][j] == 0) {
	            		return false;
	            	}
	            	
	            }
		  }
		  return true; 
	}

	// Question 4.b]
	public static boolean verifierFormat(String chaine) { 
		if( chaine.length() == 4) {
			char lettre1 = chaine.charAt(0);
			char lettre2 = chaine.charAt(1);
			char lettre3 = chaine.charAt(2);
			char lettre4 = chaine.charAt(3);
			String ligne = "" + lettre2 + lettre3;
			int ligneInt = Integer.parseInt(ligne);
				
			
			if(lettre1 != 'd' & lettre1 != 'r') {
				return false;
			}
			if(ligneInt < 0 || ligneInt > 99) {
				return false;
			}
			if(lettre4 < 'A' || lettre4 > 'z') {
				return false;
			}
			return true;
		}else {
			return false;
		}
		
	}

	// Question 4.c]
	public static int[] conversionCoordonnees(String chaine) {
		int[] InputJoueur = new int[3];
		char lettre1 = chaine.charAt(0); // r   d
		char lettre2 = chaine.charAt(1); // 0  9
		char lettre3 = chaine.charAt(2); // 0  9 
		char lettre4 = chaine.charAt(3); // A  Z a z 
		String chiffresString = "" + lettre2 + lettre3;
		int chiffresInt = Integer.parseInt(chiffresString);
		
		/* reveler ou drapeau */
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
		String lettreS = "" + lettre4; 		// convert char en String
		if(lettreS.toUpperCase() == lettreS) { // CAPS ?
			char lettreChar = lettreS.charAt(0); // convert String en char
			InputJoueur[1] = Character.getNumericValue(lettreChar) - 10 ; // insert la colonne
		}else if (lettreS.toUpperCase() != lettreS) { //minuscule ? 
			char lettreChar = lettreS.charAt(0); // convert String en char
			InputJoueur[1] = Character.getNumericValue(lettreChar) - 10 + 26 ; // -10 pour l'offset et +26 car lettre minuscule 
		}

		return InputJoueur;
		
	}

	// Question 4.d]
	public static void jeu() {
		
	}

	// Question 4.e]
	// Votre *unique* mÃ©thode main
	// elle est en haut 


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
