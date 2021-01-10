import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    Collection<Person> persons = new ArrayList<>();
    for (int i = 0; i < 10_000_000; i++) {
      persons.add(new Person(
              names.get(new Random().nextInt(names.size())),
              families.get(new Random().nextInt(families.size())),
              new Random().nextInt(100),
              Sex.values()[new Random().nextInt(Sex.values().length)],
              Education.values()[new Random().nextInt(Education.values().length)])
      );
    }
    Stream<Person> stream1 = persons.stream();
    // Количество несовершеннолетних
    long a = stream1.filter(x -> x.getAge() < 18).count();
    //System.out.println("Количество несовершеннолетних = " + a);
    Stream<Person> stream2 = persons.stream();
    // список фамилий призывников
    List<String> list1 = stream2
            .filter(x -> x.getSex() == Sex.MAN)
            .filter(x -> x.getAge() >= 18 && x.getAge() < 27)
            .map(x -> x.getFamily())
            .collect(Collectors.toList());
    //System.out.println(list1);
    Stream<Person> stream3 = persons.stream();
    // отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
    List<Person> list2 = stream3
            .filter(x -> x.getEducation() == Education.HIGHER)
            .filter(x -> x.getAge() >= 18)
            .filter(x -> x.getAge() < 60 && x.getSex() == Sex.WOMEN || x.getAge() < 65 && x.getSex() == Sex.MAN)
            .sorted(Comparator.comparing(x -> x.getFamily()))
            .collect(Collectors.toList());
    //System.out.println(list2);
  }
}
