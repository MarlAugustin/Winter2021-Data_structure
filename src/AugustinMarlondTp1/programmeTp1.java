package AugustinMarlondTp1;

import java.util.Scanner;

public class programmeTp1 {

	public static void main(String[] args) {

		Scanner clavier = new Scanner(System.in);

		int jour, mois, annee;
		int decalage;
		String moisStr, dateStr;
		System.out.print("Inscrivez une date (JJ MMM AAAA) : ");
		jour = clavier.nextInt();
		moisStr = clavier.next();
		mois = moisEnInt(moisStr);
		annee = clavier.nextInt();
		System.out.print("Inscrivez le decalage : ");
		decalage = clavier.nextInt();
		if (decalage > 0) {
			dateStr = avanceDate(jour, mois, annee, decalage);
		} else {
			dateStr = reculeDate(jour, mois, annee, -decalage);
		}
		System.out.print("Nouvelle date : " + dateStr);
	}

	public static String moisEnStr(int mois) {
		String moisStr;
		switch (mois) {
		case 1:
			moisStr = "JAN";
			break;
		case 2:
			moisStr = "FEV";
			break;
		case 3:
			moisStr = "MAR";
			break;
		case 4:
			moisStr = "AVR";
			break;
		case 5:
			moisStr = "MAI";
			break;
		case 6:
			moisStr = "JUN";
			break;
		case 7:
			moisStr = "JUL";
			break;
		case 8:
			moisStr = "AOU";
			break;
		case 9:
			moisStr = "SEP";
			break;
		case 10:
			moisStr = "OCT";
			break;
		case 11:
			moisStr = "NOV";
			break;
		case 12:
			moisStr = "DEC";
			break;
		default:
			moisStr = "XXX";
			break;
		}
		return moisStr;
	}

	public static int moisEnInt(String moisStr) {
		int mois;
		switch (moisStr) {
		case "JAN":
			mois = 1;
			break;
		case "FEV":
			mois = 2;
			break;
		case "MAR":
			mois = 3;
			break;
		case "AVR":
			mois = 4;
			break;
		case "MAI":
			mois = 5;
			break;
		case "JUN":
			mois = 6;
			break;
		case "JUL":
			mois = 7;
			break;
		case "AOU":
			mois = 8;
			break;
		case "SEP":
			mois = 9;
			break;
		case "OCT":
			mois = 10;
			break;
		case "NOV":
			mois = 11;
			break;
		case "DEC":
			mois = 12;
			break;
		default:
			mois = -1;
			break;
		}
		return mois;
	}

	public static String avanceDate(int jour, int mois, int annee, int decalage) {
		int jourSuivant, moisSuivant, anneeSuivante;
		for (int i = decalage; i > 0; --i) {
			jourSuivant = jourDateSuivante(jour, mois, annee);
			moisSuivant = moisDateSuivante(jour, mois, annee);
			anneeSuivante = anneeDateSuivante(jour, mois, annee);
			jour = jourSuivant;
			mois = moisSuivant;
			annee = anneeSuivante;
		}
		return String.format("%02d %s %d", jour, moisEnStr(mois), annee);
	}

	public static int jourDateSuivante(int jour, int mois, int annee) {
		if (jour == joursParMois(mois, annee)) {
			jour = 1;
		} else {
			jour++;
		}
		return jour;
	}

	public static int moisDateSuivante(int jour, int mois, int annee) {
		if (jour == joursParMois(mois, annee)) {
			mois = (mois % 12) + 1;
		}
		return mois;
	}

	public static int anneeDateSuivante(int jour, int mois, int annee) {
		if (jour == 31 && mois == 12) {
			annee++;
		}
		return annee;
	}

	// A FAIRE
	public static int joursParMois(int mois, int annee) {
		int nbJours = 30;
		if (mois== 4 ||  mois== 6 || mois== 9 || mois== 11) {
			 nbJours=30;
		} else if (mois==1 || mois==3 || mois == 5 || mois==7 || mois==8 || mois==10 || mois==12 ){
			nbJours=31;
		} else  if (mois==2 &&(annee% 4 == 0 && annee%100 !=0 ||annee%400==0)) { 
			nbJours=29;
		}else if (mois==2 && !(annee% 4 == 0 && annee%100 !=0 ||annee%400==0)){ 
			nbJours=28 ;
		}else {
			nbJours=0;
		}
		return nbJours;
	}

	// A FAIRE
	public static String reculeDate(int jour, int mois, int annee, int decalage) {
		int jourPrecedent, moisPrecedent, anneePrecedente;
		for (int i = decalage; i > 0; --i) {
			jourPrecedent = jourDatePrecedente(jour, mois, annee);
			moisPrecedent = moisDatePrecedente(jour, mois, annee);
			anneePrecedente = anneeDatePrecedente(jour, mois, annee);
			jour = jourPrecedent;
			mois = moisPrecedent;
			annee = anneePrecedente;
		}
		return String.format("%02d %s %d", jour, moisEnStr(mois), annee);
	}

	// Fait
	public static int jourDatePrecedente(int jour, int mois, int annee) {
		if (jour == 1 && mois==1) {
			mois=12;
			jour=joursParMois(mois, annee);
		}
	   else if (jour == 1) {
			--mois;
			jour=joursParMois(mois, annee);
		} else {
			--jour;
		}
		return jour;
	}

	// Fait
	public static int moisDatePrecedente(int jour, int mois, int annee) {
		if(jour==1 && mois==1) {
			mois=12;
		}else if(jour==1) {
			mois-=1;
		}
		
		return mois;
	}

	// Fait
	public static int anneeDatePrecedente(int jour, int mois, int annee) {
		int anneePrecedente;
		if (jour == 1 && mois == 1) {
			anneePrecedente = annee - 1;
		} else {
			anneePrecedente = annee;
		}
		return anneePrecedente;
	}

}
