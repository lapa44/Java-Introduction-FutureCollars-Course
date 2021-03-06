package pl.coderstrust.multithreading.asyncqueue;

import java.time.Duration;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo1Producer1ConsumerSleeping {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Queue<Integer> warehouse = new PriorityQueue<>(DEFAULT_CAPACITY);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(new Producer(warehouse, lock, Duration.ofSeconds(0)));
        service.execute(new Consumer(warehouse, lock, Duration.ofSeconds(1)));
    }
}
