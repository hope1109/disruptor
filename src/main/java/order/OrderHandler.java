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
            System.out.println("�����߽������Ѷ��������ͣ�" + this.eventType + "�����Ϊ��" + value.getNum());
        }

    }

    @Override
    public void onEvent(EventValue<PushValue> pushValueEventValue) throws Exception {
        if (eventType == pushValueEventValue.getEventType()) {
            queue.offer(pushValueEventValue.getPushValue());
            //System.out.println("�����߽������Ѷ��������ͣ�" + this.eventType + "�����Ϊ��" + pushValueEventValue.getPushValue().getNum());
        }
    }
}
