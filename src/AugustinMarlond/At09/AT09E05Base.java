package AugustinMarlond.At09;

import java.util.Scanner;

public class AT09E05Base {

public static void main(String[] args) {
	Scanner clavier = new Scanner(System.in);
	lireNote(clavier);
	
}
	public static void lireNote(Scanner cl) throws ArithmeticException{
		System.out.println("Inscrivez votre note");
		String  note=cl.next();
		int i = Integer.parseInt(note);
		if(i<0 || i>100) {
			throw new ArithmeticException (" Valeur invalide!!!");
		}if(note.contains(".(//d)*")){
			throw new ArithmeticException ("Ce n'est pas un Int!!!");
		}
			else {
		}
		System.out.println(note);
		}
}
