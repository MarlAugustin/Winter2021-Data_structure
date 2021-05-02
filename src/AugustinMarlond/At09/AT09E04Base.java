package AugustinMarlond.At09;
import java.util.*;

public class AT09E04Base 
{public static void main(String[] args) {
	Scanner clavier = new Scanner(System.in);
	lireNote(clavier);
	
}
	public static void lireNote(Scanner cl) throws ArithmeticException{
		System.out.println("Inscrivez votre note");
		int note=cl.nextInt();
		if(note<0 || note>100) {
			throw new ArithmeticException ("java.lang.ArithmeticException: Valeur invalide!!!");
		}else {
		System.out.println(note);
		}
		}
}
