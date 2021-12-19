package App;

import java.io.IOException;
import java.util.Scanner;
import FrontEnd.StoreFront;

public class driver {

	@SuppressWarnings("unchecked")
	public static <T> void main(String[] args) throws IOException, InterruptedException {
		// initializing setup
		Scanner input = new Scanner(System.in);
		StoreFront<T> store = new StoreFront<>();
		// creating the server thread
		Runnable runnable = new managementServer();
		Thread manageServ = new Thread(runnable);
		manageServ.start();

		Welcome();

		// simple loop for options
		for (int i = 1; i > 0; i--) {
			store.getInv().checkJSONSize();
			Options();
			int user = input.nextInt();
			// User selects buy
			if (user == 1) {
				System.out.println("Please Select the item to buy or press 0 to exit");
				System.out.printf(store.getInv().toString());
				int key = input.nextInt();
				System.out.println("Buying...");
				// checks for quit
				if (key == 0) {
					System.out.println("Quitting...");
					i++;

				} else {
					// buys the users prod
					store.buyProduct(key - 1);
					i++;
				}
			}
			// user selects sell
			else if (user == 2) {

				System.out.println("Please Select the item to sell or press 0 to exit");
				System.out.printf(store.getCart().toString());
				int key = input.nextInt();
				// checks for quit
				if (key == 0) {
					System.out.println("Quitting...");
					i++;

				}
				// sells the prod
				else {
					System.out.println("Selling...");
					store.returnProduct(key - 1);
					i++;
				}
				// user selects return cart
			} else if (user == 3) {
				System.out.println("Returning Cart...");
				store.returnCart();
				i++;
			}
			// user selects empty cart
			else if (user == 4) {
				System.out.println("Empting Cart...");
				store.getCart().emptyCart();
				i++;
			}
			// user selects to sort
			else if (user == 5) {
				System.out.println("1. Ascending, 2. Decending");
				int key = input.nextInt();
				// user selects ascending
				if (key == 1) {
					// sorts both inventories ascending
					store.getInv().getInv().sort(new Sorter());
					store.getCart().getInv().sort(new Sorter());
					store.sortDecending();
					System.out.println("Sorting Inv and Cart...");
				}
				if (key == 2) {
					// sorts both inventories by ascending
					store.getInv().getInv().sort(new Sorter());
					store.getCart().getInv().sort(new Sorter());
					System.out.println("Sorting Inv and Cart...");
				}
				i++;
			}
			// User Selects Quit
			else if (user == 0) {
				System.out.println("Quitting...");
				break;
				// user inputs wrong
			} else {
				System.out.println("Sorry incorect input, try again");
				i++;
			}
		}
		input.close();
	}

	/**
	 * responsible for the welcome message
	 */
	public static void Welcome() {
		System.out.println("Welcome to the Jimbo's Shoperooni, please choose one option below");
	}

	/**
	 * DRY code for options
	 */
	public static void Options() {
		System.out.println("1. Buy, 2. Sell, 3. Return Cart, 4. Empty Cart, 5. Sort Inventory 0. Quit");
	}
}
