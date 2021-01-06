package proj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.coobird.thumbnailator.Thumbnails;
/**
 * Classe FenetreSim. Elle h�rite de Fenetre.
 *
 */
public class FenetreSim extends Fenetre {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -593101953514101670L;
	
	/**
	 * Couleur bleur clair pour le fond des boutons
	 */
	Color bleuFondClair = new Color(17,164,174);
	
	/**
	 * Couleur bleu fonc� pour le fond des panneaux
	 */
	Color fond = new Color(29,71,88);
	
	/**
	 * Panneau de la simultaion
	 */
	protected Panneau panSim;
	
	/**
	 * Panneau des boutons
	 */
	protected JPanel panBoutons = new JPanel();
	
	/**
	 * Label du nombre de sardines
	 */
	JLabel NbPoissons = new JLabel("<html><body><font color='white'>Nombre de sardines :</body></html>");
	
	/**
	 * JSpinner pour choisir le nombre de sardines
	 */
	private JSpinner ChoixNbPoissons;
	
	/**
	 * Label du nombre d'obstacles
	 */
	JLabel NbObstacles = new JLabel("<html><body><font color='white'>Nombre d'obstacles :</body></html>");
	
	/**
	 * JSpinner pour choisir le nombre d'obstacles
	 */
	private JSpinner ChoixNbObstacles;
	
	/**
	 * Label du nombre de pr�dateurs
	 */
	JLabel NbPredateur = new JLabel("<html><body><font color='white'>Nombre de pr�dateurs :</body></html>");
	
	/**
	 * JSpinner pour choisir le nombre de pr�dateurs
	 */
	private JSpinner choixNbPredateurs ;
	
	/**
	 * Label taille sardines
	 */
	JLabel tailleSardine = new JLabel("<html><body><font color='white'>Taille des sardines</body></html>");
	
	/**
	 * JSlider pour choisir la taille des sardines
	 */
	private JSlider choixTailleSardine;
	
	/**
	 * Label taille des pr�dateurs
	 */
	JLabel taillePredateur = new JLabel("<html><body><font color='white'>Taille des pr�dateurs</body></html>");
	
	/**
	 * JSlider pour choisir la taille des pr�dateurs
	 */
	private JSlider choixTaillePredateur;
	
	/**
	 * Label poids aligner
	 */
	JLabel Aligner = new JLabel("<html><body><font color='white'>S'aligner</body></html>");
	
	/**
	 * JSlider choix du poids d'aligner
	 */
	private JSlider choixAligner;
	
	/**
	 * Label poids rapprocher
	 */
	JLabel Rapprocher = new JLabel("<html><body><font color='white'>Se rapprocher</body></html>");
	
	/**
	 * JSlider choix du poids rapprocher
	 */
	private JSlider choixRapprocher = new JSlider();
	
	/**
	 * Label poids eviter pr�dateurs
	 */
	JLabel EviterPredateur = new JLabel("<html><body><font color='white'>Eviter les pr�dateurs</body></html>");
	
	/**
	 * JSlider choix du poids �viter pr�dateur
	 */
	private JSlider choixEviter = new JSlider();
	
	/**
	 * Label poids �loigner
	 */
	JLabel Eloigner = new JLabel("<html><body><font color='white'>S'�viter</body></html>");
	
	/**
	 * JSlider choix du poids de s'�loigner
	 */
	private JSlider choixEloigner = new JSlider();
	
	/**
	 * Label vitesse des pr�dateurs
	 */
	JLabel VitPred = new JLabel("<html><body><font color='white'>Vitesse des pr�dateurs</body></html>");
	
	/**
	 * JSlider choix vitesse des pr�dateurs
	 */
	private JSlider choixVitPred = new JSlider();
	
	/**
	 * Label vitesse des sardines
	 */
	JLabel VitPois = new JLabel("<html><body><font color='white'>Vitesse des sardines</body></html>");
	
	/**
	 * JSlider choix de la vitesse des sardines
	 */
	private JSlider choixVitPois = new JSlider();
	
	/**
	 * Bouton relancer
	 */
	JButton Relancer = new JButton("Relancer");
	
	/**
	 * Bouton configurations particuli�res
	 */
	JButton configSpe = new JButton("Configurations Particuli�res");
	
	/**
	 * Stop simulation
	 */
	boolean stopSim = false;
	
	/**
	 * Compteur du nombre de sardines
	 */
	int cpteur;
	
