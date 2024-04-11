package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SingletonQueue {
    /**
     * ˽��ʵ��,��ʼ����ʱ�򲻼��أ��ӳټ���/�����أ�
     */
    private static SingletonQueue singleton;


    /**
     * ����
     */
    public static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10000);;

    /**
     * ˽�й���
     */
    private SingletonQueue(){}

}
