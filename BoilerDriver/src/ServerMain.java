//package com.example.boilerbanker;

import java.io.IOException;

public class ServerMain {

	public static void main(String[] args) {
			Server newServer = new Server(5002);
			try {
				newServer.listen();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
