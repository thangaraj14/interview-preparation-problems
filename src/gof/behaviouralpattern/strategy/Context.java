package gof.behaviouralpattern.strategy;

import com.sun.tools.javac.util.List;

import java.util.function.Consumer;
import java.util.function.Function;

class Context {
    private Consumer<List<String>> sortStrategy;
    private Function<List<String>, String> searchStrategy;

    void sort(List<String> list) {
        sortStrategy.accept(list);
    }

    public Context(Consumer<List<String>> sortStrategy, Function<List<String>, String> searchStrategy) {
        this.sortStrategy = sortStrategy;
        this.searchStrategy = searchStrategy;
    }

    public void setSortStrategy(Consumer<List<String>> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setSearchStrategy(Function<List<String>, String> searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public String search(List<String> list) {
        return searchStrategy.apply(list);
    }
}

class Client {
    public static void main(String[] args) {
        List<String> list = List.of("b", "a", "c");
        Consumer<List<String>> bubbleSort = l -> System.out.println("List sorted using Bubble sort implementation");
        Function<List<String>, String> binarySearch = list1 -> {
            System.out.println("list is binary searched");
            return null;
        };
        Context context = new Context(bubbleSort, binarySearch);

        context.sort(list);
        String searchedElement = context.search(list);

        System.out.println("-------------");

        Consumer<List<String>> quickSort = list1 -> System.out.println("List sorted using Quick sort implementation");
        Function<List<String>, String> linearSearch = l -> {
            System.out.println("list is linearly searched");
            return null;
        };
        context.setSortStrategy(quickSort);
        context.setSearchStrategy(linearSearch);
        context.sort(list);
        context.search(list);
    }
}