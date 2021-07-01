### 1. 【强制】在一个 switch 块内，每个 case 要么通过 continue/break/return 等来终止，要么注释说明程序将继续执行到哪一个 case 为止；在一个 switch 块内，都必须包含一个 default语句并且放在最后，即使它什么代码也没有。
> 说明：注意 break 是退出 switch 语句块，而 return 是退出方法体。


### 2. 【强制】当 switch 括号内的变量类型为 String 并且此变量为外部参数时，必须先进行 null判断。
反例：如下的代码输出是什么？
```java
public class SwitchString {
    
    public static void main(String[] args) {
        method(null);
    }
    
    public static void method(String param) {
        switch (param) {
        // 肯定不是进入这里
            case "sth":
            System.out.println("it's sth");
            break;
            // 也不是进入这里
            case "null":
            System.out.println("it's null");
            break;
            // 也不是进入这里
            default:
            System.out.println("default");
        }
    } 
}
```
上述代码会抛出 `NullPointException`
