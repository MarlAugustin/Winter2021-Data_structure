package AugustinMarlondTp3;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;
public class TP03E02 {
	public static final int NOM_IDX = 0;
	public static final int PRENOM_IDX = 1;
	public static final int ADRESSE_IDX = 2;
	public static final int VILLE_IDX = 3;
	public static final int CODE_POSTAL_IDX = 4;
	public static final int TELEPHONE_IDX = 5;
	public static final int COURRIEL_IDX = 6;
	public static final int BOTTIN_COLS = 7;
	public static final int BOTTIN_LIGNES = 25;
	
	public static void main(String[] args) {
		Scanner cl = new Scanner(System.in);
		String[][] infosForm = {
				{"Nom de famille", "Nom", "validerNom", "Inscrivez le nom de famille"},
				{"Prenom", "Prenom", "validerPrenom", "Indiquez le prenom"},
				{"Adresse", "Adresse", "validerAdresse", "Indiquez l'adresse (# rue)"},
				{"Ville", "Ville", "validerVille", "Inscrivez la ville"}, 
				{"Code postal", "C.P.", "validerCodePostal", "Inscrivez le code postal (A0A 0A0)"},
				{"Telephone", "Telephone", "validerTelephone", "Indiquez le numero de telephone (514-555-5555)"}, 
				{"Courriel", "Courriel", "validerCourriel", "Indiquez le courriel"}, 
		};
		// Initialise le bottin, qui peut contenir 25 entrees (lignes)
		// Chaque entree contient 7 informations sur la personne (colonnes)
		// Les index, pour chaque information, sont definis comme constantes plus haut
		String[][] bottin = new String[BOTTIN_LIGNES][BOTTIN_COLS];
		int nbEntrees = 0;
		int choix;
		
		do {
			System.out.println("Menu principal");
			System.out.println("--------------");
			System.out.println("1. Lire le bottin");
			System.out.println("2. Afficher le bottin");			
			System.out.println("3. Ajouter au bottin");
			System.out.println("4. Retirer du bottin");
			System.out.println("5. Modifier le bottin");
			System.out.println("6. Enregistrer le bottin");
			System.out.println("9. Quitter");
			System.out.println("--------------");
			System.out.print("Votre choix > ");
			choix = cl.nextInt();
			cl.nextLine();
			switch (choix) {
			case 1:
				nbEntrees = lireBottin(bottin);
				break;
			case 2:
				afficherBottin(bottin, nbEntrees, infosForm);
				break;
			case 3:
				if (nbEntrees < BOTTIN_LIGNES) {
					nbEntrees = ajoutBottin(cl, bottin, nbEntrees, infosForm);
				} else {
					System.out.println("Bottin plein, ajout impossible!");
				}
				break;
			case 4:
				if (nbEntrees > 0) {
					nbEntrees = retraitBottin(cl, bottin, nbEntrees);
				} else {
					System.out.println("Bottin vide, retrait impossible!");					
				}
				break;
			case 5:
				if (nbEntrees > 0) {
					modifierBottin(cl, bottin, nbEntrees, infosForm);
				} else {
					System.out.println("Bottin vide, modification impossible!");					
				}
				break;
			case 6:
				ecrireBottin(bottin, nbEntrees);
				break;
			case 9:
				break;
			default:
				System.out.println("Choix invalide");
				break;
			}
			System.out.println();
			System.out.println("--------------");
		} while (choix != 9);
		System.out.println("Au revoir.");
	}

	public static String validerInfo(String valeur, String[] infos) {
		try {
			Class cl = TP03E02.class;
			Method me = cl.getMethod(infos[2], new Class[] {String.class});
			valeur = (String) me.invoke(cl, new Object[] {valeur});
		} catch (NoSuchMethodException e) {
            System.err.println(e);
            valeur = "";            
        } catch (InvocationTargetException e) {
            System.err.println(e);
            valeur = "";
        } catch (IllegalAccessException e) {
            System.err.println(e);
            valeur = "";        	
        }
		return valeur;
	}
	
