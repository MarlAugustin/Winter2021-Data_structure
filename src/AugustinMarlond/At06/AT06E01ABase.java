package AugustinMarlond.At06;

import java.awt.Color;
import Commun.FenetreDessin;

public class AT06E01ABase {
	public static void main(String[] args) {
		FenetreDessin fd = new FenetreDessin("Puissance 4", 640, 480);
		char[][] grille = new char[6][7];
		boolean joueur1 = true;
		boolean gagnant = false;
		char charLu = 0;

		fd.imageDessinDirect(false);
		do {
			fd.animationAttendreProchaineImage();

			// Affichage du jeu
			fd.effaceFenetre();
			dessineJeu(fd, grille, joueur1);
			if (gagnant) {
				fd.couleurRemplissage(Color.WHITE);
				fd.dessineRectanglePlein(558, 230, 632, 250);
				fd.dessineTexte(560, 248, "Gagnant");
			}
			fd.imageRafraichir();

			// Traitement des interactions
			if (fd.clavierEvenement()) {
				charLu = fd.clavierCharactere();
				if (charLu == 'R') { // Reinitialiser le jeu
					viderGrille(grille);
					joueur1 = true;
					gagnant = false;
				}
			}
			if (fd.sourisEvenement()) {
				// Si on clic dans la zone de jeu et qu'il n'y a pas de gagnant
				if (!gagnant && fd.sourisBouton() == 1 && fd.sourisPositionX() >= 25 && fd.sourisPositionX() <= 550) {
					if (ajouteJeton(grille, (fd.sourisPositionX() - 25) / 75, joueur1)) {
						// Si un jeton a ete ajoute, on verifie s'il y a un gagnant
						if (unGagnant(grille)) {
							gagnant = true;
						} else {
							joueur1 = !joueur1; // Pas de gagnant, prochain joueur...
						}
					}
				}
			}
		} while (charLu != 'Q');
	}

