package basic;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        //Lambda
        //list.forEach(x -> System.out.println(x));

        list.forEach(x -> {
            x++;
            System.out.println(x);
        });
    }
}
