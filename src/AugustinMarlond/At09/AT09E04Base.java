package AugustinMarlond.At09;
import java.util.*;

public class AT09E04Base {
public static void main(String[] args) {
	Scanner clavier = new Scanner(System.in);
	try {
	lireNote(clavier);
	}catch (ArithmeticException erreur) {
		System.out.println(erreur);
	}
	
}
	public static void lireNote(Scanner cl) throws ArithmeticException{
		System.out.println("Inscrivez votre note");
		int note=cl.nextInt();
		if(note<0 || note>100) {
			throw new ArithmeticException ("Valeur invalide!!!");
		}else {
		System.out.println(note);
		}
		}
}
