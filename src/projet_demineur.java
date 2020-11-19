
//
// Maxime Chanu L1 G5 - B 
// 
//   Optionnel : vous pouvez aussi joindre un document PDF donnant des explications suppl√©mentaires
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
		
		init(12,12,6);
		boolean go = caseCorrecte(3,2); // verif de la fonction OK 
		System.out.println(go);
		afficherTableau2D(T);
		calculerAdjacent();
		System.out.println();
		afficherTableau2D(Tadj);
		afficherGrille(true);
		System.out.println();
		revelation(1,1);
		System.out.println();
		afficherGrille(true);
		
		
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
	        /* Variable de dÈtection des cases autour */
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
	                    Tadj[i][j] = nbAdj; // resultat placÈ dans la case concernÈe 
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
	                   /* revelÈ toutes les mines */
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
	// Exercice 3 : R√©v√©ler une case
	//

	// Question 3.a]
	public static boolean caseAdjacenteZero(int i, int j) { 
		boolean zero = false;
		int a = 0;
		int b = 0;
		// parcours des cases adjacentes
		 for(a = i-1; a <= i+1; a++){
             for(b = j-1; b <= j+1; b++){
             	if(a >= 0 & a < Tadj.length & b >= 0 & b < Tadj[i].length ) {
             		 if(T[a][b] == 1){ // revelÈe ?
                        zero = true;
                      }
             	}
             }
         }
		if(zero & Tadj[i][j] == 0) {
			return true;
		}else {
			return false;
		}
		
	}

	// Question 3.b]
	public static void revelation(int i, int j) { 
		T[i][j] = 1;
		int a = 0;
		int b = 0;
		//boolean auMoinsUneNouvelleCase = true;
		if(Tadj[i][j] == 0) {
			for(int ko =0; ko < 30; ko++) {
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
	public static void actionDrapeau() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	}
	
	
	// Question 3.e]
	public static void revelerCase() { // ATTENTION, vous devez modifier la signature de cette fonction
		
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static void aGagne() {
		
	}

	// Question 4.b]
	public static void verifierFormat() { // ATTENTION, vous devez modifier la signature de cette fonction
		
		
	}

	// Question 4.c]
	public static void conversionCoordonnees() { // ATTENTION, vous devez modifier la signature de cette fonction
		
		
	}

	// Question 4.d]
	public static void jeu() {
		
	}

	// Question 4.e]
	// Votre *unique* m√©thode main
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
