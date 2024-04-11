package blockingqueue;


public class Customer3 extends Thread{

    private static final Customer3 instance = new Customer3();

    public static Customer3 getInstance() {
        return instance;
    }
    private Customer3() {
        setName("Thread-Customer3");
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
