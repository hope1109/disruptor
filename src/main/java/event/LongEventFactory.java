package event;

import com.lmax.disruptor.EventFactory;
import event.LongEvent;

// �¼�������
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
