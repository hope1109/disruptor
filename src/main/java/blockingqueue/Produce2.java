package blockingqueue;

public class Produce2 extends Thread{

    @Override
    public void run() {
        for (int i = 5000; i < 10000; i++) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SingletonQueue.queue.offer("" + i);
            System.out.println("produce num:" + i);
        }
    }
}
