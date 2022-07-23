package multithreading.producerconsumer;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        new ProducerConsumer<Integer>(/* numProducerThreads = */ 1, /* numConsumerThreads = */ 4,
                /* queueCapacity = */ 10) {
            @Override
            public void producer() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Producing " + i);
                    produce(i);
                }
            }

            @Override
            public void consumer() {
                for (Optional<Integer> opt; (opt = consume()).isPresent(); ) {
                    int i = opt.get();
                    System.out.println("Got " + i);
                }
            }
        };
    }
}
