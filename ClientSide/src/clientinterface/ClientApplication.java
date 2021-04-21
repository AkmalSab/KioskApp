package clientinterface;

import kioskapp.ordertransaction.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientApplication {
	
	public void SendCreditCardNumber(int cardNumber, int orderID, float totalAmount, String orderMode) {
		
		OrderTransaction ot = new OrderTransaction();
		
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
			byte sendingDataBuffer3[] = new byte[bufferSize];
			byte sendingDataBuffer4[] = new byte[bufferSize];
			byte sendingDataBuffer5[] = new byte[bufferSize];
			byte sendingDataBuffer6[] = new byte[bufferSize];
			
			// Convert data to bytes and store data in the buffer2
			
			// Last4Digits
			String sentence2 = String.valueOf(cardNumber);
			// Transaction Date
			String sentence3 = "now()";
			// Order id
			String sentence4 = String.valueOf(orderID);
			// AmountCharged
			String sentence5 = String.valueOf(totalAmount);
			// OrderMode
			String sentence6 = orderMode;
			
			// Last4Digits
			sendingDataBuffer2 = sentence2.getBytes();
			// Transaction Date
			sendingDataBuffer3 = sentence3.getBytes();
			// Order id
			sendingDataBuffer4 = sentence4.getBytes();
			// AmountCharged
			sendingDataBuffer5 = sentence5.getBytes();
			// OrderMode
			sendingDataBuffer6 = sentence6.getBytes();
			
			// Creating a UDP packet 2
			// Last4Digits
			DatagramPacket sendingPacket2 = new DatagramPacket(sendingDataBuffer2,
					sendingDataBuffer2.length, serverAddress, SERVERPORT2);
			// Transaction Date
			DatagramPacket sendingPacket3 = new DatagramPacket(sendingDataBuffer3,
					sendingDataBuffer3.length, serverAddress, SERVERPORT2);
			// Order id
			DatagramPacket sendingPacket4 = new DatagramPacket(sendingDataBuffer4,
					sendingDataBuffer4.length, serverAddress, SERVERPORT2);
			// AmountCharged
			DatagramPacket sendingPacket5 = new DatagramPacket(sendingDataBuffer5,
					sendingDataBuffer5.length, serverAddress, SERVERPORT2);
			// OrderMode
			DatagramPacket sendingPacket6 = new DatagramPacket(sendingDataBuffer6,
					sendingDataBuffer6.length, serverAddress, SERVERPORT2);
			
			// Sending UDP packet to the server2
			// Last4Digits
			clientSocket.send(sendingPacket2);
			// Transaction Date
			clientSocket.send(sendingPacket3);	
			// Order id
			clientSocket.send(sendingPacket4);	
			// AmountCharged
			clientSocket.send(sendingPacket5);	
			// OrderMode
			clientSocket.send(sendingPacket6);	
			
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

