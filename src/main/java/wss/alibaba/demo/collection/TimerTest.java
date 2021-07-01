package wss.alibaba.demo.collection;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/24 17:41
 */

public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
//        Integer a = 1;
//        Integer b = 2;
//        Integer c = null;
//        Boolean flag = false;
//        // a*b 的结果是 int 类型，那么 c 会强制拆箱成 int 类型，抛出 NPE 异常
//        Integer result=(flag? a*b : c);
        TimeUnit.DAYS.sleep(1);
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