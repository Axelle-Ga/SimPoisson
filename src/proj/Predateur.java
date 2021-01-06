package proj;

import java.util.List;

/**
 * Predateur est la classe définissant les prédateurs. 
 * Elle hérite de Poisson
 *
 */
public class Predateur extends Poisson{
	
	/**
	 * Taille du prédateur : largeur (en pixel)
	 */
	protected int largeur;
	
	/**
	 * Taille du prédateur : longueur (en pixel)
	 */
	protected int longueur;

	/**
	 * Constructeur de Predateur
	 * 
	 * @param x 	Position en x
	 * @param y		Position en y
	 * @param or	Orientation (angle en radian)
	 * @param largeur	Taille du prédateur : largeur (en pixel)
	 * @param longueur	Taille du prédateur : longueur (en pixel)
	 * @param vitPred	Norme du vecteur vitesse
	 */
	public Predateur(int x, int y, double or, int largeur , int longueur, double vitPred) {
		super(x,y,or);		
		this.largeur=largeur;
		this.longueur=longueur;
		this.Vnorm = vitPred;
		}
	
	/**
	 * Calcul le vecteur suivre pour que les prédateurs suivent les sardines
	 * 
	 * @param listSardine	Liste des sardines de la simulation
	 * @return 				Vecteur suivre : double[]
	 */
	protected double[] vecteurSuivre(List<Sardine> listSardine) {
		double[] vecteurSuivre = {0, 0};
		List<Sardine> poissonPercu = poissonsPercus(listSardine, this.rayoninf, this.rayonMed);	//On récupère tous les poissons perçus par le predateur
		if (poissonPercu.isEmpty()) {
			return(vecteurSuivre);	//Si il n'y a pas de poissons perçus on renvoie le vecteur {0,0}
			}
		int nbrPoissonProche = 0;		//On initialise un compteur de poisson
		int somme_X = 0;	//On initialise la somme des positions en x
		int somme_Y = 0;	//On initialise la somme des positions en y

		for( Poisson i : poissonPercu) {
			somme_X += i.position[0];		
			somme_Y += i.position[1];		
			nbrPoissonProche += 1;
			}
		int moyenne_X = somme_X/nbrPoissonProche;	//On calcule la moyenne en X et en Y
		int moyenne_Y = somme_Y/nbrPoissonProche;
		
		vecteurSuivre[0] = moyenne_X - this.position[0];	//On calcul le vecteur que doit suivre le poisson pour s'aligner
		vecteurSuivre[1] = moyenne_Y - this.position[1];	// On divise par t = 1 pour être homogène
		return(normerVecteur(vecteurSuivre));
	}
	

	/**
	 * Méthode permettant d'attribuer un poids à chaque action du prédateur, et de modifier le vecteur vitesse
	 * en conséquence.<br>
	 * La somme coefficientée des différents vecteurs est ajoutée au vecteur vitesse et la vitesse est 
	 * ensuite normé.
	 * 
	 * @param o	Liste des obstacles de la simulation
	 * @param p Liste des sardines de la simulation
	 * @param predateur	Liste des prédateurs de la simulation
	 * 
	 * @see #vecteurSuivre(List)
	 * @see Poisson#vecteurEviter2(List)
	 * @see Poisson#vecteurEvitePredateur(List)
	 * @see Poisson#orienter(double[])
	 * @see Poisson#normaliseVitesse()
	 */
	protected void decision(List<Obstacle> o, List<Sardine> p, List<Predateur> predateur) {
			
			//On définit les coeff pour le vecteur vitesse final
			double coeff_Suivre = 0.5;
			double coeff_Evite = 0.9;
			
			//On récupère les differents vecteurs vitesses
			double[] vitSuivre = vecteurSuivre(p);
			double[] vitEvite = vecteurEviter2(o);
			double[] vitEvitePredateur = vecteurEvitePredateur(predateur);

			
			//Si le requin est dans l'obstacle ou dans autre requin il rebondi radicalement
			if((vitEvite[0]==-this.vitesse[0] && vitEvite[1]==-this.vitesse[1]) || (vitEvitePredateur[0]==-this.vitesse[0] && vitEvitePredateur[1]==-this.vitesse[1])) {
				orienter(-this.vitesse[0],-this.vitesse[1]);
				normaliseVitesse();
			}
			
			//On définit le nouveau vecteur vitesse comme la somme des différents vecteurs 
			double[] vitNouvelle = new double[2];
			vitNouvelle[0] = coeff_Suivre*vitSuivre[0]+coeff_Evite*(vitEvite[0]+vitEvitePredateur[0]);
			vitNouvelle[1] = coeff_Suivre*vitSuivre[1]+coeff_Evite*(vitEvite[1]+vitEvitePredateur[1]);
			
			//On calcul la norme de vecteur
			double normeVect =Math.sqrt(Math.pow(vitNouvelle[0], 2)+Math.pow(vitNouvelle[1], 2));
			
			//Si la norme est nulle le poisson continue d'avancer
			if( normeVect==0.0) {
				orienter(this.vitesse);
				normaliseVitesse();
			}
			else{
				//on rajoute la nouveau vecteur vitesse à celui du poisson
				vitNouvelle[0]= this.vitesse[0] +vitNouvelle[0];
				vitNouvelle[1]= this.vitesse[1] +vitNouvelle[1];
				orienter(vitNouvelle);
				
				//Et on le norme
				normaliseVitesse();
			}
	}

}
