package AugustinMarlond.At04;

import java.util.Scanner;

public class introductionTableaux {

	public static void main(String[] args) {

		
				Scanner clavier = new Scanner(System.in);
				int choix;
				float moyenne = -1;
				String[] plages = null;
				int[] donneesJour = null;
				int[][] donneesJours = null;

				do {
					System.out.println("-------------------------");
					System.out.println("Menu principal");
					System.out.println("-------------------------");
					System.out.println("1 - Initialiser les tableaux pour une journee");
					System.out.println("2 - Entrer les donnees pour une journee");
					System.out.println("3 - Afficher les donnees pour chaque plage");
					System.out.println("4 - Moyenne des donnees de la journee");
					System.out.println("5 - Minimum et maximum de la journee");
					System.out.println("-------------------------");
					System.out.println("6 - Initialiser le tableau pour plusieurs jours");
					System.out.println("7 - Entrer les donnes pour un ou tous les jours");
					System.out.println("8 - Afficher la matrice des donnees");
					System.out.println("9 - Verifier si la matrice est reguliere");
					System.out.println("10- Calculer la moyenne pour un jour");
					System.out.println("-------------------------");
					System.out.println("0 - Quitter");
					System.out.print("\nVotre choix ---> ");
					choix = clavier.nextInt();
					switch (choix) {
					case 1:
						// TODO: Initialiser le tableau donneesJour
						// TODO: Initialiser le tableau plages
						// TODO: Appeler la fonction initialisePlages
						System.out.print("Indiquez le nombre de plages: ");
						choix = clavier.nextInt();
						clavier.nextLine();
						plages = new String[choix];
					    donneesJour = new int[choix];
						initialisePlages(clavier, plages);
					
						break;
					case 2:
						// TODO: Appeler la fonction entrerDonneesJour
						entrerDonneesJour(clavier, donneesJour, plages);
						break;
					case 3:
						// TODO: Appeler la fonction afficherDonneesJour
						afficherDonneesJour( donneesJour, plages);
						break;
					case 4:
						// TODO: Appeler calculeMoyenne et recuperer la moyenne
						moyenne=calculeMoyenne(donneesJour);
						if (!Float.isNaN(moyenne)) {
							System.out.println("La moyenne de la journee est : " + moyenne);
						}
						break;
					case 5:
						// TODO: s'assurer que donneesJour n'est pas null
						// Ne pas oublier d'implementer trouvePosMin et trouvePosMax
						if(donneesJour!=null && plages !=null ) {
							System.out.println("La valeur minimale de la journee est : " 
									+ donneesJour[trouvePosMin(donneesJour)]);
							System.out.println("La valeur maximale de la journee est : " 
									+ donneesJour[trouvePosMax(donneesJour)]);
						}
							
						break;
					case 6:
						// TODO: Appeler initialiserMatrice afin d'initialiser la matrice donneesJours
						initialiserMatrice(clavier);
						break;
					case 7:
						// TODO: Appeler entrerDonneesJours
						break;
					case 8:
						// TODO: Appeler afficherMatrice
						break;
					case 9:
						// TODO: Selon le resultat de l'appel de matriceReguliere afficher si
						// la matrice est reguliere ou irreguliere
						break;
					case 10:
						System.out.print("Indiquez le jour : ");
						choix = clavier.nextInt();
						// TODO: Appeler calculerMoyenneJour et recuperer la moyenne
						if (!Float.isNaN(moyenne)) {
							System.out.println("La moyenne de la journee est : " + moyenne);
						}
						break;
					}
				} while (choix != 0);
				System.out.println("A la prochaine");
			}

			public static void initialisePlages(Scanner cl, String[] plages) {
				// TODO: Demander le nom de chaque plage a l'usager et les sauvegarder dans le tableau plages
				// Faire les lectures des noms avec cl.nextLine()
				for (int i=0;i<plages.length;i++) {
					System.out.println("Indiquez la plage # "+(i+1)+" :");
					plages [i]=cl.nextLine();
				}
			}

