package proj;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Poisson est la classe abstraite definissant le comportement des poissons
 * 
 * @see Sardine
 * @see Predateur
 *
 */
abstract class Poisson {
	
	/**
	 * La position du poisson
	 */
	protected int[] position;
	
	/**
	 * Le vecteur vitesse du poisson
	 * 
	 * @see normaliseVitesse()
	 */
	protected double[] vitesse;
	
	/**
	 * Angle du vecteur vitesse (orientation)
	 */
	protected double orientation;
	
	/**
	 * Rayon inf�rieur (dans lequel le poisson evite les obstacles)
	 * 
	 */
	protected double rayoninf = 20;
	
	/**
	 * Rayon medium (dans lequel le poisson s'aligne avec les autres)
	 * 
	 */
	protected double rayonMed = 50;
	
	/**
	 * Rayon sup�rieur (dans lequel le poisson se rapproche de la position moyenne)
	 */
	protected double rayonsup = 80;
	
	/**
	 * Angle maximal dont le poisson peut tourner (en radian)
	 */
	protected double angleMax = Math.PI/4; //angle en radian
	
	/**
	 * Norme du vecteur vitesse
	 */
	protected double Vnorm=10;
	
	/**
	 * Angle moiti� du champ de vision (en radian)
	 * 
	 * @see champsDeVision()
	 */
	protected double angleDePerception = Math.toRadians(165); //angle moiti� du champ de vision
	
	/**
	 * Obstacle le plus proche
	 */
	protected Obstacle obsPlusProche;

	
	/**
	 * Constructeur Poisson.
	 * 
	 * @param x		La position en x du poisson
	 * @param y		La position en y du poisson
	 * @param or	L'orientation (angle en degr�) du poisson
	 */
	public Poisson (int x , int y , double or) {

		this.position = new int[2];
		this.position[0] = x ;
		this.position[1] = y ;
		this.orientation = Math.toRadians(or);
		
		this.vitesse = new double[2];
		this.vitesse[0] = Math.sin(orientation);
		this.vitesse[1] = Math.cos(orientation);
		this.normaliseVitesse();

		
	}
	
	
	
	/**
	 * Normalise le vecteur vitesse du poisson
	 */
	protected void normaliseVitesse() {
		double nv = this.normVit();
		this.vitesse[0] = this.vitesse[0]/nv; 
		this.vitesse[1] = this.vitesse[1]/nv;
	}
	
	/**
	 * Renvoie la norme du vecteur vitesse
	 * 
	 * @return	la norme du vecteur vitesse (double)
	 */
	protected double normVit() {
		
		double norm = Math.sqrt(Math.pow(this.vitesse[0], 2) + Math.pow(this.vitesse[1],2));
		return(norm);
	}
	
	/**
	 * Normalisation d'un vecteur
	 * 
	 * @param vecteur 	Vecteur � normer
	 * @return			Vecteur norm� (double[])
	 */
	public static double[] normerVecteur(double[] vecteur) {
		//Normalise le vecteur
		double normeVect =Math.sqrt(Math.pow(vecteur[0], 2)+Math.pow(vecteur[1], 2));
		if(normeVect == 0) {
			return(vecteur);
		}
		vecteur[0]= vecteur[0]/normeVect;
		vecteur[1]= vecteur[1]/normeVect;
		return(vecteur);
	}
	
	/**
	 * Modifie le vecteur vitesse pour orienter le poisson suivant la nouvelle orientation
	 * 
	 * @param angle		Nouvelle orientation (angle en radians) du poisson
	 */
	protected void orienter(double angle) {
		this.orientation = angle;
		this.vitesse[0] = (int)(Math.sin(this.orientation)*3);
		this.vitesse[1] = (int)(Math.cos(this.orientation)*3);
	}
	
	/**
	 * Modifie le vecteur vitesse et l'orientation pour orienter le poisson suivant
	 * le nouveau vecteur vitesse
	 * 
	 * @param vitx	Vitesse en x
	 * @param vity	Vitesse en y
	 */
	protected void orienter(double vitx,double vity) {
		
		this.vitesse[0] = vitx;
		this.vitesse[1] = vity;
		this.orientation = Math.atan2(vity,vitx);
	}
	
	/**
	 * Modifie le vecteur vitesse et l'orientation pour orienter le poisson suivant
	 * le nouveau vecteur vitesse
	 * 
	 * @param vit	Vecteur vitesse (double[])
	 */
	protected void orienter(double[] vit) {
		
		this.vitesse[0] = vit[0];
		this.vitesse[1] = vit[1];
		this.orientation = Math.atan2(this.vitesse[1],this.vitesse[0]);
	}
	

