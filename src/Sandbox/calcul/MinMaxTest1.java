package Sandbox.calcul;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinMaxTest1 {

	@Test
	public void testMax3() {
// ----- DONNEES VALIDES -----
// Cas ouÌ€ le maximum est aÌ€ droite
		assertEquals(89, MinMax.max3(10, 45, 89));
// Cas ouÌ€ le maximum est au milieu
		assertEquals(45, MinMax.max3(20, 45, 30));
// Cas ouÌ€ le maximum est aÌ€ gauche
		assertEquals(78, MinMax.max3(78, 13, 45));
// 3 donneÌ?es identiques.
		assertEquals(45, MinMax.max3(45, 45, 45));

// ----- DONNEES INVALIDES -----
// Cas ouÌ€ le maximum donne 100.
		assertEquals(100, MinMax.max3(-152, -4, 300));
// Cas ouÌ€ le maximum donne 0.
		assertEquals(0, MinMax.max3(-4, -300, -7));

// ----- DONNEES LIMITES -----
// 1 donneÌ?e limite eÌ?gale aÌ€ 0.
		assertEquals(75, MinMax.max3(0, 75, 4));
// 1 donneÌ?e limite eÌ?gale aÌ€ 100.
		assertEquals(100, MinMax.max3(78, 100, 8));

// ----- DONNEES MELANGEES -----
// DonneÌ?es valides, invalides et cas limites.
		assertEquals(100, MinMax.max3(30, -252, 100));
	}
	@Test
	public void testMin3() {
		
	}
	
}
