package Products;

public class Weapon extends SallableProd {

	private int damage;

	/**
	 * adds a new Weapon
	 *
	 * @param i   Product Designator
	 * @param n   Product Name
	 * @param d   Product Description
	 * @param q   Product Quantity
	 * @param p   Product Price
	 * @param def Product Damage
	 */
	public Weapon(char i, String n, String d, int q, double p, int dam) {
		super(i, n, d, q, p);
		setDamage(dam);
	}

	/**
	 * Gets the damage
	 * 
	 * @return int the Damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the damage
	 * 
	 * @param damage the damage to set
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
