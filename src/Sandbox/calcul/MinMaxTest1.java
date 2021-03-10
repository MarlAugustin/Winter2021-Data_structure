package Sandbox.calcul;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinMaxTest1 {

	@Test
	public void testMax3() {
// ----- DONNEES VALIDES -----
// Cas où le maximum est à droite
		assertEquals(89, MinMax.max3(10, 45, 89));
// Cas où le maximum est au milieu
		assertEquals(45, MinMax.max3(20, 45, 30));
// Cas où le maximum est à gauche
		assertEquals(78, MinMax.max3(78, 13, 45));
// 3 donne�?es identiques.
		assertEquals(45, MinMax.max3(45, 45, 45));

// ----- DONNEES INVALIDES -----
// Cas où le maximum donne 100.
		assertEquals(100, MinMax.max3(-152, -4, 300));
// Cas où le maximum donne 0.
		assertEquals(0, MinMax.max3(-4, -300, -7));

// ----- DONNEES LIMITES -----
// 1 donne�?e limite e�?gale à 0.
		assertEquals(75, MinMax.max3(0, 75, 4));
// 1 donne�?e limite e�?gale à 100.
		assertEquals(100, MinMax.max3(78, 100, 8));

// ----- DONNEES MELANGEES -----
// Donne�?es valides, invalides et cas limites.
		assertEquals(100, MinMax.max3(30, -252, 100));
	}
	@Test
	public void testMin3() {
		
	}
	
}
