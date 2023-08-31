package demo;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;


// 生产者
public class TradeTransactionPublisher implements Runnable {

    Disruptor<TradeTransaction> disruptor;
    private final CountDownLatch latch;

    public TradeTransactionPublisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeTransactionEventTranslator tradeTransloator = new TradeTransactionEventTranslator();
        //模拟一千万次交易的发生
        int LOOP = 10000000;
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(tradeTransloator);
        }
        latch.countDown();
    }
}
