package handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import event.LongEvent;


/**
 * ����������ִ�н���ֵ����10�Ĳ���
 */
public class C12EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number *= 10;
        System.out.println(System.currentTimeMillis()+": c1-2 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number *= 10;
        System.out.println(System.currentTimeMillis()+": c1-2 consumer finished.number=" + number);
    }
}