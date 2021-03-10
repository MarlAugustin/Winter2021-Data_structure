package AugustinMarlond.At04;

import static org.junit.Assert.*;

import org.junit.Test;



public class introductionTableauxTest {
	int[] donneesJour = {43,23,45,12};
	@Test
	public void calculeMoyenne() {
		for (int i=0;i<donneesJour.length;i++) {
			assertEquals(31, introductionTableaux.calculeMoyenne(donneesJour));
	
		}
	}

}
