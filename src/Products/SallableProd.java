package Products;

public class SallableProd implements Comparable<SallableProd> {
	/*
	 * attributes of a Sallable Product
	 */

	private char designator;
	private int special;
	private String name;
	private String desc;
	private double price;
	private int quantity;

	/**
	 * setting parameters for the product
	 * 
	 * @param i Product designator
	 * @param n Product Name
	 * @param d Product Description
	 * @param q Product Quantity
	 * @param p Product Price
	 */
	public SallableProd(char i, String n, String d, int q, double p) {
		designator = i;
		name = n;
		desc = d;
		quantity = q;
		price = p;
	}

	/**
	 * default constructor for sallable prod
	 */
	public SallableProd() {
		
		designator = ' ';
		name = "";
		desc = "";
		price = 0;
		quantity = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the description to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the designator
	 */
	public char getDesignator() {
		return designator;
	}

	/**
	 * @param desig the designator to set
	 */
	public void setDesignator(char desig) {
		this.designator = desig;
	}

	/**
	 * @return the special
	 */
	public int getSpecial() {
		return special;
	}

	/**
	 * @param special the special to set
	 */
	public void setSpecial(int special) {
		this.special = special;
	}

	/**
	 * returning the product details
	 */
	public String toString() {
		String str = ("Name : " + name + "\nDescription : " + desc + "\nQuantity : " + quantity + "\nPrice : " + price);
		return str;
	}
	
	@Override
	/**
	 * adds in the new compare function
	 * @param comp the product to compare
	 */
	public int compareTo(SallableProd comp) {
		// TODO Auto-generated method stub
		
		return toString().compareTo(comp.toString());
	}
}
