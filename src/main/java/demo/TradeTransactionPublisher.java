package demo;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;


// ������
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
        //ģ��һǧ��ν��׵ķ���
        int LOOP = 10000000;
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(tradeTransloator);
        }
        latch.countDown();
    }
}
