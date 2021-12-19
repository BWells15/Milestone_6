package Products;

public class Armor extends SallableProd {

	private int defense;

	/**
	 * adds a new Armor
	 * 
	 * @param i   Product Designator
	 * @param n   Product Name
	 * @param d   Product Description
	 * @param q   Product Quantity
	 * @param p   Product Price
	 * @param def Product Defense
	 */
	public Armor(char i, String n, String d, int q, double p, int def) {
		super(i, n, d, q, p);
		setDefense(def);
	}

	/**
	 * returns the defense
	 * 
	 * @return defense the items defense
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * sets the defense
	 * 
	 * @param defense the items defense
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}

}
