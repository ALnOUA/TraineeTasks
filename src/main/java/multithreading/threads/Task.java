package multithreading.threads;



import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import multithreading.resources.ResourceClass;

import java.util.Map;
import java.util.concurrent.*;

@Log
public class Task {
    private static ResourceClass resourceClass = new ResourceClass();
    private static Map<String, Integer> commonResource = resourceClass.getCommonResource();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newScheduledThreadPool(10);
        ThreadIncrementingValue thread = new ThreadIncrementingValue(resourceClass);
        ThreadPrintResult threadPrintResult = new ThreadPrintResult(commonResource);
        for (int i = 0; i < 10; i++) {
            executorService.submit(thread);
        }
        executorService.shutdown(); //if something goes wrong with shutDown, it will be forced
        if(executorService.awaitTermination(5,TimeUnit.SECONDS)){
            log.info("======>All tasks are done<===============");
        }
        else{
            executorService.shutdownNow();
            log.info("==============>Forced interruption<================== ");
            if(executorService.isTerminated()){
                log.info("==============>Forced interruption has been done successfully<================== ");
            }
        }

        while(true){
            if (executorService.isTerminated()){
                threadPrintResult.start();
                return;
            }
        }
    }
}

@AllArgsConstructor
@Log
class ThreadPrintResult extends Thread {
    Map<String, Integer> commonResource;
    @Override
    public synchronized void run() {
        log.info("=========================================\n            LOG INFO STARTED");
        for (Map.Entry<String, Integer> entry : commonResource.entrySet()) {
            log.info("Thread name: "+entry.getKey() +" Data: " + entry.getValue());
        }
        log.info("=========================================\n            LOG INFO ENDED");
    }
}

@AllArgsConstructor
@Log
class ThreadIncrementingValue extends Thread {
    private ResourceClass resourceClass;
    private synchronized void incrementData(ResourceClass resourceClass){
        Map<String, Integer> commonResource = resourceClass.getCommonResource();
        long start,finish;
        start = System.nanoTime();
        resourceClass.incrementData();
        finish = System.nanoTime();
        commonResource.put(Thread.currentThread().getName(),resourceClass.getData());
        resourceClass.setCommonResource(commonResource);
        resourceClass.setWastedTime(finish-start);
    }

    @Override
    public synchronized void run() {
            log.info("\n-----------------------------"+"\nThread  "+Thread.currentThread().getName() +" is working\n Incrementing data...\n" + " Before incrementation : " + resourceClass.getData());
            incrementData(resourceClass);
            log.info(resourceClass.toString());
            log.info("After incrementing: " + resourceClass.getData() +"\n incrementing ended\n "+"Thread  "+Thread.currentThread().getName() +" end\n"+"-----------------------------");
    }
}
