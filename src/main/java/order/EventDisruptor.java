package order;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

public abstract class EventDisruptor<T> {
    private RingBuffer<EventValue<T>> ringBuffer;

    private Disruptor<EventValue<T>> disruptor;

    public void init(int size, String name) {
        disruptor = new Disruptor<EventValue<T>>(new EventValueFactory<T>(),size, Executors.defaultThreadFactory(), ProducerType.MULTI,new BlockingWaitStrategy());
    }

    protected Disruptor<EventValue<T>> getDisruptor() {
        return disruptor;
    }

    public void start() {
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
    }

    public boolean isEmpty() {
        return ringBuffer.getBufferSize() == ringBuffer.remainingCapacity();
    }

    public void put(int eventType, T pushValue) {
        long sequence = ringBuffer.next();
        EventValue<T> eventValue = ringBuffer.get(sequence);
        eventValue.setEventType(eventType);
        eventValue.setPushValue(pushValue);
        ringBuffer.publish(sequence);
    }
}
