### 【强制】在 JDK7 版本及以上，Comparator 实现类要满足如下三个条件，不然 Arrays.sort，
Collections.sort 会抛 IllegalArgumentException 异常。
说明：三个条件如下
- x，y 的比较结果和 y，x 的比较结果相反。
- x>y，y>z，则 x>z。
- x=y，则 x，z 比较结果和 y，z 比较结果相同。

反例：下例中没有处理相等的情况，交换两个对象判断结果并不互反，不符合第一个条件，在实际使用中
可能会出现异常。
```java
new Comparator<Student>() {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId() > o2.getId() ? 1 : -1;
    }
};
```