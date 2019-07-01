package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static String username = "";
	public static void main(String[] args) {
		System.out.println("Client started.");
		try {
			
			Socket soc = new Socket("127.0.0.1", 9879);

			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

			// listener thread

			Scanner scan = new Scanner(System.in);
			while (true) {
				System.out.println("Enter a valid username: ");
				username = scan.nextLine();
				if(!username.equals("")) {
					out.println(username);
					break;
				}
			}

			Thread t1 = new Thread(new Runnable() {
				public void run() {
					while (true) {
						String received;
						try {
							received = in.readLine();
							System.out.println((received.startsWith(username) ? "You" + (received.substring(username.length(),received.length())) : received)); 

						} catch (IOException e) {
							System.err.println("Failed to receive data");
						}

					}
				}
			});
			//wow
			t1.start();
			Scanner input = new Scanner(System.in);
			while (true) {
				System.out.println("Enter MSG to send to all clients");
				String entered = input.nextLine();
				out.println(entered);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
