package demo;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;


public class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {

    private final Random random = new Random();

    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        this.generateTradeTransaction(event);
    }

    private TradeTransaction generateTradeTransaction(TradeTransaction trade) {
        trade.setPrice(random.nextDouble() * 9999);
        return trade;
    }
}
