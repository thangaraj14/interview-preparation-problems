package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class TestCourse {

    static class Person {
        int data;
        String name;

        public Person(int data, String name) {
            this.data = data;
            this.name = name;
        }
    }

    public static void main(String[] args) {

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(12, "th"));

        Map<Boolean, Set<Person>> collect = list.stream()
                                                .collect(Collectors.partitioningBy(p -> p.data > 10,
                                                        Collectors.toSet()));

    }

}