	/**
	 * Dessine la fenetre de jeu et les jetons des joueurs Le jetons en position
	 * [0][0] dans la grille se retrouve en haut a gauche de l'ecran
	 * 
	 * @param fd     Pointeur vers la fenetre principale
	 * @param grille Grille contenant la position des jetons
	 * @param j1     Indique si c'est le tour au premier joueur (true) ou au second
	 *               (false)
	 */
	public static void dessineJeu(FenetreDessin fd, char[][] grille, boolean j1) {
		fd.dessineSegment(25, 470, 550, 470);
		for (int i = 0; i <= 7; ++i) {
			fd.dessineSegment(25 + i * 75, 20, 25 + i * 75, 470);
		}
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 7; ++j) {
				if (grille[i][j] != 0) {
					switch (grille[i][j]) {
					case 'R':
						fd.couleurRemplissage(Color.RED);
						break;
					case 'B':
						fd.couleurRemplissage(Color.BLUE);
						break;
					}
					fd.dessineCerclePlein(62 + j * 75, 57 + i * 75, 35);
				}
			}
		}
		if (j1) {
			fd.couleurRemplissage(Color.RED);
		} else {
			fd.couleurRemplissage(Color.BLUE);
		}
		fd.dessineCerclePlein(595, 240, 35);
		fd.couleurRemplissage(Color.WHITE);
	}

	/**
	 * Verifie s'il y a un gagnant, soit 4 jetons de la meme couleur alignes Appelle
	 * les fonctions pour verifier les lignes, les colonnes et les diagonales
	 * 
	 * @param grille Grille contenant la position des jetons
	 * @return true si un gagnant a ete detecte, false sinon
	 */
	public static boolean unGagnant(char[][] grille) {
		boolean gagne = false;
		gagne |= verifierLignes(grille);
		gagne |= verifierColonnes(grille);
		gagne |= verifierDiagonales(grille);
		return gagne;
	}

	/**
	 * Retire tous les jetons de la grille en mettant les valeurs a 0
	 * 
	 * @param grille Grille contenant la position des jetons
	 */
	public static void viderGrille(char[][] grille) {
		// TODO: Remettre toutes les cases de la grille a 0
		// TODO: Tests unitaires
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				grille[i][j] = 0;
			}
		}
	}

	/**
	 * Ajoute un jeton dans la colonne choisie pour le joueur actuel, si possible Il
	 * n'est pas possible d'ajouter un jeton si la colonne est deja pleine
	 * 
	 * @param grille  Grille contenant la position des jetons
	 * @param colonne Colonne dans laquelle le jeton doit etre ajoute
	 * @param j1      Joueur actuel: true-Premier joueur (rouge), false-Second
	 *                joueur (bleu)
	 * @return true si le jeton a ete ajoute, false sinon
	 */
	public static boolean ajouteJeton(char[][] grille, int colonne, boolean j1) {
		boolean ajout = false;
		// TODO: S'assurer qu'il reste de la place dans la colonne
		// TODO: Ajouter le jeton dans la colonne (joueur 1: R, joueur 2: B)
		// TODO: Tests unitaires
		int i = grille.length - 1;
		while (grille[i][colonne] != 0 && i >= 0) {
			--i;
		}
		if (i >= 0) {

			if (j1) {
				grille[i][colonne] = 'R';
			} else {
				grille[i][colonne] = 'B';
			}
			ajout = true;
		}
		return ajout;
	}

	/**
	 * Verifie s'il y a 4 jetons adjacents d'un meme joueur sur une meme ligne
	 * 
	 * @param grille Grille contenant la position des jetons
	 * @return true si un gagnant a ete detecte, false sinon
	 */
	public static boolean verifierColonnes(char[][] grille) {
		boolean trouve = false;
		int compteJetonR = 0;
		int compteJetonB = 0;
		// TODO: Verifier si 4 jetons d'une meme couleur se retrouvent sur une ligne
		// TODO: Tests unitaires
		for (int j = 0; j < grille[0].length - 3; j++) {
			compteJetonR = 0;
			compteJetonB = 0;
			for (int i = 0; i < grille.length ; i++) {
			/**	Ma version
			 * if (grille[i][j] == 'R') {
					compteJetonR++;
				} else {
					compteJetonR = 0;
				}
				if (grille[i][j] == 'B') {
					compteJetonB++;
				} else {
					compteJetonB = 0;
				}
				if (compteJetonR == 4) {
					trouve = true;
				}
				if (compteJetonB == 4) {
					trouve = true;
				}*/
				//version du prof de Zein
				if(grille[i][j]==grille[i][j+1] &&grille[i][j+1]==grille[i][j+2] && grille[i][j+2]==grille[i][j+3]&& grille[i][j]=='R') {
					trouve=true;
				}
				if(grille[i][j]==grille[i][j+1] &&grille[i][j+1]==grille[i][j+2] && grille[i][j+2]==grille[i][j+3] && grille[i][j]=='B') {
					trouve=true;
				}
			}
		}
		return trouve;
	}

	/**
	 * Verifie s'il y a 4 jetons adjacents d'un meme joueur sur une meme colonne
	 * 
	 * @param grille Grille contenant la position des jetons
	 * @return true si un gagnant a ete detecte, false sinon
	 */
	public static boolean verifierLignes(char[][] grille) {
		boolean trouve = false;
		int compteJetonR = 0;
		int compteJetonB = 0;
		// TODO: Verifier si 4 jetons d'une meme couleur se retrouvent dans une colonne
		// TODO: Tests unitaires
		for (int i = 0; i < grille.length - 3; i++) {
			compteJetonR = 0;
			compteJetonB = 0;
			for (int j = 0; j < grille[i].length; j++) {
				/**if (grille[i][j] == 'R') {
					compteJetonR++;
				} else {
					compteJetonR = 0;
				}
				if (grille[i][j] == 'B') {
					compteJetonB++;
				} else {
					compteJetonB = 0;
				}
				if (compteJetonR == 4) {
					trouve = true;
				}
				if (compteJetonB == 4) {
					trouve = true;
				}*/
				//version du prof de Zein
				if(grille[i][j]==grille[i+1][j] &&grille[i+1][j]==grille[i+2][j] && grille[i+2][j]==grille[i+3][j]&& grille[i][j]=='R') {
					trouve=true;
				}
				if(grille[i][j]==grille[i+1][j] &&grille[i+1][j]==grille[i+2][j] && grille[i+2][j]==grille[i+3][j] && grille[i][j]=='B') {
					trouve=true;
				}

			}
		}
		return trouve;
	}

	/**
	 * Verifie s'il y a 4 jetons adjacents d'un meme joueur en diagonale NOTE: Il y
	 * a deux types de diagonales a verifier : \ et /
	 * 
	 * @param grille Grille contenant la position des jetons
	 * @return true si un gagnant a ete detecte, false sinon
	 */
	public static boolean verifierDiagonales(char[][] grille) {
		boolean trouve = false;
		int compteJetonR = 0;
		int compteJetonB = 0;
		// TODO: Verifier si 4 jetons d'une meme couleur se retrouvent sur une diagonale
		// \
		// TODO: Verifier si 4 jetons d'une meme couleur se retrouvent sur une diagonale
		// /
		// TODO: Tests unitaires
		
		//diagonale \
		for (int i = 0; i < grille.length - 3; i++) {
			for (int j = 0; j < grille.length - 2; j++) {
				if (grille[i][j] == grille[i + 1][j + 1] && grille[i + 1][j + 1] == grille[i + 2][j + 2]
						&& grille[i + 2][j + 2] == grille[i + 3][j + 3] && grille[i][j] !=0)
				{
					trouve = true;
				}
			}
		}
		//diagonale /
			for (int i = 3; i < grille.length ; i++) {
			for (int j = 0; j < grille.length - 2; j++) {
			if (grille[i][j] == grille[i - 1][j + 1] && grille[i - 1][j + 1] == grille[i - 2][j + 2]
						&& grille[i - 2][j + 2] == grille[i - 3][j + 3] && grille[i][j] !=0
						) {
					trouve = true;
				}
			}
		}

		return trouve;
	}

}
