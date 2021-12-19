package FrontEnd;

import java.util.ArrayList;

import Products.Armor;
import Products.Health;
import Products.SallableProd;
import Products.Weapon;

public class ShoppingCart<T> {

	/**
	 * the current cart
	 */
	private ArrayList<T> inv = new ArrayList<T>();

	/**
	 * Gets the inventory
	 * 
	 * @return inventory
	 */
	public ArrayList<T> getInv() {
		return inv; 
	}

	/**
	 * method that adds the product to cart
	 * 
	 * @param i the product being added
	 */
	public void addToCart(SallableProd i) {
		if (i != null) {
			inv.add((T) i);
		} else {
			System.out.println("Failed to add to cart");
		}
	}

	/**
	 * empties the cart
	 */
	public void emptyCart() {
		System.out.print("Emptying Cart...");
		inv = new ArrayList<T>();
	}

	/**
	 * removing a product from the cart
	 * 
	 * @param i item to be removed from cart
	 * @return returns the product being removed
	 */
	public SallableProd removeFromCart(int i) {
		// checks if the item exists
		if (i >= inv.size()) {
			System.out.println("That item does not exist, please try again");
			return null;
			// removes the item from cart
		} else {
			if (inv.get(i) != null) {
				SallableProd temp = (SallableProd) inv.get(i);
				inv.remove(i);
				return temp;
			} else {
				System.out.println("Item does not exist in cart");
				return null;
			}
		}
	}

	/**
	 * Adds the product to the inventory manifest
	 * 
	 * @param i Product Designator
	 * @param n Product Name
	 * @param d Product Description
	 * @param e Product Quantity
	 * @param p Product Price
	 * @param s Product special value
	 * 
	 */
	public void addProduct(char i, String n, String d, int e, double p, int s) {
		if (i == 'a') {
			Armor arm = new Armor(i, n, d, e, p, s);
			inv.add((T) arm);
		}
		if (i == 'w') {
			Weapon wep = new Weapon(i, n, d, e, p, s);
			inv.add((T) wep);
		}
		if (i == 'h') {
			Health heal = new Health(i, n, d, e, p, s);
			inv.add((T) heal);
		}
	}

	/**
	 * Printing the cart inventory
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < inv.size(); i++) {
			str = str + (i + 1) + ". " + inv.get(i).toString() + "\n\n\n";
		}
		return str;
	}

}