			public static void entrerDonneesJour(Scanner cl, int[] donneesVec, String[] plages) {
				// TODO: S'assurer que les deux tableaux ne sont pas null
				// TODO: Demander et lire les donnees pour chaque plage dans donneesVec
				// Si les tailles des tableaux plages et donneesVec sont identiques, on identifie la plage
				// par son nom lorsque l'on demande une donnee, sinon on numerote les plages a partir de 1
				if(donneesVec!=null && plages !=null ) {
				for (int i=0;i<donneesVec.length;i++) {
				System.out.println("Inscrivez la donnee pour la plage " + plages[i] +" :");
				donneesVec[i]=cl.nextInt() ;
				}
			}
			}
			public static void afficherDonneesJour(int[] donneesVec, String[] plages) {
				// TODO: S'assurer que les deux tableaux ne sont pas null
				// TODO: Afficher la donnee associee a chaque plage
				// Si les tailles des tableaux plages et donneesVec sont identiques, on identifie la plage
				// par son nom lorsque l'on affiche une donnee, sinon on numerote les plages a partir de 1
				if(donneesVec!=null && plages !=null ) {
					for (int i=0;i<donneesVec.length;i++) {
					System.out.println("Donnee pour la plage " + plages[i] +" : " + donneesVec[i]);
					}
				}
				
			}

			// TODO: Faire des tests unitaires pour cette fonction
			public static float calculeMoyenne(int[] donneesVec) {
				float moyenne = Float.NaN;
				int somme=0;
				for (int i=0;i<donneesVec.length;i++) {
					somme+=donneesVec[i];
				}
				moyenne=(somme/donneesVec.length);
				
				// TODO: Calculer la moyenne si donneesVec n'est pas null
				return moyenne;
			}

			public static int trouvePosMin(int[] donneesVec) {
				int posMin = 0;
				// TODO: Identifier l'indice de la valeur minimale
				// On considere que le tableau donneesVec n'est jamais null
				
				for (int i = 1; i < donneesVec.length; ++i) { 
					if (donneesVec[i] < donneesVec[ posMin ])
						posMin = i; 
				}
				
				return posMin;
			}

			// TODO: Faire des tests unitaires pour cette fonction
			public static int trouvePosMax(int[] donneesVec) {
				int posMax = 0;
				// TODO: Identifier l'indice de la valeur maximale
				// On considere que le tableau donneesVec n'est jamais null
				for (int i = 1; i < donneesVec.length; ++i) { 
					if (donneesVec[i] > donneesVec[ posMax ])
						posMax = i; 
				}
				return posMax;
			}

			public static int[][] initialiserMatrice(Scanner cl) {
				int[][] matrice = null;
				int valeur;

				System.out.print("Indiquez le nombre de jours : ");
				valeur = cl.nextInt();
				// TODO: initialise matrice avec le nombre de ligne indique par l'usager
				int [][] matrice = new int [valeur] [valeur];
				
				for (int i = 0; i < matrice.length; ++i) {  // Pour chaque ligne
					System.out.print("Entrez le nombre de plages pour le jour " + (i + 1) + " : ");
					valeur = cl.nextInt();
					
					// TODO: Initialise une ligne de matrice avec le nombre de colonnes specifiees
				}
				return matrice;
			}

			public static void entrerDonneesJours(Scanner cl, int[][] donneesMat) {
				int valeur;
				// TODO: Demander les donnees seulement si la matrice n'est pas null
					System.out.print("Indiquez le jour (0 pour tous) : ");
					valeur = cl.nextInt();
					if (valeur == 0) {
						// TODO: Demander les donnees pour tous les jours
						// Vous pouvez appeler entrerDonneesJour pour chaque ligne de la matrice
						// Inscrivez null pour le parametree correspondant au tableau des noms des plages
					} else if (valeur > 0 && valeur <= donneesMat.length) {
						// TODO: Demander les donnees pour la journee specifiee
						// Vous pouvez appeler entrerDonneesJour pour la ligne choisie de la matrice
						// Inscrivez null pour le parametree correspondant au tableau des noms des plages
					}
			}

			public static void afficherMatrice(int[][] matrice) {
				// TODO: si matrice n'est pas null, l'afficher
			}

			// TODO: Faire des tests unitaires pour cette fonction
			public static boolean matriceReguliere(int[][] matrice) {
				boolean reguliere = true;
				// TODO: si la matrice est null ou a une taille de 0, elle n'est pas reguliere
				// TODO: Sinon, verifier si toutes les lignes ont la meme longueur
				return reguliere;
			}

			// TODO: Faire des tests unitaires pour cette fonction
			public static float calculerMoyenneJour(int[][] matrice, int ligne) {
				float moyenne = Float.NaN;
				// TODO: Si la matrice n'est pas null et que la ligne demandee existe
				// calculez la moyenne de la ligne.  Vous pouvez utiliser calculeMoyenne
				return moyenne;
			

	}

}
