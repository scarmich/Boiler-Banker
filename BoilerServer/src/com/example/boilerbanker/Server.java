package com.example.boilerbanker;
import java.io.IOException;

//package com.example.boilerbanker;


public class Server extends AbstractServer {

	public Server(int port){
		super(port);
	}
	
	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server starts listening for connections.
	 */
	protected void serverStarted(){
		System.out.println("Server listening for connections on port " + getPort());
	}
	  
	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server stops listening for connections.
	 */
	protected void serverStopped(){
		System.out.println("Server has stopped listening for connections.");
	}

	public void handleMessageFromClient (Object msg, ConnectionToClient client){
  		System.out.println("Message received: " + msg + " from " + client);
  		String delims = "[ ]";
  		String message = (String) msg;
  		String[] tokens = message.split(delims);
  		String userName = tokens[0];
  		String password = tokens[1];
  		System.out.println(userName);
  		System.out.println(password);
  		
  		PFCUDriver driver;
  		
  		try {
			driver = new PFCUDriver(userName, password);
			if (driver.getFlag()) {
	  			User user = new User(userName);
	  			System.out.println(user.getBalance() + " " + user.getTransactions()[0].getLocation());
	  			this.sendToAllClients((Object) user);
	  		} else {
	  			String failure = "Incorrect credentials";
	  			this.sendToAllClients((Object) failure);
	  		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		/***************************
  		 * implement call to Web driver here using userName and password
  		 */
  		
	}
	
}

