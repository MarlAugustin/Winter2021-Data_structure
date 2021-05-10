package AugustinMarlond.At09;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AT09E06Base {
	public static void main(String[] args) throws Exception {
		Scanner clavier = new Scanner(System.in);
		int nb1=0, nb2=0, res;
		char op = 0;
		String equation;
		String eqRegEx = "^(-?[0-9]+)[ ]*([+*/^-])[ ]*(-?[0-9]+)$";
		String eqRegExFact = "^(-?[0-9]+)[ ]*!$";
		Exception regEx= new Exception ("Les valeurs acceptables sont comprises entre 1 et 12 inclusivement.");

		do {
			System.out.print("> ");
			equation = clavier.nextLine().trim().toUpperCase();
			res = Integer.MIN_VALUE;
			if (equation.matches(eqRegEx)) {
				Matcher m = Pattern.compile(eqRegEx).matcher(equation);
				m.find();
				
				nb1 = Integer.parseInt(m.group(1));
				op = m.group(2).charAt(0);
				nb2 = Integer.parseInt(m.group(3));
				switch (op) {
				// Ajouter le traitement des operations ici
				case '+': {
					res = somme(nb1, nb2);
					break;
				}
				case '-': {
					res = difference(nb1, nb2);
					break;
				}
				case '*': {
					res = multiplication(nb1, nb2);
					if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
						res = Integer.MIN_VALUE;
					} else {

					}

					break;
				}
				case '/': {
					try {
					res = division(nb1, nb2);
					}catch(ArithmeticException erreur1 ){
						System.out.println("La division par zero est impossible");
					}
					
					break;
				}
				case '^': {
					res = exposant(nb1, nb2);
					if (nb1 == 0 || nb2 == 0) {
						res = multiplication(nb1, nb2);
						if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
							res = Integer.MIN_VALUE;
						}
					}
					break;
				}
				case '!': {
				
					res = exposant(nb1, nb2);
					if (nb1 == 0 || nb2 == 0) {
						res = multiplication(nb1, nb2);
					}
					break;
				}
				}
			} else if (equation.matches(eqRegExFact)) {
				Matcher m = Pattern.compile(eqRegExFact).matcher(equation);
				m.find();
				nb1 = Integer.parseInt(m.group(1));
				try{
				op = '!';
				res = factorielle(nb1);
				}catch(Exception erreur) {
					System.out.print(erreur);
				}
				} else {
					op = 'X';
				}
			if (op != 'Q') {
				if (res == Integer.MIN_VALUE) {
					System.out.println("Erreur");
				} else {
					System.out.println(res);
				}
			}
		} while (op != 'Q');
		
		System.out.println("Au revoir!");
	}

	public static int somme(int nb1, int nb2) {
		return (nb1 + nb2);

	}

	public static int difference(int nb1, int nb2) {
		return (nb1 - nb2);
	}

	public static int multiplication(int nb1, int nb2) {
		return (nb1 * nb2);

	}

	public static int division(int nb1, int nb2) {
		return (int) (nb1 / nb2);
	}

	public static int exposant(int nb1, int nb2) {
		return (int) (Math.pow(nb1, nb2));
	}

	public static int factorielle(int nb1) throws Exception {
		int resultat = Integer.MIN_VALUE;
		Exception regEx= new Exception ("Les valeurs acceptables sont comprises entre 1 et 12 inclusivement.");
		if (nb1 == 0 ||nb1>12) {
		resultat = 1;
		throw regEx;
		}
		if (nb1 > 0 && nb1 <= 12) {
		resultat = 1;
		while (nb1 > 1) {
		resultat *= nb1;
		nb1--;
		}
		}
		return resultat;
		

	}

}
