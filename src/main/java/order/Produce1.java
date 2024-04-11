package order;

public class Produce1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            PushValue pushValue = new PushValue();
            pushValue.setEventType("3");
            pushValue.setNum(i);
            OrderPushDisruptor.getInstance().put(3,pushValue);
        }

        for (int i = 0; i < 5000; i++) {
            PushValue pushValue = new PushValue();
            pushValue.setEventType("4");
            pushValue.setNum(i);
            OrderPushDisruptor.getInstance().put(4,pushValue);
        }
    }
}
