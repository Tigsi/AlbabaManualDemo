###【强制 *****】避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名来访问即可。
通过字节码查看细节：
```java
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        // 利用两种方法调用 test 字节码的区别。
        // test.test();    // 方法1
        Test.test();    // 方法2
    }

    public static void test() {
        System.out.println("test");
    }
}

```
方法 1 字节码如下：
```java
 0 new #2 <wss/alibaba/demo/collection/Test>
 3 dup
 4 invokespecial #3 <wss/alibaba/demo/collection/Test.<init>>
 7 astore_1
 8 aload_1
 9 pop
10 invokestatic #4 <wss/alibaba/demo/collection/Test.test>
13 return
```
方法 2 字节码如下：
```java
 0 new #2 <wss/alibaba/demo/collection/Test>
 3 dup
 4 invokespecial #3 <wss/alibaba/demo/collection/Test.<init>>
 7 astore_1
 8 invokestatic #4 <wss/alibaba/demo/collection/Test.test>
11 return
```
> 由上可知，利用实例对象去调用静态方法会多一步实例变量入栈和出栈的操作，所以此操作会增加额外的性能消耗，要避免。

###【强制 OO】所有的覆写方法，必须加@Override 注解。
> 说明：getObject()与 get0bject()的问题。一个是字母的 O，一个是数字的 0，加@Override 可以准确判断是否覆盖成功。另外，如果在抽象类中对方法签名进行修改，其实现类会马上编译报错。

###【强制 O】相同参数类型，相同业务含义，才可以使用 Java 的可变参数，避免使用 Object。
> 说明：可变参数必须放置在参数列表的最后。（建议开发者尽量不用可变参数编程）
正例：
```java
public List<User> listUsers(String type, Long... ids) {...}
```
###【强制 OO】外部正在调用或者二方库依赖的接口，不允许修改方法签名，避免对接口调用方产生。
> 影响：接口过时必须加@Deprecated 注解，并清晰地说明采用的新接口或者新服务是什么。

###【强制 OO】不能使用过时的类或方法。
> 说明：java.net.URLDecoder 中的方法 decode(String encodeStr) 这个方法已经过时，应该使用双参数 decode(String source, String encode)。接口提供方既然明确是过时接口，那么有义务同时提供新的接口；作为调用方来说，有义务去考证过时方法的新实现是什么。

####【强制 *****】Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用 equals。
- 正例："test".equals(object);
- 反例：object.equals("test");
> 说明：推荐使用 JDK7 引入的工具类 java.util.Objects#equals(Object a, Object b)