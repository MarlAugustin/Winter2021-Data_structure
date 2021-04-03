package AugustinMarlondTp2;

import static org.junit.Assert.*;

import org.junit.Test;

public class Exercices1Test {
	int[] vecA = {14 ,14 ,20 ,20 ,4 ,18 ,19 ,5};
	int[] vecB = {14 ,14 ,20 ,20 ,4 ,18};
	int[] vecF =  {19 ,5};
	int[] vecFusion =  {4 ,5 ,14 ,14 ,18 ,19 ,20 ,20};
	
	@Test
	public void extraitValeurs() {
		
		fail("Not yet implemented");
	}

	@Test
	public void fusionVec() {
		int[] vecTest=Exercices1.fusionVec(vecB, vecF);
		assertTrue(vecA,vecTest);
	}
	@Test
	public void triSel() {
		Exercices1.triSel(vecA);
		assertArrayEquals(vecFusion,vecA);
	}

	@Test
	public void rechercheBin() {
		fail("Not yet implemented");
	}
}
