package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class managementServer implements Runnable {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * runs the server
	 */
	public void run() {
		// System.out.println("Waiting for Client connection......");
		try {
			serverSocket = new ServerSocket(6666);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// out.println("connected to server on port " + clientSocket.getLocalPort());
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String inputLine;
		try {
			// takes an input
			while ((inputLine = in.readLine()) != null) {

				String input[] = inputLine.split(":");
				// interprets the user input
				if (",".equals(inputLine)) {
					System.out.println(" a message to shut the Server down");
					out.println("Quit");
					break;
				}
				// does the add function
				if (input[0].equals("U")) {
					out.println("   ---   U Command Received  ");
					if (input.length == 7) {
						// checks to see if the designator exists
						if (input[1].equals("a") || input[1].equals("w") || input[1].equals("h")) {
							// converts typed into JSON
							String addedJMSG = ("{\"designator\": \"" + input[1] + "\",\"name\": \"" + input[2]
									+ "\",\"desc\": \"" + input[3] + "\",\"quantity\": " + input[4] + ",\"price\": "
									+ input[5] + ",\"special\": " + input[6] + "}");
							// adds json to inv1.json
							addToFile(addedJMSG);
						} else {
							out.println("not enough characters");
						}
					} else {
						out.println("not a correct designator");
					}

				}
				// does the return function
				if (input[0].equals("R")) {
					out.println("   ---   R Command Recived " + jSonToString());

				} else {
					// out.println(" a message of: " + inputLine);
					// out.println("Waiting");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Server is shut down");
	}

	/**
	 * adds a given string into the JSON
	 * 
	 * @param string the string to add to json
	 * @throws IOException the IO exception thrown
	 */
	public void addToFile(String string) throws IOException {
		String filename = "inv1.json";
		PrintWriter pw;
		// setting file to copy to
		File file = new File(filename);
		FileWriter fw = new FileWriter(file, true);
		pw = new PrintWriter(fw);
		// prints into json file
		pw.println(string);
		pw.close();

	}

	/**
	 * takes the inventory and converts to a string
	 * 
	 * @return the string to be passed
	 * @throws FileNotFoundException exception handler
	 */
	public String jSonToString() throws FileNotFoundException {
		String text = "";
		// setting the file and scanner
		File file = new File("inv1.json");
		Scanner s = new Scanner(file);
		// loop that adds each item as an index, each line is a new item
		while (s.hasNext()) {
			String json = s.nextLine();
			text = text + "<" + json;

		}
		s.close();
		return text;
	}

}
