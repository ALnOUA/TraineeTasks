package multithreading.threads;

import java.util.concurrent.*;

public class ThreadsCreation {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("main thread has been started ");
        MyThreadExtendingThread thread1 = new MyThreadExtendingThread();
        Thread thread2 = new Thread(new MyThreadImplementingRunnable());
        Thread thread3 = new Thread(new MyThreadImplementingRunnableWithExecutor());
        Callable task = ()-> "my thrad implementing Callable is running";
        FutureTask<String> future = new FutureTask<>(task);
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(coreCount);

        scheduledExecutorService.schedule(new MyThreadImplementingRunnableWithScheduledExecutor(),10,TimeUnit.SECONDS);

        for(int i=0; i<20;i++){
            executorService.execute(thread3);
        }
        System.out.println("Thread name "+Thread.currentThread().getName());
        new Thread(future).start();
        thread1.start();
        thread2.start();
        System.out.println(future.get());


    }
}
class MyThreadExtendingThread extends  Thread{
    @Override
    public void run() {
        System.out.println("my thread extended of Thread is working");
    }
}
class MyThreadImplementingRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("my thread implemented Runnable is working");
    }
}

class MyThreadImplementingRunnableWithExecutor implements Runnable{

    @Override
    public void run() {
        System.out.println("Fixed executor: Thread name "+Thread.currentThread().getName());
    }
}

class MyThreadImplementingRunnableWithScheduledExecutor implements Runnable{

    @Override
    public void run() {
        System.out.println("Scheduled: Thread name "+Thread.currentThread().getName());
    }
}


