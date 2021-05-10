package AugustinMarlond.At09;


import java.util.*;


public class AT09E05Base {

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		try {
			lireNote(clavier);
		} catch (OutOfBounds erreur2) {
			System.out.println(erreur2);
		}

	}

	public static int lireNote(Scanner cl) throws InputMismatchException, OutOfBounds {
		int note=0;
		try {
		System.out.println("Inscrivez votre note");
		 note = cl.nextInt();
		}catch(InputMismatchException erreur1) {
			System.out.println("Ce n'est pas un Int!!!");
		}if(note<0 || note>100) {
			throw new OutOfBounds("Valeur invalide!!!");
		}

		return note;
	}
}
