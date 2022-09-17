package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomCollectors {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(2,6,8,9,0,1,52,5,61,8,9,96,0,18,23);

        Collector<Integer,List<Integer>,List<Integer>> toList= Collector.of(ArrayList::new, //supplier
                                       (list,e)->list.add(e), // Accumulator, List::add
                                        (list1,list2)->  {list1.addAll(list2); return list1;}, // combiner
                                        Collector.Characteristics.IDENTITY_FINISH

                );
            numbers.stream().filter(e->e%2==0).collect(toList);
    }
}