	/**
	 * Label du compteur
	 */
	JLabel Compteur = new JLabel("Il reste : " + Integer.toString(cpteur) + " sardines");
	
	
	
	
	/**
	 * Constructeur FenetreSim
	 * 
	 * @param pois Nombre de Sardines
	 * @param obs Nombre d'obstacles
	 * @param pred Nombre de pr�datuers
	 * @param taille_pois Taille des sardines
	 * @param taille_pred Taille des pr�dateurs
	 * @param coef_Rapproche Poids du vecteur rapprocher
	 * @param coeff_Aligne	Poids du vecteur aligner
	 * @param coeff_Eloigne Poids du vecteur s'�loigner
	 * @param coeff_EvitePredateur Poids du vecteur �viter pr�dateurs
	 * @param vitPois Norme du vecteur vitesse des sardines
	 * @param vitPred Norme du vecteur vitesse des pr�dateurs
	 * @param P Panneau de la simulation
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 * @see #afficheFond()
	 */
	public FenetreSim(int pois, int obs, int pred, double taille_pois, double taille_pred,double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_EvitePredateur,double vitPois, double vitPred, Panneau P) {
		//Constructeur de la fen�tre de simulation
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		cpteur = pois;
		this.setTitle("Oc�an de Poisson");		
		this.setSize(screen);
		this.setLocationRelativeTo(null);
		
		configBase(pois, obs, pred, taille_pois, taille_pred, coef_Rapproche, coeff_Aligne, coeff_Eloigne, coeff_EvitePredateur,vitPois,vitPred, P);
		afficheFond();
		
		// Si on ferme la fen�tre �a arr�te tout
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//On met le panneau dans la fen�tre
		this.setContentPane(panSim);
	
		// On rend visible la fen�tre avec le panneau
		this.setVisible(true);
		
		// On permet � l'utilisateur de pouvoir modifier la taille de la fen�tre
		this.setResizable(true);
	}
	/**
	 * Affichage de l'image en fond
	 * 
	 * @see #FenetreSim(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	public void afficheFond() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		try
        {	
			//En fonction de la r�solution de l'�cran on charge l'image adapt�e
			Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
			if (resolution.equals(new Dimension(1280,720))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_1280x720.png"));
			}
			else if (resolution.equals(new Dimension(1366,768))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_1366x768.png"));
			}
			else if (resolution.equals(new Dimension(1440,900))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_1440x900.png"));
			}
			else if (resolution.equals(new Dimension(1536,864))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_1536x864.png"));
			}
			else if (resolution.equals(new Dimension(1600,900))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_1600x900.png"));
			}
			else if (resolution.equals(new Dimension(1920,1080))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_1920x1080.png"));
			}
			else if (resolution.equals(new Dimension(5120,2880))) {
				Img = ImageIO.read(getClass().getClassLoader().getResource("fond_5120x2880.png"));
			}
			//Si il n'y a pas d'image adapt�e on retaille l'image grace � la librairie net.coobird.thumbnailator.Thumbnails
			else {
				Img = ImageIO.read(getClass().getClassLoader().getResource("ocean4.png"));
				Img = Thumbnails.of(Img).forceSize(screen.width-430, screen.height).asBufferedImage();
			}
            panSim.img = Img;
        }
        catch (Exception e ){
            
       }
	}
	
	
	/**
	 * Configuration de la fen�tre de simulation
	 * 
	 * @param pois Nombre de sardines
	 * @param obs  Nombre d'obstacles
	 * @param pred Nombre de pr�dateurs
	 * @param taille_pois Taille des sardines
	 * @param taille_pred Taille des pr�dateurs
	 * @param coef_Rapproche Poids du vecteur rapprocher
	 * @param coeff_Aligne Poids du vecteur aligner
	 * @param coeff_Eloigne Poids du vecteur �loigner
	 * @param coeff_EvitePredateur Poids du vecteur �viter les pr�dateurs
	 * @param vitPois Norme du vecteur vitesse des sardines 
	 * @param vitPred Norme du vecteur vitesse des pr�dateurs
	 * @param P Panneau de la simulation
	 * 
	 * @see #proprieteCompteur()
	 * @see #proprieteRelancer()
	 * @see #proprieteChoixNbPoissons(int)
	 * @see #proprieteChoixNbObstacles(int)
	 * @see #proprieteChoixNbPredateurs(int)
	 * @see #proprieteChoixTaillePredateur(double)
	 * @see #proprieteChoixTailleSardine(double)
	 * @see #proprieteChoixAligner(double)
	 * @see #proprieteChoixRapprocher(double)
	 * @see #proprieteChoixEloigner(double)
	 * @see #proprieteChoixEviter(double)
	 * @see #proprieteVitPois(double)
	 * @see #proprieteVitPred(double)
	 * @see #proprieteConfigSpe()
	 * @see #proprietePanBoutons1()
	 * @see #proprietePanBoutons2()
	 */
	public void configBase(int pois, int obs, int pred, double taille_pois, double taille_pred,double coef_Rapproche,double coeff_Aligne, double coeff_Eloigne,double coeff_EvitePredateur,double vitPois, double vitPred, Panneau P) {
		
		panSim = P;
		panSim.setLayout(new BorderLayout(10,10));
		
		
		//Instanciation du Compteur
		proprieteCompteur();
		
		//Instanciation du bouton relancer
		proprieteRelancer();
		
		//Instanciation des JSpinner pour le nombre d'�l�ments de la simulation
		proprieteChoixNbPoissons(pois);
		proprieteChoixNbObstacles(obs);
		proprieteChoixNbPredateurs(pred);
		
		//Instanciation des JSlider pour la taille des poissons
		proprieteChoixTaillePredateur(taille_pred);
		proprieteChoixTailleSardine(taille_pois);
		
		//Instanciation des JSlider pour les coefficient du comportement des poissons
		proprieteChoixAligner(coeff_Aligne);
		proprieteChoixRapprocher(coef_Rapproche);
		proprieteChoixEloigner(coeff_Eloigne);
		proprieteChoixEviter(coeff_EvitePredateur);	
		
		//Instanciation des JSlider pour la vitesse des poissons
		proprieteVitPois(vitPois);
		proprieteVitPred(vitPred);
		
		//Instanciation du panneau contenant les boutons
		proprieteConfigSpe();
		proprietePanBoutons1();
		proprietePanBoutons2();
		
		panSim.add(panBoutons, BorderLayout.EAST);
	}
	
