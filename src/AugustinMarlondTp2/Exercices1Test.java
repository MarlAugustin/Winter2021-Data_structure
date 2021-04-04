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
		int[] vecTest=Exercices1.extraitValeurs(vecA, true);
		assertArrayEquals(vecB,vecTest);
		int[] vecTest2=Exercices1.extraitValeurs(vecA, false);
		assertArrayEquals(vecF,vecTest2);
	}

	@Test
	public void fusionVec() {
		int[] vecTest=Exercices1.fusionVec(vecB, vecF);
		int[] vecA = {14,14,19,5,20,20,4,18};
		assertArrayEquals(vecA,vecTest);
		int[] vecB = {15 ,12 ,20 ,25 ,3 ,12};
		int[] vecF =  {19 ,5};
		int[] vecTest2=Exercices1.fusionVec(vecB, vecF);
		int[] vecFusion =  {15,12,19,5,20,25,3,12};
		assertArrayEquals(vecFusion,vecTest2);
	}
	@Test
	public void triSel() {
		Exercices1.triSel(vecA);
		assertArrayEquals(vecFusion,vecA);
		Exercices1.triSel(vecF);
		int[] vecTest= {5,19};
		assertArrayEquals(vecTest,vecF);
	}

	@Test
	public void rechercheBin() {
		assertEquals(6,Exercices1.rechercheBin(vecFusion,20));
		assertEquals(0,Exercices1.rechercheBin(vecA,4));

	}
}
