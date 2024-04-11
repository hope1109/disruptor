package thread;

import java.util.concurrent.Executor;

/**
 *  同步执行任务
 */
public class DirectExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
