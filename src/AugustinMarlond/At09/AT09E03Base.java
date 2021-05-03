package AugustinMarlond.At09;

import java.util.*;

public class AT09E03Base {
	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		int N=0;
		
		verifierFormat(clavier);
		}
	
	public static void verifierFormat(Scanner clavier) {
		int N=0;
		float Z=0;
		System.out.print("Combien de nombres ? ");
		N = clavier.nextInt();
		String[] tab = new String[N];
		for(int i=0;i<N;i++) {
				System.out.print("Donnez un nombre: ");
				tab[i] = clavier.next();
			try {
				 Z = Float.parseFloat(tab[i]);
			}catch(Exception erreur) {
				//replace est necessaire
				System.out.println("Utilisez le point decimal");
				tab[i]=tab[i].replace(',', '.');
				 Z = Float.parseFloat(tab[i]);	
			}
			System.out.println("Nombre ecrit :"+ Z);
		}
			
		
	
	}
}

