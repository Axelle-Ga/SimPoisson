package proj;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe Principal
 *
 */
public class Principal {
	
	/**
	 * Nombre de sardines
	 */
	private static int nombrePoissons;
	
	/**
	 * Liste des sardines de la simulation
	 */
	protected List<Sardine> banc;
	
	/**
	 * Nombre de prédateurs
	 */
	private int nombrePredateurs;
	
	/**
	 * Liste des prédateurs de la simulation
	 */
	protected List<Predateur> predateur;
	
	/**
	 * Nombre d'obstacles
	 */
	private int nombreObstacles;
	
	/**
	 * Liste des obstacles de la simulation
	 */
	protected List<Obstacle> obstacles;
	
	/**
	 * Temps d'éxécution
	 */
	protected int tempsExe;
	
	/**
	 * Poids du vecteur se rapprocher
	 */
	double coef_Rapproche;
	
	/**
	 * Poids du vecteur s'aligner
	 */
	double coeff_Aligne;
	
	/**
	 * Poids du vecteur s'éviter entre sardines
	 */
	double coeff_Eloigne;
	
	/**
	 * Poids du vecteur éviter les prédateurs
	 */
	double coeff_Evite;
	
	
	/**
	 * Constructeur de Principal
	 * 
	 * @param nombrePoissons Nombre de sardines
	 * @param nombreObstacles Nombre d'obstacles
	 * @param nombrePredateurs Nombre de prédateurs
	 * @param taillePois Taille des sardines
	 * @param taillePred Taille des prédateurs
	 * @param coef_Rapproche Poids du vecteur se rapprocher
	 * @param coeff_Aligne Poids du vecteur s'aligner
	 * @param coeff_Eloigne Poids du vecteur s'éviter entre sardines
	 * @param coeff_Evite Poids du vecteur éviter les prédateurs
	 * @param vitPois Vitesse des sardines
	 * @param vitPred Vitesse des prédateurs
	 * 
	 * @see creerObstacles( int, int)
	 * @see creerPredateur(double, double, int, int)
	 * @see creerBanc(double, double, int, int)
	 */
	
	public Principal(int nombrePoissons, int nombreObstacles, int nombrePredateurs,double taillePois,double taillePred,double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_Evite,double vitPois,double vitPred) {
		Principal.nombrePoissons = nombrePoissons;
		this.nombrePredateurs = nombrePredateurs;
		this.nombreObstacles = nombreObstacles;
		
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.obstacles = creerObstacles(resolution.width-485, resolution.height-80);
		this.predateur = creerPredateur(taillePred,vitPred,resolution.width-435, resolution.height);
		this.banc = creerBanc(taillePois,vitPois,resolution.width-435, resolution.height);
		
		this.coef_Rapproche = coef_Rapproche;
		this.coeff_Aligne = coeff_Aligne;
		this.coeff_Eloigne = coeff_Eloigne;
		this.coeff_Evite = coeff_Evite;
	}
	
