
import java.util.Arrays;
import java.util.Objects;

public class Produits {
	
	int nbProduits;	
	int[] tabPrix ;
	int[] tabQte;


	public Produits(int nbProduits,int[] tabPrix, int[] tabQte) {
		this.nbProduits = nbProduits;
		this.tabPrix = tabPrix;
		this.tabQte = tabQte;
	}
	
	public void ajouterProduit(int prix, int qte) {
		int nb = this.nbProduits++;
		
		if (nb<tabPrix.length) {
			tabPrix[nb]=prix;
			tabQte[nb]=qte;
			this.nbProduits=nb;
		} else
			System.out.println("Le tableau est déjà plein");
	}@Override
	public boolean equals(Object o) {
		Produits produit = (Produits) o;
		return Objects.equals(nbProduits, nbProduits)
		&& Objects.equals(tabPrix, tabPrix)
		&& Objects.equals(tabQte ,tabQte);
		}
		
	
}