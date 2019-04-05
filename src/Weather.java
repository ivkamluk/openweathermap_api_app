import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;
import org.json.JSONException;

public interface Weather extends Remote {
	public Vector<WeatherData> forecastForToday(int date_number) throws RemoteException, 
	MalformedURLException,IOException, JSONException;
}