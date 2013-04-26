package com.example.boilerbanker;
//package com.example.boilerbanker;

import java.io.IOException;

public class ServerMain {

	public static void main(String[] args) {
			Server newServer = new Server(5003);
			try {
				newServer.listen();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
 