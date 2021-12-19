package Products;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvMan<T> {
	/**
	 * the manifest array
	 */
	private ArrayList<T> inv = new ArrayList<T>();

	public InvMan() { 
		readFromFile();

	}

	/**
	 * @return the inv
	 */
	public ArrayList<T> getInv() {
		return inv;
	}

	/**
	 * moves items from the inventory manifest to the cart
	 * 
	 * @param i location in the manifest
	 * @return the product being sold
	 */
	public SallableProd sellProduct(int i) {
		// checks if the item exists
		if (i >= inv.size()) {
			System.out.println("That item does not exist, please try again");
			return null;
		}
		// sells the item

		else
			return (SallableProd) inv.get(i);

	}

	/**
	 * Sets the inventory
	 * 
	 * @param i inventory array to be set
	 */
	public void setInv(ArrayList<T> i) {
		inv = i;
	}

	/**
	 * returns a product back to the inventory manifest
	 * 
	 * @param prod Product that is returned
	 */
	@SuppressWarnings("unchecked")
	public void returnProduct(SallableProd prod) {
		if (prod != null) {
			inv.add((T) prod);
		}
	}

	/**
	 * deletes an item from the inventory
	 * 
	 * @param i index of the item deleted
	 */
	public void delete(int i) {
		if (inv.get(i) != null) {
			inv.remove(i);
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
	@SuppressWarnings("unchecked")
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
	 * returns a string with the inventory manifest
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i < inv.size(); i++) {
			str = str + (i + 1) + ". " + inv.get(i).toString() + "\n\n";

		}
		return str;
	}

	/**
	 * Saves the inventory to a json file
	 * 
	 * @param append determines whether the record is appending or not
	 * @param string the string to add
	 */
	public void saveToFile(boolean append, String string) {
		String filename = "inv1.json";
		// itterates through the inventory
		for (int i = 1; i < inv.size(); i++) { 
			PrintWriter pw;
			try {
				// setting file to copy to
				File file = new File(filename);
				FileWriter fw = new FileWriter(file, append);
				pw = new PrintWriter(fw);
				// prints into json file
				pw.println(string);

				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * grabs inventory from json file
	 * 
	 */
	public void readFromFile() {
		// starts up a placeholder inventory, setting the file
		String filename = "inv1.json";
		// catching IO exceptions
		try {
			// setting the file and scanner
			File file = new File(filename);
			Scanner s = new Scanner(file);
			// loop that adds each item as an index, each line is a new item
			while (s.hasNext()) {
				String json = s.nextLine();
				ObjectMapper objectMapper = new ObjectMapper();
				// setting a temporary product
				SallableProd prod = objectMapper.readValue(json, SallableProd.class);
				// adds the product, creating new objects based on the signifier
				this.addProduct(prod.getDesignator(), prod.getName(), prod.getDesc(), prod.getQuantity(),
						prod.getPrice(), prod.getSpecial());
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * checks the JSON against the inventory.
	 * @return code value
	 * @throws FileNotFoundException exception handling
	 * @throws JsonMappingException exception handling
	 * @throws JsonProcessingException exception handling
	 */
	public int checkJSONSize() throws FileNotFoundException, JsonMappingException, JsonProcessingException{
		int count = 0;
		String filename = "inv1.json";
		File file = new File(filename);
		Scanner s = new Scanner(file);
		// checks the amount of entries in the JSON
		while (s.hasNext()) {
			s.nextLine();
			count++;
		}
		// checks if equal
		if (inv.size() == count) {
			s.close();
			return 0;
		}
		// checks if greater
		if (inv.size() > count) {
			s.close();
			return 1;
		}
		// checks if less 
		if (inv.size() < count) {
			Scanner j = new Scanner(file);
			// checks for each of the counts
			for(int i = 0; i <= count - 1  ; i++) {
				String json = j.nextLine();
				// checks for only the difference
				if (i > inv.size() - 1) {
					// adds the new JSON entry
					ObjectMapper objectMapper = new ObjectMapper();
					SallableProd prod = objectMapper.readValue(json, SallableProd.class);
					// adds the product, creating new objects based on the signifier
					this.addProduct(prod.getDesignator(), prod.getName(), prod.getDesc(), prod.getQuantity(),
							prod.getPrice(), prod.getSpecial());
					System.out.println("items added");
				}
			}
			j.close();
			s.close();
			return 2;
		}
		else {
			s.close();
			return 3;
		}
		
	}
	
}
