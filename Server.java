package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket ssoc = new ServerSocket(9879);
			while(true) {
				Socket soc = ssoc.accept();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						ConnectionHandler ch = new ConnectionHandler(soc);
					}
				});
				t1.start();
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