	/**
	 * Constructeur de Principal avec une configuration particulière
	 * 
	 * @param nombrePoissons Nombre de sardines
	 * @param nombrePredateurs Nombre de prédateurs
	 * @param taillePois Taille des sardines
	 * @param taillePred Taille des prédateurs
	 * @param coef_Rapproche Poids du vecteur se rapprocher
	 * @param coeff_Aligne Poids du vecteur s'aligner
	 * @param coeff_Eloigne Poids du vecteur s'éviter entre sardines
	 * @param coeff_Evite Poids du vecteur éviter les prédateurs
	 * @param vitPois Vitesse des sardines
	 * @param vitPred Vitesse des prédateurs
	 * @param choixConf Choix de la configuration(true = prison, false = labyrinthe)
	 * 
	 * @see #creerBancDansPrison(double, double)
	 * @see #creerPredateur(double, double, int, int)
	 * @see #creerBarriere()
	 * @see #creerLab()
	 */
	public Principal(int nombrePoissons,int nombrePredateurs,double taillePois,double taillePred,double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_Evite,double vitPois,double vitPred, boolean choixConf) {
		Principal.nombrePoissons = nombrePoissons;
		this.nombrePredateurs = nombrePredateurs;
		
		if (choixConf == true){				// Choisit la configuration (ie choix entre prison et labyrinthe)
			this.obstacles = creerBarriere();	
		}
		else {
			this.obstacles = creerLab();
		}
		
		this.predateur = creerPredateurDansPrison(taillePred,vitPred);  // On crée les prédateurs dans l'enceinte de la prison
		this.banc = creerBancDansPrison(taillePois,vitPois);			// De même que les sardines
		
		this.nombreObstacles = obstacles.size();
		this.coef_Rapproche = coef_Rapproche;
		this.coeff_Aligne = coeff_Aligne;
		this.coeff_Eloigne = coeff_Eloigne;
		this.coeff_Evite = coeff_Evite;
	
		
	}
	
	
	/**
	 * Création d'un banc de sardines
	 * 
	 * @param taille Taille des sardines
	 * @param vitesse Norme du vecteur vitesse
	 * @param largeurEcran Largeur de l'écran disponible
	 * @param hauteurEcran Hauteur de l'écran disponible
	 * @return Banc de sardines sous forme de liste
	 * 
	 * @see Sardine#Sardine(int , int, double, int, int, double)
	 */
	private List<Sardine> creerBanc(double taille, double vitesse,int largeurEcran, int hauteurEcran) {
		List<Sardine> P = new ArrayList<Sardine>();
		
		for( int i=0;i<nombrePoissons;i++) {

			int x = new Random().nextInt(largeurEcran);		// Donne une positon aléatoire pour chaque sardines
			int y = new Random().nextInt(hauteurEcran);
			double o = new Random().nextInt(360);			// Donne une orientation aléatoire pour chaque sardines
			Sardine p = new Sardine(x,y,o,(int)(taille),(int)(taille/3),vitesse);
			P.add(p);
		}
		return P;
	}
	
	/**
	 * Création d'un banc de sardines à l'intérieur de la prison
	 * 
	 * @param taille Taille des sardines
	 * @param vitesse Norme du vecteur vitesse
	 * @return Banc de poissons sous forme de liste
	 * 
	 * @see Sardine#Sardine(int , int, double, int, int, double)
	 */
	private List<Sardine> creerBancDansPrison(double taille, double vitesse) {
		List<Sardine> P = new ArrayList<Sardine>();
		for( int i=0;i<nombrePoissons;i++) {
			int x = new Random().nextInt(90)+370;		// Donne une positon aléatoire, au centre de la prison, pour chaque sardines
			int y = new Random().nextInt(90)+350;
			double o = new Random().nextInt(360);
            Sardine p = new Sardine(x,y,o,(int)(taille),(int)(taille/3),vitesse);
            P.add(p);
		}
		return P;
	}
	
