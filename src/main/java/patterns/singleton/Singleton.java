package patterns.singleton;

public class Singleton {
    private static Singleton uniqueInst = new Singleton();
    private Singleton (){}
   /* public static Singleton getUniqueInst (){
        if(uniqueInst == null){
            uniqueInst = new Singleton();
        }
        return uniqueInst;
    }*/

   public static Singleton getUniqueInst(){
       return uniqueInst;
   }
   // еще как вариант добавить синхронайзд
}
