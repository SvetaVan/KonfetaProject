package patterns.strategy;

public abstract class Duck {

    FlyBehavior fb;

    public void display(){
        System.out.print("I am a duck");
    }

    public abstract void swim();

    public void performFly(){
        fb.fly();
    }

    public void setFlyBehavior (FlyBehavior flyBehavior){
        fb = flyBehavior;
    }




}
