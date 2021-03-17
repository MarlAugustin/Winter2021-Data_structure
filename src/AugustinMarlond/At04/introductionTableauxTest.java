package AugustinMarlond.At04;

import static org.junit.Assert.*;

import org.junit.Test;



public class introductionTableauxTest {
	int[] donneesJour = {43,23,45,12};
	int[][] matrice={{43,23,45,12,43},
					 {44,33,22,44,55},
	 				 {500,-1000,200,400,55}
						};
	
	
	@Test
	public void calculeMoyenne() {
	assertEquals(30.75, introductionTableaux.calculeMoyenne(donneesJour),0.01);
	int[] donneesJour = {54,-100,49,31};
	assertEquals(8.5, introductionTableaux.calculeMoyenne(donneesJour),0.01);
	//ça reconnait l'erreur
			
	}@Test
	public void trouvePosMax() {
	assertEquals(2, introductionTableaux.trouvePosMax(donneesJour),0.01);
	int[] donneesJour = {54,-100,49,31};
	assertEquals(0, introductionTableaux.trouvePosMax(donneesJour),0.01);
	//ça reconnait l'erreur
			
	}
	@Test
	public void matriceReguliere() {
		assertEquals(true, introductionTableaux.matriceReguliere(matrice));
		//ça reconnait l'erreur
		int[][] matrice={{43,23,45,12},
				 {44,33,22,44,55,66},
				 {500,-1000,200,400,55}
					};
		assertEquals(false, introductionTableaux.matriceReguliere(matrice));

		
	}@Test
	public void calculerMoyenneJour() {
		assertEquals(31, introductionTableaux.calculerMoyenneJour(matrice, 2),0.01);
		assertEquals(30.75, introductionTableaux.calculerMoyenneJour(matrice, 1),0.01);

	}

}
