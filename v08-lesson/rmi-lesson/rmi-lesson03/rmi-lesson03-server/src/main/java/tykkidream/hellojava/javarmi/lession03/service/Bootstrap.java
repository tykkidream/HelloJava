package tykkidream.hellojava.javarmi.lession03.service;

import tykkidream.hellojava.javarmi.lession03.api.FileService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Tykkidream on 2015/11/1.
 *
 * 创建RMI注册表，启动RMI服务，并将远程对象注册到RMI注册表中。
 */
public class Bootstrap {
    public static void main(String[] args) {
        try {
            // 创建一个远程对象。
            FileService service = new FileServiceImpl();

            /*
             注册通讯端口。

             本地主机上的远程对象注册表Registry的实例，并指定端口为默认的1099，
             必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上。
              */
            LocateRegistry.createRegistry(1099);

            /*
             注册通讯路径。

             把远程对象注册到RMI注册服务器上，并命名为fileo
              */
            Naming.rebind("rmi://127.0.0.1:1099/file", service);

            /*
            绑定的URL标准格式为：rmi://host:port/name，其中协议名可以省略，下面的写法也是正确的。

             Naming.rebind("//127.0.0.1:1099/file", service);
             */

            System.out.println("启动完成");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
