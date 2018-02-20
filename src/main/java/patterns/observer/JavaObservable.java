package patterns.observer;

import java.util.Observable;

public class JavaObservable extends Observable {
    double temp;


    public void measurementChanged(){
        setChanged();
        notifyObservers();
    }
}
