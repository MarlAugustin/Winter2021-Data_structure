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
		long []vec1={5, 20, 25, 45, 60, -1, -1};
		long []vec2={10,15,30,40,50,-1};
		long []vecF= new long [13];
		
		AT05E01.fusionVec(vec1, 5, vec2, 5, vecF, true);
		for (int i=0; i<10; i++) {
			System.out.println(vecF[i]);
		}
		long vecTest[]= {5,10,15,20,25,30,40,45,50,60,-1,-1,-1};
		assertArrayEquals(vecF,vecTest );
	}
	/**
	@Test
	public void triSel() {
		assertEquals(30.75, AT05E01.triSel(vec, vec.length),0.01);
	}
	@Test
	public void ajout() {
		assertEquals(30.75, AT05E01.ajout(vec, vec.length, 0, false),0.01);
	}
	@Test
	public void rechercheBin() {
		assertEquals(30.75, AT05E01.rechercheBin(vec, vec.length, 0),0.01);
	}
	@Test
	public void rechercheSeq() {
		assertEquals(30.75, AT05E01.rechercheSeq(vec, 10, vec.length, false),0.01);
	}
	@Test
	public void retrait() {
		assertEquals(30.75, AT05E01.retrait( vec, 10, 1000, 'b') ,0.01);
	}
*/
}
