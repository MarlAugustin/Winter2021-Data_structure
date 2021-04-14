package AugustinMarlond.At07;

	import java.util.*;

	public class AT07E01Base{

		public static void main(String[] args) {
			Scanner clavier = new Scanner(System.in);
			String message;
			char choix;
			int cle;

			do {
				System.out.println("Menu principal");
				System.out.println("-------------------------");
				System.out.println("1 - Encoder ");
				System.out.println("2 - Decoder");
				System.out.println("3 - Statistiques");
				System.out.println("9 - Quitter l'application");
				System.out.print("Votre choix > ");
				choix = clavier.next().charAt(0);
				clavier.nextLine();
				switch (choix) {
				case '1':
					System.out.print("Entrez le message a encoder : ");
					message = clavier.nextLine();
					System.out.print("Entrez la cle/decalage (1-25): ");
					cle = clavier.nextInt();
					System.out.println("Le message encode : " + transformeMessage(message, cle));
					break;
				case '2':
					System.out.print("Entrez le message a decoder : ");
					message = clavier.nextLine();
					System.out.print("Entrez la cle/decalage (1-25): ");
					cle = clavier.nextInt();
					System.out.println("Le message decode : " + transformeMessage(message, cle));
					break;
				case '3':
					System.out.print("Entrez le message a traiter : ");
					message = clavier.nextLine();
					affStats(message);
					break;
				}
				System.out.println("-------------------------");
			} while (choix != '9');
			System.out.println("Au revoir!");
			clavier.close();
		}

		/**
		 * Recherche le caractere de substitution pour le caractere donne en premier
		 * argument selon le code de Cesar avec une cle de chiffrement donnee en
		 * deuxieme argument. Si le caractere recu n'est pas une lettre alphabetique
		 * (a-z U A-Z), la fonction retourne le caractere sans aucune transformation.
		 * 
		 * @param caractere Le caractere a encoder/decoder.
		 * @param cle La cle de chiffrement du code de Cesar.
		 * @return Le caractere endode/decode.
		 */
		public static char decaleLettre(char caractere, int cle) {
			String alphabet;
			int position = -1, nouvellePosition;
			char nouveauCar = caractere;
			if (caractere >= 'a' && caractere <= 'z') {
				alphabet = "abcdefghijklmnopqrstuvwxyz";
			} else if (caractere >= 'A' && caractere <= 'Z') {
				alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			} else {
				alphabet = "";
			}
			position=alphabet.indexOf(caractere);
			if (position != -1) {
				nouvellePosition = Math.floorMod(position + cle, 26);
				nouveauCar=alphabet.charAt(nouvellePosition);
			}
			// TODO: Calculer position: l'indice de caractere dans alphabet.  
			// TODO: Si position est différent de -1 alors:
			// TODO: - Calculer nouvellePosition:l'indice de caractere apres decalage. 
			//         Utiliser la formule donnee dans l'enonce
			// TODO: - Calculer nouveauCar
			
			return nouveauCar;
		}

		/**
		 * Transforme le message recu comme premier argument en decalant toutes les
		 * lettres selon la cle. La cle doit avoir une valeur comprise entre -25 et 25
		 * inclusivement, sinon le message original est retourne. Chaque caractere du
		 * message doit etre passe a la fonction decaleLettre() qui se chargera de l'encoder.
		 * 
		 * @param message Le message a enncoder/decoder.
		 * @param cle La cle de chiffrement du code de Cesar.
		 * @return Le message encode/decode.
		 */
		public static String transformeMessage(String message, int cle) {
			StringBuilder nouveauMessage = new StringBuilder("");
			char caractere;
			if(cle>-25 && cle<25 ) {
				for(int i=0;i<message.length();i++) {
				caractere=message.charAt(i) ;
				nouveauMessage.append(decaleLettre(caractere,cle));
				}
			}else {
				nouveauMessage.append(message);
			}
			// TODO: Si cle est comprise entre -25 et +25, alors:
			// TODO:   -Extraire chaque caractere de message et appeler decalerLettre()
			//           pour obtenir le caractere decale. 
			// TODO:   -Ajouter le caracere obtenu a nouveaumessage
			// TODO: Sinon,ajouter message a nouveaumessage 
		
			return nouveauMessage.toString();
		}

		/**
		 * Afiiche les statistiques sur une chaine de caracteres : 
		 * - le nombre de mots
		 * - la longueur moyenne des mots
		 * - la frequence des lettres dans la chaine.
		 * 
		 * @param chaine La chaine de caracteres a analyser.
		 */
		public static void affStats(String chaine) {
			System.out.println("Nombre de mots : " + nbMots(chaine));
			System.out.println("Longueur moyenne des mots : " + longueurMoyenneMot(chaine));
			System.out.println("La frequence des lettres dans le message :");
			int[] freq = frequenceLettre(chaine);
			for (int i = 0; i < 26; ++i) {
				System.out.print(String.format("%4c", 'a' + i));
			}
			System.out.println();
			for (int i = 0; i < 26; ++i) {
				System.out.print(String.format("%4d", freq[i]));
			}
			System.out.println();
	}

		/**
		 * Calcule le nombre de mots dans une chaine de caracteres.
		 * 
		 * @param chaine La chaine a analyser.
		 * @return Le nombre de mots dans la chaine.
		 */
		public static int nbMots(String chaine) {
			int nbMots = 0;
			//String[] tabMots;
			// TODO: extraire les mots de chaine dans tabMots[]. Utiliser split(" ") 
			// TODO: calculer nbMots  
		String[] tabMots=chaine.split(" ");
		for(int i=0;i<tabMots.length;i++) {
			nbMots++;
		}
			return nbMots;
		}

		/**
		 * Calcule la longueur moyenne des mots dans la chaine donnee en argument.
		 * 
		 * @param chaine La chaine a analyser.
		 * @return La longueur moyenne des mots dans la chaine.
		 */
		public static float longueurMoyenneMot(String chaine) {
			int somme = 0;
			float moyenne = Float.NaN;
			String[] tabMots ;
			// TODO: extraire les mots de chaine dans tabMots[]. Utiliser split(" ") 
			// TODO: calculer la somme des  longueur des toutes les cases de tabMots[]. 
			// TODO: calculer moyenne
			return moyenne;
		}

		/**
		 * Convertit la chaine donnee en argument en minuscule, puis calcule le nombre
		 * de fois que chaque lettre alphabetique apparait dans cette chaine. 
		 * - la case d'indice 0 correspond a la frequence de 'a', 
		 * - la case d'indice 1 correspond a la frequence de 'b', 
		 *   ... 
		 * - la case d'indice 25 correspond a la frequence de 'z'
		 * 
		 * @param chaine La chaine de caracteres a traiter.
		 * @return Le tableau des frequences des lettres alphabetiques.
		 */
		public static int[] frequenceLettre(String chaine) {
			int[] tabFreq = new int[26];
			char ch;
			// TODO: Convertir chaine en minuscule
			// TODO: extraire chaque caractere de chaine dans ch. Si ch est un 
			//          caractere entre 'a' et 'z', incrementer tabFreq[ch - 'a'] 
			
			return tabFreq;
		}

	}