	public static String demandeInfo(Scanner clavier, String[] infos, String defaut) {
		String nouvelleDonnee = "";
		boolean donneeValide = false;
		System.out.print(infos[3] + " [" + defaut + "] : ");
		nouvelleDonnee = clavier.nextLine().trim();
		while (!nouvelleDonnee.isEmpty() && !donneeValide) {
			nouvelleDonnee = validerInfo(nouvelleDonnee, infos);
			if (nouvelleDonnee.isEmpty()) {
				System.out.println("Entree invalide, SVP reessayez!");
				System.out.print(infos[3] + " [" + defaut + "] : ");
				nouvelleDonnee = clavier.nextLine().trim();					
			} else {
				donneeValide = true;
			}
		} 
		if (nouvelleDonnee.isEmpty()) {
			nouvelleDonnee = defaut;
		}			
		return nouvelleDonnee;
	}

	public static void afficherBottin(String[][] bottin, int nbEntrees, String[][] tabInfos) {
		System.out.println(String.format("%-20s%-20s%-25s%-20s%-8s%-15s%s",
				tabInfos[NOM_IDX][1],tabInfos[PRENOM_IDX][1],tabInfos[ADRESSE_IDX][1],tabInfos[VILLE_IDX][1],
				tabInfos[CODE_POSTAL_IDX][1],tabInfos[TELEPHONE_IDX][1],tabInfos[COURRIEL_IDX][1]));
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < nbEntrees; ++i) {
			System.out.println(String.format("%-20s%-20s%-25s%-20s%-8s%-15s%s", 
					bottin[i][NOM_IDX],bottin[i][PRENOM_IDX],bottin[i][ADRESSE_IDX],bottin[i][VILLE_IDX],
					bottin[i][CODE_POSTAL_IDX],bottin[i][TELEPHONE_IDX],bottin[i][COURRIEL_IDX]));
		}
	}
		
	public static int ajoutBottin(Scanner cl, String[][] bottin, int nbEntrees, String[][] tabInfos) {
		int infoIndex = 0;
		boolean annule = false;
		while (infoIndex < tabInfos.length && !annule) {
			bottin[nbEntrees][infoIndex] = demandeInfo(cl, tabInfos[infoIndex], "");
			if (bottin[nbEntrees][infoIndex].trim().isEmpty()) {
				annule = true;
				bottin[nbEntrees] = new String[BOTTIN_COLS];
			}
			infoIndex++;
		}
		if (!annule) {
			nbEntrees++;
		}
		return nbEntrees;		
	}
	
	public static int retraitBottin(Scanner cl, String[][] bottin, int nbEntrees) {
		int entree = 0;
		System.out.println(String.format("     %-20s%-20s","Nom","Prenom"));
		System.out.println("--------------------------------------------");
		for (int i = 0; i < nbEntrees; ++i) {
			System.out.println(String.format("%2d : %-20s%-20s", (i+1), bottin[i][NOM_IDX],bottin[i][PRENOM_IDX]));
		}
		System.out.println(" 0 : Annuler");
		System.out.print("Entree a supprimer? ");
		entree = cl.nextInt();
		cl.nextLine();
		entree--;
		if (entree >= 0 && entree < nbEntrees) {
			nbEntrees--;
			bottin[entree] = bottin[nbEntrees];
			bottin[nbEntrees] = new String[BOTTIN_COLS];
		}
		return nbEntrees;
	}
	
	public static void modifierBottin(Scanner cl, String[][] bottin, int nbEntrees, String[][] tabInfos) {
		int entree = 0, ligne = 0;
		System.out.println(String.format("     %-20s%-20s","Nom","Prenom"));
		System.out.println("--------------------------------------------");
		for (int i = 0; i < nbEntrees; ++i) {
			System.out.println(String.format("%2d : %-20s%-20s", (i+1), bottin[i][NOM_IDX],bottin[i][PRENOM_IDX]));
		}
		System.out.println(" 0 : Annuler");
		System.out.print("Entree a modifier? ");
		ligne = cl.nextInt();
		cl.nextLine();
		ligne--;
		if (ligne >= 0 && ligne < nbEntrees) {
			for (int i = 0; i < tabInfos.length; ++i) {
				System.out.println("" + (i+1) + " - " + tabInfos[i][0] + " : " + bottin[ligne][i]);
			}
			System.out.println("0 : Annuler");			
			System.out.print("Information a modifier? ");
			entree = cl.nextInt();
			cl.nextLine();
			entree--;
			if (entree >= 0 && entree < BOTTIN_COLS) {
				bottin[ligne][entree] = demandeInfo(cl, tabInfos[entree], bottin[ligne][entree]);
			}
		}
	}

	/**
	 * Valide le nom de famille.  Il doit commencer par une lettre majuscule.
	 * S'il est compose, les deux parties peuvent etre separees par un espace
	 * ou un trait d'union et la deuxieme partie doit commencer par une majuscule.
	 * 
	 * @param nom Nom de famille a valider
	 * @return Le nom de famille s'il est valide, une chaine vide sinon.
	 */
	public static String validerNom(String nom) {
		String nouveauNom = "";
		// TODO : Verifier le format
		if(nom.matches("([\\p{javaUpperCase}])([\\p{javaLowerCase}]*)(([-][\\p{javaUpperCase}])([\\p{javaLowerCase}]*))?")) {
			nouveauNom=nom;
		}
		return nouveauNom;
	}
	
	/**
	 * Valide le prenom recu en parametre.  Il doit commencer par une lettre majuscule.
	 * S'il est compose, les deux parties doivent etre separees par un trait
	 * d'union et la deuxieme partie doit commencer par une majuscule.
	 * 
	 * @param prenom Prenom a valider
	 * @return Le prenom, s'il est valide, sinon, une chaine vide.
	 */
	public static String validerPrenom(String prenom) {
		String nouveauPrenom = "";
		// TODO : Verifier le format
		if(prenom.matches("([\\p{javaUpperCase}])([\\p{javaLowerCase}]*)+(([-][\\p{javaUpperCase}])([\\p{javaLowerCase}]*))?")) {
			nouveauPrenom=prenom;
		}
		return nouveauPrenom;
	}

	/**
	 * Determine si l'adresse recue en parametre est valide.
	 * Une adresse est valide si elle debute par un nombre entier suivi
	 * d'un espace et d'une chaine d'au moins deux caracteres.
	 * 
	 * @param adresse Chaine representant l'adresse a valider
	 * @return Retourne l'adresse si elle est valide ou une chaine vide sinon
	 */
	public static String validerAdresse(String adresse) {
		String nouvelleAdresse = "";
		// TODO : Verifier le format
		if(adresse.matches("[0-9]{1,} .{2,}")) {
			nouvelleAdresse=adresse;
		}
		return nouvelleAdresse;
	}

	// TODO: JAVADOC
	// TODO: JUNIT - 5 tests unitaires pertinents
	/**
	 * Cette fonction reçoit ville retourne la chaîne nouvelleVille, 
	 * on transforme le premier caractère en majuscule et le reste on les transforme en miniscule.
	 * @author Marlond Augustin
	 * @param ville  c'est le nom de ville qu'il faut formater
	 * @return nouvelleVille c'est le nom de ville formater
	 */
	public static String validerVille(String ville) {
		// TODO : Ajuster le format
		String nouvelleVille = "laval";	
		nouvelleVille=ville.toUpperCase().charAt(0)+ville.substring(1);
		
		return nouvelleVille;
	}

	/**
	 * Valide le code postal recu en parametre et ajuste le format.
	 * Un code postal valide contient une alternance de lettres majuscules
	 * et de chiffres.  Un espace ou un trait d'union peut apparaitre entre
	 * le 3e et le 4e caractere du code postal.  Si le code postal est valide,
	 * on le met sous la forme A0A 0A0 .
	 * 
	 * @param codePostal Code postal a valider
	 * @return Code postal standardise ou chaine vide s'il n'est pas valide.
	 */
	public static String validerCodePostal(String codePostal) {
		String nouveauCP = "";
		// TODO : Verifier le format
		// TODO : Ajuster le format
		if(codePostal.matches("([\\p{javaUpperCase}])([\\p{javaLetterOrDigit}]{2})([- ])?([\\p{javaLetterOrDigit}]{3})")) {
			nouveauCP=codePostal.replaceAll("([\\p{javaUpperCase}])([\\p{javaLetterOrDigit}]{2})([- ])?([\\p{javaLetterOrDigit}]{3})", "$1$2 $4");
		}
		return nouveauCP;
	}

	// TODO: JAVADOC
	// TODO: JUNIT - 5 tests unitaires pertinents
	/**
	 * La fonction accepte les numéro de téléphones de différents formats. Si elle  correspond au format demander
	 * nouveauTelephone formate telephone. Sinon elle reste vide.
	 * 
	 * @param telephone telephone a valider
	 * @return nouveauTelephone standardise ou chaine vide s'il n'est pas valide. 
	 */
	public static String validerTelephone(String telephone) {
		String nouveauTelephone = "";
		// TODO : Verifier le format
		// TODO : Ajuster le format
		if(telephone.matches("\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})")) {
			nouveauTelephone=telephone.replaceAll("\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})", "($1)$2-$3");
			}
		return nouveauTelephone;
	}
	
	/**
	 * Valide le courriel recu en parametre.  Un courriel valide contient
	 * des sequences de lettres minuscules et de chiffres qui peuvent etre
	 * separees par des points.  Elles sont suivies par le symbole '@' et 
	 * par une alternance de sequences de lettres minuscules et de points. 
	 * L'adresse doit se terminer par ".com" ou ".ca"
	 * 
	 * @param courriel Courriel a valider
	 * @return Le courriel recu ou une chaine vide s'il n'est pas valide
	 */
	public static String validerCourriel(String courriel) {
		String nouveauCourriel = "";
		// TODO : Verifier le format
		if(courriel.matches("([\\p{javaLetterOrDigit}]+)(.[\\p{javaLetterOrDigit}]+)?(@[\\p{javaLowerCase}]+)(.[\\p{javaLowerCase}]+)?(.com|.ca)")) {
			nouveauCourriel=courriel;
		}
		return nouveauCourriel;
	}

	// TODO: JAVADOC
	// TODO: JUNIT - 5 tests unitaires pertinents
	/**
	 * On verifie si ligne possède 7 éléments, sinon on affiche une erreur et valide devient faux.
	 * On verifie les informations individullement en utilisant les différentes méthodes valider...
	 * et si le contenu de donne n'est pas vide valide devient vrai sinon c'est faux
	 * @author Marlond Augustin
	 * @param uneLigne elle contient les informations qui devront être vérifier
	 * @return valide pour foir si la ligne est valide ou non
	 */
	public static boolean validerLigne(String[] uneLigne) {
		boolean valide = true;
		String donnee = "";
		// TODO: Valider le nombre d'elements sur la ligne (7)
		// TODO: Si le nombre d'elements est incorrect, afficher un message
		// 			et mettre valide a false
		// TODO: Sinon, valider chaque information individuellement
		// 			- Appeler la fonction de validation correspondante et
	    //				recuperer le resultat dans donnee
		//			- Si la donnee est invalide, on affiche un message
		//				et on met valide a false
		//			- Sinon, copier donnee dans la case appropriee de la ligne
		// TODO: Faire 7 fois la validation, soit une validation pour 
		//			chacun des sept elements de la ligne
		
		if(uneLigne.length==7) {
			donnee=validerNom(uneLigne[0]);
			if(!donnee.equals("")) {
			}else {
				System.out.println("Nom invalide"+ uneLigne[0]);
				valide=false;
			}
			String donnee1=validerPrenom(uneLigne[1]);
			if(!donnee1.equals("")) {
			}else {
				System.out.println("prenom invalide "+ uneLigne[1]);
				valide=false;
			}
			String donnee2=validerAdresse(uneLigne[2]);
			if(!donnee2.equals("")) {
			}else {
				System.out.println("adresse invalide : "+ uneLigne[2]);
				valide=false;
			}
			String donnee3=validerVille(uneLigne[3]);
			if(!donnee3.equals("")) {
				uneLigne[3]=donnee3;
			}else {
				System.out.println("Ville invalide : "+uneLigne[3]);
				valide=false;
			}
			String donnee4=validerCodePostal(uneLigne[4]);
			if(!donnee4.equals("")) {
				uneLigne[4]=donnee4;
			}else {
				System.out.println("Code postal invalide : "+uneLigne[4]);
				valide=false;
			}
			String donnee5=validerTelephone(uneLigne[5]);
			if(!donnee5.equals("")) {
				uneLigne[5]=donnee5;
			}else {
				System.out.println("telephone invalide : "+uneLigne[5]);
				valide=false;
			}
			String donnee6=validerCourriel(uneLigne[6]);
			if(!donnee6.equals("")) {
			}else {
				System.out.println("Courriel invalide : "+uneLigne[6]);
				valide=false;
			}
		}else {
			System.out.println("Nombre d'elements invalide ("+uneLigne.length+"!=7)");
			valide=false;
		}
		return valide;
	}
	
	// TODO: JAVADOC
	/**Elle ouvre le fichier « src/<votre nom>/TP03/bottin.txt » et copie son contenu
	 * dans le tableau bottin, reçu en paramètre
	 * Si une ligne est invalide, un message apparaît à la console et
	 * elle n'est pas ajoutée au tableau bottin
	 * @author Marlond
	 * @param bottin elle copie le contenue de src/AugustinMarlondTp3/bottin.txt
	 * @return nbEntrees on concatène selon le nombre de ligne valide dans le nom de fichier
	 */
	public static int lireBottin(String[][] bottin) {
		BufferedReader tamponBottin = null;
		String ligneBottin;
		String[] tmpStrTab;
		int nbEntrees = 0;
		// TODO: Initialiser tamponBottin afin d'ouvrir le fichier texte
		//			"src/<votre nom>/TP03/bottin.txt" en mode lecture
		// TODO: Lire chaque ligne du fichier et la nettoyer (trim)
		// TODO: Decouper la ligne et sauvegarder le resultat dans tmpStrTab
		// TODO: Valider la ligne a l'aide de la fonction validerLigne
		// TODO: Enregistrer la ligne dans le bottin si elle est valide
		// TODO: Sinon, afficher un message a la console
		// TODO: Attraper les exceptions FileNotFoundException et IOException
		// TODO: Fermer le fichier dans le bloc finally
		
		String nomFichier = "src/AugustinMarlondTp3/bottin.txt";
		try {
			tamponBottin = new BufferedReader(new FileReader(nomFichier));
		while((ligneBottin=tamponBottin.readLine())!=null ) {
			ligneBottin=ligneBottin.trim();
			if(!ligneBottin.isEmpty() && nbEntrees<bottin.length) {
				tmpStrTab=ligneBottin.split(":") ;
				if(validerLigne(tmpStrTab)==true) {
					bottin[nbEntrees] = tmpStrTab;
					nbEntrees++;
				}else{
					System.out.println("Ligne refusee :"+ligneBottin);
				}
			}
		}
		} catch (FileNotFoundException exc) {
		System.out.println("Le fichier " + nomFichier + " est introuvable.");
		} catch (IOException exc) {
			System.out.println("Probleme de lecture du fichier: " + nomFichier);
		} finally{
		try{
		if (tamponBottin!= null) {
			tamponBottin.close();
		}
		} catch (IOException exc) {
		System.out.println("Probleme de fermeture du fichier: " + nomFichier);
		}
		}
		return nbEntrees;
	}
	
	// TODO: JAVADOC
	/**Elle écrase le fichier « src/<votre nom>/TP03/bottin.txt » pour y écrire le bottin reçu en paramètre.
	 * chaque données (colonnes) doivent être séparées par  « : ». Si il y a une erreur on affiche le contenu 
	 * des exceptions
	 * @author Marlond
	 * @param bottin  on l'utilise pour recevoir les informations liés aux élèves
	 * @param nbEntrees on utilise pour set la longueur du bottin
	 */

	public static void ecrireBottin(String[][] bottin, int nbEntrees) {
		BufferedWriter tamponBottin = null;
		StringBuilder ligneBottin = new StringBuilder();
		// TODO: Initialiser tamponBottin afin d'ouvrir le fichier texte
		//			"src/<votre nom>/TP03/bottin.txt" en mode ecriture
		// TODO: Pour chacune des entrees dans le bottin
		// TODO: Vider le StringBuilder (setLength(0))
		// TODO: Construire la ligne dans le StringBuilder en separant
		//			chaque element par ":"
		// TODO: Ecrire la ligne dans le fichier suivi d'un saut de ligne
		// TODO: Attraper l'exception IOException1
		// TODO: Fermer le fichier dans le bloc finally
	
		String nomFichier = "src/AugustinMarlondTp3/bottin.txt";
		try{
			tamponBottin= new BufferedWriter(new FileWriter(nomFichier));
			// Concatenerles informations dans une chaine modifiable de type StringBuilder
			for(int i=0; i<nbEntrees;i++) {
			for(int j=0; j<BOTTIN_COLS;j++) {
			ligneBottin.append(bottin[i][j]).append(":");
			}
			tamponBottin.write(ligneBottin.toString()); // Ecrirela chaine dans le fichier
			tamponBottin.newLine(); // Ajouter un saut de ligne
			ligneBottin.setLength(0); // Vider la chaine avant de passer a l’etudiantsuivant
			}
			} catch (IOException exc) {
			System.out.println("Erreur d'ecriture");
			} finally{
			try{
			if (tamponBottin!= null) {
				tamponBottin.close(); // Fermer le fichier
			}
			} catch (IOException exc) {
			System.out.println("Probleme de fermeture du fichier: " + nomFichier);
			}
			}
		
	}
}
