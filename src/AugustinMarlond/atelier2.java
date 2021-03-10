package AugustinMarlond;
import java.util.Random;
import java.util.Scanner;

public class atelier2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner clavier = new Scanner(System.in);
		int choix, val1, val2, val3;

		do {
			// menu d'affichage qui demande � l'utilisateur son choix
			System.out.println("Menu principal");
			System.out.println("-------------------------");
			System.out.println("1 - Calcul moyenne");
			System.out.println("2 - Determine ordre");
			System.out.println("3 - Exposant maximal");
			System.out.println("4 - Calcul somme");
			System.out.println("5 - Exercice multiplication");
			System.out.println("6 - Table multiplication");
			System.out.println("7 - Conversion d'une note");
			System.out.println("9 - Quitter");
			System.out.println(" ");
			System.out.print("Votre choix ?:");
			choix = clavier.nextInt();
			switch (choix) {
			case 1:
				System.out.println("Entrez 3 valeurs separees par des espaces :");
				val1 = clavier.nextInt();
				val2 = clavier.nextInt();
				val3 = clavier.nextInt();
				float r�ponse = moyenne(val1, val2, val3);
				System.out.println("La moyenne vaut " + r�ponse);
				break;
			case 2:
				System.out.println("Entrez 3 valeurs separees par des espaces :");
				val1 = clavier.nextInt();
				val2 = clavier.nextInt();
				val3 = clavier.nextInt();
				String r�ponse2 = ordre(val1, val2, val3);
				System.out.println("Valeurs en ordre " + r�ponse2);
				break;
			case 3:
				System.out.println("Entrez une base :");
				int base = clavier.nextInt();
				System.out.println("Entrez la valeur recherchee :");
				int valeurRecherchee = clavier.nextInt();
				int exposant = expMax(base, valeurRecherchee);
				System.out.println(
						"La valeur la plus pres de la valeur recherchee est :" + (int) Math.pow(base, exposant));
				System.out.println("en utilisant la base " + base + " et l'exposant " + exposant);
				break;
			case 4:
				System.out.println("Calcule la somme des n premiers entiers. Inscrivez n :");
				int n = clavier.nextInt();
				int resultat4 = somme(n);
				System.out.println("La somme des " +n + " premiers entiers vaut " + resultat4);

				break;
			case 5:
				System.out.println("Inscrivez la valeur maximale voulue :");
				int valeurMax = clavier.nextInt();
				exMult(clavier, valeurMax);
				break;
			case 6:
				System.out.println("Indiquez la valeur maximale de la table :");
				int valeurMaxTableau = clavier.nextInt();
				while (valeurMaxTableau > 25) {
					System.out.println("la valeur maximale, qui doit �tre comprise entre 1 et 25 inclusivement");
					System.out.println("Indiquez une nouvelle valeur maximale de la table :");
					valeurMaxTableau = clavier.nextInt();
				}
				tableMult(valeurMaxTableau) ;
				break;
			case 7:
				System.out.println("Entrez la note en pourcentage :");
				int notePourcentage=clavier.nextInt() ;
				char noteEnLettre=conversionNote (notePourcentage);
				System.out.println("La note en lettre est: " + noteEnLettre );
				break;
			}
			System.out.println("--------------------------------");
			System.out.println(" ");
		} while (choix != 9);
		System.out.println("Au revoir");
		clavier.close();
	}

	public static float moyenne(int nb1, int nb2, int nb3) {
		// Cette fonction permet de calculer la moyenne des chiffres inscrits par
		// l'usager
		float moyenneChiffre;
		moyenneChiffre = ((float) (nb1 + nb2 + nb3) / 3);
		return moyenneChiffre;
	}

	public static String ordre(int nb1, int nb2, int nb3) {
		String ordreDesNombres;
		  if (nb1 == nb2 && nb2 == nb3) {
			ordreDesNombres = ("constant");
			//si les chiffres sont �gaux on affiche constant
		}else if ((nb1 <= nb2 && nb2 <= nb3)||(nb1 < nb2 && nb2 == nb3)||(nb1 == nb2 && nb2 <nb3)) {
			ordreDesNombres = ("croissant");
			// si le nb 1 est plus petit que nb 2 et que nb3 est plus grand que nb 2
			// ordreDesNombres re�oit la valeur "croissant"
		} else if ((nb1 >= nb2 && nb2 >= nb3)||(nb1 > nb2 && nb2 == nb3)||(nb1 == nb2 && nb2 >nb3)) {
			ordreDesNombres = ("d�croissant");
			// si le nb 1 est plus grand que nb 2 et que nb3 est plus petit que nb 2
			// ordreDesNombres re�oit la valeur "D�croissant"
		}else {
			ordreDesNombres = ("quelconque");
			// autres
		}

		return ordreDesNombres;

	}

	public static int expMax(int base, int valeurMax) {
		// permet de trouver les exposants maximums
		int r�sultat = 0, exposant = 0;
		for (int i = 0; r�sultat <= valeurMax; i++) {
			r�sultat = (int) Math.pow(base, i);
			exposant++;
		} 
		if (base<1|| valeurMax<1 ) {
			exposant=1;
		}
		if (valeurMax<base ) {
			exposant=2;
		}
		// je r�duis l'exposant par 2 pour faire en sorte qu'on enl�ve les exposants de
		// trop
		return (exposant - 2);
	}

	public static int somme(int nbMax) {
		int total = 0;
		// cette fonction permet d'additionner la somme des nombres de 0 � nbMax en
		// stockant le r�sultat de chaque boucle dans la variables total
		for (int i = 0; i <= nbMax; i++) {
			total = total + i;
		}
		if(nbMax<0) {
			total=-1;
		}
		if (total>Integer.MAX_VALUE) {
			total=-1;
		}
		return total;
	}

	public static void exMult(Scanner clavier, int valMax) {
		int reponseUsager, premierChiffreAleatoire, deuxi�meChiffreAleatoire;
		/**
		 * les diff�rents variables permettre de cr��er des chiffres al�atoire qui sont
		 * supp�rieur � 0 mais inf�rieur � valMax de l'usager. clavier permet de retenir
		 * la r�ponse de l'usager
		 */
		do {
			premierChiffreAleatoire = (int) (Math.random() * valMax + 1);
			premierChiffreAleatoire = (int) Math.round(premierChiffreAleatoire);
			deuxi�meChiffreAleatoire = (int) (Math.random() * valMax + 1);
			deuxi�meChiffreAleatoire = (int) Math.round(deuxi�meChiffreAleatoire);
			System.out.println("Combien font " + premierChiffreAleatoire + "* " + deuxi�meChiffreAleatoire + "? ");
			reponseUsager = clavier.nextInt();
			System.out.println("BRAVO! Vous avez la bonne reponse");
		} while (reponseUsager == (premierChiffreAleatoire) * (deuxi�meChiffreAleatoire));
		System.out.println("Erreur le r�sultat de " + premierChiffreAleatoire + " * " + deuxi�meChiffreAleatoire
				+ " est " + (premierChiffreAleatoire) * (deuxi�meChiffreAleatoire));

	}

	public static void tableMult(int valMax) {
		int chiffre, multiplicateur;
		System.out.print("  X | ");

		for (chiffre = 1; chiffre <= valMax; chiffre++) {
			String numero = String.format(" %3d", chiffre);
			System.out.print(numero);

		}
		System.out.println();

		System.out.print("----|");

		for (chiffre = 0; chiffre <= valMax; chiffre++) {
			//cette fonction permet de mettre les tirets dans la largeur du tableau
			System.out.print("----");
		}
		System.out.println();

		for (chiffre = 1; chiffre <= valMax; chiffre++) {
			//cette boucle permet d'affficher de bien formater le tableau et affiche
			//la barre horizontal
			String bordure = String.format("%3d ", chiffre);
			System.out.print(bordure + "| ");

			for (multiplicateur = 1; multiplicateur <= valMax; multiplicateur++) {
				//cette boucle aide � calculer et afficher le r�sultat
				//de la multiplication
				int produit = chiffre * multiplicateur;
				String resultat = String.format(" %3d", produit);
				System.out.print(resultat);
			}
			System.out.println();
		}
		
	}

	public static char conversionNote(float notePourcent) {
		char lettre = ' ';
		/**
		 * cette fonction permet de trier selon la note de l'usager la valeur de son
		 * r�sultat en lettre
		 **/
		if (notePourcent >= 91 && notePourcent <= 100) {
			lettre = 'A';
		} else if (notePourcent >= 75 && notePourcent <= 90) {
			lettre = 'B';
		} else if (notePourcent >= 61 && notePourcent <= 74) {
			lettre = 'C';
		} else if (notePourcent >= 51 && notePourcent <= 60) {
			lettre = 'D';
		} else if (notePourcent >= 0 && notePourcent <= 50) {
			lettre = 'F';
		} else {
			lettre = 'X';
		}
		return lettre;
	}
}
