package patterns.observer;

public interface Display {
    void display();

    interface Observer {
        void update(double temperature);
    }
}
