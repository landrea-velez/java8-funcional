package interfaces;

import java.util.function.Predicate;

//boolean test(T t);
public class PredicateApp {

    private void method1(){
        Predicate<Integer> checkAgeNumber = x -> x >= 18;

        boolean result = checkAgeNumber.test(32);

        System.out.println(result);
    }

    private void method2(){
        Predicate<Integer> greaterThan = x -> x > 10;
        Predicate<Integer> lowerThan = x -> x < 20;

        boolean result1 = greaterThan.and(lowerThan).negate().test(15);
        boolean result2 = greaterThan.or(lowerThan).test(15);

        System.out.println(result1);
        System.out.println(result2);
    }

    private void method3(int num, Predicate<Integer> fx){
        boolean result = fx.test(num);
        System.out.println(result);
    }

    public static void main(String[] args) {
        PredicateApp app = new PredicateApp();
        //app.method2();
        //Predicate<Integer> fx1 = x -> x > 5;
        app.method3(10, x -> x > 5);
    }
}
