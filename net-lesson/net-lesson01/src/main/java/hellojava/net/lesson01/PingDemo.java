package hellojava.net.lesson01;

import java.io.IOException;
import java.net.InetAddress;

/**
 * 类似于 ping 命令
 *
 * 实际不一定准确。
 *
 * 参考：https://www.jianshu.com/p/908dd9db739b
 *  按资料上的说法,java 的 InetAddress 的 isReachable 方法在 root 用户下使用 ping 命令探测, 非 root用户下使用端口 7 探测。
 *  只有root权限或者拥有cap_net_raw权限才可以创建raw socket,所以我们赋予java程序创建raw socket的权限再次尝试。
 *  setcap cap_net_raw+ep /usr/java/jdk-13.0.1/bin/java
 */
public class PingDemo {

    public static void main(String[] args) throws IOException {
        InetAddress localHost = InetAddress.getByName("172.16.0.1");

        int timeOut =  3000 ;  //超时应该在3钞以上
        boolean reachable = localHost.isReachable(timeOut);
        // 当返回值是true时，说明host是可用的，false则不可。
        System.out.println(reachable);

    }
}
