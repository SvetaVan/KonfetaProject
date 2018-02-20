package patterns.singleton;

public enum Sun {

    INSTANCE;


    public int getTemperature() {
        return temperature;
    }

    int temperature = 10000;


    public static void main(String[] args) {
        Sun.INSTANCE.getTemperature();
    }


}
