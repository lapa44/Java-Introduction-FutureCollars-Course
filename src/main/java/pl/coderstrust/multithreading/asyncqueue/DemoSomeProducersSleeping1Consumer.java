package pl.coderstrust.multithreading.asyncqueue;

import java.time.Duration;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoSomeProducersSleeping1Consumer {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Queue<Integer> warehouse = new PriorityQueue<>(DEFAULT_CAPACITY);
        int producersNumber = 3;
        ExecutorService service = Executors.newFixedThreadPool(producersNumber + 1);
        for (int i = 0; i < producersNumber; i++) {
            service.execute(new Producer(warehouse, lock, Duration.ofSeconds(1)));
        }
        service.execute(new Consumer(warehouse, lock, Duration.ofSeconds(0)));
    }
}
