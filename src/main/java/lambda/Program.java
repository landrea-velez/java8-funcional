package lambda;

public class Program {

    public static void main(String[] args) {
        //Operation op1 = new Sum();
        Operation op1 = (a,b) -> a + b;
        Operation op2 = (a,b) -> a * b;

        int result1 = op1.operate(5,4);
        int result2 = op2.operate(5,4);

        System.out.println(result1);
        System.out.println(result2);
    }
}
