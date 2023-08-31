package demo;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

// Ïû·ÑÕß1
public class TradeTransactionConsumer1 implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    public void onEvent(TradeTransaction event) {
        event.setId(UUID.randomUUID().toString());
        //System.out.println(event.getId());
    }

}
