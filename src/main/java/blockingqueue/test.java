package blockingqueue;

public class test {

    public static void main(String[] args) {
        Produce1 p = new Produce1();
        p.start();
        Produce2 p2 = new Produce2();
        p2.start();
        Customer3.getInstance().start();
        Customer1.getInstance().start();
        Customer2.getInstance().start();
        Customer4.getInstance().start();



    }

}
