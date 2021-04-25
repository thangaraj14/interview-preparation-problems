package thread.pluralsight.advanced.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayingWithExecutorsAndRunnables {

	public static void main(String[] args) {
		
		Runnable task = () -> System.out.println("I am in thread " + Thread.currentThread().getName());
		
		// ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		for (int i = 0 ; i < 10 ; i++) {
			// new Thread(task).start();
			service.execute(task);
		}
		
		service.shutdown();
		
	}
}
