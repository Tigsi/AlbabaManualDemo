### 【推荐】使用 CountDownLatch 进行异步转同步操作
注意：
- 线程执行代码注意要 catch 异常，确保 countDown 方法被执行到，避免主线程无法执行至 await 方法，直到超时才返回结果。
- 子线程抛出异常堆栈，不能在主线程 try - catch 到
> 说明：
> - CountDownLatch是在java1.5被引入的，它存在于java.util.concurrent包下。CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行。
> - CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
示例
```java
public class Test {

    private static final int THREAD_COUNT_NUM = 7;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT_NUM);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("第" + index + "颗龙珠已收集到！");
                    //模拟收集第i个龙珠,随机模拟不同的寻找时间
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每收集到一颗龙珠,需要等待的颗数减1
                countDownLatch.countDown();
            }).start();
        }
        //等待检查，即上述7个线程执行完毕之后，执行await后边的代码
        countDownLatch.await();
        System.out.println("集齐七颗龙珠！召唤神龙！");
    }
}

```

##### CountDownLatch和join的区别

首先，两者都能够实现阻塞线程等待完成后，再继续进行后续逻辑，对于两者相同的功能这里不再赘述。 我们直接说说它们之间的区别，考虑一个场景，我们的主线程阻塞到某处，等待其它线程完成某些操作之后再继续后续操作，具体就是主线程 M，等待子线程 T1 和 T2 完成某项操作，但是子线程 T1 和 T2 中的任务可能有很多事情要做，而其中只有中间某一步完成后，其实就可以唤醒主线程继续执行，而不需要等待子线程所有任务执行完毕再执行主线程。这种情况下，如果通过调用子线程的 join 方法来卡住主线程，那么主线程就必须等待子线程所有任务全部执行完毕，才能继续执行，这种情况下效率是极其低下甚至不可行的，此时就必须要用 CountDownLatch 来实现更加细粒度的任务控制，示例代码如下：

```java
public class Worker extends Thread {  
    private String name;  
    private long time;  
      
    private CountDownLatch countDownLatch;  
      
    public Worker(String name, long time, CountDownLatch countDownLatch) {  
        this.name = name;  
        this.time = time;  
        this.countDownLatch = countDownLatch;  
    }  
      
    @Override  
    public void run() {  
        try {  
            Thread.sleep(time);  
            System.out.println(name+"第一阶段工作完成");  
              
            countDownLatch.countDown();  
              
            Thread.sleep(2000); //这里就姑且假设第二阶段工作都是要2秒完成  
            System.out.println(name+"第二阶段工作完成");  
            System.out.println(name+"工作完成，耗费时间="+(time+2000));  
              
        } catch (InterruptedException e) {  
            // TODO 自动生成的 catch 块  
            e.printStackTrace();  
        }     
    }  
}
```
test.java
```java
public class Test {  
    public static void main(String[] args) throws InterruptedException {  
        CountDownLatch countDownLatch = new CountDownLatch(2);  
        Worker worker0 = new Worker("worker0", (long) (Math.random()*2000+3000), countDownLatch);  
        Worker worker1 = new Worker("worker1", (long) (Math.random()*2000+3000), countDownLatch);  
        Worker worker2 = new Worker("worker2", (long) (Math.random()*2000+3000), countDownLatch);  
          
        worker0.start();  
        worker1.start();      
        countDownLatch.await();  
          
        System.out.println("准备工作就绪");  
        worker2.start();  
    }  
}
```
以上代码 work0 和 work1 只需要执行完第一阶段的任务主线程 work2 就可以开始执行了，如果用 join 的话，就必须要等待 work0 和 work1 的所有任务执行完毕，work2 就需要无故等待两秒中再执行，所以显然此时用 CountDownLatch 是更加合适的。

