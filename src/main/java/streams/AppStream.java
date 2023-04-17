package streams;

import function.Client;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.Comparator.comparingInt;

public class AppStream {

    private void m1getDevelopers(List<Client> list, String searchText) {
        Predicate<Client> fx = e -> e.getJob().toLowerCase().contains(searchText);

        list.stream()
                .filter(fx)
                .forEach(System.out::println);
    }

    private void m2getInverse(List<Client> list) {
        Comparator<Client> inverse = (x1, x2) -> x2.getIdClient() - x1.getIdClient();

        list.stream()
                .sorted(inverse)
                .forEach(System.out::println);
    }

    private void m3getAdults(List<Client> list) {
        Predicate<Client> isAdult = e -> Period.between(e.getBirthDate(), LocalDate.now()).getYears() >= 18;

        list.stream()
                .filter(isAdult)
                .forEach(System.out::println);
    }

    public void m4getOldestAdult(List<Client> list) {
        list.stream()
                .sorted(Comparator.comparing(Client::getBirthDate)) //e -> e.getBirthDate()
                .limit(1)
                .forEach(System.out::println);
    }

    public void m5getMaxMinSalary(List<Client> list) {
        double max = list.stream()
                .mapToDouble(Client::getSalary)
                .max()
                .orElse(0);

        double min = list.stream()
                .mapToDouble(Client::getSalary)
                .min()
                .orElse(0);

        Client cli = list.stream()
                .max(Comparator.comparing(Client::getSalary))
                .orElse(new Client());

        System.out.println("Max Salary: " + max);
        System.out.println("Min Salary: " + min);
        System.out.println("Client Max Salary " + cli);
    }

    public void m6getAverage(List<Client> list) {
        double avg = list.stream()
                .mapToDouble(Client::getSalary)
                .average()
                .orElse(0);
        System.out.println(avg);
    }

    public void m7getSummary(List<Client> list) {
        DoubleSummaryStatistics stats = list.stream()
                .mapToDouble(Client::getSalary)
                .summaryStatistics();

        System.out.println(stats);
        System.out.println("Count: " + stats.getCount());
        System.out.println("Average: " + stats.getAverage());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Sum: " + stats.getSum());
    }

    public void m8getDistinct(List<Client> list) {
        list.stream()
                .distinct()
                .forEach(System.out::println);
    }

    public void m9getCount(List<Client> list) {
        long count1 = list.stream()
                //.filter()
                .count();
        int count2 = list.size();

        System.out.println(count1);
        System.out.println(count2);
    }

    public void m10skipLimit(List<Client> list) {
        list.stream()
                .skip(4)
                .limit(2)
                .forEach(System.out::println);
    }

    public void m11getAnyYounger(List<Client> list) {
        Predicate<Client> isYounger = e -> Period.between(e.getBirthDate(), LocalDate.now()).getYears() < 18;

        boolean rpta = list.stream()
                .anyMatch(isYounger);

        System.out.println("Is any younger: " + rpta);
    }

    public void m12map(List<Client> list) {
        list.stream()
                .map(e -> {
                    e.setSalary(e.getSalary() * 1.10);
                    return e.getSalary();
                })
                .forEach(System.out::println);
    }

    public void m12flatMap(List<Client> list) {
        list.stream()
                .flatMap(e -> {
                    e.setSalary(e.getSalary() * 1.10);
                    return Stream.of(e, "a", "b");
                })
                .forEach(System.out::println);
    }

    public void m14peek(List<Client> list) {
        List<Client> newList = list.stream()
                .filter(e -> e.getSalary() > 3000)
                .peek(System.out::println)
                .filter(e -> e.getSalary() > 4000)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public void m15GroupBy(List<Client> list) {
        Map<String, List<Client>> byCountry = list.stream()
                            .collect(groupingBy(Client::getCountry));

        Map<String, Map<String,List<Client>>> byCountryAndJob = list.stream()
                        .collect(groupingBy(Client::getCountry, groupingBy(Client::getJob)));

        System.out.println(byCountry);
        System.out.println(byCountryAndJob);
    }

    public void m16ToMapToSet(List<Client> list) {
        Map<Integer, Client> map = list.stream()
                                    .collect(toMap(Client::getIdClient, Function.identity()));

        //Set<Client> set = list.stream().collect(toSet());
        Set<Client> set = new HashSet<>(list);

        System.out.println(map.keySet());
        System.out.println(map.values());
        map.entrySet().forEach(System.out::println);
    }

    public void m17Order(List<Client> list){
        list.stream()
                .sorted(comparingInt(Client::getIdClient).reversed())
                .forEach(System.out::println);

        Stream.of(1,2,4,5)
                .sorted(reverseOrder())
                .forEach(System.out::println);
    }

        public static void main(String[] args) {
        AppStream appStream = new AppStream();

        Client e1 = new Client(1, "Client1", "Trainee Developer", LocalDate.of(1991, 1, 1), 1000.00, "Peru");
        Client e2 = new Client(2, "Client2", "QA", LocalDate.of(1993, 2, 1), 2000.00, "Peru");
        Client e3 = new Client(3, "Client3", "Architect", LocalDate.of(1995, 3, 1), 3000.00, "Paraguay");
        Client e4 = new Client(4, "Client4", "Cloud Engineer", LocalDate.of(1987, 4, 1), 4000.00, "Colombia");
        Client e5 = new Client(5, "Client5", "DevOps Engineer", LocalDate.of(1956, 1, 1), 5000.00, "Colombia");
        Client e6 = new Client(6, "Client6", "Scrum Master", LocalDate.of(2002, 11, 1), 4500.00, "Argentina");
        Client e7 = new Client(7, "Client7", "Leader Project", LocalDate.of(1998, 12, 1), 10000.00, "Mexico");
        Client e8 = new Client(8, "Client8", "Senior Developer", LocalDate.of(1996, 7, 1), 7000.00, "Rep. Dominicana");
        Client e9 = new Client(9, "Client9", "Junior Developer", LocalDate.of(2005, 8, 1), 500.00, "Ecuador");
        Client e10 = new Client(10, "Client10", "Mobile Developer", LocalDate.of(1975, 9, 1), 3000.00, "Chile");
        Client e11 = new Client(11, "Client11", "Accounting", LocalDate.of(1985, 7, 3), 2000.00, "España");
        Client e12 = new Client(12, "Client12", "Manager", LocalDate.of(1980, 9, 2), 2000.00, "Mexico");
        Client e13 = new Client(13, "Client13", "Manager II", LocalDate.of(1980, 9, 2), 2000.00, "Peru");


        List<Client> list = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13);

        //appStream.m1getDevelopers(list, "developer");
        appStream.m17Order(list);


    }
}
