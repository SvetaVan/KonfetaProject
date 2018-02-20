package patterns.observer;

public class CurrentForecast implements Display.Observer,Display {
    private double temperature;
    private Subject weatherData;

    public CurrentForecast(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(double temperature) {
        this.temperature = temperature;
        display();
    }

    @Override
    public void display() {
        System.out.println(temperature);
    }
}
