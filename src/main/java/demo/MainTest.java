package demo;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainTest {

    public static void main(String[] args) throws Exception {
        long beginTime = System.currentTimeMillis();

        int bufferSize = 1024 * 64;

        ExecutorService executor = Executors.newFixedThreadPool(6);

        Disruptor<TradeTransaction> disruptor = new Disruptor<TradeTransaction>(new EventFactory<TradeTransaction>() {
            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());

        EventHandlerGroup<TradeTransaction> handlerGroup = disruptor.handleEventsWith(new TradeTransactionConsumer2(), new TradeTransactionConsumer1());

        TradeTransactionConsumer3 consumer3 = new TradeTransactionConsumer3();
        //������C1,C2����֮��ִ��JMS��Ϣ���Ͳ��� Ҳ���������ߵ�C3
        handlerGroup.then(consumer3);

        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);
        executor.submit(new TradeTransactionPublisher(latch, disruptor));
        latch.await();//�ȴ�����������.
        disruptor.shutdown();
        executor.shutdown();

        System.out.println("�ܺ�ʱ:" + (System.currentTimeMillis() - beginTime));
    }

}
