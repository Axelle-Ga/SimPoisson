package proj;

import java.awt.Point;
import java.util.List;
/**
 * Sardine est la classe définissant les sardines. Elle hérite de Poisson.
 */
public class Sardine extends Poisson {
	
	/**
	 *Taille de la sardine : largeur (en pixel)
	 */
	protected int largeur;
	/**
	 *Taille de la sardine : longeur (en pixel)
	 */
	protected int longueur;
	/**
	 *Rayon du cercle pour la comparaison avec les autres sardines pour ne pas les toucher
	 */
	protected double visionPoisson = 20;


	/**
	 * Constructeur de Sardine
	 * 
	 * @param x 	Position en x
	 * @param y		Position en y
	 * @param or	Orientation (angle en degré)
	 * @param largeur	Taille de la sardine : largeur (en pixel)
	 * @param longueur	Taille de la sardine : longeur (en pixel)
	 * @param vitesse	Norme de la vitesse
	 * 
	 * @see Poisson#Poisson(int, int, double)
	 */
	public Sardine(int x, int y, double or, int largeur,int longueur, double vitesse ) {
		super(x,y,or);		
		this.largeur=largeur;
		this.longueur=longueur;
		this.Vnorm = vitesse;
	}
	
	/**
	 * Indique si la sardine est mangée
	 * 
	 * @param predateurs Liste des prédateurs de la simulation
	 * @return true si la sardine est mangée, false sinon
	 */
	protected boolean etreMange(List<Predateur> predateurs) {
		//Spécial pour que les sardines soient mangées
		for (int i=0; i<predateurs.size();i++) {
			if (Point.distance(predateurs.get(i).position[0],predateurs.get(i).position[1], this.position[0], this.position[1])<rayoninf) {
				return true;	
			}
		}
		return false;
	}


	// INTERRACTION AVEC LES BANCS DE SARDINES ET LES AUTRES OBSTACLES

	// CHANGEMENT DE DIRECTION PAR RAPPORT AUX OBJETS AUTOUR

		
	/**
	 * Retourne le vecteur pour s'aligner
	 * 
	 * @param listSardine Liste des sardines de la simulation
	 * @return	Vecteur Aligner (double[])
	 */
	protected double[] vecteurAligner(List<Sardine> listSardine) {
		
		double[] vecteurAligne = {0, 0};
		List<Sardine> sardinePercu = poissonsPercus(listSardine, this.rayoninf, this.rayonMed);	//On récupère toutes les sardines perçus par la sardine
		if (sardinePercu.isEmpty()) {
			return(vecteurAligne);	//Si il n'y a pas de poissons perçus on renvoie le vecteur {0,0}
			}
		int nbrPoissonProche = 0;		//On initialise un compteur de sardines
		double sommex = 0;	//On initialise la somme des orientations
		double sommey = 0;
		for( Poisson i : sardinePercu) {
			sommex += i.vitesse[0];		//On somme les vitesses des sardines perçues
			sommey += i.vitesse[1];	
			nbrPoissonProche += 1;
			}
		double moyenne_sommex = sommex/nbrPoissonProche;	//On calcule la moyenne des vitesses
		double moyenne_sommey = sommey/nbrPoissonProche;
		vecteurAligne[0] = moyenne_sommex;	//On calcul le vecteur que doit suivre la sardine pour s'aligner
		vecteurAligne[1] = moyenne_sommey;
		return(normerVecteur(vecteurAligne));
	}
		
