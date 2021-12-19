package Products;

public class Health extends SallableProd {

	private int health;

	/**
	 * adds a new Health
	 * 
	 * @param i   Product Designator
	 * @param n   Product Name
	 * @param d   Product Description
	 * @param q   Product Quantity
	 * @param p   Product Price
	 * @param def Product Healing
	 */
	public Health(char i, String n, String d, int q, double p, int hel) {
		super(i, n, d, q, p);
		setHealth(hel);

	}

	public Health() {
		super();
		health = 0;
	}

	/**
	 * Gets the healing
	 * 
	 * @return int health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the healing
	 * 
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
}
