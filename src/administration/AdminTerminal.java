package administration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AdminTerminal {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
 /**
  * the starting point
  * @param args default args
  * @throws IOException exception handling
  * @throws InterruptedException exception handling
  */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		AdminTerminal client = new AdminTerminal();
		client.start("127.0.0.1", 6666);

	}

	/**
	 * starts the client program
	 * 
	 * @param ip   the ip of the client
	 * @param port the port of the server
	 * @throws UnknownHostException the exception that keeps the code from crashing
	 * @throws IOException          keeps client from crashing on bad IO
	 */
	public void start(String ip, int port) throws UnknownHostException, IOException {
		// opens the socket
		clientSocket = new Socket(ip, port);
		//opens the in and out 
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//the information print
		System.out.println(
				"Please enter your command below (Return Inventory - R; Add new item - U:designator:name:description:quantity:price:special)");
		int i = 1;
		String response;
		//runs an infinite loop
		while (i != 0) {
			//creates a scanner
			Scanner mess = new Scanner(System.in);
			String message;
			
			message = mess.next();
			System.out.print(message);
			//  Checks if the leading char is U or R
			if ((message.charAt(0) == 'U') || (message.charAt(0) == 'R')) {
				response = this.sendMessage(message);
			} else {
				response = ("invalid command");
			}
			
			String[] parsedRes = response.split("<");
			// displays message
			for (int j = 0; j < parsedRes.length; j++) {
				System.out.println(parsedRes[j]);
			}

			mess.close();

			// client.cleanup();
		}
	}

	/**
	 * sends the message given
	 * 
	 * @param msg the message to input
	 * @return returns the read message
	 * @throws IOException keeps IO exceptions from throwing
	 */
	public String sendMessage(String msg) throws IOException {
		out.println(msg);

		return in.readLine();
	}
}
