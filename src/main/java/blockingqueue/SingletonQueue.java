package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SingletonQueue {
    /**
     * 私有实例,初始化的时候不加载（延迟加载/懒加载）
     */
    private static SingletonQueue singleton;


    /**
     * 队列
     */
    public static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10000);;

    /**
     * 私有构造
     */
    private SingletonQueue(){}

}