	/**
	 * Création d'un banc de prédateurs
	 * 
	 * @param taille Taille des prédateurs
	 * @param vitesse Norme du vecteur vitesse
	 * @param largeurEcran Espace disponible en x
	 * @param hauteurEcran Espace disponible en y
	 * @return Liste des prédateurs
	 * 
	 * @see Predateur#Predateur(int, int, double, int, int, double)
	 */
	private List<Predateur> creerPredateur(double taille, double vitesse,int largeurEcran, int hauteurEcran) {
		List<Predateur> predateurs = new ArrayList<Predateur>();
		for( int i=0;i<nombrePredateurs;i++) {
			int x = new Random().nextInt(largeurEcran);
    		int y = new Random().nextInt(hauteurEcran);
    		double o = new Random().nextInt(360);
    		Predateur requin = new Predateur(x,y,o,(int)(taille),(int)(taille/3),vitesse);
			predateurs.add(requin);
		}	
		return(predateurs);	
	}
	
	
	/**
     * Création des prédateurs à l'intérieur de la prison dans une zone différente des sardines
     * 
     * @param taille Taille des prédateurs
     * @param vitesse Norme du vecteur vitesse
     * @return Banc de prédateurs sous forme de liste 
     * 
     * @see Predateur#Predateur(int , int, double, int, int, double)
     */
    private List<Predateur> creerPredateurDansPrison(double taille, double vitesse) {
        List<Predateur> predateur = new ArrayList<Predateur>();
        for( int i=0;i<nombrePredateurs;i++) {
            int x = 400;
            int y = 400;
            while ((x>370 && x<460)&&(y>350 && y<440)) {		// Les prédateurs ne peuvent pas être, en position initial, dans la zone de création des sardines
            													// cette zone étant caractérisée par : X c [370,460] et Y c [350,440]
                x = new Random().nextInt(400)+200;			
                y = new Random().nextInt(400)+200;
            }
            
            double o = new Random().nextInt(360);
            Predateur requin = new Predateur(x,y,o,(int)(taille),(int)(taille/3),vitesse);
            predateur.add(requin);
        }
        return predateur;
    }
    
	
	/**
	 * Création des obstacles
	 * 
	 * @param largeurEcran Espace disponible en x
	 * @param hauteurEcran Espace disponible en y
	 * @return Liste des obstacles
	 * 
	 * @see Obstacle#Obstacle(int, int, int)
	 */
	private List<Obstacle> creerObstacles(int largeurEcran, int hauteurEcran) {
		List<Obstacle> obstacles = new ArrayList<Obstacle>();
		
		for(int i = 0; i < this.nombreObstacles; i++) {
			int x = new Random().nextInt(largeurEcran);
			int y = new Random().nextInt(hauteurEcran);
			int taille = new Random().nextInt(80)+5;
			Obstacle o = new Obstacle(x , y , taille);
			obstacles.add(o);
		}
		
		return obstacles;
	}
	
	
	/**
	 * Création de la barrière circulaire servant de prison
	 * 
	 * @return Liste des obstacles formant une barrière
	 * 
	 * @see Obstacle#Obstacle(int, int, int)
	 */
	private List<Obstacle> creerBarriere() {
		List<Obstacle> obstacles = new ArrayList<Obstacle>() ;
		int compteur = 0;
		
		for(double i = 0; i < 2*Math.PI; i+=(Math.PI)/16) {  // On "parcourt" le cercle trigonométrique en "déposant" tout les PI/16 des obstacles
			int x = (int)(400*(1-Math.sin(i)));
			int y = (int)(400*(1+Math.cos(i)))-50;
			
			Obstacle obs = new Obstacle(x,y,90);
			obstacles.add(compteur,obs);
			compteur++;
			
		}
		
		return obstacles;
	}
	
	

	/**
	 * Création d'un labyrinthe d'obstacles
	 * 
	 * @return Liste des obstacles formant un labyrinthe
	 * 
	 * @see #creerLabMurCentral()
	 * @see #creerLabMurIntermediaire()
	 * @see #creerLabMurExterne()
	 */
	private List<Obstacle> creerLab() {
		List<Obstacle> obstacles = new ArrayList<Obstacle>() ;
		for (Obstacle i : creerLabMurCentral()) {		// Ajout du mur central au labyrinthe
			obstacles.add(i);
		}
		for (Obstacle i : creerLabMurIntermediaire()) {  // Ajout du mur intermédiaire au labyrinthe
			obstacles.add(i);
		}
		for (Obstacle i : creerLabMurExterne()) {		// Ajout du mur externe au labyrinthe
			obstacles.add(i);
		}
		return obstacles;	
	}
	
