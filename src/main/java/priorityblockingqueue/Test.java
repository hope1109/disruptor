package priorityblockingqueue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueue<SignEntity> queue = new PriorityBlockingQueue<>(2, (o1, o2) -> o2.getCountter() - o1.getCountter());
        SignEntity signEntity5 = new SignEntity();
        signEntity5.setCountter(5);

        SignEntity signEntity1 = new SignEntity();
        signEntity1.setCountter(1);
        SignEntity signEntity2 = new SignEntity();
        signEntity2.setCountter(1);
        SignEntity signEntity3 = new SignEntity();
        signEntity3.setCountter(2);
        SignEntity signEntity4 = new SignEntity();
        signEntity4.setCountter(2);
        queue.offer(signEntity5);
        queue.offer(signEntity1);
        queue.offer(signEntity2);
        queue.offer(signEntity3);
        queue.offer(signEntity4);



        for (int i = 0; i < 5; i++) {


                System.out.println(queue.take());
        }
        double d = 99.99d;
        long s = (long) d;
        System.out.println(s);

        double d1 = Double.parseDouble(null);

    }

}
