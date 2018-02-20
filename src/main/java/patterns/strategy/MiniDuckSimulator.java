package patterns.strategy;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck redHead = new RedHeadDuck();
        redHead.performFly();
        redHead.display();
        redHead.setFlyBehavior(new FlyNoWay());
        redHead.performFly();
    }
}
