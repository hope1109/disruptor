package order;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderHandler extends EventOrderHandler<PushValue>{


    public OrderHandler(int eventType){
        this.eventType = eventType;
        this.start();
    }


    @Override
    public void process(List<PushValue> list) throws IOException {

        for (PushValue value : list) {
            System.out.println("消费者进行消费订单，类型：" + this.eventType + "，序号为：" + value.getNum());
        }

    }

    @Override
    public void onEvent(EventValue<PushValue> pushValueEventValue) throws Exception {
        if (eventType == pushValueEventValue.getEventType()) {
            queue.offer(pushValueEventValue.getPushValue());
            //System.out.println("消费者进行消费订单，类型：" + this.eventType + "，序号为：" + pushValueEventValue.getPushValue().getNum());
        }
    }
}