	/**
	 * Création du mur central du labyrinthe
	 * 
	 * @return Liste des obstacles du mur central
	 * 
	 * @see #creerLab()
	 * @see Obstacle#Obstacle(int, int, int)
	 */
	private List<Obstacle> creerLabMurCentral() {
		List<Obstacle> obstacles = new ArrayList<Obstacle>() ;
		int compteur = 0;
		for(double i = 6*(Math.PI)/16; i < 2*Math.PI; i+=(Math.PI)/16) {
			int x = (int)(75*(1-Math.sin(i)))+340;
			int y = (int)(75*(1+Math.cos(i)))+300;
			Obstacle obs = new Obstacle(x,y,20);
			obstacles.add(compteur,obs);
			compteur++;
		}
		return obstacles;
	}
	
	
	/**
	 * Création du mur intermédiaire du labyrinthe
	 * 
	 * @return Liste des obstacles du mur intermédiaire du labyrinthe
	 * 
	 * @see #creerLab()
	 * @see Obstacle#Obstacle(int, int, int)
	 */
	private List<Obstacle> creerLabMurIntermediaire() {
		List<Obstacle> obstacles = new ArrayList<Obstacle>() ;
		int compteur = 0;
		for(double i = 20*(Math.PI)/16; i < 2*Math.PI + 16*(Math.PI)/16; i+=(Math.PI)/16) { 
			int x = (int)(200*(1-Math.sin(i)))+200;
			int y = (int)(200*(1+Math.cos(i)))+170;
			Obstacle obs = new Obstacle(x,y,40);
			obstacles.add(compteur,obs);
			compteur++;
		}
		return obstacles;
	}
	
	/**
	 * Création du mur externe du labyrinthe
	 * 
	 * @return Liste des obstacles du mur externe
	 * 
	 * @see #creerLab()
	 * @see Obstacle#Obstacle(int, int, int)
	 */
	private List<Obstacle> creerLabMurExterne() {
		List<Obstacle> obstacles = new ArrayList<Obstacle>() ;
		int compteur = 0;
		for(double i = 10*(Math.PI)/16; i < 2*Math.PI+7*(Math.PI)/16; i+=(Math.PI)/16) { 
			int x = (int)(350*(1-Math.sin(i)))+25;
			int y = (int)(350*(1+Math.cos(i)))+5;
			Obstacle obs = new Obstacle(x,y,80);
			obstacles.add(compteur,obs);
			compteur++;
		}
		return obstacles;
	}


	/**
	 * Modification des positions pour faire avancer le banc de sardines
	 * 
	 * @param largeurEcran Espace en x disponible
	 * @param hauteurEcran Espace en y disponible
	 * 
	 * @see Sardine#decision(List, List, List,double ,double ,double ,double)
	 * @see Poisson#avancer(int, int)
	 */
	protected void avancerBanc(int largeurEcran, int hauteurEcran) {
		for( int i=0;i<banc.size();i++) {
			this.banc.get(i).decision(obstacles, banc, predateur,coef_Rapproche,coeff_Aligne,coeff_Eloigne,coeff_Evite);
			this.banc.get(i).avancer(largeurEcran, hauteurEcran);
		}
	}
	
	
	/**
	 * Modificaction des positions des prédateurs pour les faire avancer
	 * 
	 * @param largeurEcran Espace disponible en x
	 * @param hauteurEcran Espace disponible en y
	 * 
	 * @see Predateur#decision(List, List, List)
	 * @see Poisson#avancer(int, int)
	 */
	protected void avancerPredateur(int largeurEcran, int hauteurEcran) {
		for( int i=0;i<predateur.size();i++) {
			this.predateur.get(i).decision(obstacles, banc, predateur);
			this.predateur.get(i).avancer(largeurEcran, hauteurEcran);
		}
	}
	
	
	/**
	 * Modification de la norme du vecteur vitesse des prédateurs
	 * 
	 * @param nouvVit Norme du vecteur vitesse
	 */
	protected void setVitessePred(double nouvVit) {
		for( int i=0;i<nombrePredateurs;i++) {
			predateur.get(i).Vnorm = nouvVit;
		}
	}
	
	/**
	 * Modification du rayon supérieur du champs de vision des sardines
	 * 
	 * @param cdv Rayon supérieur du champs de vision
	 */
	protected void setChampsVision(double cdv) {
		for( int i=0;i<nombrePoissons;i++) {
			banc.get(i).rayonsup = cdv;
		}
	}

	
	/**
	 * Modification de la norme du vecteur vitesse des sardines du banc
	 * 
	 * @param nouvVit Norme du vecteur vitesse
	 */
	protected void setVitesseSardine(double nouvVit) {
		for( int i=0;i<nombrePoissons;i++) {
			banc.get(i).Vnorm = nouvVit;
		}
	}
	

	
	public static void main(String[] args) {
		Fenetre F = new Fenetre();
	}

}
