import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import event.LongEvent;
import event.LongEventFactory;
import event.LongEventTranslator;
import handler.C11EventHandler;
import handler.C12EventHandler;
import handler.C21EventHandler;
import handler.C22EventHandler;

import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int bufferSize = 1024*1024;//���ζ��г��ȣ�������2��N�η�
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        /**
         * ����Disruptor�����ڵ������ߣ���������
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,bufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,new BlockingWaitStrategy());

        //�����ǵ��ø��ֲ�ͬ�����ĵط�.
        parallel(disruptor);

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        /**
         * ����10
         */
        ringBuffer.publishEvent(new LongEventTranslator(),10L);
        ringBuffer.publishEvent(new LongEventTranslator(),100L);
    }


    /**
     * ���м���ʵ��,c1,c2���಻����
     * <br/>
     * p --> c11
     *   --> c21
     */
    public static void parallel(Disruptor<LongEvent> disruptor){
        disruptor.handleEventsWith(new C11EventHandler(),new C21EventHandler());
        disruptor.start();
    }


    /**
     * ��������ִ��
     * <br/>
     * p --> c11 --> c21
     * @param disruptor
     */
    public static void serial(Disruptor<LongEvent> disruptor){
        disruptor.handleEventsWith(new C11EventHandler()).then(new C21EventHandler());
        disruptor.start();
    }


    /**
     * ���η�ʽִ��
     * <br/>
     *   --> c11
     * p          --> c21
     *   --> c12
     * @param disruptor
     */
    public static void diamond(Disruptor<LongEvent> disruptor){
        disruptor.handleEventsWith(new C11EventHandler(),new C12EventHandler()).then(new C21EventHandler());
        disruptor.start();
    }


    /**
     * ��ʽ���м���
     * <br/>
     *   --> c11 --> c12
     * p
     *   --> c21 --> c22
     * @param disruptor
     */
    public static void chain(Disruptor<LongEvent> disruptor){
        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler());
        disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
        disruptor.start();
    }

    /**
     * ���м���ʵ��,c1,c2���಻����,ͬʱC1��C2�ֱ���2��ʵ��
     * <br/>
     * p --> c11
     *   --> c21
     */
    public static void parallelWithPool(Disruptor<LongEvent> disruptor){
        disruptor.handleEventsWithWorkerPool(new C11EventHandler(),new C11EventHandler());
        disruptor.handleEventsWithWorkerPool(new C21EventHandler(),new C21EventHandler());
        disruptor.start();
    }

    /**
     * ��������ִ��,ͬʱC11��C21�ֱ���2��ʵ��
     * <br/>
     * p --> c11 --> c21
     * @param disruptor
     */
    public static void serialWithPool(Disruptor<LongEvent> disruptor){
        disruptor.handleEventsWithWorkerPool(new C11EventHandler(),new C11EventHandler()).then(new C21EventHandler(),new C21EventHandler());
        disruptor.start();
    }

}
