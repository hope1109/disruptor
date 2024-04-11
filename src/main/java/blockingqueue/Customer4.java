package blockingqueue;


public class Customer4 extends Thread{

    private static final Customer4 instance = new Customer4();

    public static Customer4 getInstance() {
        return instance;
    }
    private Customer4() {
        setName("Thread-Customer4");
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
