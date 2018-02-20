package patterns.observer;

public class WeatherStationTest {
    public static void main(String[] args) {
        DataWeather dataWeather = new DataWeather();
        CurrentForecast currentForecast = new CurrentForecast(dataWeather);
        dataWeather.setMeasurement(-5.20);
    }
}
