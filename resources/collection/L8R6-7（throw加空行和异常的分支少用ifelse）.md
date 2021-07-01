### 6. 【推荐】当某个方法的代码总行数超过 10 行时，return / throw 等中断逻辑的右大括号后均需要加一个空行。
> 说明：这样做逻辑清晰，有利于代码阅读时重点关注。

### 7. 【推荐】表达异常的分支时，少用 if-else 方式。
下面这段代码，在条件为真的情况下我们执行相关的业务代码，否则抛出异常
```java
void Demo(...)
{
     if (true_to_continue){
        ... //业务代码
     }
     else{
        throw new SomeException();
     }
}
```
如果，我们将if内的条件进行反转之后，同样的功能，但是我们减少了else的使用：
```java
void Demo(...)
{
    if (!true_to_continue){
        throw new SomeException();
    }
     ... //业务代码
}
```

> 说明：如果非使用 if()...else if()...else...方式表达逻辑，避免后续代码维护困难，请勿超过 3 层。
正例：超过 3 层的 if-else 的逻辑判断代码可以使用卫语句、策略模式、状态模式等来实现。

##### 卫语句
卫语句(Guard clause)指的是在遇到异常情况时，提前进行抛出，这在一定程度上可以减少 if-else 分支结构的使用，可以在逻辑性和代码简洁性上得到提高

示例如下：
```java
public void findBoyfriend (Man man) {
   if (man.isUgly()) {
      System.out.println("本姑娘是外貌协会的资深会员");
      return;
   }
   if (man.isPoor()) {
      System.out.println("贫贱夫妻百事哀");
      return;
   }
   if (man.isBadTemper()) {
      System.out.println("银河有多远，你就给我滚多远");
      return; 
   }
    System.out.println("可以先交往一段时间看看");
}
```

- 策略模式参考：https://app.yinxiang.com/fx/38589c25-d008-41d1-b2a7-c77c00d153fe
- 状态模式参考：https://app.yinxiang.com/fx/a66e7b5b-9fad-4692-b178-54bbb6b48571