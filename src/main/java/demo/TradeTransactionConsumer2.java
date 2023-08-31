package demo;

import com.lmax.disruptor.EventHandler;

// Ïû·ÑÕß2
public class TradeTransactionConsumer2 implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        //do something....
    }
}
