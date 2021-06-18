package wss.alibaba.demo.collection;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author wangsisi
 * @email 1874174561@qq.com
 * @date 2021/6/15 11:20
 */

public class L5R3 {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond());
        System.out.println(now.toEpochMilli());

        Instant ins = Instant.ofEpochSecond(1623727238);
        ZonedDateTime zonedDateTime = ins.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        System.out.println(zonedDateTime);
        System.out.println(localDateTime);
    }
}
