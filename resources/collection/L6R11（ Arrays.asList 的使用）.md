### ***【强制】使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法
它的add/remove/clear 方法会抛出 UnsupportedOperationException 异常。
> 说明：asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。Arrays.asList 体现的是适配器模式，只是转换接口，后台的数据仍是数组。
```java
String[] str = new String[] { "chen", "yang", "hao" };
List list = Arrays.asList(str);
list.add("yangguanbao");    //运行时异常。
str[0] = "change";  //也会随之修改，反之亦然。
```