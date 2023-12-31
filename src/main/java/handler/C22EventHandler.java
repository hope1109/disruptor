package handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import event.LongEvent;

public class C22EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number *= 20;
        System.out.println(System.currentTimeMillis()+": c2-2 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number *= 20;
        System.out.println(System.currentTimeMillis()+": c2-2 consumer finished.number=" + number);
    }
}