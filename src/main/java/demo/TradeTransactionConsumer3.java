package demo;

import com.lmax.disruptor.EventHandler;

// Ïû·ÑÕß3
public class TradeTransactionConsumer3 implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {

    }
}
