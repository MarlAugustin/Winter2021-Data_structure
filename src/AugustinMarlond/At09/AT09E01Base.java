package AugustinMarlond.At09;

import java.util.*;

public class AT09E01Base {
	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		calculMoyenne(clavier);
	}

	public static void calculMoyenne(Scanner clavier) {
		final int Max = 5;
		int[] tab = new int[Max];
		int N, som = 0;
		int moy = 0;

		try {
			System.out.print("Combien de nombres ? ");
			N = clavier.nextInt();
			for (int i = 0; (i < N); i++) {
				System.out.print("Donnez un nombre: ");
				tab[i] = clavier.nextInt();
				som += tab[i];
			}
			moy = som / N;
			System.out.println("Résultat: " + moy);
		} catch (ArrayIndexOutOfBoundsException erreur)  {
			System.out.println("Dépassement de la taille maximale du tableau");

		}

	}

}
