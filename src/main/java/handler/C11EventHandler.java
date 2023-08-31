package handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import event.LongEvent;


/**
 * C1-1��������
 * ��������ִ�н���ֵ+10�Ĳ��������Կ�����������ͬʱʵ����EventHandler��WorkHandler�����ӿڡ�
 * �������Ҫ�ػ���ֻ��Ҫʵ��EventHandler�༴�ɡ������Ҫ�ػ���ֻ��Ҫʵ��WorkHandler�༴�ɡ�
 * ����Ϊ���ܹ�ͬʱ����ػ��ͷǳػ���ʵ�֣����ͬʱʵ���������࣬��Ȼ��Ҳûɶ���⡣
 */
public class C11EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1 consumer finished.number=" + number);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println(System.currentTimeMillis()+": c1-1 consumer finished.number=" + number);
    }

}