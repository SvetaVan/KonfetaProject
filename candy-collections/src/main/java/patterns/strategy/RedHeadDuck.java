package patterns.strategy;

public class RedHeadDuck extends Duck{
    public RedHeadDuck(){
        fb = new FlyWithWings();
    }
    @Override
    public void display(){
        super.display();
        System.out.println(", redhead duck");
    }

    @Override
    public void swim() {
        System.out.println("I can swim!");
    }
}
