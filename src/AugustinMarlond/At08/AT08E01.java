package AugustinMarlond.At08;

import java.lang.reflect.*;
import java.util.*;

public class AT08E01 {


		public static void main(String[] args) {
			Scanner cl = new Scanner(System.in);
			String[][] infosForm = {
					{"ID Formulaire", "valideIDForm", "Inscrivez l'identificateur du formulaire (REV-#####/##)", ""},
					{"Nom, Prenom", "valideNom", "Indiquez le nom de famille et le prenom de l'etudiant", ""},
					{"Code MEQ", "valideMEQ", "Indiquez le code du MEQ de l'etudiant (AAAA #### ####)", ""},
					{"Matricule", "valideMatricule", "Inscrivez le numero de matricule de l'etudiant", ""}, 
					{"Trimestre", "valideTrimestre", "Inscrivez le trimestre (ex:A2020)", ""},
					{"Programme", "valideProgramme", "Indiquez les programme d'etude", ""}, 
					{"Sigle du cours", "valideSigle", "Indiquez le sigle du cours (###-AAA-MO)", ""}, 
					{"ID Evaluation", "valideIDEval", "Identifiez l'evaluation (AA###)", ""}, 
					{"Date de l'evaluation", "valideDate", "Indiquez la date de l'evaluation (JJ/MM/AAAA)", ""}, 
					{"Note", "valideNote", "Indiquez la nouvelle note (CT = 100)", ""}
			};
			char choix;
			
			do {
				for (int i = 0; i < infosForm.length; ++i) {
					System.out.println("" + (char) ('A'+i) + " - " +infosForm[i][0] + " : " + infosForm[i][3]);
				}
				System.out.println("T - Entrer toutes les donnees");
				System.out.println("Q - Quitter");
				System.out.print("Votre choix : ");
				choix = cl.nextLine().toUpperCase().charAt(0);
				if (choix >= 'A' && choix < 'A' + infosForm.length) {
					infosForm[choix - 'A'][3] = demandeInfo(cl, infosForm[choix - 'A']);
				} else if (choix == 'T') {
					for (int i = 0; i < infosForm.length; ++i) {
						infosForm[i][3] = demandeInfo(cl, infosForm[i]);					
					}
				}
			} while (choix != 'Q');
			System.out.println("Bonne journee.");
		}
		
		public static String demandeInfo(Scanner clavier, String[] infos) {
			String nouvelleDonnee = null;
			boolean donneeValide = false;
			try {
				Class cl = AT08E01.class;
				Method me = cl.getMethod(infos[1], new Class[] {String.class});
				System.out.print(infos[2] + " [" + infos[3] + "] : ");
				nouvelleDonnee = clavier.nextLine().trim();
				while (!nouvelleDonnee.isEmpty() && !donneeValide) {
					nouvelleDonnee = (String) me.invoke(cl, new Object[] {nouvelleDonnee});
					if (nouvelleDonnee.isEmpty()) {
						System.out.println("Entree invalide, SVP reessayez!");
						System.out.print(infos[2] + " [" + infos[3] + "] : ");
						nouvelleDonnee = clavier.nextLine().trim();					
					} else {
						donneeValide = true;
					}
				} 
				if (nouvelleDonnee.isEmpty()) {
					nouvelleDonnee = infos[3];				
				}			
			}
	        catch (Throwable e) {
	            System.err.println(e);
	        }
			return nouvelleDonnee;
		}
		
		// ID Formulaire : REV-#####/##
		public static String valideIDForm(String idForm) {
			String nouvelID = "";
			// TODO : Verifier le format
			// TODO : Ajuster le format
			if(idForm.matches("(REV([- ])?)?\\d{5}[-/ ]?([0-1][0-9]|20)")) {
				nouvelID =idForm.replaceAll("(REV([- ])?)?(\\d{5})[-/ ]?([0-1][0-9]|20)","REV-$3/$4");
			}
			
			return nouvelID;
		}	
		
