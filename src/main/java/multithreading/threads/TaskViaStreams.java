package multithreading.threads;

import multithreading.resources.ResourceClass;
import multithreading.utils.ThreadTaskHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class TaskViaStreams {
    private static ResourceClass resourceClass = new ResourceClass();
    private static Map<String, Integer> commonResource = resourceClass.getCommonResource();

    public static void main(String[] args) throws InterruptedException {
        ThreadTaskHelper threadTaskHelper = new ThreadTaskHelper();
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        ThreadPrintResult threadPrintResult = new ThreadPrintResult(commonResource);
        List<ThreadIncrementingValue> listOfThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listOfThreads.add(new ThreadIncrementingValue(resourceClass));
        }

        listOfThreads.stream().parallel().forEach(executorService::submit);

        threadTaskHelper.endWorkWithThreads(executorService);

        while(true){
            if (executorService.isTerminated()){
                threadPrintResult.start();
                return;
            }
        }
    }
}