	/**
	 * Modifie la position du poisson en utilisant le vecteur vitesse
	 * 
	 * @param largeurEcran		Espace disponible en largeur
	 * @param hauteurEcran		Espace disponible en hauteur
	 */
	protected void avancer(int largeurEcran,int hauteurEcran) {
		//On rajoute la vitesse � la position pour faire avancer le poisson
		this.position[0] = (int)(this.position[0] + this.vitesse[0]*Vnorm)%largeurEcran ;
		this.position[1] = (int)(this.position[1] + this.vitesse[1]*Vnorm)%hauteurEcran ;
		
		//Les poissons ressortent de l'autre c�t� de la fen�tre
		if(this.position[0]<0) {
			this.position[0] = this.position[0]+largeurEcran;
		}
		
		if(this.position[1]<0) {
			this.position[1] = this.position[1]+hauteurEcran;
		}
	}

	/**
	 * Calcul le vecteur d'�vitement des obstacles du poisson
	 * <p>Pour chaque obstacle per�u le vecteur �viter correspond au vecteur partant de 
	 * l'obstacle et allant au poisson.Ensuite, on fait la moyenne des vecteurs �viter de 
	 * chaque obstacle per�u.</p>
	 * 	
	 * @param obstacle	Liste des obstacles de la simulation
	 * @return	Vecteur norm� d'�vitement des obstacles (double)
	 */
	protected double[] vecteurEviter2(List<Obstacle> obstacle) {
		double[] vecteurEvite = {0,0};
		//On r�cup�re les obstacles per�us par le poisson
		List<Obstacle> ObstaclesVus = ObstaclesPercus(obstacle,0,50);
		//On calcul le vecteur �viter pour chaque obstacles per�us
		for(Obstacle i : ObstaclesVus) {
			double dist = Point.distance(position[0], position[1], i.positionCentre[0],i.positionCentre[1]);
			double dX = position[0]-i.positionCentre[0];
			double dY = position[1]-i.positionCentre[1];
			if(dist<0) {
				vecteurEvite[0] += -this.vitesse[0];
				vecteurEvite[1] += -this.vitesse[1];
				System.out.println("poisson dans l'obstacle");
				return(vecteurEvite);
				}			
			vecteurEvite[0] += i.taille*dX;
			vecteurEvite[1] += i.taille*dY;
			}
		return(normerVecteur(vecteurEvite));
	}
	
		
	/**
	 * Calcule le vecteur pour �viter les pr�dateurs.
	 * <p>Pour chaque predateur per�u le vecteur �viter correspond au vecteur partant du 
	 * pr�dateur et allant au poisson.Ensuite on fait la moyenne des vecteurs �viter de 
	 * tous les pr�dateurs per�us.</p>
	 * 
	 * @param predateurs 	Liste des pr�dateurs de la simulation
	 * @return				Vecteur norm� d'�vitement des pr�dateurs (double).
	 */
	protected double[] vecteurEvitePredateur(List<Predateur> predateurs) {
		double[] vecteurEvite = {0,0}; //Initialisation � 0
		//On r�cup�re les pr�dateurs per�us
		List<Predateur> PredateurVus = PredateurPercus(predateurs,0,50);
		for(Predateur i : PredateurVus) {
			//On somme les vecteurs �viter
			double dX = position[0]-i.position[0];
			double dY = position[1]-i.position[1];		
			vecteurEvite[0] += i.largeur*dX;
			vecteurEvite[1] += i.largeur*dY;
		}
		//On retourne le vecteur �viter total normalis�
		return(normerVecteur(vecteurEvite));
		}
	

	
	/**
	 * Renvoie les poissons per�us par le poisson
	 * 
	 * @param listSardine	Liste des poissons de la simulation
	 * @param rayonMin		Rayon minimum
	 * @param rayonMax		Rayon maximum
	 * @return Liste des poissons per�us
	 */
	public List<Sardine> poissonsPercus(List<Sardine> listSardine, double rayonMin, double rayonMax){
		/* Retourne sous forme d'arraylist les poissons per�us ie les poissons dont la position
		 * est dans le cercle de centre la position du poisson �tudi� et de rayon : rayonMax et � 
		 *  l'�xt�rieur du cercle de centre la position du poisson �tudi� et de rayon : rayon min
		 *  Et qui sont dans le champ de vision
		 */
		
		//On cr�e une liste dans laquelle on va stocker les poissons per�us
		List<Sardine> Sardine = new ArrayList<>();	
		
		for (Sardine i : listSardine) {
			//On calcule la distance du poisson � l'obstacle i
			double d = Point.distance(this.position[0], this.position[1], i.position[0], i.position[1]);
			
			if (d>rayonMin && d<rayonMax && this.estDansChampsDeVision(i)==true) {
				Sardine.add(i);	//Si le poisson est dans le cercle de perception on le rajoute � la liste			
			}
		}
		return(Sardine);
	}
	
		
	/**
	 * Renvoie les deux angles limites du champ de vision
	 * 
	 * @return	Champ de vision: tableau contenant l'angle min et max (double[]).
	 */
	protected double[] champsDeVision() {
		
		//angleDePerception correspond � la moiti� de l'angle du champ de vision total
		
		//On renvoie les deux angles limites sous forme de tableau : {angleMin,angleMax}
		
		double[] champDeVision = new double[2];
		
		//angleMin (on prend 2 fois le modulo pour avoir une valeur positive)
		champDeVision[0] = (((this.orientation - this.angleDePerception)%(2*Math.PI))+(2*Math.PI))%(2*Math.PI);
		
		//angleMin (on prend 2 fois le modulo pour avoir une valeur positive)
		champDeVision[1] = (((this.orientation + this.angleDePerception)%(2*Math.PI))+(2*Math.PI))%(2*Math.PI);
		return(champDeVision);
		}
	
		
	/**
	 * Renvoie true si le poisson est dans le champ de vision, false sinon
	 * 
	 * @param poisson Poisson � tester
	 * @return	Bool�en : true si le poisson est dans le champ de vision, false sinon
	 */
	protected boolean estDansChampsDeVision(Poisson poisson) {
		double[] cdvision = this.champsDeVision();	//On r�cup�re le champ de vision du poisson
		double alpha = this.angleOrigineAvecPoisson(poisson);		//On va calculer l'angle entre l'origine et la droite form�e par les 2 poissons
		if (alpha>=cdvision[0] || alpha<=cdvision[1]) {	//Si le poisson est dans le champ de vision, on retourne true sinon false
			return (true);
		}			
		return(false);
	}
		
		
	/**
	 * Renvoie true si l'obstacle est dans le champ de vision, false sinon
	 * 
	 * @param obstacle Obstacle � tester
	 * @return	Bool�en : true si l'obstacle est dans le champ de vision, false sinon
	 */
	protected boolean ObstacleDansChampsDeVision(Obstacle obstacle) {
		double[] cdvision = this.champsDeVision();	//On r�cup�re le champ de vision du poisson
		double alpha = this.angleOrigineAvecObstacle(obstacle);	//angle entre l'origine et la droite form�e par les 2 poissons
		if (alpha>=cdvision[0] || alpha<=cdvision[1]) {
			return (true);	//Si le poisson est dans le champ de vision on retourne true, false sinon
		}
		return(false);
	}
	
