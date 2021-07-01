### 23.【推荐】循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。
> 说明：下例中，反编译出的字节码文件显示每次循环都会 new 出一个 StringBuilder 对象，然后进行 append
操作，最后通过 toString 方法返回 String 对象，造成内存资源浪费。

反例：
```java
String str = "start";
for (int i = 0; i < 100; i++) {
    str = str + "hello"; 
}

```
这段代码的字节码：
```java
 0 ldc #2 <start>
 2 astore_1
 3 iconst_0
 4 istore_2
 5 iload_2
 6 bipush 100
 8 if_icmpge 37 (+29)
11 new #3 <java/lang/StringBuilder>
14 dup
15 invokespecial #4 <java/lang/StringBuilder.<init>>
18 aload_1
19 invokevirtual #5 <java/lang/StringBuilder.append>
22 ldc #6 <hello>
24 invokevirtual #5 <java/lang/StringBuilder.append>
27 invokevirtual #7 <java/lang/StringBuilder.toString>
30 astore_1
31 iinc 2 by 1
34 goto 5 (-29)
37 return

```