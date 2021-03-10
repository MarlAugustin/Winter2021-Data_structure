
public class Produit {

	public static void main(String[] args) {
		final long LIMITE = 35000000;
		if (calculerPoduit(20) > LIMITE) {
			System.out.println("Limite dépassée");
		}
	}

	public static long calculerPoduit(int maximum) {
		long produit = 1;
		for (int i = 2; i <= maximum; i += 3) {
			produit *= i * 2 - 1;

		}
		return produit;
	}

}