### **【强制】不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使用 Iterator
方式，如果并发操作，需要对 Iterator 对象加锁。
正例：
```java
public class C5 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");  // 注释放开会怎样
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}
```
反例：
```java
for (String item : list) {
    if ("1".equals(item)) {
        list.remove(item);
    } 
}
```
> 说明：以上代码的执行结果肯定会出乎大家的意料，那么试一下把“1”换成“2”，会是同样的结果吗？


解释:

首先大家应该了解，对集合做remove,and等操作会触修改次数（modCount）的增加。如下方法：
```java

private void fastRemove(int index) {
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work
}

```
Iterator.hasNext() 方法
```java
public boolean hasNext() {
    return cursor != size;
}

public E next() {
    checkForComodification();
    int i = cursor;
    if (i >= size)
        throw new NoSuchElementException();
    Object[] elementData = ArrayList.this.elementData;
    if (i >= elementData.length)
        throw new ConcurrentModificationException();
    cursor = i + 1;
    return (E) elementData[lastRet = i];
}
```
- cursor：下一个元素的索引位置(调用Interator.next()是会触发cursor+1)
- size：集合长度
> 总结：由 next 会调用 checkForComodification 而 remove 方法会改变 modCount 的值，所以只要调用了 remove 之后在遍历 调用 next 方法，就会报错，但是如果如上例，当删除 "1" 之后 hasNext 方法就会判断为 false，所以没有调用 next 方法

为什么使用下面的方法就不会出现这种情况：
```java
while (iterator.hasNext()) {
    String item = iterator.next();
    if ("2".equals(item)) {
        iterator.remove();
    }
}
```
因为这里使用的是 iterator 的 remove 方法如下
```java
public void remove() {
    if (lastRet < 0)
        throw new IllegalStateException();
    checkForComodification();

    try {
        ArrayList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
        expectedModCount = modCount;
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}
```
 它会改变 cursor 的值。