package AugustinMarlond.At06;

import static org.junit.Assert.*;

import org.junit.Test;

import AugustinMarlond.At04.introductionTableaux;

public class AT06E01ABaseTest {

	@Test
	public void viderGrille() {
		char[][] grille = { { 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' } };
		char[][] grilleTest = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 },{ 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }};
		AT06E01ABase.viderGrille(grille);
		assertArrayEquals(grille,grilleTest);
	}

	@Test
	public void ajouteJeton() {
		char[][] grille = { { 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' } };
		//boolean reponse=AT06E01ABase.ajouteJeton(grille, 2, true);
		//assertEquals();
		//assertEquals(false, AT06E01ABase.ajouteJeton(grille, 4, true));
		//assertEquals(false, AT06E01ABase.ajouteJeton(grille, 1, true));

	}

	@Test
	public void verifierColonnes() {
		char[][] grille = { { 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', 'R', 'R', 'R', 'R' } };
		char[][] grilleTest = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }};
		assertTrue(AT06E01ABase.verifierColonnes(grille));
		assertFalse(AT06E01ABase.verifierColonnes(grilleTest));
	}

	@Test
	public void verifierLignes() {
		char[][] grille = { { 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', 'R', 'R', 'R', 'R' } };
		char[][] grilleTest = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }};
		assertTrue(AT06E01ABase.verifierLignes(grille));
		assertFalse(AT06E01ABase.verifierLignes(grilleTest));
		
	}

	@Test
	public void verifierDiagonales() {
		char[][] grille = { { 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', '0', 'R', '0', '0' },
				{ 'R', 'B', '0', '0', 'R', '0', '0' }, { 'R', 'B', '0', 'R', 'R', 'R', 'R' } };
		char[][] grilleTest = { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }};
		assertFalse(AT06E01ABase.verifierDiagonales(grille));
		assertFalse(AT06E01ABase.verifierDiagonales(grilleTest));
		
	}
}