		// Nom, Prenom : Bedard, Jean-Philippe
		public static String valideNom(String nom) {
			String nouveauNom = "";
			// TODO : Verifier le format
			//pourquoi ça marche [\\p{javaLowerCase}] ?
			if(nom.matches("([A-Z]([\\p{javaLowerCase}]*))([- ][A-Z]([\\p{javaLowerCase}]*))?, ([A-Z]([\\p{javaLowerCase}]*))([-][A-Z]([\\p{javaLowerCase}]*))?")) {
				nouveauNom=nom;
			}
			return nouveauNom;
		}
		
		// #MEQ : LLLL #### ####
		public static String valideMEQ(String noMEQ) {
			String nouveauMEQ = "";
			// TODO : Verifier le format
			// TODO : Ajuster le format
			if(noMEQ.matches("([\\p{javaUpperCase}]{4})([ ])?(\\d{4})([ ])?(\\d{4})")) {
				nouveauMEQ =noMEQ.replaceAll("([\\p{javaUpperCase}]{4})([ ])?(\\d{4})([ ])?(\\d{4})","$1 $3 $5");
			}
			return nouveauMEQ;
		}
		
		// Matricule : #######
		public static String valideMatricule(String matricule) {
			String nouveauMat = "";
			if(matricule.matches("(19|20)?\\d{7}")) {
				nouveauMat = matricule.replaceAll("(19|20)?(\\d{7})","$2");
			}
			// TODO : Verifier le format
			// TODO : Ajuster le format
			return nouveauMat;
		}
		
		// Trimestre (H ou Hiver + annee) : A####
		public static String valideTrimestre(String trimestre) {
			String nouveauTrimestre = "";
			if(trimestre.matches("[A|Automne|H|Hiver][- ]?(19|20)\\d{2}")) {
				nouveauTrimestre =trimestre.replaceAll("([A|Automne|H|Hiver])([- ])?(19|20)(\\d{2})", "$1$3$4");
			}
			// TODO : Verifier le format
			// TODO : Ajuster le format
			return nouveauTrimestre;
		}
		
		// Programme : Chaine de caracteres
		public static String valideProgramme(String prog) {
			String nouveauProg = "";
			if(prog.matches("\\p{Alpha+}*")) {
				nouveauProg = prog;
			}
			// TODO : Verifier le format
			return nouveauProg;
		}
		
		// Sigle cours : ###-...-MO
		public static String valideSigle(String sigle) {
			String nouveauSigle = "";
			if(sigle.matches("\\d{3}[-][\\p{javaLetterOrDigit}][-]MO")) {
			 nouveauSigle = sigle;
			}
			// TODO : Verifier le format
			return nouveauSigle;
		}

		// ID evaluation : AA###
		public static String valideIDEval(String idEval) {
			String nouvelID = "";
			if(idEval.matches("[\\p{javaUpperCase}]{2}[0-9]{3}")) {
				nouvelID=idEval;
			}
			// TODO : Verifier le format
			return nouvelID;
		}
		
		// Date evaluation : JJ/MM/AAAA
		public static String valideDate(String date) {
			String nouvelleDate = "";
			if(date.matches("([0-2][0-9]|30|31)([-/ ])?(0[0-9]|10|11|12)([-/ ])?(20[0-2][0-9])")) {
				nouvelleDate=date.replaceAll("([0-2][0-9]|30|31)([-/ ])?(0[0-9]|10|11|12)([-/ ])?(20[0-2][0-9])", "$1/$3/$5");
			}
			// TODO : Verifier le format
			// TODO : Ajuster le format
			return nouvelleDate;
		}
		
		// Note : 00-99 + CT
		public static String valideNote(String note) {
			String nouvelleNote = "";
			if(note.matches("(\\d{1}|\\d{2}|CT)")) {
			nouvelleNote=note;
			}
			// TODO : Verifier le format
			return nouvelleNote;
}
}

