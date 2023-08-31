package event;

import com.lmax.disruptor.EventFactory;

public class Event<T> {
    public static final EventFactory<Event> FACTORY = new EventFactory<Event>() {
        @Override
        public Event newInstance() {
            return new Event();
        }
    };

    private T value;

    private Event() {
    }

    public Event(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

