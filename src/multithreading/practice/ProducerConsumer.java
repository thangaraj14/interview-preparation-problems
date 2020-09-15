package multithreading.practice;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {


    public static void main(String[] args) {


        Lock lock = new ReentrantLock();
        Condition producerCond1 = lock.newCondition();
        Condition consumerCond1 = lock.newCondition();

        List<Integer> buffer1 = new ArrayList<>(20);

        class Producer implements Callable<String> {
            List<Integer> buffer;
            Condition producerCond;  Condition consumerCond;
            public Producer( List<Integer> buffer,  Condition producerCond,  Condition consumerCond){
                this.buffer=buffer;
                this.producerCond=producerCond;
                this.consumerCond=consumerCond;
            }
            public String call() throws InterruptedException {
                int count = 0;
                try {
                lock.lock();
                while (count++ < 50) {

                        while (this.buffer.size() > 20) {
                            producerCond.await();
                        }
                        this.buffer.add((int) (Math.random() * 10));
                        consumerCond.signalAll();
                    }
                }
                finally {
                    lock.unlock();
                }
                return "Produced " + (count-1);
            }
        }

        class Consumer implements Callable<String> {
            List<Integer> buffer;
            Condition producerCond;  Condition consumerCond;
            public Consumer( List<Integer> buffer,  Condition producerCond,  Condition consumerCond){
                this.buffer=buffer;
                this.producerCond=producerCond;
                this.consumerCond=consumerCond;
            }
            public String call() throws InterruptedException {
                int count=0;
                try {
                lock.lock();

                while (count++  <50) {

                        while (this.buffer.size() == 0) {
                            consumerCond.await();
                        }
                        this.buffer.remove(buffer.size() - 1);
                        producerCond.signalAll();

                    }
                }
                finally {
                    lock.unlock();
                }
                return "consumer " + (count-1);
            }
        }

        ExecutorService service = Executors.newFixedThreadPool(8);

        ExecutorService service1 = Executors.newCachedThreadPool();


        List<Callable<String>> listProds = new ArrayList<>();
        List<Callable<String>> listCons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listProds.add(new Producer(buffer1,producerCond1,consumerCond1));
        }

        for (int i = 0; i < 4; i++) {
            listCons.add(new Consumer(buffer1,producerCond1,consumerCond1));
        }

        List<Callable<String>> allCalls = new ArrayList<>();
        allCalls.addAll(listProds);
        allCalls.addAll(listCons);

        try {
            List<Future<String>> future = service.invokeAll(allCalls);

            future.forEach(e -> {

                try {
                    System.out.println("result:: " + e.get());
                } catch (ExecutionException | InterruptedException ex) {
                    System.out.println("error "+ex.getMessage());
                }
            });

        }catch ( InterruptedException ex ){
            System.out.println("error "+ex.getMessage());
        }finally {
            service.shutdown();
        }
    }
}
