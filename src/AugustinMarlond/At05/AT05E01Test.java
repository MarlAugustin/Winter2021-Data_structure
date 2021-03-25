package AugustinMarlond.At05;

import static org.junit.Assert.*;

import org.junit.Test;

import AugustinMarlond.At04.introductionTableaux;

public class AT05E01Test {
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
	
	@Test
	public void triSel() {
	long []vec1={5, 20, 25, 45, 60, 1, 0};
	long []vec2= {99,43,24,96,0,54,10};
	long []vecTest={0,1,5, 20, 25, 45, 60,};
	long []vecTest2={0,10,24,43,54,96, 99};
		AT05E01.triSel(vec1, vec1.length);
		AT05E01.triSel(vec2, vec2.length);
		assertArrayEquals(vec1,vecTest );
		assertArrayEquals(vec2,vecTest2 );

	}
	@Test
	public void ajout() {
	long []vec1={5, 20, 25, 45, 60, 1, -1};
	long []vecTest={5, 20, 25, 45, 60, 1,65};
	AT05E01.ajout(vec1, 6, 65, true);
	assertArrayEquals(vec1,vecTest);
	long []vec2={0,10,24,43,54,96, -1};
	long []vecTest2={0,10,24,43,54,96, 99};
	AT05E01.ajout(vec2,6, 99, true);
	assertArrayEquals(vec2,vecTest2);
	//bizarrement ça marche pas
	}
	@Test
	public void rechercheBin() {
	long []vec1={5, 20, 25, 45, 60, 1, -1};
	long []vec2={0,10,24,43,54,96, 99};
	AT05E01.rechercheBin(vec1, vec1.length, 45);
	AT05E01.rechercheBin(vec2, vec2.length, 45);
	assertEquals(3, AT05E01.rechercheBin(vec1, vec1.length, 45),0.1);
	assertEquals(5, AT05E01.rechercheBin(vec2, vec2.length, 96),0.1);

	}
	@Test
	public void rechercheSeq() {
		long []vec1={5, 20, 25, 45, 60, 1, -1};
		long []vec2={0,10,24,43,54,96, 99};
		AT05E01.rechercheSeq(vec1, vec1.length, 45, true);
		AT05E01.rechercheSeq(vec2, vec2.length, 45, false);
		assertEquals(-1, AT05E01.rechercheSeq(vec1, 10, vec1.length, true),0.01);
		assertEquals(-1, AT05E01.rechercheSeq(vec2, 10, vec2.length, true),0.01);
	}
	@Test
	public void retrait() {
			long []vec1={5, 20, 25, 45, -1, 1, -1};
			long vecTest[]={5, 20, 25, 45, -1, 1, -1};
 			AT05E01.retrait( vec1, 7, 60, 'S');
 			assertArrayEquals(vec1,vecTest);
 			long []vec2={0,1,25, 43, 25, 45, 60,-1};
 		long []vecTest2={0,1,25, 43, 25, 45, -1,-1};
 			AT05E01.retrait( vec2, vec2.length, 60, 'B');
 			assertArrayEquals(vec2,vecTest2);
	}

}
