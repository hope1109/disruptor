package blockingqueue;

public class Produce1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            SingletonQueue.queue.offer("" + i);
            System.out.println("produce num:" + i);
        }

    }
}
