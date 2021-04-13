package AugustinMarlondTp2;

import java.util.*;

public class Exercices2 {

	public static final byte NB_JOURS_SEMAINE = 7;

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		char choix;
		int[][] unMois = null;
		int mois = -1, annee = -1;
		int jourSem = 0, posRel = 0, leJour;

		do {
			System.out.print("MENU PRINCIPAL");
			if (mois > 0) {
				System.out.print(" (" + mois + "-" + annee + ")");
			}
			System.out.println();
			System.out.println("----------------------------");
			System.out.println("G - Genere un mois");
			if (mois > 0) {
				System.out.println("M - Affiche le mois genere");
				System.out.println("S - Indique le nombre de semaines completes");
				System.out.println("F - Indique le nombre de fin de semaines completes");
				System.out.println("R - Trouve la date d'un jour relatif");
			}
			System.out.println("A - Affiche le calendrier annuel (BONUS)");
			System.out.println("Q - Quitter");
			System.out.print("--> Votre choix : ");
			choix = clavier.next().toUpperCase().charAt(0);
			switch (choix) {
			case 'G':
				System.out.print("Indiquez le numero du mois suivi de l'annee: ");
				mois = clavier.nextInt();
				annee = clavier.nextInt();
				unMois = genereMois(mois, annee);
				break;
			case 'M':
				afficheMois(unMois);
				break;
			case 'F':
				System.out.println("Nombre de fin de semaines : " + nbFinSemaines(unMois));
				break;
			case 'S':
				System.out.println("Nombre de semaines : " + nbSemaines(unMois));
				break;
			case 'R':
				System.out.print("Indiquez le jour de la semaine \n" + "(0:dimanche, 1:lundi, ... 6:samedi) : ");
				jourSem = clavier.nextInt();
				System.out.print(
						"Indiquez la position relative du jour \n" + "(1:premier, 2:deuxieme, -1:dernier, etc) : ");
				posRel = clavier.nextInt();
				leJour = trouveJour(unMois, jourSem, posRel);
				if (leJour > 0) {
					System.out.println("Le jour recherche est le " + leJour);
				} else {
					System.out.println("Aucun jour trouve avec ces specifications!");
				}
				break;
			case 'A':
				System.out.print("Indiquez l'annee voulue : ");
				afficheAnnee(clavier.nextInt());
				break;
			}
			System.out.println("----------------------------");
		} while (choix != 'Q');
		System.out.println("Au revoir!");
	}

	/**
	 * Permet de trouver le jour de la semaine correspondant a une date donnee
	 * 
	 * @param jour  Jour de la date (1 - 31)
	 * @param mois  Mois de la date (1 - 12)
	 * @param annee Annee de la date (XXXX)
	 * @return Jour de la semaine correspondant (0:dimanche, ..., 6:samedi)
	 */
	public static int jourSemaine(int jour, int mois, int annee) {
		int jourSemaine, z;
		if (mois <= 2) {
			z = annee - 1;
			jourSemaine = (23 * mois / 9 + jour + 4 + annee + z / 4 - z / 100 + z / 400) % 7;
		} else {
			z = annee;
			jourSemaine = (23 * mois / 9 + jour + 4 + annee + z / 4 - z / 100 + z / 400 - 2) % 7;
		}
		return jourSemaine;
	}

	/**
	 * Calcule le nombre de jours pour un mois donne
	 * 
	 * @param mois  Mois recherche
	 * @param annee Annee, car fevrier peut etre bisextile
	 * @return Nombre de jours dans le mois
	 */
	public static int joursParMois(int mois, int annee) {
		int[] nbJours = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if ((annee % 4 == 0 && annee % 100 != 0) || (annee % 400 == 0)) {
			nbJours[1]++;
		}
		return nbJours[mois - 1];
	}

	/**
	 * Cree un tableau 2D contenant la liste des jours du mois demande en parametre.
	 * Le tableau contient 1 colonne pour chaque jour de la semaine et autant de
	 * lignes que necessaire pour stocker tous les jours du mois. La premiere
	 * colonne (indice 0) correspond a dimanche
	 * 
	 * @param mois  Numero du mois a generer (1 a 12)
	 * @param annee Annee du mois a generer
	 * @return Tableau contenant les dates du mois, et 0 pour les jours sans date
	 */
	public static int[][] genereMois(int mois, int annee) {
		int premierJour = jourSemaine(1, mois, annee);
		int joursMois = joursParMois(mois, annee);
		int nbSem = (int) Math.ceil((joursMois + premierJour) / 7.0f);
		int[][] cal = new int[nbSem][NB_JOURS_SEMAINE];
		int index = premierJour;
		for (int i = 1; i <= joursMois; ++i) {
			cal[index / 7][index % 7] = i;
			index++;
		}
		return cal;
	}

	/**
	 * Affiche le calendrier du mois passe en argument avec les jours de la semaine
	 * en en-tete
	 * 
	 * @param calMois Tableau/calendrier du mois a afficher
	 */
	public static void afficheMois(int[][] calMois) {
		// TODO: Afficher le calendrier a la console en respectant
		// le format de l'exemple
// Exemple d'affichage pour 04-2009
// Di  Lu  Ma  Me  Je  Ve  Sa
// --------------------------
//              1   2   3   4  
//  5   6   7   8   9  10  11  
// 12  13  14  15  16  17  18  
// 19  20  21  22  23  24  25 
// 26  27  28  29  30        
		System.out.println("  Di Lu Ma Me Je Ve Sa");
		System.out.print("----------------------------");
		for (int i = 0; i < calMois.length; i++) {
			System.out.println("");
			for (int j = 0; j < calMois[i].length; j++) {
				if (calMois[i][j] == 0) {
					System.out.print(String.format("%3s", (" ")));
				}
				else {
					System.out.print(String.format("%3d", calMois[i][j]));
				}	
			}
			
		}
		System.out.println("");
	}

	/**
	 * Calcule le nombre de semaines completes dans le mois Une semaine est complete
	 * lorsque son lundi et vendredi se retrouvent dans le meme mois
	 * 
	 * @param calMois Tableau/calendrier du mois courant
	 * @return Nombre de semaines completes
	 */
	public static int nbSemaines(int[][] calMois) {
		// TODO: Calculer le nombre de semaines completes
		int compte = 0;

		for (int i = 0; i < calMois.length; i++) {
			for (int j = 0; j < calMois[i].length; j++) {
				if (calMois[i][j] != 0) {
					compte++;
				}
			}
		}

		return (compte / 7);
	}

	/**
	 * Calcule le nombre de fin de semaines completes dans le mois Une fin de
	 * semaine est complete lorsque le samedi et le dimanche se trouvent dans le
	 * meme mois
	 * 
	 * @param calMois Tableau/calendrier du mois courant
	 * @return Nombre de fin de semaines completes
	 */
	public static int nbFinSemaines(int[][] calMois) {
		// TODO: Calculer le nombre de fin de semaines completes
		// TODO: Ecrire des tests unitaires
		int compte = 0, tmp=1;
		for (int i = 0; i < calMois.length; i++) {
				if (calMois[i][6] != 0 && calMois[tmp][0] != 0) {
					compte++;
				}
			}
		
		return compte;
	}

	/**
	 * Fonction qui recherche la date du jour correspondant a la position realtive
	 * recue en parametre
	 * 
	 * @param calMois Tableau/calendrier du mois dans lequel faire la recherche
	 * @param jour    Jour de la semaine recherche (0:dimanche, ..., 6:samedi)
	 * @param pos     Position relative du jour (1: premier, 2: second, -1:dernier,
	 *                etc)
	 * @return La date du jour recherche ou -1 si non trouve
	 */
	public static int trouveJour(int[][] calMois, int jour, int pos) {
		// TODO: Trouver le jour correspondant a la specification
		// Exemple: Le premier lundi d'avril 2009 est le 6 (jour=1, pos=1)
		// Exemple: L'avant dernier vendredi d'avril 2009 est le 17 (jour=5, pos=-2)
		// Traiter separement les pos positives et negatives
		// TODO: Ecrire des tests unitaires
		int jourTrouve = -1;
		int i = 0;
		int nbSemaine = nbFinSemaines(calMois);
		if (calMois.length == 5) {
			if (pos == 1) {
				i = 0;
				jourTrouve = calMois[i][jour];
			} else if (pos == 2) {
				i = 1;
				jourTrouve = calMois[i][jour];
			} else if (pos == -2) {
				i = nbSemaine - 2;
				jourTrouve = calMois[i][jour];
			} else if (pos == -1) {
				i = nbSemaine - 1;
				;
				jourTrouve = calMois[i][jour];
			} else {
				i = nbSemaine - 3;
				jourTrouve = calMois[i][jour];
			}
		} else {
			if (pos == 1) {
				i = 0;
				jourTrouve = calMois[i][jour];
			}
			if (pos == 2) {
				i = 1;
				jourTrouve = calMois[i][jour];
			}
			if (pos == -2) {
				i = nbSemaine - 2;
				jourTrouve = calMois[i][jour];
			}
			if (pos == -1) {
				i = nbSemaine - 1;
				jourTrouve = calMois[i][jour];
			}
		}

		return jourTrouve;
	}

	/**
	 * Affiche le calendrier de chacun des mois d'une annee sous forme d'une table
	 * de 3 lgines de 4 mois.
	 * 
	 * @param annee Annee a afficher
	 */
	public static void afficheAnnee(int annee) {
		int[][] unMois = null;
		int[][] unMois2 = null;
		int[][] unMois3 = null;
		int[][] unMois4 = null;
		int[][] unMois5 = null;
		int[][] unMois6 = null;
		int[][] unMois7 = null;
		int[][] unMois8 = null;
		int[][] unMois9 = null;
		int[][] unMois10 = null;
		int[][] unMois11 = null;
		int[][] unMois12 = null;
		System.out.println();
		unMois = genereMois(1, annee);
		unMois2 = genereMois(2, annee);
		unMois3 = genereMois(3, annee);
		unMois4 = genereMois(4, annee);
		affiche4Mois(unMois, unMois2, unMois3, unMois4);
		unMois5 = genereMois(5, annee);
		unMois6 = genereMois(6, annee);
		unMois7 = genereMois(7, annee);
		unMois8 = genereMois(8, annee);
		affiche4Mois(unMois5, unMois6, unMois7, unMois8);
		unMois9 = genereMois(9, annee);
		unMois10 = genereMois(10, annee);
		unMois11 = genereMois(11, annee);
		unMois12 = genereMois(12, annee);
		affiche4Mois(unMois9, unMois10, unMois11, unMois12);
		// TODO: BONUS: Afficher le calendrier d'une annee en format 3x4
		// Appeler la fonction affiche4Mois pour chaque "ligne"
// Exemple d'Affichage pour 2009
// Janvier 2009                   Fevrier 2009                   Mars 2009                      Avril 2009                     
// Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa
// --------------------------     --------------------------     --------------------------     --------------------------
//                  1   2   3      1   2   3   4   5   6   7      1   2   3   4   5   6   7                  1   2   3   4  
//  4   5   6   7   8   9  10      8   9  10  11  12  13  14      8   9  10  11  12  13  14      5   6   7   8   9  10  11  
// 11  12  13  14  15  16  17     15  16  17  18  19  20  21     15  16  17  18  19  20  21     12  13  14  15  16  17  18  
// 18  19  20  21  22  23  24     22  23  24  25  26  27  28     22  23  24  25  26  27  28     19  20  21  22  23  24  25  
// 25  26  27  28  29  30  31                                    29  30  31                     26  27  28  29  30          
//
// Mai 2009                       Juin 2009                      Juillet 2009                   Aout 2009                      
// Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa
// --------------------------     --------------------------     --------------------------     --------------------------
//                      1   2          1   2   3   4   5   6                  1   2   3   4                              1  
//  3   4   5   6   7   8   9      7   8   9  10  11  12  13      5   6   7   8   9  10  11      2   3   4   5   6   7   8  
// 10  11  12  13  14  15  16     14  15  16  17  18  19  20     12  13  14  15  16  17  18      9  10  11  12  13  14  15  
// 17  18  19  20  21  22  23     21  22  23  24  25  26  27     19  20  21  22  23  24  25     16  17  18  19  20  21  22  
// 24  25  26  27  28  29  30     28  29  30                     26  27  28  29  30  31         23  24  25  26  27  28  29  
// 31                                                                                           30  31                      
//
// Septembre 2009                 Octobre 2009                   Novembre 2009                  Decembre 2009                  
// Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa
// --------------------------     --------------------------     --------------------------     --------------------------
//          1   2   3   4   5                      1   2   3      1   2   3   4   5   6   7              1   2   3   4   5  
//  6   7   8   9  10  11  12      4   5   6   7   8   9  10      8   9  10  11  12  13  14      6   7   8   9  10  11  12  
// 13  14  15  16  17  18  19     11  12  13  14  15  16  17     15  16  17  18  19  20  21     13  14  15  16  17  18  19  
// 20  21  22  23  24  25  26     18  19  20  21  22  23  24     22  23  24  25  26  27  28     20  21  22  23  24  25  26  
// 27  28  29  30                 25  26  27  28  29  30  31     29  30                         27  28  29  30  31
	}

	/**
	 * Affiche les calendriers de 4 mois cote a cote (sur une meme "ligne") Le
	 * nombre de lignes a reserver pour l'affichage correspond au nombre maximum de
	 * semaines parmi les 4 calendriers
	 * 
	 * @param cal1 Tableau/calendrier du premier mois a afficher
	 * @param cal2 Tableau/calendrier du aecond mois a afficher
	 * @param cal3 Tableau/calendrier du troisieme mois a afficher
	 * @param cal4 Tableau/calendrier du quatrieme mois a afficher
	 */
	public static void affiche4Mois(int[][] cal1, int[][] cal2, int[][] cal3, int[][] cal4) {
		// TODO: BONUS - Afficher une "ligne" de 4 calendriers

		System.out.println("  Di Lu Ma Me Je Ve Sa" + "  	Di Lu Ma Me Je Ve Sa" + "  	     Di Lu Ma Me Je Ve Sa"
				+ "  	    Di Lu Ma Me Je Ve Sa");
		System.out.println("--------------------------  " + " --------------------------  "
				+ "   -----------------------  " + "     -----------------------  ");
		for (int i = 0; i < 6; i++) {
			if (i > cal1.length - 1) {
				System.out.print(String.format("%21s", (" ")));
			} else {
				for (int j = 0; j < cal1[i].length; j++) {
					if (cal1[i][j] == 0) {
						System.out.print(String.format("%3s", (" ")));
					} else {
						System.out.print(String.format("%3d", cal1[i][j]));
					}
				}
			}
			System.out.print("          ");
			if (i > cal2.length - 1) {
				System.out.print(String.format("%21s", (" ")));
			} else {
				for (int k = 0; k < cal2[i].length; k++) {

					if (cal2[i][k] == 0) {
						System.out.print(String.format("%3s", (" ")));
					} else {
						System.out.print(String.format("%3d", cal2[i][k]));
					}
				}
			}
			System.out.print("         ");
			if (i > cal3.length - 1) {
				System.out.print(String.format("%21s", (" ")));
			} else {
				for (int l = 0; l < cal3[i].length; l++) {
					if (cal3[i][l] == 0) {
						System.out.print(String.format("%3s", (" ")));
					} else {
						System.out.print(String.format("%3d", cal3[i][l]));
					}

				}
			}
			System.out.print("          ");
			if (i > cal4.length - 1) {
				System.out.print(String.format("%21s", (" ")));
			} else {
				for (int m = 0; m < cal4[i].length; m++) {
					if (cal4[i][m] == 0) {
						System.out.print(String.format("%3s", (" ")));
					} else {
						System.out.print(String.format("%3d", cal4[i][m]));

					}
				}

			}
			System.out.println("");
		}

		/*
		 * 
		 * afficheMois(cal1); afficheMois(cal2); afficheMois(cal3); afficheMois(cal4);
		 */
// Exemple d'affichage
// Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa     Di  Lu  Ma  Me  Je  Ve  Sa
// --------------------------     --------------------------     --------------------------     --------------------------
//                  1   2   3      1   2   3   4   5   6   7      1   2   3   4   5   6   7                  1   2   3   4  
//  4   5   6   7   8   9  10      8   9  10  11  12  13  14      8   9  10  11  12  13  14      5   6   7   8   9  10  11  
// 11  12  13  14  15  16  17     15  16  17  18  19  20  21     15  16  17  18  19  20  21     12  13  14  15  16  17  18  
// 18  19  20  21  22  23  24     22  23  24  25  26  27  28     22  23  24  25  26  27  28     19  20  21  22  23  24  25  
// 25  26  27  28  29  30  31                                    29  30  31                     26  27  28  29  30          
	}

}
