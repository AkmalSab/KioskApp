import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ftmk.rmi.sensor.TemperatureSensor;

/**
 * This class represent the client-side of RMI application
 * 
 * @author emalianakasmuri
 *
 */
public class TemperatureClientRMIApp {

	public static void main(String[] args) {
		
		
		try {
			
			// Get registry
			Registry rmiRegistry = LocateRegistry.getRegistry();
			
			// Look-up for the remote object
			TemperatureSensor remoteSensorJasin = (TemperatureSensor) rmiRegistry.lookup("SensorJasin");
			TemperatureSensor remoteSensorAyerKeroh = (TemperatureSensor) rmiRegistry.lookup("SensorAyerKeroh");
			
			// Invoke method from the remote object
			int currentTemperature = remoteSensorJasin.getTemperature();
			int currentTemperature2 = remoteSensorAyerKeroh.getTemperature();
			int currentTemperature3 = remoteSensorAyerKeroh.getTemperatureByDay();
			String currentTemperature4 = remoteSensorAyerKeroh.getTemperatureAverage();
			
			System.out.println("Current temperature in Jasin is " + currentTemperature + " Celcius");
			System.out.println("Current temperature in Ayer Keroh is " + currentTemperature2 + " Celcius");
			System.out.println("Current temperature in Ayer Keroh on Monday is " + currentTemperature3 + " Celcius");
			System.out.println("average temperature in Ayer Keroh is " + currentTemperature4 + " Celcius");
			
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
