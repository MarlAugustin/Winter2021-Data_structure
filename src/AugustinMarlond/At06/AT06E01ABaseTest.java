package AugustinMarlond.At06;

import static org.junit.Assert.*;

import org.junit.Test;

import AugustinMarlond.At04.introductionTableaux;

public class AT06E01ABaseTest {

	@Test
	public void viderGrille() {
		char[][] grille = { {'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'}};
		char[][] grilleTest = { {'0','0','0','0','0','0','0'},
				{'0','0','0','0','0','0','0'},
				{'0','0','0','0','0','0','0'},
				{'0','0','0','0','0','0','0'},
				{'0','0','0','0','0','0','0'},
				{'0','0','0','0','0','0','0'}};
		assertArrayEquals(grilleTest,AT06E01ABase.viderGrille(grille));
	}
	@Test
	public void ajouteJeton() {
		char[][] grille = { {'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'},
				{'R','B','0','0','R','0','0'}};
		assertEquals(true,  AT06E01ABase.ajouteJeton(grille,3,true));
		assertEquals(false,  AT06E01ABase.ajouteJeton(grille,4,true));
		assertEquals(false,  AT06E01ABase.ajouteJeton(grille,1,true));
		for(int i=0;i<grille.length;i++) {
			for(int j=0;j<grille[i].length;j++) {
	System.out.print(grille [i][j]  +"       "	);
				
			}System.out.println(" ");
	}

}
}