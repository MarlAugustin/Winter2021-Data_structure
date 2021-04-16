package AugustinMarlond.At07;

import static org.junit.Assert.*;

import org.junit.Test;

public class AT07E01BaseTest {

	@Test
	public void decaleLettre() {
		assertEquals('D',AT07E01Base.decaleLettre('A', 3));
		assertEquals('A',AT07E01Base.decaleLettre('Z', -25));
		assertEquals('Z',AT07E01Base.decaleLettre('A', -1));
	}

	@Test
	public void transformeMessage() {
		assertEquals("Erqmrxu Fhvdu!",AT07E01Base.transformeMessage("Bonjour Cesar!",3));
		assertEquals("Bonjour Cesar!",AT07E01Base.transformeMessage("Erqmrxu Fhvdu!",-3));
		assertEquals("bcdef",AT07E01Base.transformeMessage("abcde",1));

	}	@Test
	public void nbMots() {
		assertEquals(2,AT07E01Base.nbMots("Bonjour Cesar!"));
		assertEquals(8,AT07E01Base.nbMots("Bonjour tout le monde ceci est un test"));
		assertEquals(1,AT07E01Base.nbMots("abcde"));
	}
	@Test
	public void longueurMoyenneMot() {
		float resultat=AT07E01Base.longueurMoyenneMot("Bonjour Cesar!");
		assertTrue(6.5==resultat);
		float resultat2=AT07E01Base.longueurMoyenneMot("Erqmrxu Fhvdu!");
		assertTrue(6.5==resultat2);
		float resultat3=AT07E01Base.longueurMoyenneMot("abcde");
		assertTrue(5==resultat3);

	}
	@Test
	public void frequenceLettre() {
		int[] tabFreq =  { 1, 1, 1, 0, 1, 0,  0,   0,   0,   1,   0,   0,   0,   1,   2,   0,   0,   2,   1,   0,   1,   0,   0,   0,  0 ,  0};
		assertArrayEquals(tabFreq,AT07E01Base.frequenceLettre("Bonjour Cesar!"));
		int[] tabFreq2 ={0,   0,   0  , 1,   1 ,  1 ,  0  , 1 ,  0 ,  0 ,  0 ,  0 ,  1  , 0 ,  0  , 0  , 1  , 2 ,  0 ,  0 ,  2 ,  1  , 0 ,  1 ,  0  , 0};
		assertArrayEquals(tabFreq2,AT07E01Base.frequenceLettre("Erqmrxu Fhvdu!"));
	}
	
}
