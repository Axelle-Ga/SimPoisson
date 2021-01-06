package proj;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Classe Fenetre, elle définie la fenêtre d'intro, de fin et de configurations spéciales
 *
 */
public class Fenetre extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3312220672890977450L;
	
	/**
	 * Couleur de fond des boutons
	 */
	Color bleuFondClair = new Color(17,164,174);
	
	/**
	 * Taille des sardines
	 */
	double TaillePois = 7;
	
	/**
	 * Taille des prédateurs
	 */
	double TaillePred = 20;
	
	/**
	 * Norme du vecteur vitesse des sardines
	 */
	double VitesseSardine = 10;
	
	/**
	 * Norme du vecteur vitesse des prédateurs
	 */
	double VitessePredateur = 10;
	
	/**
	 * Principal
	 * @see Principal
	 */
	private Principal aquarium = new Principal(0,0,0,TaillePois,TaillePred,0.5,0.4,0.5,0.8,VitesseSardine,VitessePredateur);
	
	/**
	 * Panneau de simulation
	 * @see Panneau
	 */
	protected Panneau pan = new Panneau(aquarium.banc,aquarium.obstacles,aquarium.predateur, TaillePois,0.5,0.4,0.5,0.8,VitesseSardine);
	
	/**
	 * Label du nombre de sardines
	 */
	private JLabel label = new JLabel("<html><body><font color='white'>Nombre de sardines :</body></html>");
	
	/** 
	 * JSpinner pour choisir le nombre de sardines
	 */
	private JSpinner ChoixNbPoissons;
	
	/**
	 * Label du nombre d'obstacles
	 */
	private JLabel label2 = new JLabel("<html><body><font color='white'>Nombre d'obstacles:</body></html>");
	
	/** 
	 * JSpinner pour choisir le nombre d'obsatcles
	 */
	private JSpinner ChoixNbObstacles;
	
	/**
	 * Label du nombre de prédateurs
	 */
	private JLabel label3 = new JLabel("<html><body><font color='white'>Nombre de predateurs:</body></html>");
	
	/** 
	 * JSpinner pour choisir le nombre de prédateurs
	 */
	private JSpinner ChoixNbPredateurs;
	
	/**
	 * Label taille des sardines
	 */
	private JLabel label5 = new JLabel("<html><body><font color='white'>Taille des sardines :</body></html>");
	
	/**
	 * Label taille des prédateurs
	 */
	private JLabel label6 = new JLabel("<html><body><font color='white'>Taille prédateurs :</body></html>");
	
	/**
	 * JSlider pour choisir la taille des sardines
	 */
	protected JSlider taillePois = new JSlider(0,50,7);
	
	/**
	 * JSlider pour choisir la taille des prédateurs
	 */
	protected JSlider taillePred = new JSlider(0,50,20);
	
	/**
	 * Label de fin
	 */
	private JLabel labelFin1;
	
	/**
	 * Label de fin (réalisation)
	 */
	private JLabel labelFin2 = new JLabel("Ce projet a été réalisé par : Axelle Gaigé, Charlotte Desponds et Maïlys Monge");
	
	
	
	
	/**
	 * Label de fin EasterEgg(EE) (Introduction)
	 */
	JLabel intro = new JLabel("<html>Si vous n'êtes pas interréssé par les poissons vivants, voici les histoires des poissons de notre équipe de développeurs qui ont subi un triste sort...</html>", SwingConstants.HORIZONTAL);
	/**
	 * Label de fin EE (Axelle)
	 */
	JLabel poisAxl= new JLabel("<html>Le premier poisson d'Axelle s'appelait 'Poisson fait des bulles'.<br>"
			+ "Un jour, sa famille partit en week-end et son père<br> donna une bonne dose de nourriture au poisson pour qu'il survive."
			+ "<br>Malheureureusement, il mourut et fut enterré dans le jardin.<br>Elle acheta d'autres poissons en prépa pour se remonter le moral,<br>mais ils"
			+ " eurent tous des problèmes de vessie natatoire.<br> Un jour que Michel (un des poissons) agonisait dans son bocal,<br>la colocataire d'Axelle"
			+ " décida d'abréger ses souffrances et le jetta dans les toilettes.<br>Mais elle ne pu se résoudre à tirer la chasse. Axelle appuya donc sur le bouton"
			+ " et<br>envoya le pauvre poisson finir sa vie paisiblement dans les égouts.</html>",SwingConstants.HORIZONTAL);
	
	/**
	 * Label de fin EE (Maïlys)
	 */
	JLabel poisMai= new JLabel("<html>Maïlys avait un petit poisson jaune qui ne mangeait pas à sa faim car celui<br> de sa soeur "
			+ "était un morfal. Il était si maigre qu'il avait réussi<br>à se glisser entre le filtre"
			+ " et la vitre de l'aquarium, mais il y était resté coincé. Il resta <br>bloqué ainsi pendant plusieurs jours car"
			+ " Maïlys et sa famille ne le trouvant plus,<br>se dirent qu'il avait du être mangé par le chat. "
			+ "Le poisson fut retrouvé<br>quelques jours plus tard lors du nettoyage de l'aquarium.</html>",SwingConstants.HORIZONTAL);
	/**
	 * Label de fin EE (Charlotte)
	 */
	JLabel poisCha = new JLabel("<html>Quant à Charlotte, elle n'a jamais eu de poissons !</html>",SwingConstants.HORIZONTAL);
	/**
	 * Label de fin EE (citation)
	 */ 
	JLabel citation = new JLabel("<html>“Let me tell you something about wolves, child. When the snows fall<br>and the white winds blow, the lone wolf dies, but the pack survives,” ~ GOT~</html>",SwingConstants.HORIZONTAL);

	
	/**
	 * Bouton de création d'un océan
	 */
	JButton boutonCreer = new JButton("Créer votre Ocean");
	
	/**
	 * Bouton quitter
	 */
	JButton boutonQuitter = new JButton("Quitter");
	
	/**
	 * Bouton configurations particulières
	 */
	JButton boutonSpe = new JButton("Configurations Particulières");
	
	/**
	 * Bouton Queue Leu Leu
	 */
	JButton boutonTsu = new JButton("Queue Leu Leu");
	
	/**
	 * Bouton tourbillon
	 */
	JButton boutonTourbi = new JButton("Tourbillons de Poissons");
	
	/**
	 * Bouton gang de requins
	 */
	JButton boutonHG = new JButton("Gang de Requins");
	
	/**
	 * Bouton prison
	 */
	JButton boutonPrison = new JButton("La Prison");
	
	/**
	 * Bouton labyrinthe
	 */
	JButton boutonLab = new JButton("Le Labyrinthe");
	
	/**
	 * Bouton menu principal
	 */
	JButton boutonRevenir = new JButton("Menu Principal");
	
	/**
	 * Image de fond
	 */
	protected BufferedImage Img;
	
	
	/**
	 * Constructeur de Fenetre pour la fenêtre d'intro, si on ne met pas d'argument cela permet de lancer la fenêtre d'introduction
	 */
	public Fenetre() {
		
		configIntro();
		this.setResizable(false);
			
	}
	
	/**
	 * Constrcuteur de Fenetre pour la fenetre de fin
	 * 
	 * @param P Principal, permet de récupérer le temps de simulation qui est un attribut de Principal
	 */
	public Fenetre(Principal P) {
		
		aquarium = P;
		labelFin1 = new JLabel("Vos sardines ont survécu " + Integer.toString(aquarium.tempsExe) + " secondes");
		configFin();
		this.setResizable(false);
		
	}
	
	/**
	 * Constructeur Fenetre pour la fenetre configurations particuliaires
	 * 
	 * @param i Entier qui permet de choisir avec quelle configuration pour lancer une nouvelle Fenêtre
	 * (1 = configurations spéciales ou 2 = Fin spéciale)
	 */
	public Fenetre(int i) {
		if (i == 1) {
			configSpe();
			this.setResizable(false);
			}
		if (i == 2) {//Permet de lancer la Fenêtre d'EasterEgg si on met "2" en argument
			configFin2();
			this.setResizable(false);
		}
		
	}

	/**
	 * Modifie le style du bouton
	 * @param bouton Bouton
	 */
	protected void setStyleBouton(JButton bouton) {
		bouton.setBackground(bleuFondClair);
		bouton.setForeground(Color.white);
		bouton.setOpaque(true);
		bouton.setBorderPainted(false);
	}
	
	/**
	 * Lancement de la configuration tourbillon
	 * 
	 * @see #boutonTourbi
	 */
	protected void configTourbi() {
		
		new Thread(new Simulation(500,0,0,TaillePois,TaillePred,1,0,0,0,VitesseSardine,VitessePredateur)).start();
	    dispose();
	}
	
	/**
	 * Lancement de la configuration gang de requins
	 * 
	 * @see #boutonHG
	 */
	protected void configAcharnement() {
		dispose();
		
		new Thread(new Simulation(1,0,30,15,TaillePred,0.5,0.4,0.5,0.8,VitesseSardine,VitessePredateur)).start();
	    
	}
	
	/**
	 * Lancement de la configuration prison
	 * 
	 * @see #boutonPrison
	 */
	protected void configPrison() {
		dispose();
		//Mettre "true" dans Simulation() permet de lancer la création de la prison
		new Thread(new Simulation(50,1,TaillePois,TaillePred,0.5,0.4,0.5,0.8,VitesseSardine,VitessePredateur,true)).start();
	    
		
	}
	
	/**
	 * Lancement de la configuration labyrinthe
	 * @see #boutonLab
	 */
	protected void configLab() {
		dispose();
		//Mettre "false" dans Simulation() permet de lancer la création du labyrinthe
		new Thread(new Simulation(100,0,TaillePois,TaillePred,0.5,0.4,0.5,0.95,VitesseSardine,VitessePredateur,false)).start();
	    
		
	}
	
	/**
	 * Lancement de la configuration Queue leu leu
	 * @see #boutonTsu
	 */
	protected void configQueue() {
		//Lance on simulation normale avec 200 sardines, pas de prédateur et des valeurs spécifiques pour les coefficients de la  méthode decision() des sardines
		new Thread(new Simulation(200,0,0,TaillePois,TaillePred,0.3,1,0,0,VitesseSardine,VitessePredateur)).start();
	    dispose();
		
	}
	
	/**
	 * Lancement de la simulation avec les paramètres choisis par l'utilisateur
	 */
	protected void configSim() {
		
		//Récupération des paramètres choisis par l'utilisateurs
		int nbPoissons = (int) ChoixNbPoissons.getValue();
		int nbObstacles = (int) ChoixNbObstacles.getValue();
		int nbPredateurs = (int) ChoixNbPredateurs.getValue();
		
	    //On enlève la Fenêtre d'introduction
	    dispose(); 
	    //On crée un nouveau Thread pour pouvoir lancer la simulation
	    new Thread(new Simulation(nbPoissons,nbObstacles,nbPredateurs,TaillePois,TaillePred,0.5,0.4,0.5,1,VitesseSardine,VitessePredateur)).start();;
	    
	}

	/**
	 * Fenetre de choix de configurations particulières
	 * 
	 * @see #setFontSpe()
	 * @see #setBoutonSpe1()
	 * @see #setBoutonSpe2()
	 * @see #addBoutonSpe()
	 */
	protected void configSpe() {
		
		//On met une nouvelle Image en fond
		try
        {
			Img = ImageIO.read(getClass().getClassLoader().getResource("ocean4.png"));
            pan.img = Img;
            
        }
        catch (Exception e )
        {
             
        }
		//Cette méthode décrite en-dessous permet de modifier la police à l'interieur des boutons
		setFontSpe();
		//On gère l'apparence de la nouvelle fenêtre
		pan.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		this.setTitle("Configuration particulière");
		this.setSize(800,400);
    	pan.setLayout(new GridLayout(3, 2,50,50));
    	
    	//On attribut un ActionListener aux boutons
    	setBoutonSpe1();
    	setBoutonSpe2();
    	//On ajoute les boutons
    	addBoutonSpe();
    	
    	setContentPane(pan);
    	revalidate();
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    	
	}
	
	/**
	 * Configurations des boutons de configurations particulières (1)
	 * 
	 * @see #configSpe()
	 */
 	protected void setBoutonSpe1() {
 		//On ajoute des ActionListener aux boutons
 		setStyleBouton(boutonTsu);
    	boutonTsu.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		     configQueue();
    		          }});
    	setStyleBouton(boutonTourbi);
    	boutonTourbi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		     configTourbi();
        		          }});
    	setStyleBouton(boutonHG);
    	boutonHG.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		     configAcharnement();
        		          }});
    	
    	
 	}
 	
 	/**
 	 * Configurations des boutons de configurations particulières (2)
 	 * 
 	 * @see #configSpe()
 	 */
 	protected void setBoutonSpe2() {
 		
 		setStyleBouton(boutonPrison);
    	boutonPrison.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		     configPrison();
        		          }});
    	setStyleBouton(boutonLab);
    	pan.add(boutonLab);
    	boutonLab.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		     configLab();
        		          }});
    	boutonRevenir.addActionListener(this);
    	setStyleBouton(boutonRevenir);
 	}
 	
 	/**
 	 * Modification des polices et des boutons de configSpe
 	 * 
 	 * @see #configSpe()
 	 */
 	protected void setFontSpe() {
 		
 		Font f = new Font("Arial",Font.PLAIN, 20);
		boutonTourbi.setFont(f);
		boutonTsu.setFont(f);
		boutonHG.setFont(f);
		boutonPrison.setFont(f);
		boutonLab.setFont(f);
		boutonRevenir.setFont(f);
		
 	}
 	
 	/**
 	 * Ajout des boutons sur le panneau de la fenêtre de configSpe
 	 * 
 	 * @see #configSpe() 
 	 */
 	protected void addBoutonSpe() {
 		pan.add(boutonTsu);
    	pan.add(boutonTourbi);
    	pan.add(boutonHG);
    	pan.add(boutonPrison);
    	pan.add(boutonRevenir);
 	}
	
 	/**
 	 * Configuration de la fenêtre d'introduction
 	 * 
 	 * @see #setHautIntro()
 	 * @see #setHautIntro2()
 	 * @see #setBasIntro()
 	 */
 	protected void configIntro() {
 		pan.removeAll();
 		this.setSize(900,400);
 		//On charge l'image pour la fenêtre d'introduction
 		try
        {
 			Img = ImageIO.read(getClass().getClassLoader().getResource("intro.png"));
            pan.img = Img;
            
        }
        catch (Exception e )
        {
             
        }
 		
 		pan.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
 		//on gère l'apparence de la fenêtre
		this.setTitle("Créer son Ocean");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan.setLayout(new BorderLayout());
		
		setHautIntro();
		setHautIntro2();
		setBasIntro();
		
	    
	    this.setContentPane(pan);
	    this.setVisible(true); 
	}
 	
 	/**
 	 * Configure les JSlider de la fenêtre d'introduction
 	 * 
 	 * @see #setHautIntro2()
 	 */
 	protected void setSliderIntro() {
 		
 		taillePois.addChangeListener((ChangeListener) new ChangeListener() {
	    	 public void stateChanged(ChangeEvent event){
	    		 double nouvTaillePois = (double)((JSlider) event.getSource()).getValue();
	    		 TaillePois = nouvTaillePois;
	    	 }});
	    taillePois.setOpaque(false);
	    taillePois.setMajorTickSpacing(10);
	    taillePois.setMinorTickSpacing(1);
	    taillePois.setPaintTicks(true);
	    taillePois.setPaintLabels(true);
	    
	    
	    taillePred.addChangeListener((ChangeListener) new ChangeListener() {
	    	 public void stateChanged(ChangeEvent event){
	    		 double nouvTaillePred = (double)((JSlider) event.getSource()).getValue();
	    		 TaillePred = nouvTaillePred;
	    	 }});
	    taillePred.setOpaque(false);
	    taillePred.setMajorTickSpacing(10);
	    taillePred.setMinorTickSpacing(1);
	    taillePred.setPaintTicks(true);
	    taillePred.setPaintLabels(true);
 	}
 	
 	/**
 	 * Configuration du panneau de choix du nombre d'éléments de la simulation
 	 * 
 	 * @see #configIntro()
 	 */
	protected void setHautIntro() {
		//Premier Champs à remplir
		JPanel top = new JPanel();
		top.add(label);
		SpinnerModel model = new SpinnerNumberModel(100,0,1200,1);
 		ChoixNbPoissons = new JSpinner(model);
	    top.add(ChoixNbPoissons);
	   
	    //Deuxième champs à remplir
		top.add(label2);
		SpinnerModel model2 = new SpinnerNumberModel(3,0,100,1);
 		ChoixNbObstacles = new JSpinner(model2);
	    top.add(ChoixNbObstacles);
	    
	    //Troisième champs à remplir
	    top.add(label3);
	    SpinnerModel model3 = new SpinnerNumberModel(5,0,500,1);
 		ChoixNbPredateurs = new JSpinner(model3);
	    top.add(ChoixNbPredateurs);
	    top.setOpaque(false);
	    
	    pan.add(top, BorderLayout.PAGE_START);
	}
	
	/**
	 * Configuration du panneau de choix de la taille des poissons de la fentre d'intro
	 * 
	 * @see #setSliderIntro()
	 * @see #configIntro()
	 */
	protected void setHautIntro2() {

	    JPanel top2 = new JPanel();
	  
	    setSliderIntro();
	    top2.add(label5);
	    top2.add(taillePois);
	    top2.add(label6);
	    top2.add(taillePred);
	    top2.setOpaque(false);
	    
	    pan.add(top2,BorderLayout.CENTER);
	}
	
	/**
	 * Configuration des boutons de la fenetre d'intro
	 */
	protected void setBasIntro() {
				
		JPanel bottom = new JPanel();
	    bottom.setLayout(new GridLayout(1, 3,10,10));
	    bottom.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
	    boutonCreer.addActionListener(this);
	    boutonQuitter.addActionListener(this);
	    boutonSpe.addActionListener(this);
	    boutonRevenir.addActionListener(this);
	    
	    setStyleBouton(boutonCreer);
	    bottom.add(boutonCreer);
	    
	    setStyleBouton(boutonSpe);
	    bottom.add(boutonSpe);
	    
	    setStyleBouton(boutonQuitter);
	    bottom.add(boutonQuitter);
	    
	    bottom.setOpaque(false);
	    pan.add(bottom, BorderLayout.SOUTH);
	}
	
	/**
	 * Configuration de la fenêtre de fin
	 * 
	 * @see #setHautFin()
	 * @see #setBasFin()
	 * 
	 */
 	protected void configFin() {
 		
 		//On charge l'image pour la fenêtre de fin
		try
        {
			Img = ImageIO.read(getClass().getClassLoader().getResource("fin.png"));
            pan.img = Img;
        }
        catch (Exception e )
        {
             
        }
		
		this.setTitle("Fin de la simulation");
		this.setSize(900,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pan.setLayout(new BorderLayout());
		setHautFin();
		setBasFin();
		this.setContentPane(pan);
		this.setVisible(true);
		
		
	}
 	
 	/**
 	 * Configuration du panneau haut de la fenêtre de fin
 	 * 
 	 * @see #configFin()
 	 */
 	protected void setHautFin() {
 		
 		//Haut de la Fenetre
 		Font f = new Font("Arial",Font.PLAIN, 20);
 		JPanel top = new JPanel();
 		top.setLayout(new GridLayout(2,1));
 		top.setBorder(BorderFactory.createEmptyBorder(20,100,50,50));
 		labelFin1.setFont(f);
 		labelFin1.setForeground(Color.white);
 		top.add(labelFin1);
 		f = new Font("Arial",Font.PLAIN, 15);
 		labelFin2.setFont(f);
 		labelFin2.setForeground(Color.white);
 		top.add(labelFin2);
 		top.setOpaque(false);
 		pan.add(top, BorderLayout.NORTH);
 	}
 	
 	/**
 	 * Configuration du panneau bas de la fenetre de fin
 	 * 
 	 * @see #configFin()
 	 */
 	protected void setBasFin() {
 		
 		//Bas de la Fenetre
 		JPanel bottom = new JPanel();
 		bottom.setLayout(new GridLayout(1,2,50,50));
 		boutonQuitter.addActionListener(this);
 		boutonRevenir.addActionListener(this);
 		
 		setStyleBouton(boutonRevenir);
 		bottom.add(boutonRevenir);
 		
 		setStyleBouton(boutonQuitter);
 		bottom.add(boutonQuitter);
 		
 		bottom.setOpaque(false);
 		bottom.setBorder(BorderFactory.createEmptyBorder(50,50,10,50));
 		pan.add(bottom, BorderLayout.SOUTH);
 	}
 	
 	
 	/**
 	 * Configuration du panneau pour la fenetre de fin spéciale
 	 * @see #boutonEE()
 	 * @see #setCouleurFin2()
 	 * @see #setLayoutFin(GridBagConstraints, JPanel)
 	 * @see #setLayoutFin2(GridBagConstraints,JPanel)
 	 */
	protected void configFin2() {
 		//on charge l'image pour la deuxième fin
 		try
        {
			Img = ImageIO.read(getClass().getClassLoader().getResource("rip.png"));
            pan.img = Img;
        }
        catch (Exception e )
        {
             
        }
 		this.setTitle("Fin de la simulation");
		this.setSize(900,420);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel text = new JPanel();
		text.setLayout(new GridBagLayout());
		text.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//Ajoute les boutons
		boutonEE();
		
		//On utilise GridBagConstraints pour afficher les JLabels
		GridBagConstraints gbc = new GridBagConstraints();
		//Met les couleurs et les polices pour les JLabels
		setCouleurFin2();
		
		//Ajout des textes à la fenêtre
		setLayoutFin(gbc, text);
		
		setLayoutFin2(gbc, text);
		
		
		pan.add(text);
		this.setContentPane(pan);
		this.setVisible(true);

 	}
	
	
	/**
	 * Ajoute les boutons à la fenêtre de fin spéciale
	 * @see #configFin2()
	 */
	protected void boutonEE() {
		boutonQuitter.addActionListener(this);
 		boutonRevenir.addActionListener(this);
 		setStyleBouton(boutonQuitter);
 		setStyleBouton(boutonRevenir);

	}
	
	/**
	 * Met les bonnes couleurs et les bonnes polices aux boutons de la fenêtre de fin
	 * @see #configFin2()
	 */
	protected void setCouleurFin2() {
		Font f = new Font("Arial",Font.PLAIN,12);
		intro.setFont(f);
		poisAxl.setFont(f);
		poisMai.setFont(f);
		poisCha.setFont(f);
		citation.setFont(f);
		intro.setForeground(bleuFondClair);
		poisAxl.setForeground(bleuFondClair);
		poisMai.setForeground(bleuFondClair);
		poisCha.setForeground(bleuFondClair);
		citation.setForeground(bleuFondClair);
	}
	
	/**
	 * Mise en forme du GridBagLayout première partie
	 * @param gbc gridbagconstraint
	 * @param text JPanel
	 * @see #configFin2()
	 */
	protected void setLayoutFin(GridBagConstraints gbc,JPanel text) {
		text.setOpaque(false);
		
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		text.add(intro, gbc);
		
	}
	
	/**
	 * Mise en forme du GridBagLayout partie 2
	 * @param gbc gridbagconstraint
	 * @param text JPanel
	 * @see #configFin2()
	 */
	protected void setLayoutFin2(GridBagConstraints gbc,JPanel text) {
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		text.add(boutonRevenir,gbc);
		
		
		gbc.gridx = 2;
		gbc.gridy = 5;
		text.add(boutonQuitter,gbc);
		
	
		gbc.gridx = 3;
		gbc.gridy = 1;
		text.add(poisAxl,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		text.add(poisMai,gbc);
	
		gbc.gridx = 3;
		gbc.gridy = 3;
		text.add(poisCha,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		text.add(citation,gbc);
		
	}
 	
 	
	
 	/**
 	 * Action en fonction du bouton sur lequel l'utilisatuer a cliqué, on ferme à chaque fois
 	 * la fenêtre quand on appuie sur un bouton et on en ouvre une autre à partir d'un nouveau Thread 
 	 * 
 	 * @see #boutonCreer
 	 * @see #boutonQuitter
 	 * @see #boutonSpe
 	 * @see #boutonRevenir
 	 * 
 	 * @see #configSim()
 	 * @see #configIntro()
 	 */
	@Override
	public  void    actionPerformed(ActionEvent event)
    {
		Object  source = event.getSource();
		
        if(source == boutonCreer) {
        	
        	configSim();
        	
	        }
	        else if(source == boutonQuitter) {
	        	
	        	this.dispose();
	        	System.exit(0);
	        }
	        
	        else if(source == boutonSpe) {
	        	
	        	dispose();
	        	new Fenetre(1);
	        
	        }
	        else if(source == boutonRevenir) {
	        	
	        	dispose();
	        	configIntro();
	        	
	        }
        
    }

}
