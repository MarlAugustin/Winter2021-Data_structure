import java.util.Scanner;

public class Somme {
	public static long calculerSomme(int n) {
		long somme = 0;
		int i = 1;
		while (i <= n) {
			somme += i;
			i++;
		}
		return somme;
	}

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		long resultat;
		int nombre;
		System.out.print("Entrez un nombre entier: ");
		nombre = clavier.nextInt();
		resultat = calculerSomme(nombre);
		System.out.println("La somme des " + nombre + " premiers entiers positifs est: " + resultat);
		clavier.close();
	}

}