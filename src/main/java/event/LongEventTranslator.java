package event;

import com.lmax.disruptor.EventTranslatorOneArg;

// �¼�ת����
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {

    @Override
    public void translateTo(LongEvent event, long sequence, Long arg0) {
        event.setNumber(arg0);
    }
}
