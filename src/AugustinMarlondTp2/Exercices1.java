package AugustinMarlondTp2;
import java.util.*;

public class Exercices1 {
	public static final Random rnd = new Random(0);

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		System.out.print("Entrez la valeur maximale : ");
		int valMax = clavier.nextInt();
		System.out.print("Inscrivez le nombre de valeurs: ");
		int nbVal = clavier.nextInt();
		int[] vecOri = genereVecAleatoire(nbVal, valMax);
		System.out.print("Vecteur initial: ");
		afficheVecteur(vecOri);
		
		int[] vecImpairs = extraitValeurs(vecOri, false);
		int[] vecPairs = extraitValeurs(vecOri, true);
		System.out.print("Vecteur pairs: ");
		afficheVecteur(vecPairs);
		System.out.print("Vecteur impairs: ");
		afficheVecteur(vecImpairs);
		System.out.println();

		triSel(vecPairs);
		triSel(vecImpairs);
		System.out.println("Apres le tri de chaque vecteur:");		
		System.out.print("Vecteur pairs: ");
		afficheVecteur(vecPairs);
		System.out.print("Vecteur impairs: ");
		afficheVecteur(vecImpairs);
		System.out.println();

		int[] vecFusion = fusionVec(vecPairs, vecImpairs);
		System.out.print("Fusion des vecteurs:");
		afficheVecteur(vecFusion);
		System.out.println();
		
		System.out.println("Statistique des vecteurs (Min - Med - Max): ");
		System.out.println("Vecteur pairs: " + genereMinMedMaxStr(vecPairs));
		System.out.println("Vecteur impairs: " + genereMinMedMaxStr(vecImpairs));
		System.out.println("Fusion des vecteurs: " + genereMinMedMaxStr(vecFusion));
		System.out.println();
		
