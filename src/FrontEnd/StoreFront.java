package FrontEnd;

import Products.InvMan;
import Products.SallableProd;

public class StoreFront<T> {

	private InvMan inv;
	private ShoppingCart cart;

	/**
	 * the basic storeFront constructor
	 */
	public StoreFront() {
		inv = new InvMan(); 
		cart = new ShoppingCart();
	}

	/**
	 * storefront constructor for adding a cart and inv
	 * 
	 * @param i the inventory added
	 * @param c the shopping cart added
	 */
	public StoreFront(InvMan i, ShoppingCart c) {
		inv = i;
		cart = c;
	}

	/**
	 * @return the inv
	 */
	public InvMan getInv() {
		return inv;
	}

	/**
	 * @param inv the inv to set
	 */
	public void setInv(InvMan inv) {
		this.inv = inv;
	}

	/**
	 * @return the cart
	 */
	public ShoppingCart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	/**
	 * buys a product
	 * 
	 * @param i product being bought (in array)
	 */
	public void buyProduct(int i) {
		SallableProd prod = inv.sellProduct(i);
		boolean prodExists = updateQty(i, true);
		if (prodExists == false) {
			// if the product isnt already in the cart, create a new item, else update
			// quantity handles this
			cart.addProduct(prod.getDesignator(), prod.getName(), prod.getDesc(), 1, prod.getPrice(),
					prod.getSpecial());
		}
		// lowers quantity of the store inventory
		((SallableProd) inv.getInv().get(i)).setQuantity(((SallableProd) inv.getInv().get(i)).getQuantity() - 1);
	}

	/**
	 * Returns all items in the cart
	 */
	public void returnCart() {
		for (int i = cart.getInv().size(); i > 0; i--) {
			int j = i - 1;
			returnProduct(j);
		}
	}

	/**
	 * sells a product
	 * 
	 * @param i the product being returned (in array)
	 */
	public void returnProduct(int i) {
		boolean prodExists = updateQty(i, false);
		SallableProd prod = cart.removeFromCart(i);
		if (prodExists = false) {
			inv.returnProduct((prod));
		}
	}
	/**
	 * updates the quantity when moving items between inventories
	 * 
	 * @param Cart   the item index from the cart
	 * @param isCart determining if it is compared to a cart
	 * @return prodExists returns if a product exists in the inventory
	 */
	public boolean updateQty(int Cart, boolean isCart) {
		boolean prodExists = false;
		// if this is done on inventory manager
		if (isCart == false) {
			// runs through each value in the inventory manager
			for (int i = 0; i < inv.getInv().size() - 1; i++) {
				// checks if they reach any matches
				if (((SallableProd) cart.getInv().get(Cart)).getName() == ((SallableProd) inv.getInv().get(i)).getName()) {
					// if a match is reached QTY + 1
					((SallableProd) inv.getInv().get(i)).setQuantity(
							((SallableProd) cart.getInv().get(Cart)).getQuantity() + ((SallableProd) inv.getInv().get(i)).getQuantity());
					prodExists = true;
					break;
				}
			}

			// if this is done on a cart
		} else if (isCart == true) {
			// runs through each value in the inventory manager
			for (int i = 0; i < cart.getInv().size() - 1; i++) {
				// checks if they reach any matches
				if (((SallableProd) cart.getInv().get(i)).getName() == ((SallableProd) inv.getInv().get(Cart)).getName()) {
					// if a match is reached QTY + 1
					((SallableProd) cart.getInv().get(i))
							.setQuantity(((SallableProd) cart.getInv().get(Cart)).getQuantity() + 1);
					prodExists = true;
					break;
				}
			}
		}
		return prodExists;
	}
	/**
	 * Takes the ascending list and reverses it making it descending
	 */
	public void sortDecending() {
		// the loop for the inventory manager
		for (int i = 0; i < inv.getInv().size() / 2; i++) {
			SallableProd temp = (SallableProd) inv.getInv().get(i);
			inv.getInv().set(i, inv.getInv().get(inv.getInv().size() - i - 1));
			inv.getInv().set(inv.getInv().size() - i - 1, temp);		
		}
		// the loop for the shopping cart
		for (int i = 0; i < cart.getInv().size() / 2; i++) {
			SallableProd temp = (SallableProd) cart.getInv().get(i);
			cart.getInv().set(i, cart.getInv().get(cart.getInv().size() - i - 1));
			cart.getInv().set(cart.getInv().size() - i - 1, temp);		
		}
	
	}
	}

