package order;

public class EventValue <T>{
    private int eventType;
    private T pushValue;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public T getPushValue() {
        return pushValue;
    }

    public void setPushValue(T pushValue) {
        this.pushValue = pushValue;
    }
}
