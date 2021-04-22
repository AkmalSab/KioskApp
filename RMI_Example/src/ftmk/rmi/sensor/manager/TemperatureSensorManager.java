package ftmk.rmi.sensor.manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


import ftmk.rmi.sensor.TemperatureSensor;

/**
 * This class manage the value of temperature from the sensor.
 * 
 * @author emalianakasmuri
 *
 */
public class TemperatureSensorManager extends UnicastRemoteObject implements TemperatureSensor {

	public TemperatureSensorManager() throws RemoteException {
		super();
	}

	@Override
	public int getTemperature() throws RemoteException {
		
		return 35;
	}
	
	@Override
	public int getTemperatureByDay() throws RemoteException {
		
		HashMap<String, String> dayTemperature = new HashMap<String, String>();
		// Add keys and values (Country, City)
		dayTemperature.put("Monday", "32");
		dayTemperature.put("Tuesday", "31");
		dayTemperature.put("Wednesday", "33");
		dayTemperature.put("Thursday", "35");
		dayTemperature.put("Friday", "36");
		dayTemperature.put("Saturday", "33");
		dayTemperature.put("Sunday", "33");
		
		System.out.println(dayTemperature.get("Monday"));
	    
	    return Integer.valueOf(dayTemperature.get("Monday"));
	}
	
	@Override
	public String getTemperatureAverage() throws RemoteException {	
				
		String answer = "";
		int avg = 0;
		HashMap<String, Integer> dayTemperature = new HashMap<String, Integer>();
		// Add keys and values (Country, City)
		dayTemperature.put("Monday", 32);
		dayTemperature.put("Tuesday", 31);
		dayTemperature.put("Wednesday", 33);
		dayTemperature.put("Thursday", 35);
		dayTemperature.put("Friday", 36);
		dayTemperature.put("Saturday", 33);
		dayTemperature.put("Sunday", 33);
		
		Iterator iterator = dayTemperature.entrySet().iterator();
		
		while (iterator.hasNext()) 
		{
            Map.Entry<String, Integer> me2 = (Map.Entry) iterator.next();
            answer = "Key: "+me2.getKey() + " & Value: " + me2.getValue();
            avg += me2.getValue();
        } 
		
		avg /= 7;
		
		return String.valueOf(avg);
	}

}
