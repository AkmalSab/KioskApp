package server;

import database.connect;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ServerApplication {
	
	static Connection connection;//Creating object of Connection class
	static Statement statement;
	
	public static String fetchData(String query) throws ClassNotFoundException {
		connect c = new connect();	
		int id = 0;
		String name = null;
		float price = 0;
		try 
		{
	    	  connection = c.getConnection();
	          statement = connection.createStatement();
	          //String q = "select * from itemproduct";
	          ResultSet resultSet = statement.executeQuery(query);
	                              
	          while (resultSet.next()) 
	          {          
	        	  id = resultSet.getInt("ItemProduct");
	              name = resultSet.getString("Name");
	              price = resultSet.getFloat("Price");                            
	              
	              System.out.println(resultSet.getInt("ItemProduct") + ", " + resultSet.getString("Name") + ", " + resultSet.getString("Price"));
	          } 
	      } 
		  catch (SQLException e) 
		  {
	          e.printStackTrace();
	      }
		
		return name;
	}
	   
	public static void main(String[] args) {
				
		// Server UDP socket runs at this port
		final int serverPort = 50001;
		
		try {
			
			// Instantiate a new DatagramSocket to receive responses from the client
		    DatagramSocket serverSocket = new DatagramSocket(serverPort);
		    
		    // Create buffers to hold receiving data.
		    byte receivingDataBuffer[] = new byte[1024];
		    
		    // Instantiate a UDP packet to store the client data using the buffer for receiving data
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);
		    System.out.println("Waiting for a client to connect...");
		    		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
		    
		    // Printing out the client sent data
		    String receivedData = new String(inputPacket.getData());
		    System.out.println("Sent from the client: " + receivedData);
		    
		    //fetchData(receivedData);
		    
		    // Process data - convert to upper case
		    String sendingData = fetchData(receivedData).toUpperCase();
		    
		    // Creating corresponding buffer to send data
		    byte sendingDataBuffer[] = sendingData.getBytes();
		    
		    // Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
		    // Create new UDP packet with data to send to the client
		    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, 
		    		sendingDataBuffer.length, senderAddress,senderPort);
		    
		    // Send the created packet to client
		    serverSocket.send(outputPacket);		    
		    
		    // Close the socket connection
		    serverSocket.close();
		      
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}
	}
}
