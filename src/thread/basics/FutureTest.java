package thread.basics;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Integer> callable = () -> {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += i;
            }
            return sum;
        };

        Future<Integer> submit = executorService.submit(callable);
        System.out.println(submit.get());

        Callable<Void> callable1 = () -> null;

        Future<Void> submit1 = executorService.submit(callable1);
        Void unused = submit1.get();

    }
}
