package Sandbox;

import java.util.Scanner;

public class ajoutTableau {
	public
	static void main(String[] args) {
	Scanner clavier = new Scanner(System.in);
	String[] nomProd = {"Navet","Brocoli","Patate","Céleri","Carotte","",""};
	int [] quant = { 1,2,3,4,5,-1,-1};
	int nbItems =5;
	String nomItem;
	int qteItem;
	System.out.print ("Indiquez l'item a ajouter :");
	nomItem = clavier.next();
	System.out.print ("Indiquez la quatite :");
	qteItem = clavier.nextInt();
	if ( nbItems < nomProd.length ) {
		nomProd [nbItems ] = nomItem;
	quant[ nbItems ] = qteItem;
	nbItems++;
	}
	
	for ( int i = 0; i < nomProd.length ; ++i) {
	System.out.println( nomProd [i] + " : " + quant[i]);	
	}
	
	
	}
}