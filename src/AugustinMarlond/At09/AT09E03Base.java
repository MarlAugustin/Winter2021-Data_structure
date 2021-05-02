package AugustinMarlond.At09;

import java.util.*;

public class AT09E03Base {
	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		calculMoyenne(clavier);
	}

	public static void calculMoyenne(Scanner clavier) {
		final int Max = 5;
		String[] tab = new String[Max];
		int N, som = 0;
		int moy = 0;

		try {
			System.out.print("Combien de nombres ? ");
			N = clavier.nextInt();
			for (int i = 0; (i < N); i++) {
				System.out.print("Donnez un nombre: ");
				tab[i] = clavier.next();
				//verifierFormat(tab);
			}
		} catch (ArrayIndexOutOfBoundsException erreur)  {
			System.out.println("Dépassement de la taille maximale du tableau");

		}

	}
	/*
	public static void verifierFormat(String tab[]) {
		for(int i=0;i<tab.length;i++) {
			System.out.println(tab[i]);
			try {
				
			}catch() {
				//replace est necessaire
				System.out.println("Nombre ecrit : " + tab[i]);
			}
			
		}*/
	
}


