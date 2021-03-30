package AugustinMarlond.At05;

public class AT05E01 {

	/**
	 * Permet de faire la fusion de 2 tableaux et met le resultat dans vecF NOTE:
	 * vecF doit etre initialise et avoir une taille suffisante.
	 * 
	 * @param vecA    Premier vecteur a fusionner
	 * @param nbElemA Nombre d'elements dans le premier vecteur
	 * @param vecB    Second vecteur a fusionner
	 * @param nbElemB Nombre d'elements dans le deuxieme vecteur
	 * @param vecF    Vecteur recevant le resultat de la fusion
	 * @param ordonne Fusion de vecteur ordonnes ou non
	 * @return Nombre d'elements dans le vecteur fusionne, -1 si erreur
	 */
	public static int fusionVec(long[] vecA, int nbElemA, long[] vecB, int nbElemB, long[] vecF, boolean ordonne) {
		int nbElemF = -1;

		
		int posVecA = 0;
		int posVecB = 0;
		int posVecF = 0;

		if (vecA.length + vecB.length <= vecF.length) {
			if (ordonne) {
			while (posVecA < nbElemA && posVecB < nbElemB) {
			
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
			while (posVecA < nbElemA) {
				vecF[posVecF++] = vecA[posVecA++];
			}
			while (posVecB < nbElemB) {
				vecF[posVecF++] = vecB[posVecB++];
			}
			
			while (posVecF < vecF.length) {
				vecF[posVecF++] = nbElemF;
			}

		}
		// TODO: Verifier si le vecF contient assez d'espace pour la fusion
		// TODO: Traiter le cas des vecteurs ordonnes et non ordonnes
		// TODO: Tests unitaires
		// Attention: nbElemF n'est pas la taille de F, ni celle de vecA + vecB,
		// mais le nombre d'element dans le vecteur fusionne
		return nbElemF;
	}

	/**
	 * Fonction qui permet de trier le tableau recu en parametre NOTE: L'ordre
	 * original du tableau sera perdu
	 * 
	 * @param vec    Tableau a trier
	 * @param nbElem Nombre d'elements contenus dans le tableau
	 */
	public static void triSel(long[] vec, int nbElem) {
	int posMin;
	long tempLong;
		for (int i =0; i <nbElem;i++) {
			posMin=i;
			for (int j=i+1 ;j< nbElem;j++){
				if (vec[j]<vec[posMin]) {
					posMin=j;
				}
			}if (posMin !=i) {
				tempLong= vec[i];
				vec[i]=vec[posMin];
				vec[posMin]=tempLong ;
			}
		}
		// TODO: Implanter le tri par selection
		// TODO: Tests unitaires
	}

	/**
	 * Permet d'ajouter un element dans un tableau
	 * 
	 * @param vec     Tableau dans lequel l'element sera ajoute
	 * @param nbElem  Nombre d'elements contenus dans le tableau
	 * @param elem    Valeur a ajouter
	 * @param ordonne Indique si on fait l'ajout dans un tableau ordonne ou non
	 * @return Nouveau nombre d'elements contenus dans le tableau
	 */
	public static int ajout(long[] vec, int nbElem, long elem, boolean ordonne) {
		// TODO: Verifier s'il reste de la place dans le vecteur
		// TODO: Traiter les vecteurs ordonnes et non ordonnes
		// TODO: Tests unitaires
		if (nbElem<vec.length) {
			
			if (ordonne) {
		int posTemporaire=nbElem;
				while(posTemporaire > 0 && elem<vec[posTemporaire-1]) {
					vec[posTemporaire]=vec[posTemporaire-1];
					posTemporaire--;
				}
				vec[posTemporaire]= elem;
				nbElem++;
		}else {
			vec[nbElem]= elem;
			nbElem++;
		}
		}
		return nbElem;
	}
	

	/**
	 * Recherche sequentielle de la premiere occurence d'une valeur
	 * 
	 * @param vec     Tableau contenant les valeurs
	 * @param nbElem  Nombre d'elements contenus dans le tableau
	 * @param valeur  Valeur recherchee
	 * @param ordonne Indique si le tableau est ordonne ou non
	 * @return Position de la valeur recherchee, -1 si non trouvee
	 */
	public static int rechercheSeq(long[] vec, int nbElem, long valeur, boolean ordonne) {
		int pos = 0;
		if (ordonne) { // Pour un tableau ordonne
			while (pos < nbElem && vec[pos] < valeur) {
				++pos;
			}
			if (pos == nbElem || vec[pos] != valeur) {
				pos = -1;
			}
		} else { // Pour un tableau non ordonne
			while (pos < nbElem && vec[pos] != valeur) {
				++pos;
			}
			if (pos == nbElem) {
				pos = -1;
			}
		}
		return pos;
	}

	/**
	 * Recherche binaire de l'occurence d'une valeur. NOTE: Le tableau doit etre
	 * ordonne
	 * 
	 * @param vec    Tableau contenant les valeurs
	 * @param nbElem Nombre d'elements contenus dans le tableau
	 * @param valeur Valeur recherchee
	 * @return Position de la valeur recherchee, -1 si non trouvee
	 */
	public static int rechercheBin(long[] vec, int nbElem, long valeur) {
		int posGa = 0;
		int posDr = nbElem - 1;
		int posMi = (posGa + posDr)/2;
		while (posGa <= posDr && valeur!=vec[posMi]) {
			if(valeur < vec[posMi]) {
				posDr=posMi - 1; 
			}else {
				posGa=posMi + 1;
			}
			posMi=(posGa + posDr)/2;
		}
		// TODO: Implanter la recherche binaire
		// TODO: Tests unitaires
		// Vous pouvez utiliser l'affichage ci-dessous pour vous aider a deboguer
//			System.out.println("Ga : " + posGa + "     Mi : " + posMi + "     Dr : " + posDr);
		return posMi;
	}

	/**
	 * Retrait d'une valeur dans un tableau
	 * 
	 * @param vec      Tableau dans lequel l'element sera retire
	 * @param nbElem   Nombre d'elements contenus dans le tableau
	 * @param elem     Valeur a retirer
	 * @param typeRech Methode a utiliser pour retrouver l'element a retirer 'B':
	 *                 recherche binaire, retrait dans un tableau ordonne 'S':
	 *                 recherche sequentielle dans un tableau ordonnee 'N':
	 *                 recherche sequentielle dans un tableau non ordonne
	 * @return Nombre d'elements restants a la suite du retrait
	 */
	public static int retrait(long[] vec, int nbElem, long elem, char typeRech) {
		int posItem;
		switch (typeRech) { // Recherche de l'element a retirer
		case 'B': // Bianire
			posItem = rechercheBin(vec, nbElem, elem);
			if(posItem!= -1) {
				nbElem--;
				for(int posTemp=posItem; posTemp< nbElem; posTemp++) {
					vec[posTemp]=vec[posTemp+1];
				}
				vec[nbElem]=-1;
			}
		
			break;
		case 'S': // Sequentielle ordonnee
			posItem = rechercheSeq(vec, nbElem, elem, true);
			if(posItem!= -1) {
				nbElem--;
				for(int posTemp=posItem; posTemp< nbElem; posTemp++) {
					vec[posTemp]=vec[posTemp+1];
				}
				vec[nbElem]=-1;
			}
			break;
		default: // Sequentielle non ordonnee
			posItem = rechercheSeq(vec, nbElem, elem, false);
			if(posItem!= -1) {
				nbElem--;
				vec[posItem]=vec[nbElem] ;
				vec[nbElem]=-1;
			}
			break;
		}
		
		
		// TODO: Retirer l'item s'il a ete trouve dans le vecteur
		// TODO: Traiter les cas ordonnes (B et S) et non ordonne (N)
		// TODO: Tests unitaires
		return nbElem;
	}
}
