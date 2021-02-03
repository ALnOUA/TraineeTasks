package multithreading.utils;

import lombok.extern.java.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Log
public class ThreadTaskHelper {

    private void checkShutDownIsDone(ExecutorService executorService) throws InterruptedException {
        if(executorService.awaitTermination(5, TimeUnit.SECONDS)){
            log.info("======>All tasks are done<===============");
        }
        else{
            executorService.shutdownNow();
            log.info("==============>Forced interruption<================== ");
            if(executorService.isTerminated()){
                log.info("==============>Forced interruption has been done successfully<================== ");
            }
        }
    }

    public void endWorkWithThreads(ExecutorService executorService) throws InterruptedException {
        executorService.shutdown();
        checkShutDownIsDone(executorService);
    }
}
