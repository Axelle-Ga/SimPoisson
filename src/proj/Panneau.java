package proj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.JPanel;



/**
 * Classe Panneau. Elle hérite de JPanel.
 * 
 *
 */
public class Panneau extends JPanel {
	
	/**
	 * ID
	 */
	private static final long serialVersionUID = -372101627355908025L;
	
	/**
	 * Liste des sardines de la simulation
	 */
	protected List<Sardine> affichePoisson;
	
	/**
	 * Liste des obstacles de la simulation
	 */
	protected List<Obstacle> afficheObstacle;
	
	/**
	 * Liste des prédateurs de la simulation
	 */
	protected List<Predateur> affichePredateur;
	
	/**
	 * Taille des sardines
	 */
	double taillePois;
	
	/**
	 * Taille des prédateurs
	 */
	double taillePred;
	
	/**
	 * Image de fond
	 */
	protected Image img;
	
/*//   Renouvellement du banc de poissons
		int compteur = 0;
		double taille_pois;
		double coef_Rapproche;
		double coeff_Aligne;
		double coeff_Eloigne;
		double coeff_EvitePredateur;
		double vitPois;
*/
	
	/**
	 * Constructeur de Panneau
	 * 
	 * @param poisson	Liste des sardines de la simulation (List)
	 * @param obstacles	Liste des obstacles de la simulation (List)
	 * @param predateur	Liste des prédateurs de la simulation (List)
	 * @param taille_pois	Taille des sardines (double)
	 * @param coef_Rapproche	Poids du vecteur rapprocher (double)
	 * @param coeff_Aligne		Poids du vecteur s'aligner (double)
	 * @param coeff_Eloigne		Poids de vecteur s'éviter entre poissons (double)
	 * @param coeff_EvitePredateur	Poids du vecteur éviter les prédateurs (double)
	 * @param vitPois		Vitesse du poisson (double)
	 */
	public Panneau (List<Sardine> poisson,List<Obstacle> obstacles,List<Predateur> predateur, double taille_pois,double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_EvitePredateur,double vitPois) {
		super();
		this.affichePoisson = poisson ;
		this.afficheObstacle = obstacles;	
		this.affichePredateur = predateur;

/*		
		this.taille_pois = taille_pois;
		this.coef_Rapproche = coef_Rapproche;
		this.coeff_Aligne = coeff_Aligne;
		this.coeff_Eloigne = coeff_Eloigne;
		this.coeff_EvitePredateur = coeff_EvitePredateur;
		this.vitPois = vitPois;
*/
	}	
	
	/**
	 * Modifie les éléments de simulation du panneau
	 * 
	 * @param poisson Liste des sardines de la simulation
	 * @param obstacles	Liste des obstacles de la simulation
	 * @param predateur Liste des prédateurs de la simulation
	 */
	public void setPanneau (List<Sardine> poisson,List<Obstacle> obstacles,List<Predateur> predateur) {
		
		this.affichePoisson = poisson ;
		this.afficheObstacle = obstacles;	
		this.affichePredateur = predateur;
		
	}	
	
	
	/**
	 * Paint tous les éléments de la simulation (fond, sardines, prédateurs, obstacles)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		
		//Permet de mettre l'image de fond, si c'est un try...catch, c'est parce que sinon il y a une erreur
		g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), this);
		
//   Nous avons pensé à rajouter une option de renouvellement de la population des poissons 
//		compteur +=1;
//		if (compteur>100) {
//			compteur = 0;
//			Principal aquariumSim = new Principal(1,0,0,taille_pois,0,coef_Rapproche,coeff_Aligne,coeff_Eloigne,coeff_EvitePredateur,vitPois,0);
//			affichePoisson.add( aquariumSim.banc.get(0));
//		}

		
		//Choix des couleurs (couleur pour les obstacles)
		Color corail = new Color(255,125,91);
		
		//Affichage des poissons
		for(int i = 0 ; i < this.affichePoisson.size();  i++) {
			try {
				if (affichePoisson.get(i).etreMange(affichePredateur) == true) {
					affichePoisson.remove(i);
				}
				g.setColor(Color.white);
				int[] posi = affichePoisson.get(i).autresPoints(affichePoisson.get(i).largeur,affichePoisson.get(i).longueur);//Récuperation des points pour former le poisson
				int[] ptx = {(int)affichePoisson.get(i).position[0], posi[0],posi[4],posi[6], posi[2]};// On fait la liste des x a tracé dans l'ordre
				int[] pty = {(int)affichePoisson.get(i).position[1], posi[1],posi[5],posi[7], posi[3]};//On fait la liste des y a tracé dans l'ordre
			//Dessine le poisson sous la forme d'un polygone
				g.fillPolygon(ptx, pty, 5);				
			}
			catch(IndexOutOfBoundsException e) {
				
			}
					  
	    }		 
	    
	    //Affiche les obstacles
	    for(int i = 0 ; i < this.afficheObstacle.size() ; i++) {
	    	
			 g.setColor(corail);
			 g.fillOval(this.afficheObstacle.get(i).position[0],this.afficheObstacle.get(i).position[1],this.afficheObstacle.get(i).taille,this.afficheObstacle.get(i).taille);
			  
		 }
	    
	    //Affiche les prédateurs
	    for(int i = 0 ; i < this.affichePredateur.size() ; i++) {
			 g.setColor(Color.RED);
			 int[] posi = affichePredateur.get(i).autresPoints(affichePredateur.get(i).largeur,affichePredateur.get(i).longueur);//Récuperation des points pour former le poisson
			 int[] ptx = {(int)affichePredateur.get(i).position[0], posi[0],posi[4],posi[6], posi[2]};// On fait la liste des x a tracé dans l'ordre
			 int[] pty = {(int)affichePredateur.get(i).position[1], posi[1],posi[5],posi[7], posi[3]};//On fait la liste des y a tracé dans l'ordre
			 g.fillPolygon(ptx, pty, 5);			  
		 }
	}

}

