package AugustinMarlond;

import static org.junit.Assert.*;

import org.junit.Test;

public class atelier2Test {

	@Test
	public void testmoyenne() {
		// moyenne
		assertEquals(20, atelier2.moyenne(20, 40, 0), 1);
		assertEquals(20, atelier2.moyenne(40, 20, 0), 1);
		assertEquals(20, atelier2.moyenne(0, 20, 40), 1);
		assertEquals(125, atelier2.moyenne(125, 125, 125), 1);
		assertEquals(90, atelier2.moyenne(125, 75, 55), 1);//reconnait l'erreur

	}

	@Test
	public void testordree() {
		// ordre
		assertEquals("croissant", atelier2.ordre(0, 20, 40));
		assertEquals("décroissant", atelier2.ordre(40, 20, 0));
		assertEquals("décroissant", atelier2.ordre(40, 40, 0));
		assertEquals("constant", atelier2.ordre(40, 40, 40));
		assertEquals("quelconque", atelier2.ordre(40, 100, 20));
		assertEquals("quelconque", atelier2.ordre(40, 40, 40)); //reconnait l'erreur
	}

	@Test
	public void testexpMax() {
		// expMax
		assertEquals(4, atelier2.expMax(2, 20), 1);
		assertEquals(-1, atelier2.expMax(20,2), 1);
		assertEquals(2, atelier2.expMax(8, 100), 0.1);
		assertEquals(0, atelier2.expMax(1, 0), 0.1);
		assertEquals(-1, atelier2.expMax(0, 1), 0.1);
	}

	@Test
	public void somme() {
		// somme
		assertEquals(55, atelier2.somme(10), 1);
		assertEquals(21, atelier2.somme(6), 1);
		assertEquals(-1, atelier2.somme(-1), 1);
		assertEquals(12, atelier2.somme (6 ),1); //ça reconnait l'erreur
		assertEquals(120,atelier2.somme (15),1); 
 
	}

	@Test
	public void testconversionNote() {
		// conversion
		assertEquals('A', atelier2.conversionNote(98), 1);
		assertEquals('F', atelier2.conversionNote(10), 1);
		assertEquals('F', atelier2.conversionNote(59), 1);
		assertEquals('D', atelier2.conversionNote(65), 1);
		assertEquals('X', atelier2.conversionNote(2500), 1);
		assertEquals('A', atelier2.conversionNote(0), 1); //ça reconnait l'erreur

	}

}
