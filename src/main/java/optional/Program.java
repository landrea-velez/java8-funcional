package optional;

import function.Client;

import java.time.LocalDate;
import java.util.Optional;

public class Program {

    private void m1Optional(){
        //Optional<Client> op = Optional.of(new Client(1, "Jaime", "Programmer", LocalDate.now(), 500.50, "Peru"));
        //Optional<Client> op = Optional.empty();
        //Optional<Client> op = Optional.ofNullable(null);
        Optional<Client> op = Optional.of(null);

        //System.out.println(op.isPresent());
        //System.out.println(op.isEmpty());
        //op.ifPresent(obj -> System.out.println(obj.getName()));
        System.out.println(op.orElse(new Client()));
        System.out.println(op.orElseGet(Client::new)); //()-> new Client()
        System.out.println(op.orElseThrow(() -> new ArithmeticException("BAD NUMBER")));
    }

    public static void main(String[] args) {
        Program program = new Program();
        program.m1Optional();
    }
}
