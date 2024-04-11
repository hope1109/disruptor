package order;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class EventOrderHandler<T> extends Thread implements EventHandler<EventValue<T>>, WorkHandler<EventValue<T>> {

    public final BlockingQueue<T> queue = new LinkedBlockingDeque<>();

    protected int eventType;

    @Override
    public void onEvent(EventValue<T> tEventValue, long l, boolean b) throws Exception {
        if (eventType == tEventValue.getEventType()) {
            queue.offer(tEventValue.getPushValue());
            System.out.println(this.getName());
        }
    }

    @Override
    public void run() {
        final List<T> list = new ArrayList<>();

        while (true) {
            list.clear();

            queue.drainTo(list, 1000);

            try {
                process(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void process(List<T> list) throws IOException;
}