	/**
	 * Retourne sous forme de liste les obstacles per�us ie les poissons dont la position est dans le cercle
	 * de centre la position du poisson �tudi� et de rayon : rayonMax et � l'�xt�rieur du cercle de centre
	 * la position du poisson �tudi� et de rayon : rayon min et qui sont dans le champ de vision
	 *  	
	 * @param listObstacles		Liste des obstacles de la simulation
	 * @param rayonMin Rayon minimum
	 * @param rayonMax Rayon maximum
	 * @return Liste des obstacles per�us
	 */
	public List<Obstacle> ObstaclesPercus(List<Obstacle> listObstacles, double rayonMin, double rayonMax){
		
		//On cr�e un arrayList dans laquelle on va stocker les poissons per�us
		List<Obstacle> ObstaclesVus = new ArrayList<>();
		
		for (Obstacle i : listObstacles) {
			
			//On calcul la distance du poisson � l'obstacle i
			double dx = this.position[0]-i.positionCentre[0];
			double dy = this.position[1]-i.positionCentre[1];
			double d = (Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2))-(i.taille/2));
			
			if (d<rayonMax /*&& this.ObstacleDansChampsDeVision(i)==true*/) {
				
				//Si le poissons est dans le cercle de perception on le rajoute � l'arraylist
				ObstaclesVus.add(i);				
			}
		}
		return(ObstaclesVus);
	}
	
	/**
	 * Retourne sous forme de liste les pr�dateurs per�us ie les poissons dont la position est
	 * dans le cercle de centre la position du poisson �tudi� et de rayon : rayonMax et � 
	 * l'�xt�rieur du cercle de centre la pos du poisson �tudi� et de rayon : rayon min
	 * Et qui sont dans le champ de vision
	 * 
	 * @param listPredateur liste des pr�dateurs de la simulation
	 * @param rayonMin Rayon minimum
	 * @param rayonMax Rayon maximum
	 * @return	Liste des pr�dateurs per�us
	 */
	public List<Predateur> PredateurPercus(List<Predateur> listPredateur, double rayonMin, double rayonMax){
		
		//On cr�e une liste dans laquelle on va stocker les poissons per�us
		List<Predateur> PredateursVus = new ArrayList<>();

		for (Predateur i : listPredateur) {

			//On calcul la distance du poisson � l'obstacle i
			double dx = this.position[0]-i.position[0];
			double dy = this.position[1]-i.position[1];
			double d = (Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2))-(i.largeur/2));
	
	
			if (d<rayonMax /*&& this.ObstacleDansChampsDeVision(i)==true*/) {
				
				//Si le poissons est dans le cercle de perception on le rajoute � la liste
				PredateursVus.add(i);				
			}
		}
		return(PredateursVus);
		}
		
	/**
	 * Renvoie l'angle form� entre l'origine et la droite form�e par les deux poissons
	 * 
	 * @param poisson Deuxi�me poisson
	 * @return	Angle en radian form� entre l'origine et la droite form�e par les deux poissons
	 */
	protected double angleOrigineAvecPoisson(Poisson poisson) {
		double alpha = 0;	//angle entre l'origine et la droite form�e par les 2 poissons
		double dx = this.position[0]-poisson.position[0];	//On calcul la distance du poisson � l'autre poisson
		double dy = this.position[1]-poisson.position[1];
		if (dx<=0 && dy>=0) {	//Poisson en bas � gauche de l'obstacle
			alpha =Math.PI-Math.atan(Math.abs(dx/dy));	//On calcul alpha (d�pend de la position relative)
		}
		else if (dx>=0 && dy>=0) {	//Poisson en bas � droite de l'obstacle
			alpha = Math.PI+Math.atan(Math.abs(dx/dy));
		}
		else if (dx>=0 && dy<=0) {	//Poisson en haut � droite de l'obstacle
			alpha = 2*Math.PI - Math.atan(Math.abs(dx/dy));
		}
		else if (dx<=0 && dy<=0) {	//Poisson en haut � gauche de l'obstacle
			alpha = Math.atan(Math.abs(dx/dy));
		}
		return(alpha);
	}
	
	/**
	 * Renvoie l'angle form� entre l'origine et la droite form�e par le poisson et l'obstacle
	 * 
	 * @param obstacle Obstacle etudi�
	 * @return	Angle en radian form� entre l'origine et la droite form�e par le poisson et l'obstacle
	 */
	protected double angleOrigineAvecObstacle(Obstacle obstacle) {
		double alpha = 0;	//angle entre l'origine et la droite form�e par le poisson et l'obstacle
		double dx = this.position[0]-obstacle.positionCentre[0];	//On calcul la distance du poisson � l'obstacle
		double dy = this.position[1]-obstacle.positionCentre[1];
		if (dx<=0 && dy>=0) {	//Poisson en bas � gauche de l'obstacle
			alpha =Math.PI-Math.atan(Math.abs(dx/dy));	//On calcul alpha (d�pend de la position relative)
		}
		else if (dx>=0 && dy>=0) {	//Poisson en bas � droite de l'obstacle
			alpha = Math.PI+Math.atan(Math.abs(dx/dy));
		}
		else if (dx>=0 && dy<=0) {	//Poisson en haut � droite de l'obstacle
			alpha = 2*Math.PI - Math.atan(Math.abs(dx/dy));
		}
		else if (dx<=0 && dy<=0) {	//Poisson en haut � gauche de l'obstacle
			alpha = Math.atan(Math.abs(dx/dy));
		}
		return(alpha);
	}
	
	/**
	 * R�cup�re les coordonn�es des points du polygone afin de tracer un poisson avec la bonne orientation
	 * 
	 * @param largeur Taille du poissson : largeur (en px)
	 * @param longueur	Taille du poissson : longueur (en px)
	 * @return Liste des positions pour tracer le poisson
	 */
	protected int[] autresPoints(int largeur , int longueur ) {
		
		double x1 = position[0] - largeur*vitesse[0]*4/7+vitesse[1]*longueur;
		double y1 = position[1] - largeur*vitesse[1]*4/7-vitesse[0]*longueur;
		
		double x2 = position[0] - largeur*vitesse[0]*4/7-vitesse[1]*longueur;
		double y2 = position[1] - largeur*vitesse[1]*4/7+vitesse[0]*longueur;
		
		double x3 = x2 - largeur*(vitesse[0])*3/7;
		double y3 = y2 - largeur*(vitesse[1])*3/7;
		
		double x4 = x1 - largeur*(vitesse[0])*3/7;
		double y4 = y1 - largeur*(vitesse[1])*3/7;
		
		int[] positions = {(int)(x1),(int)(y1),(int)(x2),(int)(y2),(int)(x3),(int)(y3),(int)(x4),(int)(y4)};
		return (positions);
	}
			

}
	
