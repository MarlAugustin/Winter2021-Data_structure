package Sandbox.calcul;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinMaxTest2 {

	@Test
	public void test() {
		assertEquals(89, MinMax.max3(10, 45, 89));
		assertEquals(45, MinMax.max3(20, 45, 30));
		assertEquals(78, MinMax.max3(78, 13, 45));
		assertEquals(45, MinMax.max3(45, 45, 45));
		assertEquals(100, MinMax.max3(-152, -4, 300));
		assertEquals(0, MinMax.max3(-4, -300, -7));
		assertEquals(75, MinMax.max3(0, 75, 4));
		assertEquals(100, MinMax.max3(78, 100, 8));
		assertEquals(100, MinMax.max3(30, -252, 100));

	}

}
