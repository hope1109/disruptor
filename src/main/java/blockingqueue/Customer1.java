package blockingqueue;

public class Customer1 extends Thread implements Customer{

    private static final Customer1 instance = new Customer1();

    public static Customer1 getInstance() {
        return instance;
    }
    private Customer1() {
        setName("Thread-Customer1");
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
