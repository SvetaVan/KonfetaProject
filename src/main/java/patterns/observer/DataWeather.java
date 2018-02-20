package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class DataWeather implements Subject {
    private List<Display.Observer> observers;
    private double temperature;

    public DataWeather (){
        observers = new ArrayList<>();
    }




    @Override
    public void registerObserver(Display.Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Display.Observer observer) {
        if(observers.contains(observer)){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i <observers.size() ; i++) {
            Display.Observer observer1 = (Display.Observer)observers.get(i);
            observer1.update(temperature);
        }
    }

    public void measurementChanged(){
        notifyObserver();
    }

    public void setMeasurement (double temperature){
        this.temperature = temperature;
        measurementChanged();
    }
}
