package Commun;

import java.util.*;
import java.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

/**
 * Fen�tre de dessin
 *
 * <p>
 * Cette classe permet d'�crire des applications graphiques simples en dessinant
 * dans une fen�tre.
 *
 * <p>
 * <b>NB.</b> Pour toutes les m�thodes de dessin, le coin en haut � gauche de la
 * fen�tre a les coordonn�es (0, 0). Le coin en bas � droite de la fen�tre a les
 * coordonn�es (largeur - 1, hauteur - 1), si la fen�tre est de dimension
 * largeur � hauteur.
 *
 * @author Nicolas Leduc &lt;nleduc@cmontmorency.qc.ca&gt;
 * @version 20190911
 */
public class FenetreDessin {

	/** Largeur de la fen�tre */
	public final int largeur;

	/** Hauteur de la fen�tre */
	public final int hauteur;

	/**
	 * Construit une nouvelle fen�tre de dessin avec le titre et les dimensions
	 * pass�s en param�tres.
	 *
	 * @param aTitre
	 *            titre de la fen�tre
	 * @param aLargeur
	 *            largeur de la fen�tre
	 * @param aHauteur
	 *            hauteur de la fen�tre
	 */
	public FenetreDessin(String aTitre, int aLargeur, int aHauteur) {

		titre = new String(aTitre);
		largeur = aLargeur;
		hauteur = aHauteur;

		// Initialisation des variables pour la gestion des evenements
		sourisVerrou = new Object();
		sourisEvenement = null;
		sourisPosition = new Point(0, 0);
		sourisBouton = 0;

		clavierVerrou = new Object();
		clavierTamponEvenement = new LinkedList<KeyEvent>();
		clavierTouche = 0;
		clavierTamponne = false; // N'utilise pas de tampon pour les evenements du clavier

		imageVerrou = new Object();

		dessinDirect = true; // Envoye un message paint apres chaque ajout au dessin

		image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_ARGB);
		graphique = image.createGraphics();
		graphique.setFont(new Font("Courier New", Font.PLAIN, 16)); // Caractere 10x16
		graphique.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Creation du thread de la fenetre de dessin
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					creerGUI();
				}
			});
		} catch (Exception e) {
			System.err.println("Erreur: interruption lors de la creation du GUI");
			System.err.println("Exception: " + e);
			System.exit(1);
		}

		// Initialise le rappel pour l'animation a 30 images par seconde
		imageChrono = new Timer();
		imageChrono.scheduleAtFixedRate(new FDImageChronoTache(), 0, 1000 / 30);

		// Affiche une image vide blanche
		couleurCrayon(Color.BLACK);
		couleurRemplissage(Color.WHITE);
		effaceFenetre();
		imageSynchronise();
	}

	// ******************** PRIMITIVES DE DESSIN ********************
	/**
	 * Change la couleur de dessin des lignes en utilisant la classe Couleur.
	 * 
	 * Les couleurs disponibales sont: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN,
	 * LIGHT_GRAY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW
	 *
	 * @param aCouleur
	 *            couleur
	 * 
	 * @see java.awt.Color
	 */
	public void couleurCrayon(Color aCouleur) {
		graphique.setColor(aCouleur);
	}

	/**
	 * Change la couleur de dessin des lignes.
	 *
	 * Les composantes de rouge, vert et bleu de la couleur doivent �tre compris
	 * entre 0 et 1. Si les trois composantes sont � 0, on obtient du noir; si les
	 * trois composantes sont � 1, on obtient du blanc.
	 *
	 * @param aRouge
	 *            composante de rouge
	 * @param aVert
	 *            composante de vert
	 * @param aBleu
	 *            composante de bleu
	 */
	public void couleurCrayon(float aRouge, float aVert, float aBleu) {
		couleurCrayon(new Color(aRouge, aVert, aBleu));
	}

	/**
	 * Change la couleur de remplissage en utilisant la classe Couleur.
	 * 
	 * Les couleurs disponibales sont: BLACK, BLUE, CYAN, DARK_GRAY, GRAY, GREEN,
	 * LIGHT_GRAY, MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW
	 *
	 * @param aCouleur
	 *            couleur
	 * 
	 * @see java.awt.Color
	 */
	public void couleurRemplissage(Color aCouleur) {
		couleurRemplissage = aCouleur;
	}

	/**
	 * Change la couleur de remplissage des formes.
	 *
	 * Les composantes de rouge, vert et bleu de la couleur doivent �tre compris
	 * entre 0 et 1. Si les trois composantes sont � 0, on obtient du noir; si les
	 * trois composantes sont � 1, on obtient du blanc.
	 *
	 * @param aRouge
	 *            composante de rouge
	 * @param aVert
	 *            composante de vert
	 * @param aBleu
	 *            composante de bleu
	 */
	public void couleurRemplissage(float aRouge, float aVert, float aBleu) {
		couleurRemplissage(new Color(aRouge, aVert, aBleu));
	}

	/**
	 * Efface la fen�tre.
	 *
	 * La fen�tre est effac�e avec la couleur de remplissage courante.
	 */
	public void effaceFenetre() {
		synchronized (image) {
			Color c = graphique.getColor();
			graphique.setColor(couleurRemplissage);
			graphique.fillRect(0, 0, largeur, hauteur);
			graphique.setColor(c);
		}
		if (dessinDirect)
			panel.repaint();
	}

	/**
	 * Dessine un point.
	 *
	 * Dessine un point (pixel) aux coordonn�es (x, y), avec la couleur de crayon
	 * courante.
	 * 
	 * @param x
	 *            position en x du point
	 * @param y
	 *            position en y du point
	 */
	public void dessinePoint(int x, int y) {
		if (x < 0 || y < 0 || x >= largeur || y >= hauteur)
			return;
		synchronized (image) {
			image.setRGB(x, y, graphique.getColor().getRGB());
		}
		if (dessinDirect)
			panel.repaint(x, y, 1, 1);
	}

	/**
	 * Dessine un segment.
	 *
	 * Dessine un segement de droite entre les coordonn�es (x1, y1) et (x2, y2),
	 * avec la couleur de crayon courante.
	 * 
	 * @param x1
	 *            position en x du debut du segment
	 * @param y1
	 *            position en y du debut du segment
	 * @param x2
	 *            position en x de la fin du segment
	 * @param y2
	 *            position en y de la fin du segment
	 */
	public void dessineSegment(int x1, int y1, int x2, int y2) {
		synchronized (image) {
			graphique.drawLine(x1, y1, x2, y2);
		}
		if (dessinDirect)
			panel.repaint(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2) + 1, Math.abs(y1 - y2) + 1);
	}

	/**
	 * Dessine un rectangle.
	 *
	 * Dessine le rectangle parall�le aux axes et d�fini par les coordonn�es de deux
	 * sommets oppos�s (x1, y1) et (x2, y2). Utilise la couleur de crayon courante.
	 * 
	 * @param x1
	 *            position en x d'un premier coin du rectangle
	 * @param y1
	 *            position en y d'un premier coin du rectangle
	 * @param x2
	 *            position en x du coin oppose du rectangle
	 * @param y2
	 *            position en y du coin oppose du rectangle
	 */
	public void dessineRectangle(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x1 - x2);
		int h = Math.abs(y1 - y2);
		synchronized (image) {
			graphique.drawRect(x, y, w, h);
		}
		if (dessinDirect)
			panel.repaint(x, y, w + 1, h + 1);
	}

	/**
	 * Dessine un rectangle plein.
	 *
	 * Dessine le rectangle plein parall�le aux axes et d�fini par les coordonn�es
	 * de deux sommets oppos�s (x1, y1) et (x2, y2). Le rectangle sera peint avec la
	 * couleur de remplissage et son contour sera de la couleur du crayon.
	 * 
	 * @param x1
	 *            position en x d'un premier coin du rectangle
	 * @param y1
	 *            position en y d'un premier coin du rectangle
	 * @param x2
	 *            position en x du coin oppose du rectangle
	 * @param y2
	 *            position en y du coin oppose du rectangle
	 */
	public void dessineRectanglePlein(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int w = Math.abs(x1 - x2) + 1;
		int h = Math.abs(y1 - y2) + 1;
		synchronized (image) {
			Color c = graphique.getColor();
			graphique.setColor(couleurRemplissage);
			graphique.fillRect(x, y, w, h);
			graphique.setColor(c);
			graphique.drawRect(x, y, w, h);
		}
		if (dessinDirect)
			panel.repaint(x, y, w + 1, h + 1);
	}

	/**
	 * Dessine un cercle.
	 *
	 * Dessine un cercle de centre (x, y) et de rayon r. Utilise la couleur de
	 * crayon courante.
	 * 
	 * @param x
	 *            position en x du centre du cercle
	 * @param y
	 *            position en y du centre du cercle
	 * @param r
	 *            rayon du cercle
	 */
	public void dessineCercle(int x, int y, int r) {
		dessineEllipse(x, y, 2 * r, 2 * r);
		// synchronized (image) {
		// graphique.drawOval(x - r, y - r, 2 * r, 2 * r);
		// }
		// if (dessinDirect)
		// panel.repaint(x - r, y - r, 2 * r + 1, 2 * r + 1);
	}

	/**
	 * Dessine un cercle plein (disque).
	 *
	 * Dessine un cercle plein (disque) de centre (x, y) et de rayon r. Le disque
	 * sera peint avec la couleur de remplissage et son contour sera de la couleur
	 * du crayon.
	 * 
	 * @param x
	 *            position en x du centre du cercle
	 * @param y
	 *            position en y du centre du cercle
	 * @param r
	 *            rayon du cercle
	 */
	public void dessineCerclePlein(int x, int y, int r) {
		dessineEllipsePlein(x, y, 2 * r, 2 * r);
		// synchronized (image) {
		// Color c = graphique.getColor();
		// graphique.setColor(couleurRemplissage);
		// graphique.fillOval(x - r, y - r, 2 * r, 2 * r);
		// graphique.setColor(c);
		// graphique.drawOval(x - r, y - r, 2 * r, 2 * r);
		// }
		// if (dessinDirect)
		// panel.repaint(x - r, y - r, 2 * r + 1, 2 * r + 1);
	}

	/**
	 * Dessine une ellipse.
	 * 
	 * Dessine une ellipse de centre (x, y) et ayant une largeur de aLarageur et une
	 * hauteur de aHauteur. Utilise la couleur de crayon courante.
	 * 
	 * @param x
	 *            position en x du centre de l'ellipse
	 * @param y
	 *            posititon en y du centre de l'ellipse
	 * @param aLargeur
	 *            largeur de l'ellipse
	 * @param aHauteur
	 *            hauteur de l'ellipse
	 */
	public void dessineEllipse(int x, int y, int aLargeur, int aHauteur) {
		synchronized (image) {
			graphique.drawOval(x - aLargeur / 2, y - aHauteur / 2, aLargeur, aHauteur);
		}
		if (dessinDirect)
			panel.repaint(x - aLargeur / 2, y - aHauteur / 2, aLargeur + 1, aHauteur + 1);
	}

	/**
	 * Dessine une ellipse pleine.
	 * 
	 * Dessine une ellipse de centre (x, y) et ayant une largeur de aLarageur et une
	 * hauteur de aHauteur. L'ellipse sera peinte avec la couleur de remplissage et
	 * son contour sera de la couleur du crayon.
	 * 
	 * @param x
	 *            position en x du centre de l'ellipse
	 * @param y
	 *            position en y du centre de l'ellipse
	 * @param aLargeur
	 *            largeur de l'ellipse
	 * @param aHauteur
	 *            hauteur de l'ellipse
	 */
	public void dessineEllipsePlein(int x, int y, int aLargeur, int aHauteur) {
		synchronized (image) {
			Color c = graphique.getColor();
			graphique.setColor(couleurRemplissage);
			graphique.fillOval(x - aLargeur / 2, y - aHauteur / 2, aLargeur, aHauteur);
			graphique.setColor(c);
			graphique.drawOval(x - aLargeur / 2, y - aHauteur / 2, aLargeur, aHauteur);
		}
		if (dessinDirect)
			panel.repaint(x - aLargeur / 2, y - aHauteur / 2, aLargeur + 1, aHauteur + 1);
	}

	/**
	 * Dessine un arc de cercle / d'ellipse.
	 * 
	 * Dessine un arc de cercle / d'ellipse allant de l'angle aAngleInitial a
	 * l'angle aAngleFinal. Les angles sont en degres (0 a 360). L'angle 0
	 * correspond a l'axe des x positifs et l'angle augmente en sens inverse des
	 * aiguilles d'une montre. L'ellipse est centree en (x, y) et a une largeur de
	 * aLargeur et une hauteur de aHauteur. Utilise la couleur de crayon courante.
	 * 
	 * @param x
	 *            position en x du centre de l'ellipse
	 * @param y
	 *            position en y du centre de l'ellipse
	 * @param aLargeur
	 *            largeur de l'ellipse
	 * @param aHauteur
	 *            hauteur de l'ellipse
	 * @param aAngleInitial
	 *            angle initial de l'arc (en degres)
	 * @param aAngleFinal
	 *            angle final de l'arc (en degres)
	 */
	public void dessineArc(int x, int y, int aLargeur, int aHauteur, int aAngleInitial, int aAngleFinal) {
		int angleArc = aAngleFinal % 360 - aAngleInitial % 360;
		angleArc = angleArc <= 0 ? angleArc + 360 : angleArc;

		synchronized (image) {
			graphique.drawArc(x - aLargeur / 2, y - aHauteur / 2, aLargeur, aHauteur, aAngleInitial, angleArc);
		}
		if (dessinDirect)
			panel.repaint(x - aLargeur / 2 - 1, y - aHauteur / 2 - 1, aLargeur + 2, aHauteur + 2);
	}

	/**
	 * Dessine un arc de cercle / d'ellipse plein (pointe de tarte).
	 * 
	 * Dessine un arc de cercle / d'ellipse plein allant de l'angle aAngleInitial a
	 * l'angle aAngleFinal. Les angles sont en degres (0 a 360). L'angle 0
	 * correspond a l'axe des x positifs et l'angle augmente en sens inverse des
	 * aiguilles d'une montre. L'ellipse est centree en (x, y) et a une largeur de
	 * aLargeur et une hauteur de aHauteur. L'arc sera peint avec la couleur de
	 * remplissage et son contour sera de la couleur du crayon.
	 * 
	 * @param x
	 *            position en x du centre de l'ellipse
	 * @param y
	 *            position en y du centre de l'ellipse
	 * @param aLargeur
	 *            largeur de l'ellipse
	 * @param aHauteur
	 *            hauteur de l'ellipse
	 * @param aAngleInitial
	 *            angle initial de l'arc (en degres)
	 * @param aAngleFinal
	 *            angle final de l'arc (en degres)
	 */
	public void dessineArcPlein(int x, int y, int aLargeur, int aHauteur, int aAngleInitial, int aAngleFinal) {
		int angleArc = aAngleFinal % 360 - aAngleInitial % 360;
		angleArc = angleArc <= 0 ? angleArc + 360 : angleArc;

		synchronized (image) {
			Color c = graphique.getColor();
			graphique.setColor(couleurRemplissage);
			graphique.fillArc(x - aLargeur / 2, y - aHauteur / 2, aLargeur, aHauteur, aAngleInitial, angleArc);
			graphique.setColor(c);
			graphique.drawArc(x - aLargeur / 2, y - aHauteur / 2, aLargeur, aHauteur, aAngleInitial, angleArc);
			graphique.drawLine(x, y, (int) (x + aLargeur / 2 * Math.cos(aAngleInitial / 360.0 * 2 * Math.PI)),
					(int) (y - aHauteur / 2 * Math.sin(aAngleInitial / 360.0 * 2 * Math.PI)));
			graphique.drawLine(x, y, (int) (x + aLargeur / 2 * Math.cos(aAngleFinal / 360.0 * 2 * Math.PI)),
					(int) (y - aHauteur / 2 * Math.sin(aAngleFinal / 360.0 * 2 * Math.PI)));
		}
		if (dessinDirect)
			panel.repaint(x - aLargeur / 2 - 1, y - aHauteur / 2 - 1, aLargeur + 2, aHauteur + 2);
	}

	/**
	 * Dessine un triangle.
	 *
	 * Dessine un triangle d�fini par les coordonn�es de ses sommets: (x1, y1), (x2,
	 * y2) et (x3, y3). Utilise la couleur de crayon courante.
	 * 
	 * @param x1
	 *            position en x du premier sommet du triangle
	 * @param y1
	 *            position en y du premier sommet du triangle
	 * @param x2
	 *            position en x du second sommet du triangle
	 * @param y2
	 *            position en y du second sommet du triangle
	 * @param x3
	 *            position en x du troisieme sommet du triangle
	 * @param y3
	 *            position en y du troisieme sommet du triangle
	 */
	public void dessineTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		Polygon poly = new Polygon();
		poly.addPoint(x1, y1);
		poly.addPoint(x2, y2);
		poly.addPoint(x3, y3);
		synchronized (image) {
			graphique.drawPolygon(poly);
		}
		if (dessinDirect) {
			Rectangle bounds = poly.getBounds();
			bounds.setSize(bounds.width + 1, bounds.height + 1);
			panel.repaint(bounds);
		}
	}

	/**
	 * Dessine un triangle plein.
	 *
	 * Dessine un triangle plein d�fini par les coordonn�es de ses sommets: (x1,
	 * y1), (x2, y2) et (x3, y3). Le triangle sera peint avec la couleur de
	 * remplissage et son contour sera de la couleur du crayon.
	 * 
	 * @param x1
	 *            position en x du premier sommet du triangle
	 * @param y1
	 *            position en y du premier sommet du triangle
	 * @param x2
	 *            position en x du second sommet du triangle
	 * @param y2
	 *            position en y du second sommet du triangle
	 * @param x3
	 *            position en x du troisieme sommet du triangle
	 * @param y3
	 *            position en y du troisieme sommet du triangle
	 */
	public void dessineTrianglePlein(int x1, int y1, int x2, int y2, int x3, int y3) {
		Polygon poly = new Polygon();
		poly.addPoint(x1, y1);
		poly.addPoint(x2, y2);
		poly.addPoint(x3, y3);
		synchronized (image) {
			Color c = graphique.getColor();
			graphique.setColor(couleurRemplissage);
			graphique.fillPolygon(poly);
			graphique.setColor(c);
			graphique.drawPolygon(poly);
		}
		if (dessinDirect) {
			Rectangle bounds = poly.getBounds();
			bounds.setSize(bounds.width + 1, bounds.height + 1);
			panel.repaint(poly.getBounds());
		}
	}

	/**
	 * Dessine / �crit du texte.
	 *
	 * �crit le texte fourni en parametre, aux coordonn�es (x, y) avec la couleur du
	 * crayon courante.
	 * 
	 * @param x
	 *            position en x du premier caractere affiche
	 * @param y
	 *            position en y du premier caractere affiche
	 * @param aTexte
	 *            texte qui sera affiche
	 */
	public void dessineTexte(int x, int y, String aTexte) {
		synchronized (image) {
			graphique.drawString(aTexte, x, y - 3);
		}
		if (dessinDirect)
			panel.repaint(x, y - 16, aTexte.length() * 10 + 1, 17);
	}

	/**
	 * Lit et retourne la couleur d'un pixel.
	 *
	 * Retourne la couleur du pixel de coordonn�es (x, y). Pour recuperer les
	 * composantes RVB, vous devez les extraire du int : 0x00RRVVBB
	 *
	 * @param x
	 *            position en x du pixel a lire
	 * @param y
	 *            position en y du pixel a lire
	 * 
	 * @return couleur du pixel
	 */
	public int litCouleurPixel(int x, int y) {
		return (x < 0 || y < 0 || x >= largeur || y >= hauteur) ? 0 : image.getRGB(x, y);
	}

	/**
	 * Lit et retourne la couleur d'un pixel.
	 *
	 * Retourne la composante rouge de la couleur du pixel de coordonn�es (x, y).
	 *
	 * @param x
	 *            position en x du pixel a lire
	 * @param y
	 *            position en y du pixel a lire
	 * 
	 * @return composante rouge de la couleur du pixel
	 */
	public float litCouleurPixelR(int x, int y) {
		return (x < 0 || y < 0 || x >= largeur || y >= hauteur) ? 0.0f : ((image.getRGB(x, y) >> 16) & 0xFF) / 255.0f;
	}

	/**
	 * Lit et retourne la couleur d'un pixel.
	 *
	 * Retourne la composante verte de la couleur du pixel de coordonn�es (x, y).
	 *
	 * @param x
	 *            position en x du pixel a lire
	 * @param y
	 *            position en y du pixel a lire
	 * 
	 * @return composante verte de la couleur du pixel
	 */
	public float litCouleurPixelV(int x, int y) {
		return (x < 0 || y < 0 || x >= largeur || y >= hauteur) ? 0.0f : ((image.getRGB(x, y) >> 8) & 0xFF) / 255.0f;
	}

	/**
	 * Lit et retourne la couleur d'un pixel.
	 *
	 * Retourne la composante bleue de la couleur du pixel de coordonn�es (x, y).
	 *
	 * @param x
	 *            position en x du pixel a lire
	 * @param y
	 *            position en y du pixel a lire
	 * 
	 * @return composante bleue de la couleur du pixel
	 */
	public float litCouleurPixelB(int x, int y) {
		return (x < 0 || y < 0 || x >= largeur || y >= hauteur) ? 0.0f : ((image.getRGB(x, y) >> 0) & 0xFF) / 255.0f;
	}

	// ******************** LECTURE AVEC JOptionPane ********************
	/**
	 * Lit un entier.
	 * 
	 * Affiche une boite de dialogue demandant a l'usager d'entrer un entier. La
	 * boite de dialogue contient le texte passe en parametre ainsi qu'une zone pour
	 * inscrire la valeur. La fonction retourne l'entier lu.
	 * 
	 * @param aTexte
	 *            Texte qui indique ce qui est demande a l'usager
	 * 
	 * @return entier inscrit dans la boite de dialogue
	 */
	public int litEntier(String aTexte) {
		String chaineLue = JOptionPane.showInputDialog(aTexte);
		return Integer.parseInt(chaineLue);
	}

	/**
	 * Lit une valeur reelle.
	 * 
	 * Affiche une boite de dialogue demandant a l'usager d'entrer une valeur
	 * reelle. La boite de dialogue contient le texte passe en parametre ainsi
	 * qu'une zone pour inscrire la valeur. La fonction retourne la valeur reelle
	 * lue.
	 * 
	 * @param aTexte
	 *            Texte qui indique ce qui est demande a l'usager
	 * 
	 * @return valeur reelle inscrite dans la boite de dialogue
	 */
	public float litReel(String aTexte) {
		String chaineLue = JOptionPane.showInputDialog(aTexte);
		return Float.parseFloat(chaineLue);
	}

	/**
	 * Lit un caractere.
	 * 
	 * Affiche une boite de dialogue demandant a l'usager d'entrer un caractere. La
	 * boite de dialogue contient le texte passe en parametre ainsi qu'une zone pour
	 * inscrire le caractere. La fonction retourne le premier caractere de la chaine
	 * lue.
	 * 
	 * @param aTexte
	 *            Texte qui indique ce qui est demande a l'usager
	 * 
	 * @return premier caractere inscrit dans la boite de dialogue
	 */
	public char litCaractere(String aTexte) {
		return JOptionPane.showInputDialog(aTexte).charAt(0);
	}

	/**
	 * Lit un mot.
	 * 
	 * Affiche une boite de dialogue demandant a l'usager d'entrer un mot. La boite
	 * de dialogue contient le texte passe en parametre ainsi qu'une zone pour
	 * inscrire le mot. La fonction retourne le premier mot (delimite par un espace)
	 * de la chaine lue.
	 * 
	 * @param aTexte
	 *            Texte qui indique ce qui est demande a l'usager
	 * 
	 * @return premier mot inscrit dans la boite de dialogue
	 */
	public String litMot(String aTexte) {
		return (JOptionPane.showInputDialog(aTexte).split(" "))[0];
	}

	/**
	 * Lit une phrase (chaine de caracteres).
	 * 
	 * Affiche une boite de dialogue demandant a l'usager d'entrer une phrase. La
	 * boite de dialogue contient le texte passe en parametre ainsi qu'une zone pour
	 * inscrire la phrase. La fonction retourne tous les caracteres entres par
	 * l'usager sous forme de chaine de caracteres
	 * 
	 * @param aTexte
	 *            Texte qui indique ce qui est demande a l'usager
	 * 
	 * @return chaine de caracteres inscrite dans la boite de dialogue
	 */
	public String litPhrase(String aTexte) {
		return JOptionPane.showInputDialog(aTexte);
	}

	// ******************** GESTION DU CLAVIER ********************
	/**
	 * Permet d'activer/desactiver le tampon clavier.
	 * 
	 * Permet d'activer/desactiver le tampon clavier pour les touches recues par la
	 * fenetre de dessin. Lorsque l'on desactive le tampon, toutes les touches non
	 * traitees sont perdues.
	 * 
	 * @param aTamponne
	 *            true = active le tampon, false = desactive le tampon
	 */
	public void clavierTamponne(boolean aTamponne) {
		if (clavierTamponne != aTamponne && aTamponne == false) {
			clavierVideTampon();
		}
		clavierTamponne = aTamponne;
	}

	/**
	 * Vide le tampon du clavier.
	 * 
	 * Toutes les touches non traitees a l'interieur du tampon seront perdues.
	 */
	public void clavierVideTampon() {
		synchronized (clavierVerrou) {
			clavierTamponEvenement.clear();
		}
	}

	/**
	 * Attend qu'une touche soit appuyee.
	 * 
	 * Attend indefiniement qu'une touche du clavier soit appuyee lorsque la fenetre
	 * de dessin est active. NB: Toutes les touches contenues dans le tampon seront
	 * perdues.
	 * 
	 * @return true si une touche a ete apuyee, false sinon
	 */
	public boolean clavierAttendreEvenement() {
		return clavierAttendreEvenement(-1);
	}

	/**
	 * Attend qu'une touche soit appuyee a l'interieur d'un delai maximal.
	 * 
	 * Attend qu'une touche soit appuyee ou que le delai indique en parametre soit
	 * atteint. Si une touche est appuyee la fonction retourne true et la touche
	 * appuyee peut etre recuperee par la fonction {@link #clavierCharactere()} NB:
	 * Toutes les touches contenues dans le tampon seront perdues.
	 * 
	 * @param aDelai
	 *            Temps maximal d'attente en millisecondes
	 * 
	 * @return true = touche appuyee, false = delai depasse
	 */
	public boolean clavierAttendreEvenement(long aDelai) {
		synchronized (clavierVerrou) {
			if (aDelai != 0) {
				clavierTamponEvenement.clear();
				try {
					if (aDelai > 0)
						clavierVerrou.wait(aDelai);
					else // (aDelai < 0)
						clavierVerrou.wait();
				} catch (InterruptedException e) {
				}
			}
		}
		return clavierEvenement();
	}

	/**
	 * Traite l'evenement clavier suivant dans le tampon.
	 * 
	 * Retire le premier evenement clavier du tampon et extrait la touche associee.
	 * La touche est accessible en utilisant la fonction
	 * {@link #clavierCharactere()}.
	 * 
	 * @return true si une touche est disponible, false si le tampon est vide.
	 */
	public boolean clavierEvenement() {
		synchronized (clavierVerrou) {
			if (clavierTamponEvenement.size() > 0) {
				KeyEvent unEvenement = clavierTamponEvenement.remove();
				clavierTouche = unEvenement.getKeyChar();
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne le dernier caractere lu.
	 * 
	 * Retourne le dernier caractere lu par la fenetre de dessin a la suite de
	 * l'attente d'une touche ou celui extrait du tampon. Le caractere reste
	 * disponible jusqu'a ce qu'un nouvel evenement le remplace.
	 * 
	 * @return dernier caractere lu / extrait du tampon
	 */
	public char clavierCharactere() {
		return clavierTouche;
	}

	// ******************** GESTION DE LA SOURIS ********************
	/**
	 * Attend l'appui de l'un des boutons de la souris.
	 *
	 * Attend indefiniement qu'un bouton de la souris soit appuye lorsque la fenetre
	 * de dessin est active.
	 * 
	 * @return true lorsqu'un bouton a �t� press�
	 */
	public boolean sourisAttendreEvenement() {
		return sourisAttendreEvenement(-1);
	}

	/**
	 * Attend l'appui d'un bouton de la souris a l'interieur d'un delai maximal.
	 * 
	 * Attend qu'un bouton de la souris soit appuye ou que le delai indique en
	 * parametre soit atteint. Si un bouton est appuyee, la fonction retourne true
	 * et les informations sont accessibles a l'aide des fonctions:
	 * {@link #sourisPositionX()}, {@link #sourisPositionY()} et
	 * {@link #sourisBouton()}.
	 * 
	 * @param aDelai
	 *            Temps maximal d'attente en millisecondes
	 * 
	 * @return true = bouton appuye, false = delai depasse
	 */
	public boolean sourisAttendreEvenement(long aDelai) {
		synchronized (sourisVerrou) {
			if (aDelai != 0) {
				sourisEvenement = null;
				try {
					if (aDelai > 0)
						sourisVerrou.wait(aDelai);
					else // (aDelai < 0)
						sourisVerrou.wait();
				} catch (InterruptedException e) {
				}
			}
		}
		return sourisEvenement();
	}

	/**
	 * Traite le dernier evenement de la souris
	 * 
	 * Extrait les infonformations concernant le dernier evenement de la souris
	 * associe a la fenetre de dessin. S'il n'existe aucun evenement en attente la
	 * fonction retourne false, sinon elle retourne true. Les informations extraites
	 * sont accessibles a l'aide des fonctions: {@link #sourisPositionX()},
	 * {@link #sourisPositionY()} et {@link #sourisBouton()}.
	 * 
	 * @return true = evenement traite, false = aucun evenement non traite
	 */
	public boolean sourisEvenement() {
		synchronized (sourisVerrou) {
			if (sourisEvenement != null) {
				sourisPosition.setLocation(sourisEvenement.getPoint());
				sourisBouton = sourisEvenement.getButton();
				sourisEvenement = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne la position en x du dernier evenement de la souris.
	 * 
	 * Retourne la position en x de la souris la derni�re fois qu'un bouton a �t�
	 * press� pendant l'appel � {@link #sourisAttendreEvenement()}.
	 *
	 * @return position en x de la souris
	 */
	public int sourisPositionX() {
		return sourisPosition.x;
	}

	/**
	 * Retourne la position en y du dernier evenement de la souris.
	 * 
	 * Retourne la position en y de la souris la derni�re fois qu'un bouton a �t�
	 * press� pendant l'appel � {@link #sourisAttendreEvenement()}.
	 *
	 * @return position en y de la souris
	 */
	public int sourisPositionY() {
		return sourisPosition.y;
	}

	/**
	 * Retourne le numero du dernier bouton presse.
	 * 
	 * Retourne le num�ro du bouton de la souris press� pendant le dernier appel �
	 * {@link #sourisAttendreEvenement()}.
	 *
	 * @return num�ro de bouton (1: gauche, 2: milieu, 3: droit)
	 */
	public int sourisBouton() {
		return sourisBouton;
	}

	// ******************** GESTION DES ANIMATIONS ********************
	/**
	 * Suspend l'ex�cution pendant un certain temps.
	 *
	 * Suspend l'execution de votre programme durant le nombre de millisecondes
	 * indique en parametre. Les evenements du clavier et da la souris continuent
	 * d'etre traites durant cette suspension.
	 * 
	 * @param aDelai
	 *            temps d'attente en millisecondes
	 */
	public static void attendre(long aDelai) {
		try {
			Thread.sleep(aDelai);
		} catch (Exception e) {
		}
	}

	/**
	 * Suspend l'execution jusqu'a l'affichage de la prochiane image.
	 * 
	 * Suspend l'execution de votre programme jusqu'a ce que le delai associe a
	 * l'affichage de la prochaine image soit atteint. Par exemple, si vous demandez
	 * une animation a 30 images par seconde, cette fonction suspendra l'execution
	 * de votre programme jusu'a ce que le prochain 1/30 seconde soit atteint. Cette
	 * fonction vous permet donc de creer des animation ayant une vitesse reguliere.
	 * 
	 * @return true lorsque le delai est atteint, false si la fonction d'animation
	 *         n'est pas activee
	 */
	public boolean animationAttendreProchaineImage() {
		if (imageChrono == null)
			return false;
		synchronized (imageVerrou) {
			try {
				imageVerrou.wait();
			} catch (InterruptedException e) {
			}
		}
		return true;
	}

	/**
	 * Modifie la vitesse de l'animation
	 * 
	 * Permet de modifier le nombre d'images par seconde associees a l'animation.
	 * L'appel de cette fonction influence le delai d'attente de la fonction
	 * {@link #animationAttendreProchaineImage()}. Le delai d'attente doit etre
	 * fourni en millisecondes par image. Si vous desirez avoir 30 images par
	 * seconde, vous devez indiquer 1000/30 millisecondes en parametre.
	 * 
	 * @param aDelai
	 *            temps d'affichage de chaque image en millisecondes
	 */
	public void animationModifierVitesse(int aDelai) {
		imageChrono.cancel();
		imageChrono = new Timer();
		imageChrono.scheduleAtFixedRate(new FDImageChronoTache(), 0, aDelai);
	}

	/**
	 * Selectionne le mode de dessin direct ou differe.
	 * 
	 * Permet d'afficher l'image a mesure que l'on fait appel aux primitives de
	 * dessin (direct) ou seulement lors de l'appel de la fonction
	 * {@link #rafraichirImage()} (differe). Astuce: le mode differe est preferable
	 * lorsque l'on veut produire des animations.
	 * 
	 * @param aDirect
	 *            true = dessin direct, false = dessin differe
	 */
	public void imageDessinDirect(boolean aDirect) {
		dessinDirect = aDirect;
	}

	/**
	 * Reaffichage de l'image courante.
	 * 
	 * Demande a la fenetre de dessin d'afficher l'image dans son etat actuel. Il
	 * peut y avoir un delai entre l'appel de cette fonction et la mise a jour de la
	 * fenetre de dessin.
	 */
	public void imageRafraichir() {
		panel.repaint();
	}

	/**
	 * Synchronise le contenu de la fen�tre.
	 *
	 * Pour des raisons d'efficacit�s, le r�sultat des fonctions de dessin n'est pas
	 * affich� imm�diatement. L'appel � sync permet de synchroniser le contenu de la
	 * fen�tre. Autrement dit, cela bloque l'ex�cution du programme jusqu'� ce que
	 * le contenu de la fen�tre soit � jour.
	 */
	public void imageSynchronise() {
		panel.repaint();
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
				}
			});
		} catch (Exception e) {
		}
	}

	// A etre execute sur un thread separe pour la gestion de la fenetre de dessin
	void creerGUI() {
		panel = new FDPanel();
		frame = new JFrame(titre);
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new FDGestionClavier());
		panel.addMouseListener(new FDGestionSouris());
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	private final String titre; // Titre de la fenetre
	private JFrame frame; // Fenetre principale
	private FDPanel panel; // Panneau contenant l'image
	private BufferedImage image; // Le tampon de l'image a afficher
	private Graphics2D graphique; // Le contexte graphique associe a l'image
	private Object sourisVerrou; // Verrou pour les evenements de la souris
	private MouseEvent sourisEvenement; // Dernier evenement de la souris
	private Point sourisPosition; // Position de la souris lors du dernier evenement
	private int sourisBouton; // Bouton de la souris appuye lors du dernier evenement
	private Object clavierVerrou; // Verrou pour les evenements du clavier
	private LinkedList<KeyEvent> clavierTamponEvenement; // Tampon evenements clavier
	private char clavierTouche; // Touche associee au dernier evenement traite
	private boolean clavierTamponne; // Utilisation du tampon clavier?
	private Object imageVerrou; // Verrou associe a l'image
	private Timer imageChrono; // Chrono pour synchroniser les animations
	private boolean dessinDirect; // Affichage direct ou differe du dessin
	private Color couleurRemplissage; // Couleur utilisee pour le remplissage et effacer l'image

	// ******************** CLASSES POUR LES RAPPELS I/O ********************
	private class FDGestionClavier extends KeyAdapter {
		public void keyPressed(KeyEvent ev) {
			FenetreDessin w = FenetreDessin.this;
			synchronized (w.clavierVerrou) {
				if (ev.getKeyChar() != 0xFFFF) {
					if (!w.clavierTamponne) {
						w.clavierTamponEvenement.clear();
					}
					w.clavierTamponEvenement.add(ev);
					clavierVerrou.notifyAll();
				}
			}
		}
	}

	private class FDGestionSouris extends MouseAdapter {
		public void mousePressed(MouseEvent ev) {
			FenetreDessin w = FenetreDessin.this;
			synchronized (w.sourisVerrou) {
				w.sourisEvenement = ev;
				sourisVerrou.notifyAll();
			}
		}
	}

	private class FDImageChronoTache extends TimerTask {
		public void run() {
			FenetreDessin w = FenetreDessin.this;
			synchronized (w.imageVerrou) {
				imageVerrou.notifyAll();
			}
		}
	}

	// ******************** PANNEAU D'AFFICHAGE DE L'IMAGE ********************
	private class FDPanel extends JPanel {
		FDPanel() {
			FenetreDessin w = FenetreDessin.this;
			Dimension dimension = new Dimension(w.largeur, w.hauteur);
			super.setMinimumSize(dimension);
			super.setMaximumSize(dimension);
			super.setPreferredSize(dimension);
		}

		public void paint(Graphics g) {
			FenetreDessin w = FenetreDessin.this;
			synchronized (w.image) {
				g.drawImage(w.image, 0, 0, null);
			}
		}

		private static final long serialVersionUID = 0;
	}
}
