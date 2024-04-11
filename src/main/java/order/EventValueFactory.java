package order;

import com.lmax.disruptor.EventFactory;

public class EventValueFactory<T> implements EventFactory<EventValue<T>> {

    @Override
    public EventValue<T> newInstance() {
        return new EventValue<T>();
    }
}
