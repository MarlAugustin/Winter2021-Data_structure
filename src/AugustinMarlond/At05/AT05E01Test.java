package AugustinMarlond.At05;

import static org.junit.Assert.*;

import org.junit.Test;

import AugustinMarlond.At04.introductionTableaux;

public class AT05E01Test {
 long[] vec= {3171100,
			52596500,
			135083900,
			307061900,
			545505400,
			1127109400,
			1689147300,
};
 boolean ordonnee;
	
	@Test
	public void fusionVec() {
		assertEquals(30.75, AT05E01.fusionVec(vec, vec.length, null, 0, null, false),0.01);
		assertEquals(30.75, AT05E01.fusionVec(vec, vec.length, null, 0, null, true),0.01);

	}
	public void triSel() {
		assertEquals(30.75, AT05E01.triSel(vec, vec.length),0.01);
	}
	public void ajout() {
		assertEquals(30.75, AT05E01.ajout(vec, vec.length, 0, false),0.01);
	}
	public void rechercheBin() {
		assertEquals(30.75, AT05E01.rechercheBin(vec, vec.length, 0),0.01);
	}
	public void rechercheSeq() {
		assertEquals(30.75, AT05E01.rechercheSeq(vec, 10, vec.length, false),0.01);
	}
	public void retrait() {
		assertEquals(30.75, AT05E01.retrait( vec, 10, 1000, 'b') ,0.01);
	}

}
