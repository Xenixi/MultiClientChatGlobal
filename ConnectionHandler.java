package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ConnectionHandler{
	//Imma change this-  it's just more practicle this way:
	private static ArrayList<PrintWriter> allClients = new ArrayList<>();
	private String username = "user:" + new Random().nextInt(); //just in case nothing's entered - we give something random
	public ConnectionHandler(Socket soc) {
		try {
		allClients.add(new PrintWriter(soc.getOutputStream(), true));
		BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		username = in.readLine();
		while(true) {
			String line = in.readLine();
			for(PrintWriter client: allClients) {
				//so what I'll do is make it so you first enter a username...
				client.println(username + ": " + line);
			}
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
