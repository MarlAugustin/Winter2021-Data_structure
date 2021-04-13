package AugustinMarlondTp2;

import static org.junit.Assert.*;

import org.junit.Test;

public class Exercices2Test {
	int[][] unMois = null;
	int[][] deuxMois = null;
	int[][] troisMois = null;
	

	@Test
	public void nbFinSemaines() {
		unMois=Exercices2.genereMois(1,2020);
		deuxMois=Exercices2.genereMois(2,2020);
		troisMois=Exercices2.genereMois(3,2020);
		assertEquals(4,Exercices2.nbFinSemaines(unMois));
		assertEquals(4,Exercices2.nbFinSemaines(deuxMois));
		assertEquals(4,Exercices2.nbFinSemaines(troisMois));


	}
	@Test
	public void trouveJour() {
		unMois=Exercices2.genereMois(1,2021);
		deuxMois=Exercices2.genereMois(2,2009);
		troisMois=Exercices2.genereMois(3,2021);
		int[][] sixMois=Exercices2.genereMois(6,2009);
		assertEquals(1,Exercices2.trouveJour(unMois, 5, 1));
		assertEquals(21,Exercices2.trouveJour(deuxMois, 6, -2));
		assertEquals(11,Exercices2.trouveJour(troisMois, 4, 2));
		assertEquals(18,Exercices2.trouveJour(sixMois, 6, -2));

	}

}
