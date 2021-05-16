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
	}

}
