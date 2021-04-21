package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClientApplication {
	
	public static void main(String[] args) {
		// The server port to which the client socket is going to connect
		final int SERVERPORT2 = 50002;

		int bufferSize = 1024;

		try {
			// Instantiate client socket
			DatagramSocket clientSocket = new DatagramSocket();

			// Get the IP address of the server
			InetAddress serverAddress = InetAddress.getByName("localhost");
			
			// Create buffer to send data2
			byte sendingDataBuffer2[] = new byte[bufferSize];
			
			// Convert data to bytes and store data in the buffer2
			String sentence2 = "1234";
			sendingDataBuffer2 = sentence2.getBytes();
			
			// Creating a UDP packet 2
			DatagramPacket sendingPacket2 = new DatagramPacket(sendingDataBuffer2,
					sendingDataBuffer2.length, serverAddress, SERVERPORT2);
			
			// Sending UDP packet to the server2
			clientSocket.send(sendingPacket2);	
			
			// Create buffer to receive data
			byte receivingDataBuffer [] = new byte[bufferSize];
			
			// Receive data packet from server
		    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,
		    		receivingDataBuffer.length);
		    clientSocket.receive(receivingPacket);
		    
		    // Unpack packet
		    String allCapsData = new String(receivingPacket.getData());
		    System.out.println(allCapsData);

			// Closing the socket connection with the server
			clientSocket.close();
			
		} catch (Exception ex) {

			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
