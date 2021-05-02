package AugustinMarlond.At09;
import java.util.*;
public class AT09E02Base {
	public static void main(String[] args) {

		Scanner clavier = new Scanner(System.in);
		calculPrix(clavier);
	}

	public static void calculPrix(Scanner clavier) {
		int[] tabPrix = { 10, 20, 30, 40, 50 };
		int[] tabQte = { 5, 3, 0, 2, 7 };
	
		for (int i = 0; (i < tabPrix.length); i++) {
			try {
			System.out.print("" + tabPrix[i] + " / " + tabQte[i] + " = ");
			System.out.println(tabPrix[i] / tabQte[i]);
			}catch(ArithmeticException errArithmetic){
			System.out.println("Division par Zéro");
		}
		}
		

	}
}

