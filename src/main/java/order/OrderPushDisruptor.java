package order;

public class OrderPushDisruptor extends EventDisruptor<PushValue>{

    private static final OrderPushDisruptor instance = new OrderPushDisruptor();

    public static OrderPushDisruptor getInstance() {
        return instance;
    }

    public void loadDisruptor() {
        init(1024 * 1024, "orderPush");


        OrderHandler orderHandler1 = new OrderHandler(1);
        OrderHandler orderHandler2 = new OrderHandler(2);
        OrderHandler orderHandler3 = new OrderHandler(3);

        OrderHandler orderHandler4 = new OrderHandler(4);
        getDisruptor().handleEventsWith(orderHandler4).handleEventsWith(orderHandler3).handleEventsWith(orderHandler2).handleEventsWith(orderHandler1);

        //getDisruptor().handleEventsWithWorkerPool(orderHandler1,orderHandler2);

        this.start();

        Produce produce = new Produce();
        produce.start();

//        Produce1 produce1 = new Produce1();
//        produce1.start();
    }

}