		System.out.print("Quelle est la valeur recherchee? ");
		int valCherchee = clavier.nextInt();
		int posVal = rechercheBin(vecFusion, valCherchee);
		if (posVal!=-1) {
			System.out.println("La valeur recherchee se trouve en position " + posVal);
		} else {
			System.out.print("Valeur introuvable");
		}
	}
	
	/**
	 * Permet de generer un vecteur de nombres aleatoires
	 * @param taille Taille du vecteur a generer
	 * @param valMax Valeur maximale des nombres generes.  Les
	 * 			valeurs seront entre 0 inclusif et valMax exclusif.
	 * @return Vecteur contenant les valeurs generees
	 */
	public static int[] genereVecAleatoire(int taille, int valMax) {
		int[] vecA = new int[taille];
		for (int i = 0; i < taille; ++i) {
			vecA[i] = Math.floorMod(rnd.nextInt(),valMax);
		}
		return vecA;
	}
	/**
	 * Fonction qui permet d'afficher un vecteur en le delimitant
	 * avec des crochets et en separant les valeurs par des virgules
	 * Ex: [1, 3, 5, 7, 9]
	 * @param vec Vecteur a afficher
	 */
	public static void afficheVecteur(int[] vec) {
		System.out.print("[");
		if (vec != null && vec.length > 0) {
			System.out.print(vec[0]);
			for (int i = 1; i < vec.length; ++i) {
				System.out.print(" ," + vec[i]);
			}
		}
		System.out.println("]");
	}

	/**
	 * Produit une chaine de caractere contenant le minimum, la mediane et
	 * le maximum du vecteur trie recu en parametre
	 * Exemple: Pour le vecteur [ 1, 3, 5, 7, 9] la chaine retournee sera
	 * "1 - 5 - 9" sans les guillements.
	 * @param vec Vecteur trie qui sera analyse
	 * @return Chaine indiquant, dans l'ordre, le min, la med et le max.
	 */
	public static String genereMinMedMaxStr(int[] vec) {
		String mmmStr = "" + vec[0] + " - ";
		if (vec.length%2 == 0) { // Nombre de cases pair
	        mmmStr += (vec[vec.length/2-1] + vec[vec.length/2])/2.0f;
	    } else { // Nombre de cases impair
	    	mmmStr += vec[vec.length/2];
	    }
		mmmStr += " - " + vec[vec.length-1];
		return mmmStr;
	}

	/**
	 * Cree un nouveau vecteur contenant seulement les valeurs paires ou
	 * impaires a partir du vecteur recu en parametre
	 * La taille du nouveau vecteur doit correspondre au nombre de valeurs extraites
	 * @param vecNb Vecteur de depart
	 * @param pair Selectionne les valeurs paires (true) ou impaires (false)
	 * @return Nouveau vecteur contenant les valeurs extraites
	 */
	public static int[] extraitValeurs(int[] vecNb, boolean pair) {
		int[] nbExtraits = null;
		// TODO: Implanter l'extraction des valeurs paires ou impaires
		// TODO: Ecrire des tests unitaires
		int nbValeur=0, k=0;
		//incrémentation de la longeur du vecteur contenant les valeur extraites
		for (int i=0;i<vecNb.length;i++) {
			if(pair==true && vecNb[i]%2==0) {
				nbValeur++;
			}if(pair==false && vecNb[i]%2!=0){
			nbValeur++;
			}nbExtraits=new int [nbValeur];
			}
		
		for (int Z=0;Z<vecNb.length;Z++) {
			if(vecNb[Z]%2==0 &&  k<nbExtraits.length && pair==true) {
				nbExtraits[k]=vecNb[Z] ;
				k++;
				
			}if(vecNb[Z]%2!=0 &&  k<nbExtraits.length && pair==false) {
				nbExtraits[k]=vecNb[Z] ;
				k++;
			}
		}
		
		return nbExtraits;
	}
	
	/**
	 * Effectue un tri par selection du vecteur recu en parametre
	 * @param vec Vecteur qui sera trie
	 */
	public static void triSel(int[] vec) {
		int posmin=0, posTemp=0;
		// TODO: Implanter le tri par selection
		// TODO: Ecrire des tests unitaires
		for(int i=0;i<vec.length;i++) {
			posmin=i;
			for(int j=i+1;j<vec.length;j++) {
				if(vec[j] <vec[posmin]) {
				posmin=j;
				}
			}
			if ( posmin != i) {
			posTemp = vec[i];
			vec[i] = vec[posmin];
			vec[posmin]=posTemp;
		}
		}
	}

	/**
	 * Fusionne deux vecteurs tries.  La taille du vecteur resultant
	 * est la somme des tailles des deux vecteurs recus en parametre
	 * @param vecA Premier vecteur trie a fusionner
	 * @param vecB Second vecteur trie a fusionner
	 * @return Vecteur trie resultant de la fusion de vecA et vecB
	 */
	public static int[] fusionVec(int[] vecA, int[] vecB) {
		// TODO: Implanter la fusion de deux vecteurs tries
		// Vous devez creer un vecteur pour contenir le resultat de la fusion
		int[] vecF = null;
		int posVecA = 0;
		int posVecB = 0;
		int posVecF = 0;
		vecF= new int [vecA.length+vecB.length];
		 posVecA = 0;

			if (vecA.length + vecB.length <= vecF.length) {
				while (posVecA < vecA.length && posVecB < vecB.length) {
				
						if(vecA[posVecA] < vecB[posVecB]) {
							vecF[posVecF] = vecA[posVecA];
							posVecA++;
						} else {
							vecF[posVecF] = vecB[posVecB];
							posVecB++;

						}
						posVecF++;
					}
				}
				while (posVecA < vecA.length) {
					vecF[posVecF++] = vecA[posVecA++];
				}
				while (posVecB < vecB.length) {
					vecF[posVecF++] = vecB[posVecB++];
				}
				
				
		return vecF;
	}
	
	/**
	 * Recherche binaire d'une valeur dans un vecteur trie
	 * @param vec Vecteur trie dans lequel la valeur sera recherchee
	 * @param valeur Valeur a rechercher
	 * @return Position de la valeur dans le vecteur ou -1 si non trouvee
	 */
	public static int rechercheBin(int[] vec, int valeur) {
		int posGa = 0;
		int posDr = vec.length - 1;
		int posMi = (posGa + posDr)/2;
		// TODO: Implanter la recherche binaire
		
		while (posGa <= posDr && valeur!=vec[posMi]) {
			if(valeur < vec[posMi]) {
				posDr=posMi - 1; 
			}else {
				posGa=posMi + 1;
			}
			posMi=(posGa + posDr)/2;
			System.out.println("Ga: "+ posGa+ " - " +" Mi : " +posMi + " - " +" Dr: " +posDr);
		}if(posGa<=posDr) {
			/*Je n'effectue rien car j'ai crée le if else juste pour
			pouvoir donné la valeur -1 à posMi, si la valeur est
			introuvable
			*/
		}else {
			posMi=-1;
		}
		
		return posMi;
	}
}