	/**
	 * Retourne le vecteur pour se rapprocher de la position moyenne
	 * 
	 * @param listSardine Liste des sardines de la simulation
	 * @return Vecteur se rapprocher de la position moyenne
	 */
	protected double[]  vecteurRapprocherMoy(List<Sardine> listSardine) {
		List<Sardine> poissonSardine = poissonsPercus(listSardine, this.rayonMed, this.rayonsup);	//On récupère tous les poissons perçus par le poisson
		double[] vecteurRapproche = {0, 0};
		if (poissonSardine.isEmpty()) {
			return(vecteurRapproche);	//Si il n'y a pas de sardine perçue on renvoie {0,0}
		}
		double somme_x = 0;	//On initialise la somme des positions en x
		double somme_y = 0;	//On initialise la somme des positions en y
		double nbPoisson = 0;	//On initialise un compteur
		for (Poisson i : poissonSardine) {
			somme_x += i.position[0];	//On parcourt la liste des poissons pour calculer les 2 sommes
			somme_y += i.position[1];
			nbPoisson += 1;
		}
		double moy_x = somme_x/nbPoisson;	//On calcul la moyenne en x
		double moy_y = somme_y/nbPoisson;	//On calcul la moyenne en y
		vecteurRapproche[0]=moy_x-this.position[0];	//On calcul le vecteur vitesse pour se rapprocher de la position moyenne
		vecteurRapproche[1]=moy_y-this.position[1];
		return(normerVecteur(vecteurRapproche));
	}
		
	
	/**
	 * Retourne le vecteur d'eloigner (evitement entre sardines)
	 * 
	 * @param listSardine Liste des sardines de la simulation
	 * @return Vecteur d'éloignement entre sardine (double[])
	 */
	protected double[] vecteurEloigner(List<Sardine> listSardine) {
		List<Sardine> poissonPercu = poissonsPercus(listSardine, 0, this.rayoninf);	//On récupère toutes les sardines perçues par la sardine
		double[] vecteurEloigne = {0, 0};
		if (poissonPercu.isEmpty()) {
			return(vecteurEloigne);		//Si il n'a a pas de poissons perçus on renvoie {0,0}
		}
		double somme_vitX_away = 0;	//On initialise à 0 la somme des vitesses en x
		double somme_vitY_away = 0;	//On initialise à 0 la somme des vitesses en y
		double nb_poisson=0;		//On initialise un compteur
		for( Poisson i : poissonPercu) {
			somme_vitX_away += -(i.position[0]- position[0]);	//On calcul les sommes
			somme_vitY_away +=-(i.position[1]- position[1]);
			nb_poisson +=1;
		}
		vecteurEloigne[0] = somme_vitX_away/nb_poisson; 	// moyenne des vitesses en x pour s'éloigner
		vecteurEloigne[1] = somme_vitY_away/nb_poisson;		// moyenne des vitesses en x pour s'éloigner
		return(normerVecteur(vecteurEloigne));	
	}	
	
		
		
	
	// CHOIX SUR LE POIDS DES ACTIONS
	/**
	 * Methode permettant d'affecter un poids à chaque action du prédateur, et de modifier le vecteur vitesse
	 * en conséquence.<br>
	 * La somme coefficientée des différents vecteurs est ajoutée au vecteur vitesse et la vitesse est 
	 * ensuite normé.
	 * 
	 * @param o Liste des obstacles de la simulation
	 * @param p Liste des sardines de la simulation
	 * @param predateur Liste des prédateurs de la simulation
	 * @param coef_Rapproche Poids du vecteur se rapprocher
	 * @param coeff_Aligne Poids du vecteur s'aligner
	 * @param coeff_Eloigne Poids du vecteur s'éloigner ( evitement entre sardine)
	 * @param coeff_EvitePredateur Poids du vecteur eviter les prédateurs
	 * 
	 * @see #vecteurRapprocherMoy(List)
	 * @see #vecteurEloigner(List)
	 * @see #vecteurAligner(List)
	 * @see Poisson#vecteurEviter2(List)
	 * @see Poisson#vecteurEvitePredateur(List)
	 * @see Poisson#orienter(double[])
	 * @see Poisson#normaliseVitesse()
	 */
	protected void decision(List<Obstacle> o, List<Sardine> p, List<Predateur> predateur,double coef_Rapproche,double coeff_Aligne,double coeff_Eloigne,double coeff_EvitePredateur) {//Fonction qui permet de faire tous les déplacements (moyenne) peut être changé
	
		
		//On récupère les differents vecteurs vitesses
		double[] vitSeRapproche = vecteurRapprocherMoy(p);
		double[] vitSeloigne = vecteurEloigner(p);
		double[] vitSaligne = vecteurAligner(p);
		double[] vitEvite = vecteurEviter2(o);
		double[] vitEvitePredateur = vecteurEvitePredateur(predateur);
		
		//Si la sardine est dans l'obstacle elle rebondi radicalement  
		//Pas besoin entre sardines et predateurs car le poisson est mangé
		if(vitEvite[0]==-this.vitesse[0] && vitEvite[1]==-this.vitesse[1]) {
			orienter(-this.vitesse[0],-this.vitesse[1]);
			normaliseVitesse();
		}
		
		//On definit le nouveau vecteur vitesse comme la somme des différents vecteurs vitesse
		double[] vitNouvelle = new double[2];	
		vitNouvelle[0] = coef_Rapproche*vitSeRapproche[0]+coeff_Eloigne*vitSeloigne[0]+coeff_Aligne*vitSaligne[0]+coeff_EvitePredateur*vitEvitePredateur[0]+1*vitEvite[0];  // Le coeff associé à éviter obstacle est constant et égal à 1
		vitNouvelle[1] = coef_Rapproche*vitSeRapproche[1]+coeff_Eloigne*vitSeloigne[1]+coeff_Aligne*vitSaligne[1]+coeff_EvitePredateur*vitEvitePredateur[1]+1*vitEvite[1];	// Le coeff associé à éviter obstacle est constant et égal à 1	
		
		//On calcul la norme de vecteur
		double normeVect =Math.sqrt(Math.pow(vitNouvelle[0], 2)+Math.pow(vitNouvelle[1], 2));
		
		//Si la norme et nulle la sardine continue d'avancer
		if( normeVect==0.0) {
			orienter(this.vitesse);
			normaliseVitesse();
		}
		else{
			//on rajoute la nouveau vecteur vitesse à celui de la sardine
			vitNouvelle[0]= this.vitesse[0] +vitNouvelle[0];
			vitNouvelle[1]= this.vitesse[1] +vitNouvelle[1];
			orienter(vitNouvelle);
			
			//Et on le norme
			normaliseVitesse();
		}
	}
}
