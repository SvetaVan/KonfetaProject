package patterns.decorator;

public class CoffeeShop {
    public static void main(String[] args) {
  //      Beverage beverage = ;
      //  System.out.println(beverage.getDescription() + " "+ beverage.cost());
   //     beverage = ;
        Beverage beverage = new Soy(new Mocha(new Espresso()));

        System.out.println(beverage.getDescription() + " "+ beverage.cost());









    }
}
