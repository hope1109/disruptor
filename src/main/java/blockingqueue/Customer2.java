package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Customer2 extends Thread{

    private static final Customer2 instance = new Customer2();

    public static Customer2 getInstance() {
        return instance;
    }

    private Customer2() {
        setName("Thread-Customer2");
    }


    @Override
    public void run() {
        while (true) {
            try {
                String take = SingletonQueue.queue.take();
                System.out.println(getName() + " xiaofei:" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
