package AugustinMarlondTp3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TP03E02Test {
	@Test
	public void validerVille() {
	assertEquals("Laval",TP03E02.validerVille("laval"));
	}
	@Test
	public void testValiderTelephone() {
	assertEquals("(450)555-1234",TP03E02.validerTelephone("4505551234"));
	}

}
