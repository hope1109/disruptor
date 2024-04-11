import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<Value> generator = generator();
        // 创建 Calendar 对象并设置为当前时间
        Calendar calendar = Calendar.getInstance();

        // 获取当前小时数
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay > 16) {
            System.out.println("当前时间已经超过16点");
        } else {
            System.out.println("当前时间未超过16点");
        }
        System.out.println(hourOfDay);

    }











































    private static List<Value> generator() {

        List<Value> values = new ArrayList<>();
        Value value = new Value();
        value.setAcctId("1");
        value.setExchId("0");
        value.setStkId("000001");
        value.setQty(1000);

        Value value1 = new Value();
        value1.setAcctId("2");
        value1.setExchId("0");
        value1.setStkId("000002");
        value1.setQty(1000);

        Value value2 = new Value();
        value2.setAcctId("3");
        value2.setExchId("1");
        value2.setStkId("600001");
        value2.setQty(1000);

        Value value3 = new Value();
        value3.setAcctId("4");
        value3.setExchId("1");
        value3.setStkId("600001");
        value3.setQty(1000);


        values.add(value);
        values.add(value1);
        values.add(value2);
        values.add(value3);

        return values;
    }
}
