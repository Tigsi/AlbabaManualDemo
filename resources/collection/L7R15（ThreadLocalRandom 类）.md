### 【推荐】避免 Random 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed 导致的性能下降。
> 说明：Random 实例包括 java.util.Random 的实例或者 Math.random()的方式。

正例：在 JDK7 之后，可以直接使用 API ThreadLocalRandom，而在 JDK7 之前，需要编码保证每个线
程持有一个单独的 Random 实例。

##### Random 
Random 生成新的随机数需要两步：
1. 根据老的 seed 生成新的 seed
2. 由新的 seed 计算出新的随机数

> 其中，第二步的算法是固定的，如果每个线程并发地获取同样的 seed，那么得到的随机数也是一样的。为了避免这种情况，Random 使用 CAS 操作保证每次只有一个线程可以获取并更新 seed，失败的线程则需要自旋重试。 因此，在多线程下用 Random 不太合适。
 
为了解决这个问题，出现了 ThreadLocalRandom，在多线程下，它为每个线程维护一个 seed 变量，这样就不用竞争了。

##### ThreadLocalRandom
ThreadLocalRandom 示例：
```java
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Player().start();
        }
    }

    private static class Player extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + ": " + ThreadLocalRandom.current().nextInt(100));
        }
    }
}
```

ThreadLocalRandom 的源码剖析：

current 方法：
```java
public static ThreadLocalRandom current() {
    // 如果线程第一次调用 current() 方法，执行 localInit()方法
    if (UNSAFE.getInt(Thread.currentThread(), PROBE) == 0)
        
        localInit();
    return instance;
}
```

localInit 方法
```java
static final void localInit() {
    int p = probeGenerator.addAndGet(PROBE_INCREMENT);
    int probe = (p == 0) ? 1 : p; // skip 0
    long seed = mix64(seeder.getAndAdd(SEEDER_INCREMENT));
    Thread t = Thread.currentThread();
    UNSAFE.putLong(t, SEED, seed);
    UNSAFE.putInt(t, PROBE, probe);
}
```
> 初始化方法 localInit() 中，为线程初始化了 seed，并保存在 UNSAFE 里。可以把这里的操作看作是初始化了 seed，把线程和 seed 以键值对的形式保存起来。

nextInt 方法
```java
public int nextInt(int bound) {
    if (bound <= 0)
        throw new IllegalArgumentException(BadBound);
    //第一处
    int r = mix32(nextSeed());
    int m = bound - 1;
    if ((bound & m) == 0) // power of two
        r &= m;
    else { // reject over-represented candidates
        for (int u = r >>> 1;
             u + m - (r = u % bound) < 0;
             u = mix32(nextSeed()) >>> 1)
            ;
    }
    return r;
}
```
nextSeed 方法
```java
    final long nextSeed() {
        Thread t; long r; // read and update per-thread seed
        UNSAFE.putLong(t = Thread.currentThread(), SEED,
                       // GAMMA 种子增量
                       r = UNSAFE.getLong(t, SEED) + GAMMA);
        return r;
    }
```