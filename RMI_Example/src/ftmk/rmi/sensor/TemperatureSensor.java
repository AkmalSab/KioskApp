package ftmk.rmi.sensor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface represents temperature sensor
 * 
 * @author emalianakasmuri
 *
 */
public interface TemperatureSensor extends Remote {
	
	/**
	 * This method gets current temperature
	 * 
	 * @return current temperature
	 * 
	 * @throws RemoteException
	 */
	public int getTemperature() throws RemoteException;
	public int getTemperatureByDay() throws RemoteException;
	public String getTemperatureAverage() throws RemoteException;

}
