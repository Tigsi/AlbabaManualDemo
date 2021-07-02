### 5. 【强制】ArrayList 的 subList 结果不可强转成 ArrayList，否则会抛出 ClassCastException 异 常：java.util.RandomAccessSubList cannot be cast to java.util.ArrayList。
>说明：subList()返回的是 ArrayList 的内部类 SubList，并不是 ArrayList 本身，而是 ArrayList 的一个视 图，对于 SubList 的所有操作最终会反映到原列表上。


### 6. 【强制】使用 Map 的方法 keySet()/values()/entrySet()返回集合对象时，不可以对其进行添加元素操作，否则会抛出 UnsupportedOperationException 异常。
keySet() 返回的类型是 KeySet 源码如下：
```java
public Set<K> keySet() {
    Set<K> ks = keySet;
    if (ks == null) {
        ks = new KeySet();
        keySet = ks;
    }
    return ks;
}
```
KeySet类型定义：
```java
final class KeySet extends AbstractSet<K>

public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>

```
如上 KeySet 的父类是 AbstractCollection 的抽象类实现源码如下：
```java
// AbstractCollection 
public boolean add(E e) {
    throw new UnsupportedOperationException();
}
```
而 KeySet 没有重写 add 方法，所以抛出 UnsupportedOperationException 异常

解释：
> 在该 Set 上调用 remove 会从 Map 中删除匹配的 Entry，而在该 Set 上调用 add 或 addAll 是没有意义的，因为你不能在没有相应的值的情况下向 map 添加 key。

### 7. 【强制】Collections 类返回的对象，如：emptyList()/singletonList()等都是 immutable list，不可对其进行添加或者删除元素的操作。 
反例：如果查询无结果，返回 Collections.emptyList()空集合对象，调用方一旦进行了添加元素的操作，就会触发 UnsupportedOperationException 异常，原因和第 6 点一致。


### 8. 【强制】在 subList 场景中，高度注意对父集合元素的增加或删除，均会导致子列表的遍历、增加、删除产生 ConcurrentModificationException 异常。
Sublist 是 ArrayList 中的一个内部类，在 add 方法中会调用 checkForComodification 方法，如果父集合发生改变则抛出 ConcurrentModificationException 异常
```java
private void checkForComodification() {
    if (ArrayList.this.modCount != this.modCount)
        throw new ConcurrentModificationException();
}
```