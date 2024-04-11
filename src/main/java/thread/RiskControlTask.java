package thread;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class RiskControlTask implements Callable<Long> {

    private final String className;



    public RiskControlTask(String className) {


        this.className = className;
    }

    @Override
    public Long call() throws Exception {
        Long result = 0L;
        try {
            // 你需要的class
            Class<?> clazz = Class.forName(className);
            // 创建对象
            Object instance = clazz.getConstructor().newInstance();

            // 调用方法
            for(Method method :clazz.getMethods()) {
                // 执行具体方法
                if("calculateQty".equals(method.getName())) {


                    RiskVO riskVO = new RiskVO();
                    riskVO.setAcctId("123456");
                    result = (Long) method.invoke(instance, riskVO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
