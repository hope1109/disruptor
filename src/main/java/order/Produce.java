package order;

public class Produce extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            PushValue pushValue = new PushValue();
            pushValue.setEventType("1");
            pushValue.setNum(i);
//            try {
//                sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            OrderPushDisruptor.getInstance().put(1,pushValue);
        }

//        for (int i = 0; i < 5000; i++) {
//            PushValue pushValue = new PushValue();
//            pushValue.setEventType("2");
//            pushValue.setNum(i);
//            OrderPushDisruptor.getInstance().put(2,pushValue);
//        }
    }
}
