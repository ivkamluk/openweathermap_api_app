import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherMap {

private	String API_KEY = "YOUR_API_KEY"; 
private	String CITY_ID = "YOUR_CITY_ID"; 
private String UNITS = "metric"; // or imperial
private	String url = "https://api.openweathermap.org/data/2.5/forecast?id=" + CITY_ID + "&appid=" +
	API_KEY + "&units="+UNITS; 
private	StringBuilder forecast;
private Vector<WeatherData> weatherData;
	
	public OpenWeatherMap() {
		forecast = new StringBuilder();
		weatherData = new Vector<WeatherData>();
	}
	
// Methods @get
	public StringBuilder getJsonData() {
		return forecast;
	}

	public Vector<WeatherData> getWeatherData() {
		return weatherData;
	}
	
	public void forecastToVector() throws MalformedURLException, IOException, JSONException {
		retrieveFromUrl();
		JSONObject json_object = new JSONObject(getJsonData().toString());			
		// all our data is inside an array
		JSONArray json_array = json_object.getJSONArray("list");
	
		// this value, show, how many we have values in our array
		short json_array_count = Short.parseShort(json_object.get("cnt").toString());		
		
		for (int i=0; i<json_array_count; i++)
		{
			getWeatherData().add(new WeatherData());
			
			JSONObject json_array_main = json_array.getJSONObject(i).getJSONObject("main");
			JSONObject json_array_wind = json_array.getJSONObject(i).getJSONObject("wind");
			JSONArray json_array_weather = json_array.getJSONObject(i).getJSONArray("weather");
			
			getWeatherData().get(i).setDate(json_array.getJSONObject(i).get("dt_txt").toString());
			getWeatherData().get(i).setTemp(Double.parseDouble(json_array_main.get("temp").toString()));
			getWeatherData().get(i).setTempMin(Double.parseDouble(json_array_main.get("temp_min").toString()));
			getWeatherData().get(i).setTempMax(Double.parseDouble(json_array_main.get("temp_max").toString()));
			getWeatherData().get(i).setPressure(Double.parseDouble(json_array_main.get("pressure").toString()));
			getWeatherData().get(i).setHumidity(Double.parseDouble(json_array_main.get("humidity").toString()));
			getWeatherData().get(i).setWindSpeed(Double.parseDouble(json_array_wind.get("speed").toString()));
			getWeatherData().get(i).setWeatherDescription(json_array_weather.getJSONObject(0).get("description").toString());
		}
		// this function is decide, if the weather is for selected day
		forecastSelectedDay();
	}
	
	private void forecastSelectedDay () {
		// first date we will always give to our variable 
		String special_date = getWeatherData().get(0).getDate();
		
		// now we will convert it
		// we do this, for comparing
		LocalDateTime date = LocalDateTime.parse(special_date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		special_date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date).toString();	

		int date_number = 1; // this variable is decide
							// if the weather is for the selected day
		
		for (int i=1; i<getWeatherData().size(); i++)
		{
			String tmp_special_date = getWeatherData().get(i).getDate();
			LocalDateTime tmp_date = LocalDateTime.parse(tmp_special_date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			tmp_special_date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(tmp_date).toString();
			
		// if dates are equals then, we set the same date_number
		// or set the next day for date_number
			if (special_date.equals(tmp_special_date))
			{
				getWeatherData().get(i-1).setDateNumber(date_number); // previous
				getWeatherData().get(i).setDateNumber(date_number);	// and next
			}
			else
				date_number += 1;
			
			special_date = tmp_special_date;
		}
	}
	
	public Vector<WeatherData> forecastForToday(int date_number) {
		
		Vector<WeatherData> forecast_weather = new Vector<WeatherData>();
		
		for (int i=0; i<getWeatherData().size(); i++)
		{	
			// now we match if the data is for selected day
			if (getWeatherData().get(i).getDateNumber() == date_number)
				forecast_weather.add(getWeatherData().get(i));
			else 
				continue;
		}
		return forecast_weather;
	}
		
	public void retrieveFromUrl() throws MalformedURLException, IOException {
		URL url_data = new URL(url);
		URLConnection conn = url_data.openConnection();
		
		BufferedReader bfread = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		String line;
		while((line = bfread.readLine()) != null)
			forecast.append(line);
		
		bfread.close();
	}
}
