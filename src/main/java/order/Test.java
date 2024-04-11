package order;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {

    private static final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

    public static void main(String[] args) throws InterruptedException {

        //OrderPushDisruptor.getInstance().loadDisruptor();

        blockingQueue.offer("2");
        blockingQueue.put("3");
        boolean offer = blockingQueue.offer("1");
        if (!offer) {
            System.out.println("当前对列已满，信号已丢弃");
        }
        System.out.println(blockingQueue.size());


    }
}
