import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;
import java.util.InputMismatchException;

import org.json.JSONException;

public class WeatherClient {

	public static void main(String[] args)
			throws NotBoundException, IOException, JSONException 
	{	
		Weather weather = (Weather) Naming.lookup("WeatherServer");
		LocalDate day = LocalDate.now();
		System.out.println("Client:Connected");
		final int max_number_date = 5;
		
		Vector<WeatherData> forecast_weather = new Vector<WeatherData>();
		
		System.out.println("Client: Enter date, to get the forecast");
		
		for (int i=1; i<=max_number_date; i++)
			System.out.println("Client: "+i+" - "+day.plusDays(i-1));
		
		Scanner scan = new Scanner (System.in);
		try {
			int date_number = scan.nextInt();
		forecast_weather = weather.forecastForToday(date_number);
		
		if (forecast_weather.isEmpty())
			System.out.println("Client: Can`t find forecast for this day");
		else
		{
			for (int i=0; i<forecast_weather.size(); i++)
			{
				System.out.println("Client: #Date# > " + forecast_weather.get(i).getDate());
				System.out.println("Client: Description > " + forecast_weather.get(i).getWeatherDescription());
				System.out.print("Client: Temp > " + forecast_weather.get(i).getTemp() + "°C");
				System.out.print(" Min temp > " + forecast_weather.get(i).getTempMin() + "°C");
				System.out.print(" Max temp > " + forecast_weather.get(i).getTempMax()+ "°C");
				System.out.println("\nClient: Humidity > " + forecast_weather.get(i).getHumidity() + " %");
				System.out.println("Client: Pressure > " + forecast_weather.get(i).getPressure() + " mm.hg");
				System.out.println("Client: Wind speed > " + forecast_weather.get(i).getWindSpeed() + " m/s\n\n");
			}	
		}
		}
		catch(InputMismatchException exception) {
			System.out.println("Error: Invalid number was entered!");
		}
	}
}
