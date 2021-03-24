package AugustinMarlond.At05;

import java.util.*;
public class AT05E1PERF {




	

		

			// Algos 1D

			// Somme et moyenne						
			// Min et max			1	2	3	4	5	T
			// Tri sel				1	2	3	4	5	T
			// Recherche directe						T
			// Recherche seq					4		T
			// Recherche seq trie			3		5	
			// Ajout non trie			2				
			// Retrait non trie					4	5	
			// Ajout trie				2				
			// Retrait trie						4	5	
			// Init tous diff							
			// Copie									
			// Fusion non tries		1					
			// Fusion tries			1					T
			// Recherche bianire			3		5	T

			// 1a - fusion + tri
			// 1b - tri + fusion
			// 2a - ajout tri
			// 2b - ajout non tri + tri
			// 3a - tri + recherche seq
			// 3b - tri + recherche bin
			// 4a - tri + retrait non tri + tri
			// 4b - tri + retrait tri
			// 5a - tri - retrait tri bin
			// 5b - tri - retrait tri seq

			public static Random rnd = new Random(0); // Generateur de nombre aleatoires
			public static final int DIM_TEST = 250000; // Dimension du vecteur de test
			// 1/PROB_INVALID: Probabilite que la valeur a trouver ne soit pas dans le tableau
			public static final int PROB_INVALID = 50; // 2% de valeurs introuvables
			
			public static void main(String[] args) {
				long delai;
				// Vecteur de base qui sera copie et utilise pour tous les tests
				long[] vecOriginal = genereVecAleatoire(DIM_TEST);

				// TEST 1 - Tri ou fusion en premier?
				System.out.print(String.format("%-15s", "Test 1: "));
				delai = test1A(vecOriginal);
				System.out.print(String.format("%,20d : ", delai).replace('\u00A0', ' '));
				delai = test1B(vecOriginal);
				System.out.println(String.format("%,20d", delai).replace('\u00A0', ' '));
				
				// TEST 2 - Ajout trie ou non trie? pour differents nombres d'ajouts 
				for (int i = 1; i <= 10; ++i) {
					System.out.print(String.format("%-15s", "Test 2 ("+ i*10 + "%): "));
					delai = test2A(vecOriginal,i*10);
					System.out.print(String.format("%,20d : ", delai).replace('\u00A0', ' '));
					delai = test2B(vecOriginal,i*10);
					System.out.println(String.format("%,20d", delai).replace('\u00A0', ' '));
				}

				// TEST 3 - Recherche sequentielle vs binaire
				System.out.print(String.format("%-15s", "Test 3: "));
				delai = test3A(vecOriginal);
				System.out.print(String.format("%,20d : ", delai).replace('\u00A0', ' '));
				delai = test3B(vecOriginal);
				System.out.println(String.format("%,20d", delai).replace('\u00A0', ' '));
				
				// TEST 4 - Retrait trie ou non trie? pour differents nombres de retrait
				for (int i = 1; i <= 10; ++i) {
					System.out.print(String.format("%-15s", "Test 4 ("+ i*10 + "%): "));
					delai = test4A(vecOriginal,i*10);
					System.out.print(String.format("%,20d : ", delai).replace('\u00A0', ' '));
					delai = test4B(vecOriginal,i*10);
					System.out.println(String.format("%,20d", delai).replace('\u00A0', ' '));
				}
				
				// TEST 5 - Retrait trie avec recherche sequentielle ou binaire
				System.out.print(String.format("%-15s", "Test 5: "));
				delai = test5A(vecOriginal,75);
				System.out.print(String.format("%,20d : ", delai).replace('\u00A0', ' '));
				delai = test5B(vecOriginal,75);
				System.out.println(String.format("%,20d", delai).replace('\u00A0', ' '));
			}

