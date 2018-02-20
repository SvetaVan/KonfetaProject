package patterns.observer;

public interface Subject {
    void registerObserver(Display.Observer observer);
    void removeObserver(Display.Observer observer);
    void notifyObserver();

}
