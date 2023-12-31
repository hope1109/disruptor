package handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import event.LongEvent;


/**
 * C1-1消费者类
 * 该消费者执行将数值+10的操作。可以看到该消费者同时实现了EventHandler和WorkHandler两个接口。
 * 如果不需要池化，只需要实现EventHandler类即可。如果需要池化，只需要实现WorkHandler类即可。
 * 本例为了能够同时讲解池化和非池化的实现，因此同时实现了两个类，当然，也没啥问题。
 */
public class C11EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1 consumer finished.number=" + number);
    }

}