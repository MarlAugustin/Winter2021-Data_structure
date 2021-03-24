package AugustinMarlondTp1;
import static org.junit.Assert.*;

	import org.junit.Test;

	import AugustinMarlondTp1.programmeTp1;
	
public class programmeTp1Test {

	@Test
	public void joursParMois() {
		assertEquals(31, programmeTp1.joursParMois(12, 2020));
		assertEquals(29, programmeTp1.joursParMois(2, 2020));
		assertEquals(28, programmeTp1.joursParMois(2, 2021));
		assertEquals(31, programmeTp1.joursParMois(1, 2021));
	}
	@Test
	public void reculeDate() {
		assertEquals("20 NOV 2020", programmeTp1.reculeDate(21,12, 2020,31));
		assertEquals("20 FEV 1930", programmeTp1.reculeDate(25,2, 1930,5));
		assertEquals("02 JUL 2000", programmeTp1.reculeDate(30,9, 2000,90));
		assertEquals("31 DEC 1999", programmeTp1.reculeDate(1,1, 2000,1));
	}
	@Test
	public void jourDatePrecedente() {
		assertEquals(20, programmeTp1.jourDatePrecedente(21,12, 2020));
		assertEquals(24, programmeTp1.jourDatePrecedente(25,2, 1930));
		assertEquals(29, programmeTp1.jourDatePrecedente(30,9, 2000));
		assertEquals(31, programmeTp1.jourDatePrecedente(1,1, 2000));
		assertEquals(15, programmeTp1.jourDatePrecedente(16,5, 2000));
		assertEquals(15, programmeTp1.jourDatePrecedente(16,5, 2000));

	}
	@Test
	public void moisDatePrecedente() {
		assertEquals(12, programmeTp1.moisDatePrecedente(21,12, 2020));
		assertEquals(2, programmeTp1.moisDatePrecedente(31,2, 1930));
		assertEquals(8, programmeTp1.moisDatePrecedente(01,9, 2000));
		assertEquals(12, programmeTp1.moisDatePrecedente(1,1, 2000));
		assertEquals(5, programmeTp1.moisDatePrecedente(16,5, 2000));
	}
	@Test
	public void anneeDatePrecedente() {
		assertEquals(2020, programmeTp1.anneeDatePrecedente(21,12, 2020));
		assertEquals(1930, programmeTp1.anneeDatePrecedente(31,1, 1930));
		assertEquals(1998, programmeTp1.anneeDatePrecedente(01,1, 1999));
		assertEquals(1999, programmeTp1.anneeDatePrecedente(1,1, 2000));
		assertEquals(2000, programmeTp1.anneeDatePrecedente(16,5, 2000));
	}
}
