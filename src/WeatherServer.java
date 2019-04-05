import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import org.json.JSONException;

public class WeatherServer extends UnicastRemoteObject implements Weather {

	protected WeatherServer() throws RemoteException{ 
		super();
	}

	public Vector<WeatherData> forecastForToday(int date_number) throws MalformedURLException, 
												IOException, JSONException, RemoteException
	{
		OpenWeatherMap opweather = new OpenWeatherMap();
		opweather.forecastToVector();
		return opweather.forecastForToday(date_number);
	}
	
	public static void main(String[] args) {

		 try {
			 // port 1099 is default for communication
				Registry rgsty = LocateRegistry.createRegistry(1099);
				rgsty.rebind("WeatherServer", new WeatherServer());
	            System.out.println("Server is ready!");
	        } catch (Exception e) {
	            System.out.println("Server exception: " + e.toString());
	            e.printStackTrace();
	        }
	}
}
