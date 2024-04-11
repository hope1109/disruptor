package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService service = null;
        List<Long> resultQty = new ArrayList<>();
        try {
            // 创建线程池
            service = Executors.newFixedThreadPool(8);
            // 线程执行结果集合
            List<Future<Long>> resultList = new ArrayList<>();
            //
            List<String> mockClassName = getMockClassName();

            for (String name : mockClassName) {

                RiskControlTask task = new RiskControlTask(name);
                Future<Long> result = service.submit(task);
                // 保存线程执行的结果
                resultList.add(result);
            }
            // 获取结果
            for (Future<Long> longFuture : resultList) {
                resultQty.add(longFuture.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }

        System.out.println(Collections.min(resultQty));
    }




    private static List<String> getMockClassName() {

        return Arrays.asList("thread.RiskMock1","thread.RiskMock2","thread.RiskMock3");
    }


}

