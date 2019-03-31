import java.io.Serializable;

//we need to provide Serializable,
//because we save data into class

public class WeatherData implements Serializable {
	private String date;
	private double temp;
	private double temp_min;
	private double temp_max;
	private double pressure;
	private double humidity;
	private double wind_speed;
	private String weather_description;
	private int date_number; // we need this variable,
	// for deciding if the weather for this day
	
// Methods @set
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public void setTempMin(double temp_min) {
		this.temp_min = temp_min;
	}
	
	public void setTempMax (double temp_max) {
		this.temp_max = temp_max;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	
	public void setWindSpeed(double wind_speed) {
		this.wind_speed = wind_speed;
	}
	
	public void setWeatherDescription(String weather_description) {
		this.weather_description = weather_description;
	}
	
	public void setDateNumber(int date_number) {
		this.date_number = date_number;
	}

// Methods @get
	public double getTemp() {
		return temp;
	}

	public double getTempMin() {
		return temp_min;
	}

	public double getTempMax() {
		return temp_max;
	}

	public double getPressure() {
		return pressure;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getWindSpeed() {
		return wind_speed;
	}
	
	public String getDate() {
		return date;
	}

	public String getWeatherDescription() {
		return weather_description;
	}
	
	public int getDateNumber() {
		return date_number;
	}		
}


