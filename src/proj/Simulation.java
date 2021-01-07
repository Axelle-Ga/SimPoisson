package proj;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;

/**
 * Classe simulation. Elle implémente l'interface runnable
 *
 */
public class Simulation implements Runnable {
	
	/**
	 * Panneau de la simulation
	 * @see Panneau
	 */
	protected Panneau panSim;
	
	/**
	 * Principal
	 * @see Principal
	 */
	protected Principal aquariumSim;
	
	/**
	 * Fenetre de la simulation
	 * @see FenetreSim
	 */
	protected FenetreSim F;
	
	/**
	 * Espace disponible en x
	 */
	protected int largeurEcran;
	
	/**
	 * Espace disponible en y
	 */
	protected int hauteurEcran;
	
	
	/**
	 * Permet d'activer l'EasterEgg
	 */
	protected boolean easterEgg = false;
	
	/**
	 * Constructeur de Simulation
	 * 
	 * @param pois Nombre de sardines
	 * @param obs Nombre d'obstacles
	 * @param pred Nombre de prédateurs
	 * @param taille_pois Taille des sardines
	 * @param taille_pred Taille des prédateurs
	 * @param coef_Rapproche Poids du vecteur rapprocher
	 * @param coeff_Aligne Poids du vecteur aligner
	 * @param coeff_Eloigne Poids du vecteur eloigner
	 * @param coeff_EvitePredateur Poids du vecteur eviter prédateurs
	 * @param vitPois Norme du vecteur vitesse des sardines
	 * @param vitPred Norme du vecteur vitesse des prédateurs
	 */
	public Simulation(int pois, int obs, int pred, double taille_pois, double taille_pred,double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_EvitePredateur,double vitPois,double vitPred) {
		
		aquariumSim = new Principal(pois,obs,pred,taille_pois,taille_pred,coef_Rapproche,coeff_Aligne,coeff_Eloigne,coeff_EvitePredateur,vitPois,vitPred);
		panSim = new Panneau(aquariumSim.banc,aquariumSim.obstacles,aquariumSim.predateur, taille_pois,coef_Rapproche,coeff_Aligne,coeff_Eloigne,coeff_EvitePredateur,vitPois);
		F = new FenetreSim(pois, obs, pred, taille_pois, taille_pred, coef_Rapproche, coeff_Aligne, coeff_Eloigne, coeff_EvitePredateur,vitPois,vitPred, panSim);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		largeurEcran = screen.width-435;
		hauteurEcran = screen.height;
		
		if(pois == 0) {//Si l'utilisateur rentre 0 sardine, cela permet d'ouvrir un fin spéciale
			easterEgg = true;
		}
		
		
		
		
	}
	
	/**
	 * Constructeur de Simulation, ici on ne met pas le nombre d'obstacles car ils ne vont pas être générés aléatoirement
	 * 
	 * @param pois Nombre de sardines
	 * @param pred Nombre de prédateurs
	 * @param taille_pois Taille des sardines
	 * @param taille_pred Taille des prédateurs
	 * @param coef_Rapproche Poids du vecteur rapprocher
	 * @param coeff_Aligne Poids du vecteur aligner
	 * @param coeff_Eloigne Poids du vecteur eloigner
	 * @param coeff_EvitePredateur Poids du vecteur eviter prédateurs
	 * @param vitPois Norme du vecteur vitesse des sardines
	 * @param vitPred Norme du vecteur vitesse des prédateurs
	 * @param choixConf Choix de la configuration(true = arène, false = labyrinthe)
	 */
	public Simulation(int pois, int pred, double taille_pois,double taille_pred, double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_EvitePredateur,double vitPois,double vitPred, boolean choixConf) {
			
			aquariumSim = new Principal(pois,pred,taille_pois,taille_pred,coef_Rapproche,coeff_Aligne,coeff_Eloigne,coeff_EvitePredateur,vitPois,vitPred, choixConf );
			panSim = new Panneau(aquariumSim.banc,aquariumSim.obstacles,aquariumSim.predateur, taille_pois,coef_Rapproche,coeff_Aligne,coeff_Eloigne,coeff_EvitePredateur,vitPois);
			F = new FenetreSim(pois, aquariumSim.obstacles.size(), pred, taille_pois, taille_pred, coef_Rapproche, coeff_Aligne, coeff_Eloigne, coeff_EvitePredateur,vitPois,vitPred, panSim);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			largeurEcran = screen.width-435;
			hauteurEcran = screen.height;
			
			
			
		}
	
	
	
	
	
	/**
	 * Evolution de la simulation
	 */
	public void run() {
		try {
			Thread.sleep(100);
		      } catch (InterruptedException e) {
		    	e.printStackTrace();
		      }
		 
		long TimeStart = System.currentTimeMillis();
		
		//Active l'EasterEgg
		if(easterEgg == true) {//Permet de lancer la fenêtre de fin spéciale
				//On utilise un invokeLater pour configurer la fenêtre dans l'EDT et pas dans le thread qui a été créé pour la simulation
				//Il en va de même pour tous les autres invokeLater appelés par la suite
				SwingUtilities.invokeLater(new Runnable() {
			          public void run() {
			        	  F.dispose();
			          }
				});
				new Thread(new Runnable() {
		      		  public void run() {
		      			  //Lance la Fenêtre de fin spéciale
		      			 new Fenetre(2);
		      		  }
		      	  }).start();
					//Permet d'arrêter le thread de la simulation
					Thread.currentThread().interrupt();
					return;
		}
			while(true == true) {//On met une boucle infinie afin qu'il n'y est pas de temps limité
				
				if(aquariumSim.banc.size() == 0) {
					long TimeEnd = System.currentTimeMillis();
					//On ajoute le temps de survie des poissons
					aquariumSim.tempsExe =(int)( (TimeEnd - TimeStart)/1000);
					//Permet de modifier swing dans l'EDT
					SwingUtilities.invokeLater(new Runnable() {
				          public void run() { 
				        	  F.dispose();
				          }
					});
					new Thread(new Runnable() {
		        		  public void run() {
		        			 Fenetre F2 = new Fenetre(aquariumSim);
		        		  }
		        	  }).start();
					//On arrête le thread courant pour continuer à faire tourner la simulation
					Thread.currentThread().interrupt();
					return;
				}
				if(F.stopSim == true) {
					//Permet d'arreter le thread quand on appuie sur le bouton "relancer" ou "configurations particulières"
					Thread.currentThread().interrupt();
					return;
				}
				
				aquariumSim.avancerPredateur(largeurEcran, hauteurEcran);
				aquariumSim.avancerBanc(largeurEcran, hauteurEcran);
				
				SwingUtilities.invokeLater(new Runnable() {
			          public void run() {
			        	  panSim.setPanneau(aquariumSim.banc, aquariumSim.obstacles, aquariumSim.predateur);
			        	  F.cpteur = aquariumSim.banc.size();
			        	 //On change le compteur de sardine en temps réel
			        	  F.Compteur.setText("Il reste : " + Integer.toString(F.cpteur) + " sardines");
			        	  F.repaint();
			          }
				});
			    
				
				try {
			        Thread.sleep(80);
			      } catch (InterruptedException e) {
			        e.printStackTrace();
			      }
			}
		
	}
}
