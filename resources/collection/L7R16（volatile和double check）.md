### 16.【推荐】通过双重检查锁（double-checked locking）（在并发场景下）存在延迟初始化的优化
> 问题隐患（可参考 The "Double-Checked Locking is Broken" Declaration），推荐解决方案中较为简单一种（适用于 JDK5 及以上版本），将目标属性声明为 volatile 型，比如将 helper 的属性声明修改为`private volatile Helper helper = null;`。 

正例：
```java
public class LazyInitDemo {
    private volatile Helper helper = null;
    public Helper getHelper() {
        if (helper == null) {
            synchronized (this) {
                if (helper == null) { helper = new Helper(); }
            }
        }
        return helper;
    }
// other methods and fields...
}
```

解释，在一个经典例子：
```java
class Singleton {
    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
```
其实上面的写法是不正确的，应该给 instance 添加 volatile 修饰。那么为什么需要 volatile 呢？

其实问题出在 instance = new Singleton(); 这一行，这里是创建 Singleton 对象的地方，其实这里可以看成三个步骤：
```
memory = allocate(); //1: 分配对象的内存空间
ctorInstance(memory); //2: 初始化对象
instance = memory； //3: 设置 instance 指向刚分配的内存地址
```

上面的伪代码可能会被重排序。什么是重排序？编译器以及处理器有时候会为了执行的效率改变代码的执行顺序，这个被称为重排序。上面的三个步骤可能会被重排序为下面的步骤：
```
memory = allocate(); //1: 分配对象的内存空间
instance = memory； //2: 设置 instance 指向刚分配的内存地址 // 注意：此时对象还没有被初始化
ctorInstance(memory); //3: 初始化对象
```
volatile在这种情况下，当一个线程执行到 instance = memory; 的时候，对象还没有被初始化，另一个线程也调用了 getInstance 方法，发现 instance 引用不为 null，就会认为这个对象已经创建好了，从而使用了未初始化的对象。