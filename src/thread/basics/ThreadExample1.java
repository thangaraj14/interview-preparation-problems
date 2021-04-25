package thread.basics;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.StringJoiner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

/**
 *
 */
public class ThreadExample1 implements Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        Thread threadRun = new Thread(runnable);
        threadRun.start();

        Callable<String> callable = () -> Thread.currentThread().getName();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> stringFuture = executorService.submit(callable);
        System.out.println(stringFuture.get());

        CompletableFuture<String> completetableFuture = new CompletableFuture<>();
        executorService.shutdown();

        Example example = new Example();
        Thread thread = new Thread(example);
        thread.start();

        StringJoiner sj = new StringJoiner(":", "[", "]");
        sj.add("One").add("Two").add("Three");
        String desiredString = sj.toString();

    }

    void try1() {
        Test t = () -> {
            return "test";
        };

        System.out.println(get());
    }

    @Override
    public String valid() {
        return null;
    }
}

class Example extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

@FunctionalInterface
interface Test {
    default String get() {
        return "String";
    }

    default String get1(String data) {
        return "String" + data;
    }

    static String geet() {
        System.out.println("test");
        return "";
    }

    String valid();
}
