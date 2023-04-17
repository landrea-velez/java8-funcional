package interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

//void accept(T t);
public class ConsumerApp {

    private void method1(){
        Consumer<String> print = x -> System.out.println(x);
        Consumer<Integer> fx = x -> {
          x = x + 5;
            System.out.println(x);
        };

        print.accept("Hola Coders");
        fx.accept(10);
    }

    private void method2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Consumer<Integer> consumer = x -> {
            x++;
            System.out.println(x);
        };

        //list.forEach(consumer);
        listAll(list, consumer);
    }

    //Funciones de Alto Orden - High Order Functions
    private void listAll(List<Integer> list, Consumer<Integer> fx){
        //list.forEach(fx);
        for(Integer i : list){
            fx.accept(i);
        }
    }

    public static void main(String[] args) {
        ConsumerApp app = new ConsumerApp();
        app.method2();
    }
}
