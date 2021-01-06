package proj;

/**
 * Classe Obstacle
 *
 */
public class Obstacle {
	
	/**
	 * Position [x,y]
	 */
	protected int[] position;
	/**
	 * Position du centre [x,y]
	 */
	protected double[] positionCentre;
	/**
	 * Taille en pixel
	 */
	protected int taille;
	
	/**
	 * Constructeur d'Obstacle
	 * 
	 * @param x Position en x
	 * @param y Position en y
	 * @param taille Taille en pixel
	 */
	public Obstacle(int x, int y, int taille) {
		
		this.position = new int[2];
		this.position[0]=x;
		this.position[1]=y;
		this.taille = taille;
		this.positionCentre = new double[2];
		this.positionCentre[0] = x + taille/2;
		this.positionCentre[1] = y + taille/2;
	}

}
