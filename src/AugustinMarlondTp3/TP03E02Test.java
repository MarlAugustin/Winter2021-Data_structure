package AugustinMarlondTp3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TP03E02Test {
	@Test
	public void validerVille() {
	assertEquals("Laval",TP03E02.validerVille("laval"));
	assertEquals("Montreal",TP03E02.validerVille("montreal"));
	assertEquals("Massachusets",TP03E02.validerVille("massachusets"));
	assertEquals("Tokyo",TP03E02.validerVille("Tokyo"));
	assertEquals("Mexico",TP03E02.validerVille("Mexico"));

	}
	@Test
	public void testValiderTelephone() {
	assertEquals("(450)555-1234",TP03E02.validerTelephone("4505551234"));
	assertEquals("(438)555-1234",TP03E02.validerTelephone("438 555 1234"));
	assertEquals("(450)555-1234",TP03E02.validerTelephone("(450) 555 1234"));
	assertEquals("(226)774-1234",TP03E02.validerTelephone("226774-1234"));
	assertEquals("(514)900-3341",TP03E02.validerTelephone("5149003341"));
	}@Test
	public void testValiderLigne() {
		String [] ligne= {"Leduc", "Nicolas","125 des Prairies","Laval","H7M 2S8" ,"(450)967-0221:moi@ecole.ca:" };
		assertFalse(TP03E02.validerLigne(ligne));
		String [] ligne2= {"Laloge","Will","320 rue Bons","Laval","A0A 0A0","(514)555-5555","laloge@hotmail.com"};
		assertTrue(TP03E02.validerLigne(ligne2));
		String [] ligne3= {"Desbiens","Luc","242 Giroux","Laval","H7N 4J1","(450)229-4229","ldb@gmail.com"};
		assertTrue(TP03E02.validerLigne(ligne3));
		String [] ligne4= {"Godbout","Vincent","1567 chemin Codos","Tremblant","N4S 0C0","(819)437-2963","vgodbout@fastmail.com"};		
		assertTrue(TP03E02.validerLigne(ligne4));
		String [] ligne5= {"Socrate","Luc","242 Giroux","Laval","H7N 4J1","(450)229-4229","ldb@gmail.com"};
		assertTrue(TP03E02.validerLigne(ligne5));

	}

}