	/**
	 * Configuration du panneau des boutons (1)
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprietePanBoutons1() {
		panBoutons.setBackground(fond);		//Le fond du panneau est bleu fonc�
		panBoutons.setLayout(new GridLayout(13,2,15,15));	//Layout sous forme de tableau (11,2)
		panBoutons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));	//Bordure vide du JPanel
		
		//Ajout de la premi�re partie des composants
		
		panBoutons.add(NbPoissons);
		panBoutons.add(ChoixNbPoissons);
		panBoutons.add(NbObstacles);
		panBoutons.add(ChoixNbObstacles);
		panBoutons.add(NbPredateur);
		panBoutons.add(choixNbPredateurs);
		panBoutons.add(Aligner);
		panBoutons.add(choixAligner);
		panBoutons.add(Rapprocher);
		panBoutons.add(choixRapprocher);
		panBoutons.add(Eloigner);
		panBoutons.add(choixEloigner);
		panBoutons.add(EviterPredateur);
		panBoutons.add(choixEviter);
		
	}
	
	/**
	 * Configuration du panneau des boutons (2)
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprietePanBoutons2() {
		
		//Ajout des composants restants
		panBoutons.add(tailleSardine);
		panBoutons.add(choixTailleSardine);
		panBoutons.add(taillePredateur);
		panBoutons.add(choixTaillePredateur);
		panBoutons.add(VitPois);
		panBoutons.add(choixVitPois);
		panBoutons.add(VitPred);
		panBoutons.add(choixVitPred);
		panBoutons.add(Compteur);
		panBoutons.add(Relancer);
		panBoutons.add(configSpe);
		panBoutons.add(boutonQuitter);
	}
	
	/**
	 * Configuration du compteur de sardines
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteCompteur() {
		
		JPanel top = new JPanel();
		
		//Modification du style du compteur
		Font f = new Font("Arial",Font.PLAIN, 10);
		Compteur.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Compteur.setForeground(Color.WHITE);
	}
	
	/**
	 * Configuration des composants pour le choix du nombre de poissons
	 * 
	 * @param pois Nombre de poissons
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixNbPoissons(int pois) {
		//Cr�ation du JSpinner pour choisir le nombre de poissons
		
		//On rajoute une marge au label
		NbPoissons.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSpinner qui affiche par d�faut le nombre de poissons de la simulation
		SpinnerModel model = new SpinnerNumberModel(pois,0,1200,1);
		ChoixNbPoissons = new JSpinner(model);
	}
	
	/**
	 * Configuration des composants pour le choix du nombre d'obstacles
	 * 
	 * @param obs Nombre d'obstacle
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixNbObstacles(int obs) {
		//Cr�ation du JSpinner pour choisir le nombre d'obstacles
		
		//On rajoute une marge au label
		NbObstacles.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSpinner qui affiche par d�faut le nombre d'obstacles de la simulation
		SpinnerModel modelObstacle = new SpinnerNumberModel(obs,0,500,1);
		ChoixNbObstacles = new JSpinner(modelObstacle);
	}
	
	/**
	 * Configuration des composants pour le choix du nombre de pr�dateurs
	 * 
	 * @param pred Nombre de pr�dateurs
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixNbPredateurs(int pred){
		//Cr�ation du JSpinner pour choisir le nombre de pr�dateurs
		
		//On rajoute une marge au label
		NbPredateur.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSpinner qui affiche par d�faut le nombre de predateurs de la simulation
		SpinnerModel modelPredateur = new SpinnerNumberModel(pred,0,100,1);
		choixNbPredateurs = new JSpinner(modelPredateur);
	}
	
	/**
	 * Configuration des composants pour le choix de la taille des sardines
	 * 
	 * @param taille_pois Taille des sardines
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixTailleSardine(double taille_pois){
		//Cr�ation du JSlider pour choisir la taille des sardines
		
		//On rajoute une marge au label
		tailleSardine.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSlider qui affiche par d�faut la taille des sardines de la simulation
		choixTailleSardine = new JSlider(0,50, (int) taille_pois);
		choixTailleSardine.setOpaque(false);
		choixTailleSardine.setMajorTickSpacing(10);
		choixTailleSardine.setMinorTickSpacing(1);
		choixTailleSardine.setPaintTicks(true);
		choixTailleSardine.setPaintLabels(true);
		choixTailleSardine.setForeground(bleuFondClair);
	}
	
	/**
	 * Configuration des composants pour le choix de la taille des pr�dateurs
	 * 
	 * @param taille_pred Taille des pr�dateurs
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixTaillePredateur(double taille_pred){
		//Cr�ation du JSlider pour choisir la taille des pr�dateurs
		
		//On rajoute une marge au label
		taillePredateur.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSlider qui affiche par d�faut la taille des pr�dateurs de la simulation
		choixTaillePredateur = new JSlider(0,50, (int) taille_pred);
		choixTaillePredateur.setOpaque(false);
		choixTaillePredateur.setMajorTickSpacing(10);
		choixTaillePredateur.setMinorTickSpacing(1);
		choixTaillePredateur.setPaintTicks(true);
		choixTaillePredateur.setPaintLabels(true);
		choixTaillePredateur.setForeground(bleuFondClair);
		
	}
	
	/**
	 * Configuration des composants pour le choix du poids du vecteur aligner
	 * 
	 * @param coeff_Aligne Poids du vecteur aligner
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixAligner(double coeff_Aligne) {
		//Cr�ation du JSlider pour choisir le coefficient d'alignement des sardines
		
		//On rajoute une marge au label
		Aligner.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSlider qui affiche par d�faut le coefficient d'alignement des sardines de la simulation
		choixAligner = new JSlider(0,100,(int)(100*coeff_Aligne));
		choixAligner.setOpaque(false);
		choixAligner.setMajorTickSpacing(25);
		choixAligner.setMinorTickSpacing(5);
		choixAligner.setPaintTicks(true);
		choixAligner.setPaintLabels(true);
		choixAligner.setForeground(bleuFondClair);
		
	}
	
	/**
	 * Configuration des composants pour le choix du poids du vecteur rapprocher
	 * 
	 * @param coef_Rapproche Poids du vecteur rapprocher
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixRapprocher(double coef_Rapproche) {
		//Cr�ation du JSlider pour choisir le coefficient de rapprochement des sardines
		
		//On rajoute une marge au label
		Rapprocher.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSlider qui affiche par d�faut le coefficient de rapprochement des sardines de la simulation
		choixRapprocher = new JSlider(0,100,(int)(100*coef_Rapproche));
		choixRapprocher.setOpaque(false);
		choixRapprocher.setMajorTickSpacing(25);
		choixRapprocher.setMinorTickSpacing(5);
		choixRapprocher.setPaintTicks(true);
		choixRapprocher.setPaintLabels(true);
		choixRapprocher.setForeground(bleuFondClair);
	}
	
	/**
	 * Configuration des composants pour le choix du poids de vecteur �loigner
	 * 
	 * @param coeff_Eloigne Poids du vecteur �loigner
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixEloigner(double coeff_Eloigne) {
		//Cr�ation du JSlider pour choisir le coefficient d'�loignement des sardines
		
		//On rajoute une marge au label
		Eloigner.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSlider qui affiche par d�faut le coefficient d'�loignement des sardines de la simulation
		choixEloigner = new JSlider(0,100,(int)(100*coeff_Eloigne));
		choixEloigner.setOpaque(false);
		choixEloigner.setMajorTickSpacing(25);
		choixEloigner.setMinorTickSpacing(5);
		choixEloigner.setPaintTicks(true);
		choixEloigner.setPaintLabels(true);
		choixEloigner.setForeground(bleuFondClair);
	}
	
	/**
	 * Configuration des composants pour le choix du poids de vecteur �viter
	 * 
	 * @param coeff_EvitePredateur Poids du vecteur �viter
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteChoixEviter(double coeff_EvitePredateur) {
		//Cr�ation du JSlider pour choisir le coefficient d'�vitement des obstacles par les sardines
		
		//On rajoute une marge au label
		EviterPredateur.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		//On cr�e le JSlider qui affiche par d�faut le coefficient d'�vitement 
		//des obstacles par les sardines de la simulation
		choixEviter = new JSlider(0,100,(int)(100*coeff_EvitePredateur));
		choixEviter.setOpaque(false);
		choixEviter.setMajorTickSpacing(25);
		choixEviter.setMinorTickSpacing(5);
		choixEviter.setPaintTicks(true);
		choixEviter.setPaintLabels(true);
		choixEviter.setForeground(bleuFondClair);
	}
	
	/**
	 * Configuration des composants pour choisir la vitesse des sardines
	 * 
	 * @param nouvVit Norme du vecteur vitesse des sardines
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteVitPois(double nouvVit) {
		
		VitPois.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		choixVitPois = new JSlider(0,30,(int)(nouvVit));
		choixVitPois.setOpaque(false);
		choixVitPois.setMajorTickSpacing(10);
		choixVitPois.setMinorTickSpacing(1);
		choixVitPois.setPaintTicks(true);
		choixVitPois.setPaintLabels(true);
		choixVitPois.setForeground(bleuFondClair);
	}
	
	/**
	 * Configuration des composants pour choisir la vitesse des pr�dateurs
	 * 
	 * @param nouvVit Norme du vecteur vitesse des sardines
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteVitPred(double nouvVit) {
		
		VitPred.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		
		choixVitPred = new JSlider(0,30,(int)(nouvVit));
		choixVitPred.setOpaque(false);
		choixVitPred.setMajorTickSpacing(10);
		choixVitPred.setMinorTickSpacing(1);
		choixVitPred.setPaintTicks(true);
		choixVitPred.setPaintLabels(true);
		choixVitPred.setForeground(bleuFondClair);
	}
	
	/**
	 * Configuration du bouton pour acc�der � la fen�tre des configurations particuli�res
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteConfigSpe() {
			
		setStyleBouton(configSpe);
		
		configSpe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				stopSim = true;
				Fenetre F = new Fenetre(1);
				
			}
			
		});
	}
	
	/**
	 * Configuration du bouton Relancer
	 * 
	 * @see #configBase(int, int, int, double, double, double, double, double, double, double, double, Panneau)
	 */
	private void proprieteRelancer() {
		//Cr�e le bouton "Relancer
		Relancer = new JButton("Relancer");
		
		//Design du bouton
		setStyleBouton(Relancer);
		
		//On lui ajoute un ActionListener
		Relancer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionBouton(e);
			}
		});
	}
	
	/**
	 * Actions r�alis�es par le bouton relanc� apr�s click dessus
	 * 
	 * @param event Event
	 * 
	 * @see #proprieteRelancer()
	 */
	private void actionBouton(ActionEvent event) {
		//Actions r�alis�es apr�s appui sur le bouton "Relancer"
		//On r�cup�re les param�tres pour la nouvelle simulation
		int nbPoissons = (int) ChoixNbPoissons.getValue();
		int nbObstacles = (int) ChoixNbObstacles.getValue();
		int nbPredateurs = (int) choixNbPredateurs.getValue();
		
		double aligner = (double) choixAligner.getValue()/100;
		double rapprocher = (double) choixRapprocher.getValue()/100;
		double eloigner = (double) choixEloigner.getValue()/100;
		double eviter = (double) choixEviter.getValue()/100;
		
		double tailleSardine = (double) choixTailleSardine.getValue();
		double taillePredateur = (double) choixTaillePredateur.getValue();
		
		
		double vitessePois = (double) choixVitPois.getValue();
		double vitessePred = (double) choixVitPred.getValue();
		
		
    	dispose();	//On ferme la fenetre
    	
    	//On cr�e un nouveau thread qui va rouvrir une fen�tre de simulation
    	stopSim = true;
    	new Thread(new Simulation(nbPoissons, nbObstacles, nbPredateurs,tailleSardine,taillePredateur, rapprocher, aligner, eloigner,eviter,vitessePois,vitessePred)).start();	
    	
	}
	
}

