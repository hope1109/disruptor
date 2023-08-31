package event;

import com.lmax.disruptor.EventFactory;
import event.LongEvent;

// 事件工厂类
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
