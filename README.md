# This is a simple example of RMI and [OpenWeatherMap API](https://openweathermap.org/api)
##### The Application can be useful for people trying to learn RMI and make a real-life application.

## Pre-run initializing
1. Sign up at OpenWeatherMap and get your [API key](https://home.openweathermap.org/api_keys).
1. Select your city id and modify these rows at the _OpenWeatherMap.java_ file.
```java
private	String API_KEY = "YOUR_API_KEY"; 
private	String CITY_ID = "YOUR_CITY_ID"; 
```
3. Change or leave row for an accepted unit at the _OpenWeatherMap.java_ file.
##### OpenWeatherMap is supporting:
* imperial: _Temperature in Fahrenheit_
```java
private String UNITS = "imperial";
```
* metric: _Temperature in Celsius_
```java
private String UNITS = "metric";
```
## Steps to run the app
#### Put all of this repository files inside a folder.
**Important! You need to have Java JDK!** 
**If you don`t have [download it](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)**
> First, you need to run commands for **WeatherServer** and only after that for **WeatherClient**
## Windows
```
open cmd 
make cd to_your_folder
make cd to_src_folder
```
and run this **WeatherServer** commands
```bash
set "path=%path%;C:\Program Files\Java\jdk1.8.0_201\bin"
javac -classpath "json.jar"; Weather.java
javac -classpath "json.jar"; WeatherData.java
javac -classpath "json.jar"; OpenWeatherMap.java
javac -classpath "json.jar"; WeatherServer.java
javac -classpath "json.jar"; WeatherClient.java
start rmiregistry
java -classpath "json.jar"; WeatherServer
java -classpath "json.jar"; WeatherClient
```
after that open another cmd and run **WeatherClient** commands
```bash
java -classpath "json.jar"; WeatherClient
PAUSE
```
## Mac and Linux
```
open terminal
make cd to_your_folder
make cd to_src_folder
```
run **WeatherServer** commands
```bash
javac -cp .:json.jar Weather.java
javac -cp .:json.jar WeatherData.java
javac -cp .:json.jar OpenWeatherMap.java
javac -cp .:json.jar WeatherServer.java
javac -cp .:json.jar WeatherClient.java
rmiregistry &
java -cp .:json.jar WeatherServer
java -cp .:json.jar WeatherClient
```
after that open another terminal window and run **WeatherClient** command
```bash
java -cp .:json.jar WeatherClient
```

## In result you will have like this: 
![server](https://user-images.githubusercontent.com/25110969/55293834-52a9b700-5403-11e9-944c-05a45e3182d1.png)
![client1](https://user-images.githubusercontent.com/25110969/55293836-53dae400-5403-11e9-9653-ee6c29750f59.png)
![client2](https://user-images.githubusercontent.com/25110969/55293837-55a4a780-5403-11e9-9016-5c5473e6c6dd.png)