			/**
			 * Fusion de 2 vecteurs de taille egale non tries suivi d'un tri
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 */
			public static long test1A(long[] vecOriginal) {
				long[] vecGa, vecDr, vecFu;
				int vecGaNb, vecDrNb, vecFuNb;
				long debut, fin;

				// Initialisation des vecteurs
				vecGa = Arrays.copyOfRange(vecOriginal, 0, vecOriginal.length/2);
				vecGaNb = vecGa.length;
				vecDr = Arrays.copyOfRange(vecOriginal, vecOriginal.length/2, vecOriginal.length);
				vecDrNb = vecDr.length;
				vecFu = new long[vecGaNb + vecDrNb];

				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				vecFuNb = AT05E01.fusionVec(vecGa, vecGaNb, vecDr, vecDrNb, vecFu, false);
				AT05E01.triSel(vecFu, vecFuNb);
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Tri de 2 vecteurs de taille egale suivi de leur fusion
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 */
			public static long test1B(long[] vecOriginal) {
				long[] vecGa, vecDr, vecFu;
				int vecGaNb, vecDrNb, vecFuNb;
				long debut, fin;

				// Initialisation des vecteurs
				vecGa = Arrays.copyOfRange(vecOriginal, 0, vecOriginal.length/2);
				vecGaNb = vecGa.length;
				vecDr = Arrays.copyOfRange(vecOriginal, vecOriginal.length/2, vecOriginal.length);
				vecDrNb = vecDr.length;
				vecFu = new long[vecGaNb + vecDrNb];
				
				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				AT05E01.triSel(vecGa, vecGaNb);
				AT05E01.triSel(vecDr, vecDrNb);
				vecFuNb = AT05E01.fusionVec(vecGa, vecGaNb, vecDr, vecDrNb, vecFu, true);
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Ajout dans un vecteur trie
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 * @param pctLongueur Pourcentage de la longueur a remplir
			 */
			public static long test2A(long[] vecOriginal, int pctLongueur) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = new long[vecOriginal.length];
				vecNb = 0;
				
				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vec.length*pctLongueur/100; ++i) {
					vecNb = AT05E01.ajout(vec, vecNb, elemAjout(vecOriginal, i), true);
				}
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Ajout dans un vecteur non trie suivi d'un tri
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 * @param pctLongueur Pourcentage de la longueur a remplir
			 */
			public static long test2B(long[] vecOriginal, int pctLongueur) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = new long[vecOriginal.length];
				vecNb = 0;
				
				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vec.length*pctLongueur/100; ++i) {
					vecNb = AT05E01.ajout(vec, vecNb, elemAjout(vecOriginal, i), false);
				}
				AT05E01.triSel(vec, vecNb);
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Recherche sequentielle dans un vecteur trie
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 */
			public static long test3A(long[] vecOriginal) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = vecOriginal.clone();
				vecNb = vec.length;
				Arrays.sort(vec); // Tri (librairie Java) non evalue pour ce test

				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vecNb; ++i) {
					AT05E01.rechercheSeq(vec, vecNb, elemRecherche(vecOriginal, i), true);
				}
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Recherche binaire dans un vecteur trie
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 */
			public static long test3B(long[] vecOriginal) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = vecOriginal.clone();
				vecNb = vec.length;
				Arrays.sort(vec); // Tri (librairie Java) non evalue pour ce test
				
				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vecNb; ++i) {
					AT05E01.rechercheBin(vec, vecNb, elemRecherche(vecOriginal, i));
				}
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Retraits dans un vecteur non trie suivi d'un tri
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 * @param pctLongueur Pourcentage des valeurs a retirer avant le tri
			 */
			public static long test4A(long[] vecOriginal, int pctLongueur) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = vecOriginal.clone();
				vecNb = vec.length;
				Arrays.sort(vec); // Tri initial (librairie Java) non evalue pour ce test

				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vec.length*pctLongueur/100; ++i) {
					vecNb = AT05E01.retrait(vec, vecNb, elemRecherche(vecOriginal, i), 'N');
				}
				AT05E01.triSel(vec, vecNb);
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Retraits dans un vecteur trie
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 * @param pctLongueur Pourcentage des valeurs a retirer
			 */
			public static long test4B(long[] vecOriginal, int pctLongueur) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = vecOriginal.clone();
				vecNb = vec.length;
				Arrays.sort(vec); // Tri initial (librairie Java) non evalue pour ce test
				
				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vec.length*pctLongueur/100; ++i) {
					vecNb = AT05E01.retrait(vec, vecNb, elemRecherche(vecOriginal, i), 'S');
				}
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Retraits dans un vecteur trie en utilisant la recherche binaire
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 * @param pctLongueur Pourcentage des valeurs a retirer
			 */
			public static long test5A(long[] vecOriginal, int pctLongueur) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = vecOriginal.clone();
				vecNb = vec.length;
				Arrays.sort(vec); // Tri initial (librairie Java) non evalue pour ce test
				
				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vec.length*pctLongueur/100; ++i) {
					vecNb = AT05E01.retrait(vec, vecNb, elemRecherche(vecOriginal, i), 'B');
				}
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Retraits dans un vecteur trie en utilisant la recherche sequentielle
			 * @param vecOriginal Vecteur aleatoire pour les tests
			 * @param pctLongueur Pourcentage des valeurs a retirer
			 */
			public static long test5B(long[] vecOriginal, int pctLongueur) {
				long[] vec;
				int vecNb;
				long debut, fin;

				// Initialisation du vecteur
				vec = Arrays.copyOfRange(vecOriginal, 0, vecOriginal.length);
				vecNb = vec.length;
				Arrays.sort(vec); // Tri initial (librairie Java) non evalue pour ce test

				// Execution du test et affichage du resultat
				debut = System.nanoTime();
				for (int i = 0; i < vec.length*pctLongueur/100; ++i) {
					vecNb = AT05E01.retrait(vec, vecNb, elemRecherche(vecOriginal, i), 'S');
				}
				fin = System.nanoTime();
				return fin - debut;
			}

			/**
			 * Genere un vecteur de nombre aleatoires en long
			 * NOTE: Utilisez cette fonction une seule fois et faites, par la suite,
			 * 		 des copies du vecteur obtenu afin d'uniformiser les tests
			 * @param taille Nombre de valeurs aleatoires voulues dans le vecteur
			 * @return Vecteur avec le nombre voulu de valeurs aleatoires
			 */
			public static long[] genereVecAleatoire(int taille) {
				long[] vecA = new long[taille];
				for (int i = 0; i < taille; ++i) {
					vecA[i] = (long) (rnd.nextDouble()*Long.MAX_VALUE);
				}
				return vecA;
			}
			
			/**
			 * Selectionne un element dans le vecteur de nombre aleatoires
			 * selon la valeur du compteur
			 * @param vecOri Vecteur de nombres aleatoires
			 * @param compteur Valeur utilisee pour choisir le nombre
			 * @return Valeur extraite du vecteur aleatoire
			 */
			public static long elemAjout(long[] vecOri, int compteur) {
				int pos = (int) ((99881L*compteur)%DIM_TEST);
				return vecOri[pos];
			}
			
			/**
			 * Genere un element a recherche a partir du vecteur de nombres aleatoires
			 * Un nombre ne se trouvant pas dans le vecteur aleatoire peut etre produit
			 * selon une probabilite predefinie par PROB_INVALID
			 * @param vecOri Vecteur de nombres aleatoires
			 * @param compteur Valeur utilisee pour choisir le nombre
			 * @return Valeur extraite du vecteur aleatoire ou valeur introuvable
			 */
			public static long elemRecherche(long[] vecOri, int compteur) {
				int pos = (int) ((99881L*compteur)%DIM_TEST);
				long valeur = vecOri[pos];
				if (pos%PROB_INVALID==0) {
					valeur++;
				}
				return valeur;
			}
		

}
